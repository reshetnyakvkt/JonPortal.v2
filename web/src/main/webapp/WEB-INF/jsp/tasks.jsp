<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="includes.jsp"%>
</head>
<body>


<div id="container" class="container-fluid">
    <%@include file="header.jsp" %>


    <div class="well">
        <div class="bar-info">
            Возможности удаленной работы:
        </div>
        <ul class="well nav nav-pills nav-stacked">
            <li>
                <a href="#">Задания</a>
            </li>
        </ul>
        <div class="bar-info">
            Задания для домашней работы:
        </div>

        <ul class="well nav nav-pills nav-stacked">
            <li class="slideable">
                <a htef="#">TaskGroup1</a>
                <ul class="well nav nav-pills nav-stacked">
                    <li>
                        <a href="/codeValidator/index">Java Code Validator</a>
                    </li>
                    <li>
                        <a href="ftp://com.jon.com.ua">Скачать</a>
                    </li>
                </ul>
            </li>
            <li class="slideable">
                <a htef="#">TaskGroup2</a>
                <ul class="well nav nav-pills nav-stacked">
                    <li>
                        <a href="#">SubStub 1</a>
                    </li>
                    <li>
                        <a href="#">SubStub 2</a>
                    </li>
                    <li>
                        <a href="#">SubStub 3</a>
                    </li>
                    <li>
                        <a href="#">SubStub 4</a>
                    </li>
                </ul>
            </li>
            <li class="slideable">
                <a htef="#">TaskGroup3</a>
                <ul class="well nav nav-pills nav-stacked">
                    <li>
                        <a href="#">SubStub 1</a>
                    </li>
                    <li>
                        <a href="#">SubStub 2</a>
                    </li>
                    <li>
                        <a href="#">SubStub 3</a>
                    </li>
                    <li>
                        <a href="#">SubStub 4</a>
                    </li>
                </ul>
            </li>
        </ul>

    </div>

    <footer id="footer"></footer>
</div>

</body>
</html>
