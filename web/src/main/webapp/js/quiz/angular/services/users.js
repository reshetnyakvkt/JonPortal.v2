/**
 * Created by Олег on 20.01.2016.
 */
quizApp.factory('users', ['$http', function($http) {
    var currentUser;

    return {
        createUser: function(user) {
            return $http.post(address + '/createUser', user)
                .success(function(data) {
                    return data;
                })
                .error(function(err) {
                    return err;
                });
        },


        checkUser: function(login, password) {
            return $http.get(address + '/getUser?login=' +  login +'&password='+ password)
                .success(function(data) {
                    return data;
                })
                .error(function(err) {
                    return err;
                });
        },

        getUserResult: function() {

            return $http.get(address + '/getUserResults?userId=' + currentUser.id)
                .success(function(data) {
                    console.log(data);
                    return data;
                })
                .error(function(err) {
                    return err;
                });
        },

        getCurrentUser: function() {
            return currentUser;
        },

        setCurrentUser: function(user) {
            currentUser = user;
        }
    };

}]);