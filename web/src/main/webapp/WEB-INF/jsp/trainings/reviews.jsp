<%--
  Created by IntelliJ IDEA.
  User: al1
  Date: 4/20/13
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
                            <h2>Отзывы</h2>
                        <hr/>

                        <div class="row">
                    Отзыв
                </div>
            </div>

        </div>
        <!-- tabs -->


    </div>

    <footer id="footer"></footer>
</div>

</body>
</html>
