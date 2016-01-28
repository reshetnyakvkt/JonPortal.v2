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

        <div class="well">
            <p>Здесь находится полезная информация</p>
            <ul class="nav nav-pills nav-stacked">
                <li class="slideable">
                    <a href="#"><button class="btn btn-link" type="button">Литература</button></a>
                    <ul class="well nav nav-pills nav-stacked">
                            <li>
                                <span class="badge">1</span> Хорстман Корнелл. Библиотека профессионала. Java 2<br>
                                <span class="badge badge-success">2</span> Брюс Эккель. Философия Java<br/>
                                <span class="badge badge-warning">3</span> Линн Бэйли. Изучаем SQL<br/>
                                <span class="badge badge-important">4</span> Мартин Граббер. SQL<br/>
                                <span class="badge badge-info">5</span> Эрих Фримен. Элизабет фримен. Паттерны проектирования<br/>
                                <span class="badge badge-inverse">6</span> Тимур Машнин. Современные Java технологии на практике<br/>
                                <span class="badge badge-success">7</span> Мартин Фаулер. Шаблоны корпоративных приложений<br/>
                                <span class="badge badge-important">8</span> Анил Химраджани. Гибкая разработка Eclipse+Spring+Hibernate<br/>
                                <span class="badge">9</span> Кларенс Хо, Роб Харроп. Spring 3 для профессионалов<br/>
                            </li>
                    </ul>
                </li>
            </ul>
            <ul class="nav nav-pills nav-stacked">
                <li>
                    <a href="#"><button class="btn btn-link" type="button">Видеоуроки</button></a>
                    <iframe width="560" height="315" src="//www.youtube.com/embed/vNDsoiOzJ_M?list=PLDbSS0ZrUpXU8RmniZWvVLomYOGmSNF_G" frameborder="0" allowfullscreen></iframe>
                    <iframe width="560" height="315" src="//www.youtube.com/embed/miDSIWHQk34?list=PLDbSS0ZrUpXU8RmniZWvVLomYOGmSNF_G" frameborder="0" allowfullscreen></iframe>
                    <iframe width="560" height="315" src="//www.youtube.com/embed/bbFtvdFno_0?list=PLDbSS0ZrUpXU8RmniZWvVLomYOGmSNF_G" frameborder="0" allowfullscreen></iframe>
                    <iframe width="560" height="315" src="//www.youtube.com/embed/WfdXkrNvFPI?list=PLDbSS0ZrUpXU8RmniZWvVLomYOGmSNF_G" frameborder="0" allowfullscreen></iframe>
                </li>            </ul>

        </div>
    </div>

    <footer id="footer"></footer>
</div>

</body>
</html>
