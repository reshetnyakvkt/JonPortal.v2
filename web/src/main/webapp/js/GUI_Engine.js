function AppConsole(element) {
    var MSG_INFO = 0;
    var MSG_WARN = 1;

    var switcher = false;
    var timerId;
    var innerText = "";
    var context;

    var animateCursor = function () {
        switcher = !switcher;
        if (switcher) {
            element.innerHTML = innerText + "_";
        } else {
            element.innerHTML = innerText;
        }

    };

    timerId = setInterval(animateCursor, 1000);

    this.printLn = function (string, msgType) {
        if (innerText.length != 0) innerText += "<br/>";
        var span = document.createElement("span");
        if (msgType == MSG_INFO) string = "<span style='color: green;'>" + string + "</span>";
        else string = "<span style='color: red;'>" + string + "</span>";
        span.innerHTML = string;
        innerText += string;
    };

    this.clear = function () {
        innerText = "";
    };

}


function TextField(element) {

    this.getText = function () {
        element.innerHTML = element.innerHTML.replace(/(&nbsp;)|(&ensp;)|(&emsp;)/g, ' ');
        var str;
        if (element.innerText) {
            str = element.innerText;
        } else {
            str = element.textContent;
        }
        return str;
    };


}

function getPlainText(element) {

        element.innerHTML = element.innerHTML.replace(/(&nbsp;)|(&ensp;)|(&emsp;)/g, ' ');
        var str;
        if (element.innerText) {
            str = element.innerText;
        } else {
            str = element.textContent;
        }
        return str;

}

function Button(element) {

    var mouseListener;
    var isEnabled = true;

    element.onmousedown = function () {
        if (isEnabled) {
            element.style.left = "1px";
            element.style.top = "1px";
            return mouseListener();
        }
    };

    element.onmouseout = element.onmouseup = function () {
        element.style.left = "0";
        element.style.top = "0";
    };

    this.setMouseListener = function (listenerFunction) {
        mouseListener = listenerFunction;
    };

    this.setEnabled = function (bool) {
        isEnabled = bool;
    }


}


// создать окно с табами
function BarTabbed(elem) {


    this.getTabHeader = function(index) {
        return getPlainText(document.getElementById("barTab-"+index));
    };

    this.getTabContent = function(index) {
        return getPlainText(document.getElementById("barPanel-"+index));
    };

    this.getNumOfTabs = function() {
        return tabs.length;
    };

    // основное окно
    var mainWindow = document.createElement("div");
    // верхняя панель
    var barTabs = document.createElement("div");
    barTabs.className="barTabs";
    // вкладки для верхней панели
    var tabs = new Array;
    // тело окна
    var barPanels = document.createElement("div");
    barPanels.style.padding="15px 5px";
    // вложенные окна для тела окна
    var panels = new Array;

    // получаем ссылки на все имеющиеся окна
    var listItems = elem.getElementsByTagName("li");

    // делаем вложенне окна для тела окна и вкладки для верхней панели
    for (var i = 0; i < listItems.length; i++) {
        // табы
        tabs[i] = document.createElement("div");
        tabs[i].innerHTML = listItems[i].title;
        tabs[i].id = "barTab-" + i;
        tabs[i].className = "barTabNoActive";
        // окна
        panels[i] = document.createElement("div");
        panels[i].innerHTML = listItems[i].innerHTML;
        panels[i].id = "barPanel-" + i;
        panels[i].style.display = "none";
        // переключение окон при клике на табы
        tabs[i].onclick = (function (val) {
            return function () {
                // скрываем все табы и открываем только выбранный
                for (var j = 0; j < tabs.length; j++) {
                    if (j==val) {
                        panels[j].style.display = "block";
                        tabs[j].className = "barTabActive";
                    }
                    else {
                        panels[j].style.display = "none";
                        tabs[j].className = "barTabNoActive";
                    }
                }
            }
        })(i);
        barTabs.appendChild(tabs[i]);
        barPanels.appendChild(panels[i]);
    }

    // добавим верхнюю панель окну
    mainWindow.appendChild(barTabs);
    // активное вложенное окно
    panels[0].style.display = "block";
    tabs[0].className = "barTabActive";
    // добавим вложенные окна
    mainWindow.appendChild(barPanels);
    // очищаем содержимое элемента
    elem.innerHTML = "";
    // создаем окно
    elem.appendChild(mainWindow);
    elem.className="barTabBody";
    elem.style.padding="0  0 5px 0";
    elem.style.visibility="visible";


}


function AjaxController() {
    var RESPONSE_SUCCESS = 0;
    var RESPONSE_ERROR = 1;
    var RESPONSE_TIME_OUT = 2;

    var request;
    var requestTimer;
    var responseListener;

    var cancelRequest = function () {
        if (request) request.abort();
        return responseListener(RESPONSE_TIME_OUT); // время ожидания ответа истекло
    };

    var createRequest = function () {
        var req;
        try {
            req = new XMLHttpRequest();
        } catch (e) {
            try {
                req = new ActiveXObject('Microsoft.XMLHTTP');
            } catch (e) {
                if (!request) try {
                    req = new ActiveXObject('Msxml2.XMLHTTP');
                } catch (e) {
                }
            }
        }
        return req;
    };

    this.sendRequest = function (method, Url, data) {
        request = createRequest();
        request.open(method, Url);
        request.send(data);
        requestTimer = setTimeout(cancelRequest, 5000); // время ожидания ответа
        request.onreadystatechange = function () {
            if (request.readyState == 4) {
                if (requestTimer) clearTimeout(requestTimer);
                if (request.status == 200) {
                    return responseListener(RESPONSE_SUCCESS, request.responseText); // ответ удачный
                } else {
                    return responseListener(RESPONSE_ERROR); // ответ с ошибкой
                }
            }
        };
    };


    this.setResponseListener = function (listenerFunction) {
        responseListener = listenerFunction;
    };

    this.abort = function () {
        if (requestTimer) clearTimeout(requestTimer);
        if (request) {
            request.abort();
        }
    }


}
