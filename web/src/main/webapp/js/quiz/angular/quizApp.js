/**
 * Created by Олег on 16.01.2016.
 */
var quizApp = angular.module('QuizApp', ['ngRoute']);
var address = 'http://192.168.1.136:8084';

quizApp.config(function ($routeProvider) {
    $routeProvider
        .when('/', {
            controller: 'userController',
            templateUrl: '../../static/views/authentication.html'
        })
        .when('/registerUser', {
            controller: 'userController',
            templateUrl: '../../static/views/registerUser.html'
        })
        .when('/userData', {
            controller: 'userController',
            templateUrl: '../../static/views/userData.html'
        })
        .when('/dashboard', {
            controller: 'dashController',
            templateUrl: '../../static/views/dashboard.html'
        })
        .when('/goToQuiz/:quizId', {
            controller: 'quizController',
            templateUrl: '../../static/views/quizPage.html'
        })

        .when('/editQuiz/:quizId', {
            controller: 'quizController',
            templateUrl: '../../static/views/editQuiz.html'
        })
        .otherwise({
            redirectTo: '/'
        });
});