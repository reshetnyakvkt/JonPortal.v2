/**
 * Created by Олег on 16.01.2016.
 */
quizApp.factory('quizes', ['$http', function($http) {



    return {
        fetchQuizes: function() {
            console.log('SERVICE!!');
            return $http.get(address + '/getQuizes')
                .success(function(data) {
                    return data;
                })
                .error(function(err) {
                    return err;
                });
        },

        fetchQuizData: function(quizId) {
            return $http.get(address + '/getQuiz/' + quizId)
                .success(function(data) {
                    return data;
                })
                .error(function(err) {
                    return err;
                });
        },

        sendQuizData: function(userQuiz) {
            return $http.post(address + '/submitUserQuiz', userQuiz)
                .success(function(data) {
                    return data;
                })
                .error(function(err) {
                    return err;
                });
        }

    };

}]);