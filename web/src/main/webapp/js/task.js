$(document).ready(function () {

    var javaConsole;
    var buttonRun;
    var buttonStop;
    var textField;
    var barTabbed;

    // класс выполненного задания
    var TaskClass = function () {
        this.className="";
        this.classCode="";

        this.getClassName = function () {
            return this.className;
        };

        this.getClassCode = function () {
            return this.classCode;
        };

        this.setClassName = function(name) {
            this.className=name;
        };

        this.setClassCode = function(code) {
            this.classCode=code;
        };

    };

    // объект - запрос для выполненного задания
    var JSONTaskRequest = function() {
        this.taskName="";
        this.taskClasses = new Array();

        this.addTaskClass = function(task) {
            this.taskClasses.push(task);
        };

        this.setTaskName = function(name) {
            this.taskName=name;
        };
    };

    // инициируем объекты
    javaConsole = new AppConsole(document.getElementById("JavaConsole"));
    javaConsole.printLn("Console", 0);

    barTabbed = new BarTabbed(document.getElementById("BarTabbed"));

    textField = new TextField(document.getElementById("TextField"));

    var ajaxController = new AjaxController();
    ajaxController.setResponseListener(function (status, data) { // ответ на Ajax-запрос

        switch (status) {
            case 0:
            {// сервер ответил нормально

                var validationResult = JSON.parse(data);
                // structure of validationResult JsonObject:
                // -----------------------
                // boolean compilation_success
                // array compilation_errors: [{int line: string massage}]
                // array test_messages: [{boolean test_success, string message}]
                // -----------------------
                if (validationResult["compilation_success"] == true) { // компиляция прошла успешно
                    javaConsole.printLn("Compiled successfully", 0);
                    var tests = validationResult["test_messages"];
                    var isPassed = true;  // прошел ли валидацию
                    javaConsole.printLn("-----------TESTS------------", 0);
                    for (var i1 = 0; i1 < tests.length; i1++) {
                        if (tests[i1]["test_success"] == true) {  // тест прошел успешно
                            javaConsole.printLn(tests[i1]["message"], 0);
                        }
                        else { // тест неудачный
                            javaConsole.printLn(tests[i1]["message"], 1);
                            isPassed = false;
                        }
                    }
                    javaConsole.printLn("----------------------------", 0);
                    if (isPassed) javaConsole.printLn("Test passed. Your code is valid", 0);
                    else javaConsole.printLn("Test not passed. Your code is not valid!!!", 1);
                }
                else { // компиляция неудачная
                    javaConsole.printLn("Compilation failed!", 1);
                    var errors = validationResult["compilation_errors"];
                    for (var i = 0; i < errors.length; i++) {
                        javaConsole.printLn("line " + errors[i]["line"] + ": " + errors[i]["message"], 1);
                    }
                    javaConsole.printLn("Validation failed!!!", 1);
                }
                break;
            }
            case  1:
            {// сервер ответил с ошибкой

                javaConsole.printLn(">Server error", 1);
                break;
            }
            default :
            { // нет ответа

                javaConsole.printLn(">Request timeout", 1);
            }
        }
        buttonRun.setEnabled(true);

    });

    buttonRun = new Button(document.getElementById("buttonRun"));
    buttonRun.setMouseListener(function () {
        try {
            buttonRun.setEnabled(false);
            javaConsole.clear();
            javaConsole.printLn(">Compiling...", 0);
            var jSonTaskRequest = new JSONTaskRequest();
            var numOfClasses=barTabbed.getNumOfTabs();
            for (var i=0;i<numOfClasses;i++) {
                var taskClass=new TaskClass();
                taskClass.setClassName(barTabbed.getTabHeader(i));
                taskClass.setClassCode(barTabbed.getTabContent(i));
                jSonTaskRequest.addTaskClass(taskClass);
            }
            jSonTaskRequest.setTaskName(document.getElementById("taskName").getAttribute("value"));
            var data=JSON.stringify(jSonTaskRequest);
            ajaxController.sendRequest("POST", "task/validate", data);
        } catch (err) {
            javaConsole.printLn("Java Script error: "+err, 2);
            buttonRun.setEnabled(true);
        }

    });

    buttonStop = new Button(document.getElementById("buttonStop"));
    buttonStop.setMouseListener(function () {
        ajaxController.abort();
        buttonRun.setEnabled(true);
    });
});





