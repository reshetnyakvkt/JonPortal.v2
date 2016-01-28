<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="../includes.jsp" %>
</head>
<body>


<div id="container" class="container-fluid">
    <%@include file="../header.jsp" %>

    <div class="well">
        <div class="alert alert-success">
            Здесь можно выполнить тренировочные задания
        </div>

        <div class="bar-info">Выберите задание:</div>

        <ul class="well nav nav-pills nav-stacked">
            <li class="slideable">
                <a href="#">Уровень 1</a>
                <ul class="well nav nav-pills nav-stacked">
                    <c:forEach var="codeValidator" items="${codeValidators}">
                        <li>
                            <a href="/codeValidator/task?taskName=<c:out value="${codeValidator.taskName}"/>"><c:out value="${codeValidator.pseudoName}"/></a>
                        </li>
                    </c:forEach>
                </ul>
            </li>
            <li class="slideable">
                <a href="#">Проверка заданий</a>
                <ul class="nav nav-pills nav-stacked">
                    <li>
                        <a href="#">
                            <iframe class="well" id="ftpIframe" width="90%" height="500px" src="examine/index.jsp"></iframe>
                        </a>
                    </li>
                </ul>
            </li>
        </ul>




    </div>

</div>


<footer id="footer"></footer>


</body>
</html>
