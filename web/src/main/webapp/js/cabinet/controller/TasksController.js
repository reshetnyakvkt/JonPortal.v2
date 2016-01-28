/**
 * Created by al1 on 06.06.15.
 */
define(['view/TasksView'], function TaskController(TasksView) {
    var controller = this;

    function start(){
        var tasks = localStorage.tasks;
        //TasksView.constructor(controller);
        TasksView.render(tasks);
    }

    return {
        start:start
    };

    function getCourseRate(calback) {
        $.ajax({
            url: "courseRate",
            dataType: 'json',
            success: calback
        });
    }

    function getSprintRate(calback) {
        $.ajax({
            url: "sprintRate",
            dataType: 'json',
            success: calback
        });
    }
});