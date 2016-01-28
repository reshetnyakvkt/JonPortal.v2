/**
 * Created by al1 on 10.06.15.
 */
define([], function (TasksView) {
    function getCourseRate(groupId, callback) {
        $.ajax({
            url: "courseRate",
            dataType: 'json',
            data: { groupId: groupId },
            success: callback
        });
    }

    function getSprintRate(groupId, sprintId, callback) {
        $.ajax({
            url: "sprintRate",
            dataType: 'json',
            data: { groupId: groupId, sprintId: sprintId },
            success: callback
        });
    }

    function checkTask(taskId, type, code, callback) {
        $.ajax({
            url: "checkTask",
            //timeout : 120000,
            type: "POST",
            data: { taskId: taskId, type: type, code: code },
            error : function (a,b,c) {
                console.log(a+b+c);
                callback("-\nВремя ожидания ответа от сервера истекло, перегрузите страницу позже");
            },
            success: callback
        });
    }

    function getUserName(callback) {
        $.ajax({
            url: "userName",
            //dataType: 'json',
            success: callback
        });
    }

    return {
        getCourseRate:getCourseRate,
        getSprintRate:getSprintRate,
        checkTask:checkTask,
        getUserName:getUserName
    };
});