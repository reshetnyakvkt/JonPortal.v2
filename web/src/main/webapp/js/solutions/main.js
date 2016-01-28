/**
 * Created by al1 on 06.06.15.
 */
requirejs.config({
    "paths": {
        //"jquery": "//code.jquery.com/jquery-1.11.3.min",
        "jquery": "https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min",
        "bootstrap" :  "//netdna.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min"
        //"datatables": "//cdn.datatables.net/1.10.7/js/jquery.dataTables.min",
        //"datatables": "datatables/jquery.dataTables.min",
        //"DT-bootstrap": "//cdn.datatables.net/plug-ins/1.10.7/integration/bootstrap/3/dataTables.bootstrap"
        //"DT-bootstrap": "datatables/dataTables.bootstrap.min"

    },

    shim : {
        "bootstrap" : { "deps" :['jquery'] }
        //"datatables": { "deps": ['jquery'] },
        //"DT-bootstrap": { "deps": ['datatables'] }
    }
});

require([], function () {
    $('[data-toggle="tooltip"]').tooltip();

});