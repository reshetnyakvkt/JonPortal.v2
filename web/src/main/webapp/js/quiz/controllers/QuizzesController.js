/**
 * Created by Reshetnyak Viktor on 25.01.2016.
 */
quizApp.controller("quizzesController", ['$scope', '$http',  function ($scope, $http){
    $scope.sprints;
    $scope.quizzes;
    $scope.newQuizText;
    $scope.newQuizLimit;
    $scope.loaded = false;

    $scope.conf={
        timeout: 3500, //миллисекунд
        tryCount: 0
    };

    $http.get('j_quizzes', $scope.conf).success(function(data, status, headers, config) {
        $scope.quizzes = data[0];
        $scope.sprints = data[1];
        $scope.loaded = true;
    }).error(function(data, status, headers, config) {
        console.log("Ответ от сервера: " + status);
    });

    $scope.refreshNew = function(){
        $scope.newQuizText = '';
        $scope.newQuizLimit = 20;
    };

    $scope.delQuiz = function(del_item){
        if (del_item === undefined) return;
        var idx = $scope.quizzes.indexOf(del_item);
        if (idx !== undefined && idx >= 0){
            //$http.get('j_quiz_del', $scope.conf).
            $http({method:'GET', url:'j_quiz_del', params: {'id': del_item.id}}).
                success(function(data, status, headers, config) {
                    if (data[0]) {
                        $scope.quizzes.remove(idx);
                    }
            }).error(function(data, status, headers, config) {
                console.log("Ответ от сервера: " + status);
            });

        }
    };

    $scope.newQuiz = function(text, limit){
        $http({method: 'GET', url: 'j_quiz_new', params: {text: text, limit: limit}}).success(function(data, status, headers, config) {
            $scope.quizzes = data[0];
            $scope.sprints = data[1];
            $scope.refreshNew();
        }).error(function(data, status, headers, config) {
            console.log("Ответ от сервера: " + status);
        })
    };

    $scope.refreshNew();
}]);