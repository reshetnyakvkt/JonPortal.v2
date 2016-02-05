/**
 * Created by Reshetnyak Viktor on 25.01.2016.
 */
//quizApp.controller("quizzesController", ['$scope', '$http',  function ($scope, $http){
quizApp.controller("quizzesController", function ($scope, $location, dataService){
    $scope.sprints;
    $scope.quizzes;
    $scope.newQuizText;
    $scope.newQuizLimit;

    var promiseObj = dataService.getData();
    promiseObj.then(function(value) {
        dataService.setQuizzes( $scope.quizzes = value[0]);
        $scope.sprints = value[1];
    });

    $scope.refreshNew = function(){
        $scope.newQuizText = '';
        $scope.newQuizLimit = 20;
    };

    $scope.delQuiz = function(del_item){
        if (del_item === undefined) return;
        var idx = $scope.quizzes.indexOf(del_item);
        if (idx !== undefined && idx >= 0){

            dataService.delQuiz(del_item.id)
                .then(function(value) {
                    if (value[0]) {
                        $scope.quizzes.remove(idx);
                        dataService.setQuizzes($scope.quizzes);
                    }
                });
        }
    };

    $scope.newQuiz = function(text, limit, quizForm){
        if(quizForm.$valid) {
            var promiseObj = dataService.newQuiz(text, limit);
            promiseObj.then(function(value) {
                dataService.setQuizzes( $scope.quizzes = value[0]);
                $scope.sprints = value[1];
                $scope.refreshNew();
            });
        }
    };

    $scope.editQuiz = function(quiz){
        dataService.setEditQuiz(quiz);
        $location.path("editQuiz");
    };

    $scope.refreshNew();
});