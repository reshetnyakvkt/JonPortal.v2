<%--
  Created by IntelliJ IDEA.
  User: al1
  Date: 4/18/13
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="../includes.jsp" %>
</head>
<body>

<div id="container" class="container-fluid">
    <%@include file="../header.jsp" %>


        <div class="row">

            <!-- tabs -->
            <div class="panel panel-default">
                <div class="panel-body">
                    <%@include file="menu.jsp" %>
                    <div class="col-md-8">
                        <div class="row">

                            <div class="span8">

                <h3>Тренинг основы реляционной базы данных Oracle</h3>

                <p>Длительность тренинга 6 часов, состоит из двух частей.<br/>
                Предназначен для тех кто раньше не работал с базами данных</p>
                <p>Краткое описание:</p>

                <ul>
                    <li>Первый день - основы реляционных баз данных</li>
                    <ul>
                        <li>Понятие БД, СУБД</li>
                        <li>Реляционные БД</li>
                        <li>Нормальные формы</li>
                        <li>Ключи</li>
                        <li>Ассоциации</li>
                        <li>SQL - insert</li>
                        <li>SQL - delete</li>
                        <li>SQL - update</li>
                        <li>SQL - select</li>
                    </ul>
                    <li>Второй день - Java интерфейс к БД JDBC</li>
                    <ul>
                        <li>Понятие JDBC</li>
                        <li>Основные объекты JDBC</li>
                        <li>Подключение из программы к БД</li>
                        <li>Statement</li>
                        <li>PreparedStatement</li>
                        <li>BLOB</li>
                        <li>Транзакции</li>
                    </ul>

                </ul>

                <br/>

            </div>

        </div>
        <!-- tabs -->


    </div>

    <footer id="footer"></footer>
</div>

</body>
</html>
