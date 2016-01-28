<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="includes.jsp" %>
    <style>
        .errorblock {
            text-align: center;
            color: #ff0000;
            background-color: #ffEEEE;
            border: 2px solid #ff0000;
            padding: 1px;
            margin: 1px;
        }
    </style>
</head>
<body onload='document.lform.j_username.focus();'>

<%@include file="header.jsp" %>

<div id="container" class="container-fluid">

    <div class="row-fluid">
        <div class="span3 well">
            <h4 class="error">Регистрация</h4>

            <c:if test="${not empty message}">
                <div class="alert alert-error">
                        <%--Your login attempt was not successful, try again.<br /> Caused : --%>
                        ${message}
                </div>
            </c:if>

            <form name="lform" method="POST" action="/register">
                <div class="form-group">
                    <label>Логин: imya_familiya (в нижнем регистре)</label>
                    <input type="text" id="username" class="form-control" name="j_username" placeholder="Логин"/>
                </div>
                <div class="form-group">
                    <label>Пароль: буквы и цифры</label>
                    <input type="password" id="password" class="form-control" name="j_password" placeholder="Пароль"/>
                </div>
                <div class="form-group">
                    <label>Группа</label>
                    <select name="group" class="form-control">

                        <c:forEach var="group" items="${groups}">
                            <c:out value="${group}"/>
                            <div class="alert alert-error">
                                <option value="${group.id}">${group.name}</option>
                            </div>
                        </c:forEach>

                    </select>
                </div>
                <div class="form-group">
                    <input type="text" id="code" class="form-control" name="code" placeholder="Код группы"/>
                </div>
                <button type="submit" name="submit" class="btn btn-default btn-block">
                    Зарегистрироваться
                </button>
            </form>

        </div>
    </div>

</div>

<footer id="footer"></footer>

</body>
</html>
