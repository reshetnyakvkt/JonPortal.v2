/**
 * Created by al1 on 06.06.15.
 */
define(['jquery', "datatables", "DT-bootstrap", "bootstrap", "codemirror/lib/codemirror", "controller/Communication"], function (JQuery, Table, tableboot, bootstrap, CodeMirror, Communication) {
    var selectedGroup;
    var selectedSprint;
    var selectedTask;
    var codeEditor;
    var table;
    var groups;

    var constructor = function () {
        var textArea = document.getElementById("code");
        codeEditor = CodeMirror.fromTextArea(textArea, {
            viewportMargin: Infinity,
            lineNumbers: true,
            lineWrapping: true,
            theme: "vibrant-ink",
            mode: "text/x-java",
            matchBrackets: true
        });
    };
    constructor();

    function renderTasks(tasks) {
        table.fnClearTable();
        table.fnAddData(tasks);
        table.fnDraw();

        $('#table_id tbody tr:first-child').addClass('selected');
        renderTask(tasks[0]);
        addPlayHandlers(table);
    }

    function renderTasksFromSprint(sprint) {
        renderTasks(sprint.tasks);
    }

    function renderGroups(groups, group) {
        Communication.getCourseRate(selectedGroup.id, function (data) {
            $('#courseRate').html(data);
        });
        $('#group').html(group.name + '<span class="caret">');
        if (groups.length > 0) {
            $('#groups').empty();
        }
        for (var i = 0; i < groups.length; i++) {
            var group = groups[i];
            $('#groups').append($('<li><a name="' + group.id + '" href="#">' + group.name + '</a></li>'));

            if (i != groups.length - 1) {
                $('#groups').append($('<li class="divider"></li>'));
            }
        }
    }

    function renderSprints(group, selectSprint) {
        if (group == undefined) {
            return;
        }
        Communication.getSprintRate(group.id, selectSprint.id, function (data) {
            $('#sprintRate').html(data);
        });

        Communication.getUserName(function (data) {
            $('#student').html(data);
        });

        var sprints = group.sprints;
        if (sprints.length > 0) {
            $('#sprints').empty();
        }
        $('#sprint').html(selectSprint.name + '<span class="caret">');
        for (var i = 0; i < sprints.length; i++) {
            var sprint = sprints[i];
            $('#sprints').append($('<li><a name="' + sprint.id + '" href="#">' + sprint.name + '</a></li>'));
            if (i != sprints.length - 1 && sprints.length < 6) {
                $('#sprints').append($('<li class="divider"></li>'));
            }
        }

        $('#sprints li a').on('click', function () {
            $('#sprint').html(this.innerHTML + '<span class="caret">');
            var sprint = getSprintById(selectedGroup, this.name);
            if (sprint != undefined) {
                selectedSprint = sprint;
                Communication.getSprintRate(selectedGroup.id, sprint.id, function (data) {
                    $('#sprintRate').html(data);
                });

                renderTasksFromSprint(sprint);
            }
        });

        renderTasksFromSprint(selectSprint)
    }

    function format() {
        var totalLines = codeEditor.lineCount();
        var totalChars = codeEditor.getTextArea().value.length;
        codeEditor.autoFormatRange({line: 0, ch: 0}, {line: totalLines, ch: totalChars});
    }

    function renderCode(task) {
        codeEditor.setValue(task.code);
    }

    function renderTask(task) {
        if (task == undefined) {
            return;
        }
        $('#taskText').html(task.text.replace(/\n/g, '<br/>'));
        $('#result').html(task.result.replace(/\n/g, '<br/>'));
        renderCode(task);
    }

    function render(parameters) {
        groups = JSON.parse(parameters);
        selectedGroup = groups[0];

        renderGroups(groups, selectedGroup);
        selectedSprint = selectedGroup.sprints[0];

        table = buildTable(selectedSprint.tasks);
        renderSprints(selectedGroup, selectedSprint);

        $('[data-toggle="tooltip"]').tooltip();

        //$('#codeAccordion').collapse('show').height('100%');

        addHandlers(table);
    }

    return {
        render: render
    };

    function buildTable(tasks) {
        if (tasks == undefined) {
            return;
        }
        table = $('#table_id').dataTable({
            "paging": false,
            "ordering": false,
            "info": false,
            "searching": false,

            "columns": [
                {"data": "name"},
                {"data": "result"},
                {"data": "action"}
            ],

            "columnDefs": [{
                "targets": -1,
                "data": null,
                "defaultContent": "<button  data-toggle='tooltip' data-placement='bottom' title='Проверить задание' class='btn btn-default play'><span class='glyphicon glyphicon-play'></span></button>"
            },
                {
                    width: '80%',
                    targets: 0
                },
                {
                    width: '10%',
                    targets: 1,
                    render: function (data, type, full, meta) {
                        return data.substring(0, data.indexOf('\n'));
                    }
                }
            ],
            "aaData": tasks
        });
        return table;
    }

    function getGroupById(groupId) {
        for (var i = 0; i < groups.length; i++) {
            if (groups[i].id == groupId) {
                return groups[i];
            }
        }
    }

    function getSprintById(selectedGroup, sprintId) {
        var sprints = selectedGroup.sprints;
        for (var i = 0; i < sprints.length; i++) {
            if (sprints[i].id == sprintId) {
                return sprints[i];
            }
        }
    }

    function addPlayHandlers(table) {
        $('.play').on('click', function (event) {
            var tr = this.parentNode.parentNode;
            var task = table.fnGetData(tr);
            var button = $(this);
            //button.disabled = true;
            button.attr("disabled", "disabled");

            var playIcon = $(button.children(0));
            playIcon.removeClass("glyphicon-play");
            playIcon.addClass("glyphicon-refresh glyphicon-refresh-animate");
            //task.code = codeEditor.getValue();

            if (event.stopPropagation) {
                event.stopPropagation();
            }
            Communication.checkTask(task.id, task.type, task.code, function (data) {
                if (data) {
                    task.result = data;
                    table.fnUpdate(data, tr, 1);
                    table.fnDraw();
                    $('#result').html(data.replace(/\n/g, '<br/>'));
                    task.result = data;
                }

                button.removeAttr("disabled");
                playIcon.removeClass("glyphicon-refresh glyphicon-refresh-animate");
                playIcon.addClass("glyphicon-play");

            });
        });
    }

    function saveCodeToSelected(table) {
        var tr = $('#table_id .selected');
        var task = table.fnGetData(tr);
        if (task == null) {
            alert('Решение не запомнено, выбирите задание и сохраните решение');
        } else {
            task.code = codeEditor.getValue();
        }
    }

    function addHandlers(table) {
        $('#table_id tbody').on('click', 'tr', function () {
            if ($(this).hasClass('selected')) {
                $(this).removeClass('selected');
            } else {
                table.$('tr.selected').removeClass('selected');
                $(this).addClass('selected');
            }
            renderTask(table.fnGetData(this));
        });

        $('#groups li a').on('click', function () {
            $('#group').html(this.innerHTML + '<span class="caret">');
            var group = getGroupById(this.name);
            if (group != undefined) {
                selectedGroup = group;
                renderSprints(group, group.sprints[0]);
                addPlayHandlers(table);
            }
        });

        $('#result').on('click', function () {
            $('#modalRes').html($(this).html());
            $('#modal').modal('toggle');
        });

        $('div.CodeMirror').on('mouseleave', function () {
            saveCodeToSelected(table);
        });

        $('#save').on('click', function () {
            saveCodeToSelected(table);
        });
    }
});