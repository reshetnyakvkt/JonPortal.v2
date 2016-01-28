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
    <div class="row-fluid">
        <div class="col-xs-3 well">
            <h4 class="error">Вход</h4>

            <c:if test="${not empty error}">
                <div class="form-group has-error has-feedback">
                            <label class="control-label">
                                ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
                            </label>
                </div>
            </c:if>

            <form name="lform" method="POST" action="/login">
                <div class="form-group">
                    <input type="text" id="username" class="form-control" name="j_username" placeholder="Логин"/>
                </div>
                <div class="form-group">
                    <input type="password" id="password" class="form-control" name="j_password" placeholder="Пароль"/>
                </div>
                <div class="checkbox">
                    <label>
                        <input type="checkbox" name="_spring_security_remember_me">Запомнить меня
                    </label>
                </div>
                <a href="/register.html">Зарегистрироваться</a>
                <button type="submit" name="submit" class="btn btn-default btn-block">
                    Войти
                </button>
            </form>

        </div>
        <div class="col-xs-8 well">
            <nav class="navbar navbar-default">
                <span class="navbar-brand"><b class="text-warning">Портал для тех кто связался с Java</b></span>
            </nav>

            <div class="well">
                <p>В разделе <span class="label label-primary">Статьи</span> находятся полезные для обучающихся статьи</p>

                <p>В разделе <span class="label label-primary">Занятия</span> находится список литературы и видеоуроки</p>

                <p>В разделе <span class="label label-primary">Задачи</span> находится система проверки заданий для курса Foundation</p>

                <p>В разделе <span class="label label-primary">Cкачать</span> находятся нужные для занятий файлы</p>
            </div>

            <nav class="navbar navbar-default">
                <span class="navbar-brand"><b class="text-warning">Новости</b></span>
            </nav>

            <div class="well">

                <p>05.10.2015 Добавлена визуализации методов сортировки <a href="/demo">перейти</a></p>

                <p>12.08.2015 Снижение рейтинга за недочёты на 1 балл <a href="/trainings/styleChecker.html">перейти</a></p>

                <p>03.05.2015 Переписан кабинет</p>

                <p>02.04.2015 Начата проверка стиля заданий ... <a href="/trainings/styleChecker.html">перейти</a></p>

                <p>28.03.2015 Добавлена статья "Соглашение о кодировании" ... <a href="/trainings/style.html">перейти</a></p>

                <p>20.01.2015 Добавлена статья "Компьютерный слэнг" ... <a href="/trainings/slang.html">перейти</a></p>

                <p>15.01.2015 Обновлён дизайн by Stefan ©</p>

                <p>04.10.2014 Добавлены видеоуроки для курса Foundation... <a href="/lessons.html">перейти</a></p>

                <p>09.07.2014 Добавлена мультимодульная проверка для курса Professional</p>

                <p>15.04.2014 Улучшен дизайн открытой части портала (Благодаря Stefan &#0169;)</p>

                <p>22.03.2014 Добавлена статья "Вредоносный код"... <a href="/trainings/nedocode.html">перейти</a></p>

                <p>17.01.2014 Закончена миграци на Git</p>

                <p>03.01.2014 Закончена миграция на Java 8</p>

                <p>15.07.2013 Добавлена проверка для заданий по работе с файлами</p>

                <p>15.03.2013 Сделана проверка заданий для курса "Foundation"</p>

                <p>07.01.2013 Сделана интеграция с SVN</p>
            </div>

        </div>
    </div>

</div>

<div class="back">

</div>

<footer id="footer"></footer>

<script data-main="/js/cabinet/main" src="/js/require.js"></script>

</body>
</html>
