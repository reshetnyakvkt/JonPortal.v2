<%--
  Created by IntelliJ IDEA.
  User: al1
  Date: 04.09.14
  Time: 11:18
  To change this template use File | Settings | File Templates.
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
                            <br/>

                            <h2>Творчество студентов</h2>
                            <hr/>

                            <div class="well">
                                <img src="/img/salva_hanoi1.jpg">
                                <p>Коммандное решение задачи <q>Ханойские башни</q></p>
                            </div>
                            <div class="well">
                                <img src="/img/salva_hanoi2.jpg">
                                <p>Эмпирическим путём было получено оптимальное решение в 15 ходов (при этом, на радостях,
                                    был испорчен реквизит - стаканчик:))</p>
                            </div>
                            <hr/>

                            <p>Иногда, при определенных обстоятельствах, учеников посещает муза и в результате рождаются
                                шедевры устного творчества</p>
                            <hr/>
                            <br/>
                            <h4>Стих первый</h4>

                            <p>Утром солнечным осенним</p>

                            <p>В темном кабинете</p>

                            <p>Собрался людей десяток</p>

                            <p>Хмурых, будто йети</p>
                            <br/>
                            <br/>

                            <p>Есть у каждого компьютер</p>

                            <p>В нем мелькают строчки</p>

                            <p>Кода сложного конструкций:</p>

                            <p>Запятые, точки…</p>
                            <br/>
                            <br/>

                            <p>Эти люди- программисты</p>

                            <p>Джаву они учат</p>

                            <p>А их хмурость не случайна:</p>

                            <p>Списков сложный случай.</p>
                            <hr/>

                            <h4>Стих второй</h4>
                            <br/>

                            <p>Сижу за ноутом в ночи</p>

                            <p>Пишу на Джаве код в тиши</p>

                            <p>Как допишу, да лягу спать</p>

                            <p>Чтобы на курс не опоздать.</p>

                            <p>Ну, а если опоздаю-</p>

                            <p>Этот стих вам прочитаю.</p>
                            <hr/>

                        </div>
                    </div>

                    <br/>

                </div>

            </div>
            <!-- tabs -->


        </div>

        <footer id="footer"></footer>
    </div>

</body>
</html>

