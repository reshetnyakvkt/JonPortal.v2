/**
 * Created by al1 on 06.06.15.
 */
requirejs.config({
    "paths": {
        //"jquery": "//code.jquery.com/jquery-1.11.3.min",
        "jquery": "https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min",
        "bootstrap" :  "//netdna.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min",
        //"datatables": "//cdn.datatables.net/1.10.7/js/jquery.dataTables.min",
        "datatables": "datatables/jquery.dataTables.min",
        //"DT-bootstrap": "//cdn.datatables.net/plug-ins/1.10.7/integration/bootstrap/3/dataTables.bootstrap"
        "DT-bootstrap": "datatables/dataTables.bootstrap.min"

    },

/*    packages: [{
        name: "codemirror",
        location: "js/cabinet/codemirror/codemirror.js",
        main: "lib/codemirror"
    }],*/

    shim : {
        "bootstrap" : { "deps" :['jquery'] },
        "datatables": { "deps": ['jquery'] },
        "DT-bootstrap": { "deps": ['datatables'] }
    }
});

require(['model/Task', 'controller/TasksController', 'codemirror/lib/codemirror', 'codemirror/mode/clike/clike' , 'star_destroyer'], function (Task, TasksController, CodeMirror, like, dest) {

    $.ajax({
        url: "tasks",
        dataType: 'json',
        success: function (data) {
            localStorage.tasks = JSON.stringify(data);
            //localStorage.tasks = data;
            TasksController.start();
        }
    });


        //localStorage.tasks = JSON.stringify(tasks);


});

/*require(["codemirror/lib/codemirror", "codemirror/mode/clike/clike"], function(CodeMirror) {

    var editor = CodeMirror.fromTextArea(document.getElementById("code"), {
        lineNumbers: true,
        //styleActiveLine: true,
        lineWrapping: true,
        theme: "vibrant-ink",
        mode: "text/x-java",
        matchBrackets: true

    });

});*/