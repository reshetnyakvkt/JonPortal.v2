/**
 * Created by Reshetnyak Viktor on 28.01.2016.
 */
quizApp.factory('dataService', function($http, $q){
    var addressQuiz = '';
    var _quizzes = null;
    var _editQuiz = null;

    return{
        setQuizzes: function (quizzes) {
            _quizzes = quizzes;
        },
        getQuizzes: function (quizzes) {
            _quizzes = quizzes;
        },

        setEditQuiz: function(quiz){
            _editQuiz = quiz;
        },
        getEditQuiz: function(){
            return _editQuiz;
        },
        editQuiz: function(quiz){
            this.setEditQuiz(quiz);
            var deferred = $q.defer();
            $http({method: 'GET', url: '#/editQuiz/'}).
                success(function(data, status, headers, config) {
                    deferred.resolve(data);
                }).
                error(function(data, status, headers, config) {
                    deferred.reject(status);
                });

        },
        getData: function(){
            var deferred = $q.defer();
            $http({method: 'GET', url: 'j_quizzes'}).
                success(function(data, status, headers, config) {
                    deferred.resolve(data);
                }).
                error(function(data, status, headers, config) {
                    deferred.reject(status);
                });

            return deferred.promise;
        },
        getQuizzes: function(){
            var deferred = $q.defer();
            $http({method: 'GET', url: addressQuiz + 'j_quizzes'}).
                success(function(data, status, headers, config) {
                    deferred.resolve(data);
                }).
                error(function(data, status, headers, config) {
                    deferred.reject(status);
                });

            return deferred.promise;
        },
        newQuiz: function(text, limit){
            var deferred = $q.defer();
            $http({method: 'GET', url: addressQuiz + 'j_quiz_new', params: {'text': text, 'limit': limit}}).
                success(function(data, status, headers, config) {
                    deferred.resolve(data);
                }).
                error(function(data, status, headers, config) {
                    deferred.reject(status);
                });

            return deferred.promise;
        },
        saveQuiz: function(quiz){
            var deferred = $q.defer();
            var quizJson = JSON.stringify(quiz);
            console.log(quizJson);
            $http({method: 'POST', url: addressQuiz + 'j_quiz_save', params: {'quizJson': quizJson}}).
                success(function(data, status, headers, config) {
                    deferred.resolve(data);
                }).
                error(function(data, status, headers, config) {
                    deferred.reject(status);
                });

            return deferred.promise;
        },
        delQuiz: function(id){
            var deferred = $q.defer();
            $http({method: 'GET', url: addressQuiz + 'j_quiz_del',  params: {'id': id}}).
                success(function(data, status, headers, config) {
                    deferred.resolve(data);
                }).
                error(function(data, status, headers, config) {
                    deferred.reject(status);
                });

            return deferred.promise;
        }
    }
});