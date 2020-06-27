package io.edukativ.myskoolin.infrastructure.timetabling;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = {
        TimeMapper.class
})
public interface TimeSlotMapper {

    TimeSlotVO dbDtoToDto(TimeSlotDbVO timeSlot);

    TimeSlotDbVO dtoToDbDto(TimeSlotVO timeSlot);

}