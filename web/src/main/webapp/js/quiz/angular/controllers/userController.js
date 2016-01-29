/**
 * Created by Олег on 20.01.2016.
 */
quizApp.controller('userController', ['$scope', 'users', '$location', function($scope, users, $location) {
    $scope.currentUser = users.getCurrentUser();
    var passExp = /((?=.*\d)(?=.*[a-z]).{8,20})/;
    $scope.user = {
        id: '',
        role: '',
        login: '',
        password: '',
        email: ''
    };
    $scope.createUser = function() {
        if($scope.validate($scope.user.login, $scope.user.password)) {
            users.createUser($scope.user)
                .success(function(data) {
                    if (data >= 0) {
                        $scope.errorMsg = '';
                        $scope.user.id = data;
                        users.setCurrentUser($scope.user);
                        $location.path('/dashboard');
                    } else {
                        $scope.errorMsg = 'Such user already exists!'
                    }
                })
                .error(function(err) {
                    $scope.errorMsg = 'The requested resource is not available.';
                });
        }

    };

    $scope.checkUser = function() {
        if($scope.validate($scope.login, $scope.password)) {
            users.checkUser($scope.login, $scope.password)
                .success(function(data) {
                    if (data.login) {
                        users.setCurrentUser(data);
                        $location.path('/dashboard');

                    } else {
                        $scope.errorMsg = 'Incorrect login or password!'
                    }
                })
                .error(function(err) {
                    $scope.errorMsg = 'The requested resource is not available.';
                });
        }
    };


    $scope.validate = function(login, password) {
        if (login.length <= 2) {
            $scope.errorMsg = 'Login must contain more than 2 symbols!';
            return false;
        } else if (login === password){
            $scope.errorMsg = 'Login and password must be different!';
            return false;
        } else if (!passExp.test(password)) {
            $scope.errorMsg = 'Invalid password! (must contain at least 8 symbols (letters and digits)';
            return false;
        } else {
            return true;
        }

    };
    $scope.toRegisterPage = function() {
        $location.path('/registerUser');
    };

    var showUserStats = function() {
        users.getUserResult().success(function(data) {
            if (data.length > 0) {
                $scope.resultList = data;

            } else {
                $scope.errorMsg = 'You might have not passed any quizes'
            }
        })
            .error(function(err) {
                $scope.errorMsg = 'The requested resource is not available.';
            });

    };

    if ($location.path() === '/userData') {
        showUserStats();
    }

    $scope.toDashboard = function() {
        $location.path('/dashboard');
    }






}]);