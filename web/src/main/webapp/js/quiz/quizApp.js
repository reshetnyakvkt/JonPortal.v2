/**
 * Created by Reshetnyak Viktor on 25.01.2016.
 */
var quizzes;
var quizApp = angular.module("quizApp", ['ngRoute'])
    .config(function($routeProvider){

    $routeProvider.when('/editQuizzes', {
            templateUrl: 'editQuizzes.html',
            controller: 'quizzesController'
        });

    $routeProvider.when("/editQuiz", {
        templateUrl: "editQuestions.html",
        controller: 'questionsEditController'
    });
    $routeProvider.otherwise({redirectTo: '/editQuizzes'});

});

Array.prototype.remove = function(from, to) {
    var rest = this.slice((to || from) + 1 || this.length);
    this.length = from < 0 ? this.length + from : from;
    return this.push.apply(this, rest);
};