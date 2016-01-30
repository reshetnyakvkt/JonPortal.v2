/**
 * Created by Reshetnyak Viktor on 25.01.2016.
 */
var quizApp = angular.module("quizApp", ['ngCookies']);


Array.prototype.remove = function(from, to) {
    var rest = this.slice((to || from) + 1 || this.length);
    this.length = from < 0 ? this.length + from : from;
    return this.push.apply(this, rest);
};