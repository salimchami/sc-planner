"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
exports.__esModule = true;
var core_1 = require("@angular/core");
var app_constants_1 = require("../../app.constants");
var AccountService = /** @class */ (function () {
    function AccountService(http) {
        this.http = http;
    }
    AccountService.prototype.get = function () {
        return this.http.get(app_constants_1.SERVER_API_URL + 'api/account', { observe: 'response' });
    };
    AccountService.prototype.save = function (account) {
        return this.http.post(app_constants_1.SERVER_API_URL + 'api/account', account, { observe: 'response' });
    };
    AccountService = __decorate([
        core_1.Injectable()
    ], AccountService);
    return AccountService;
}());
exports.AccountService = AccountService;
//# sourceMappingURL=account.service.js.map