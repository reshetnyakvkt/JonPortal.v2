<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="includes.jsp" %>
</head>
<body>


<div id="container" class="container-fluid">
    <%@include file="header.jsp" %>


    <div class="well">
        <div class="alert alert-success">
            Здесь можно выполнить тренировочные задания
        </div>

        <iframe id="tasksIframe" hspace="0" vspace="0" width="100%" height="700px" src="examine/index.html"></iframe>
    </div>

    <footer id="footer"></footer>
</div>

</body>
</html>
