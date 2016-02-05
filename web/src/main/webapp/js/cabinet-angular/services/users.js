/**
 * Created by Юыху on 28.01.2016.
 */
cabinetApp.factory('users', ['$http', function($http) {

    return {
        userName: function() {
            return $http.get('/cabinet/userName', {transformResponse: undefined})
                .then(function(response) {
                    console.log(response.data);
                    return response.data;
                },function(err) {
                    return err;
                })
        }




    };

}]);