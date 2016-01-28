<%--
  Created by IntelliJ IDEA.
  User: al1
  Date: 12.05.15
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <%@include file="includes.jsp"%>
  <%--<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>--%>

  <script>
    $(function() {
      $( "button" )
              .button()
              .click(function( event ) {
                event.preventDefault();
                alert('click');
              });
    });
  </script>
</head>
<body>


<div id="container" class="container-fluid">
  <%@include file="header.jsp" %>

  <div class="well">
    <div id="tittleText" class="alert alert-info lead text-info text-center">

      Тренажёр разработчика
    </div>
    <ul class="well nav nav-pills nav-stacked">
      <li>
        <a class="text-center" href="#">

          Простачок</a>
      </li>
    </ul>

    <%--<div class="col-md-4 col-md-offset-4">.col-md-4 .col-md-offset-4</div>--%>
    <div class="row">
      <%--<div class="">.col-md-6 .col-md-offset-3</div>--%>
      <button class="col-md-4 col-md-offset-4 btn btn-danger">A button element</button>
      <%--<div class="col-md-4"></div>--%>
    </div>
    <div class="row">
        <button class="col-md-2 col-md-offset-1 btn btn-info">button</button>
        <button class="col-md-2 btn btn-info">button</button>

        <div class="clearfix visible-xs-block"></div>

        <button class="col-md-2 btn btn-info">button</button>
        <button class="col-md-2 btn btn-info">button</button>
        <button class="col-md-2 btn btn-info">button</button>
    </div>


  </div>

  <footer id="footer"></footer>
</div>

</body>
</html>
