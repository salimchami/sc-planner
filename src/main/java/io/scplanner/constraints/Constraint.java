package io.scplanner.constraints;

import io.scplanner.annotations.Facts;
import io.scplanner.annotations.PlanningVariableFact;
import io.scplanner.exceptions.SolutionConfigurationException;
import io.scplanner.reflection.Reflection;
import io.scplanner.score.ScoreLevel;

import java.util.*;

import static java.util.stream.Collectors.toList;

public class Constraint<S, F, P> {

    private final S solution;
    private final Class<F> factClass;
    private final String constraintName;
    private final ScoreLevel scoreLevel;
    private final PenaltyFunction<F, P> penaltyFunction;
    private final FavorableScoreFunction<P> favorableScoreFunction;
    private final ConstraintFilter<F, Set<P>> filter;

    public Constraint(String constraintName,
                      ScoreLevel scoreLevel,
                      S solution,
                      Class<F> factClass,
                      ConstraintFilter<F, Set<P>> filter,
                      PenaltyFunction<F, P> penaltyFunction,
                      FavorableScoreFunction<P> favorableScoreFunction) {
        this.constraintName = constraintName;
        this.scoreLevel = scoreLevel;
        this.solution = solution;
        this.factClass = factClass;
        this.filter = filter;
        this.penaltyFunction = penaltyFunction;
        this.favorableScoreFunction = favorableScoreFunction;
    }

    public Class<F> getFactClass() {
        return factClass;
    }

    public int calculateScore(Set<P> planningVariables) throws SolutionConfigurationException {
        Map<F, Set<P>> planningVariablesByFacts = planningVariablesByFacts(planningVariables);
        final List<F> facts = factClassInstanceFromSolution(solution);
//        if (planningVariablesByFacts.isEmpty()) {
//            return facts
//                    .stream()
//                    .map(fact -> -penaltyFunction.apply(fact, planningVariables))
//                    .reduce(0, Integer::sum);
//        }
        int penalty = penalty(facts, planningVariablesByFacts);
        if (penalty > 0) {
            return -penalty;
        } else {
            return favorableScoreFunction.apply(planningVariables);
        }
    }

    private int penalty(List<F> refFacts, Map<F, Set<P>> planningVariablesByFacts) {
        return refFacts.stream()
                .mapToInt(fact -> {
                    Set<P> planningVariables = new HashSet<>();
                    final Set<P> pv = planningVariablesByFacts.get(fact);
                    if(pv != null) {
                        planningVariables = pv;
                    }
                    return penaltyFunction.apply(fact, planningVariables);
                })
                .sum();
    }

    private List<F> factClassInstanceFromSolution(S solution) throws SolutionConfigurationException {
        final List<F> facts = (List) Reflection.valueByAnnotation(solution, Facts.class);
        return facts.stream().filter(fact -> fact.getClass().getName().equals(factClass.getName())).collect(toList());
    }

    private Map<F, Set<P>> planningVariablesByFacts(Set<P> planningVariables) throws SolutionConfigurationException {
        Map<F, Set<P>> planningVariablesByFacts = new HashMap<>();
        for (P planningVariable : planningVariables) {
            final F fact = (F) Reflection.valueByAnnotation(planningVariable, PlanningVariableFact.class);
            if (fact != null) {
                if (planningVariablesByFacts.containsKey(fact)) {
                    planningVariablesByFacts.get(fact).add(planningVariable);
                } else {
                    planningVariablesByFacts.put(fact, new HashSet<>(Collections.singletonList(planningVariable)));
                }
            }
        }
        return planningVariablesByFacts;
    }

}
