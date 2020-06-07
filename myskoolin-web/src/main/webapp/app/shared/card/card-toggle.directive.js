"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
exports.__esModule = true;
var core_1 = require("@angular/core");
var CardToggleDirective = /** @class */ (function () {
    function CardToggleDirective(el) {
        this.el = el;
    }
    CardToggleDirective.prototype.onToggle = function ($event) {
        $event.preventDefault();
        this.el.nativeElement.classList.toggle('fa-minus');
        this.el.nativeElement.classList.toggle('fa-plus');
    };
    __decorate([
        core_1.HostListener('click', ['$event'])
    ], CardToggleDirective.prototype, "onToggle");
    CardToggleDirective = __decorate([
        core_1.Directive({
            selector: '[jhiCardToggleEvent]'
        })
    ], CardToggleDirective);
    return CardToggleDirective;
}());
exports.CardToggleDirective = CardToggleDirective;
//# sourceMappingURL=card-toggle.directive.js.map