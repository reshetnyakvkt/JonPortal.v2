<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <%@include file="../includes.jsp"%>
    <script src="/js/task.js"></script>
    <script src="/js/GUI_Engine.js"></script>
</head>
<body>

<div id="container" class="container-fluid">
    <%@include file="../header.jsp"%>

    <div class="well">

        <div class="well">
            Java Validator Engine
        </div>

        <div class="bar-info">
            <c:out value="${codeValidator.taskDescription}"/>
        </div>
        <div class="textBlock">
            <div class="button icon-play tab-pane" id="buttonRun">
                <%--button run--%>
            </div>
            <div class="button icon-stop tab-pane" id="buttonStop">
                <%--button stop--%>
            </div>
        </div>

        <%--edit field--%>
        <ul id="BarTabbed">
            <c:forEach var="taskClass" items="${codeValidator.taskClasses}">
                <li title="<c:out value="${taskClass.suffix}"/>">
                    <div class="well editField" id="TextField" contenteditable="true"
                         spellcheck="false"><c:out value="${taskClass.classCode}"/></div>
                </li>
            </c:forEach>
        </ul>

        <div class="console" id="JavaConsole">
            <%--console--%>
        </div>
    </div>
    <input type="hidden" id="taskName" value="<c:out value="${codeValidator.taskName}"/>"/>

</div>


</body>
</html>