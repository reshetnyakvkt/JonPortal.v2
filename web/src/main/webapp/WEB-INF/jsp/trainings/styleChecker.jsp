<%--
  Created by IntelliJ IDEA.
  User: al1
  Date: 19.02.15
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
                <div class="tab-content">
                    <div class="row">

                        <div class="span8">
                            <h2>Проверка правил кодирования</h2>
                            <hr/>

                            <p>Мы начинаем проверку на соответствие выполняемых заданий правилам кодирования</p>

                            <p>Теперь в заданиях будут проверяться правила именования, это касается студентов
                                всех курсов</p>

                            <p>Проверяться будут имена пакетов, классов и переменных</p>

                            <p>За несоответствие правилам кодирования оценка снижается на 1 балл. Так же вы увидите
                                результат проверки в разделе "Недочёты"</p>
                            <br/>

                        </div>
                    </div>

                    <br/>

                </div>

            </div>
            <!-- tabs -->

        </div>
    </div>
</div>

<footer id="footer"></footer>

</body>
</html>

