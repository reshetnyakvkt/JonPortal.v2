/**
 * Created by Олег on 16.01.2016.
 */
quizApp.controller('dashController', ['$scope', 'quizes', 'users', '$location', function($scope, quizes, users, $location) {
        $scope.currentUser = users.getCurrentUser();
        if($scope.currentUser.role === 'ADMIN') {
            $scope.isAdmin = true;
        }


    quizes.fetchQuizes().success(function(data) {
        console.log('CONTROLLER!!');
        $scope.quizList = data;
    })
        .error(function(err) {
            $scope.readError = err;
        });

    $scope.goToQuiz = function(id) {
        $location.path('/goToQuiz/' + id);

    };
    $scope.toProfilePage = function() {
        $location.path('/userData');
    }


}]);