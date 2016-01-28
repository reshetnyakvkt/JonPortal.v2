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

                <table class="table table-striped table-bordered">
                    <caption>
                        <h4>Расписание занятий на 2012 год</h4>
                    </caption>
                    <thead>
                    <tr>
                        <th>День</th>
                        <th>Дата</th>
                        <th>Описание</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>понедельник</td>
                        <td>23 сентября (19.00 - 22.00)</td>
                        <td>Базовый курс</td>
                    </tr>
                    <tr>
                        <td>вторник</td>
                        <td>с 23 сентября (19.00 - 22.00)</td>
                        <td>Базовый курс</td>
                    </tr>
                    <tr>
                        <td>среда</td>
                        <td>с 25 сентября (19.00 - 22.00)</td>
                        <td>Java</td>
                    </tr>
                    <tr>
                        <td>пятница</td>
                        <td>c 25 сентября (19.00 - 22.00)</td>
                        <td>Java</td>
                    </tr>
                    <tr>
                        <td>суббота</td>
                        <td>c 21 сентября (11.00 - 14.00)</td>
                        <td>Базовый курс</td>
                    </tr>
                    <tr>
                        <td>суббота</td>
                        <td>c 28 сентября (14.00 - 18.00)</td>
                        <td>Java</td>
                    </tr>
                    <tr>
                        <td>суббота</td>
                        <td>c 28 сентября (19.00 - 20.00)</td>
                        <td>Индивидуальные занятия</td>
                    </tr>
                    <tr>
                        <td>воскресенье</td>
                        <td>c 21 сентября (11.00 - 14.00)</td>
                        <td>Базовый курс</td>
                    </tr>
                    <tr>
                        <td>воскресенье</td>
                        <td>c 28 сентября (14.00 - 18.00)</td>
                        <td>Java</td>
                    </tr>
                    <tr>
                        <td>воскресенье</td>
                        <td>c 28 сентября (19.00 - 20.00)</td>
                        <td>Индивидуальные занятия</td>
                    </tr>
                    </tbody>
                </table>
            </div>

        </div>
        <!-- tabs -->


    </div>

    <footer id="footer"></footer>
</div>

</body>
</html>
