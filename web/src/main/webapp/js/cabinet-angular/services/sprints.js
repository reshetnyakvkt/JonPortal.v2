/**
 * Created by ���� on 28.01.2016.
 */
cabinetApp.factory('sprints', ['$http', function($http) {

    return {
        tasks: function() {
            return $http.get('/cabinet/tasks')
                .then(function(response) {
                    return response.data;
                }, function(err) {
                    return err;
                })
        },

        courseRate: function(groupId) {
            return $http.get('/cabinet/courseRate?groupId=' + groupId, {transformResponse: undefined})
                .then(function(response) {
                    return response.data;
                }, function(err) {
                    return err;
                })
        },

        sprintRate: function(groupId, sprintId) {
            return $http.get('/cabinet/courseRate?groupId=' + groupId + '&sprintId=' + sprintId, {transformResponse: undefined})
                .then(function(response) {
                    return response.data;
                }, function(err) {
                    return err;
                })
        },
        checkTask: function(taskId, type, code) {
            var data = {
                taskId: taskId,
                type: type,
                code: code
            };
            return $http({
                method: 'POST',
                url:'/cabinet/checkTask',
                params: data,
                transformResponse: undefined

            }).then(function(response) {
                return response.data;
            }, function(err) {
                return "-\nВремя ожидания ответа от сервера истекло, перегрузите страницу позже";
            });



            /*$http.post('/cabinet/checkTask', data).then(function(response) {
                return response.data;
            }, function(err) {
                return "-\nВремя ожидания ответа от сервера истекло, перегрузите страницу позже";
            });*/

        }


    };

}]);