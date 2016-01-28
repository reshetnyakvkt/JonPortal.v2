<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 03.09.13

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


                    <h3>Литература:</h3>
                    <ul>
                        <li>Кей Хорстман, Гарри Корнелл. Библиотека профессионала. Java 2</li>
                        <li>Брюс Эккель. Философия Java</li>
                        <li>Фредерик Брукс. Мифический человеко-месяц</li>
                        <li>Патрик Ноутон, Герберт Шилдт. Java 2</li>
                        <li>Гради Буч. Объектно-ориентированный анализ и проектирование</li>
                        <li>Тимоти Бадд. Объектно-ориентированное программирование в действии</li>
                        <li>Эльдар Хабибулин. Самоучитель Java 2</li>
                    </ul>

                <h3>Правила обучения</h3>
                    <p>Приходить во время</p>
                    <p>Вести себя интеллегентно, уважая других студентов и преподавателя</p>
                    <p>Выключать телефоны</p>
                    <p>Задавая вопросы, старайтесь придерживаться темы обсуждения</p>


                <h3>Полезное для начинающих программистов</h3>



                <p>Советы для успешного обучения:</p>

                <ul>
                    <li>Читайте много, пару часов в день</li>
                    <li>Интересуйтесь компьютерами, программированием, системами, алгоритмами</li>
                    <li>Много программируйте (минимум 3 часа в день)</li>
                </ul>

                <p>Полезные ссылки:</p>

                <ul>
                    <li><a href="http://www.eclipse.org/downloads/packages/eclipse-ide-java-developers/keplerr">Eclipse</a></li>
                    <li><a href="https://netbeans.org/downloads/">NetBeans</a></li>
                    <li><a href="http://www.jetbrains.com/idea/download/">Intellij Idea</a></li>
                    <li><a href="http://www.oracle.com/technetwork/java/codeconventions-150003.pdf">Стандарт именования идентификаторов</a></li>
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