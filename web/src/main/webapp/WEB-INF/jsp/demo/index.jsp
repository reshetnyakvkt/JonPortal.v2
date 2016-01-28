<%--
  Created by IntelliJ IDEA.
  User: al1
  Date: 04.10.15
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <title>Algorithms visualization</title>
  <%@include file="../includes.jsp" %>
  <link rel="stylesheet" type="text/css" href="/demo/css/style.css">
  <script type="text/javascript" src="/js/jquery-1.8.3.min.js"></script>
  <script type="text/javascript" src="/demo/js/script.js"></script>
  <meta charset="utf-8">
</head>
<body>

<div class="container-fluid">
  <%@include file="../header.jsp" %>
  <%--<div class="well">--%>

    <div class="well">
      <div id="resizable">
        <div id="outer">
          <div id="wrapper">
            <div id="container">
              <div class="inner"></div>
              <div class="deleted"></div>
            </div>
          </div>
        </div>
        <div class='plashka'></div>
        <div class="count"></div>
        <div title="resize" class="resizer"></div>
      </div>
      <div id="controls">
        <div>
          <nav class="navbar-inner" role="navigation">
            <div class="container">
              <button class="label label-default" value="sort">Start</button>
              <button class="label label-default" value="pause">Pause</button>
              <button class="label label-default" value="stop">Stop</button>
              <button class="label label-default" value="step">Step</button>
            </div>
          </nav>
        </div>

        <div>
          <button class="label label-default" value="save">Save</button>
          <button class="label label-default" value="ord">Ordered</button>
          <!--<button value="revers">Revers</button>-->
          <button class="label label-default" value="random">Random</button>
          <input name="range" type="number" min="2" value="100">

          <label for="type">Type</label>
          <select id="type">
            <optgroup label="sort">
              <option id="stupid">Stupid</option>
              <option id="bubble">Bubble</option>
              <option id="shaker">Shaker</option>
              <option id="chet">Chet-Nechet</option>
              <option id="combSort">ComboSort</option>
              <option id="select">Selection</option>
              <option id="insert">Insert</option>
              <option id="shell">Shell</option>
              <option id="qSort">qSort</option>
            </optgroup>
            <optgroup label="other">
              <option id="swapH">swap halves</option>
              <option id="revers">revers</option>
            </optgroup>

          </select>



          <label>Speed
            <input name="speed" type="range" min="0" max="1200" value="800">
          </label>
        </div>


      </div>
      <div id="bottom">
        <div id="saved">
          <p>Arrays</p>
          <div class="block">
          </div>
        </div>
      </div>


  </div>

  <footer id="footer"></footer>
<%--</div>--%>





<%--<h1 style="text-align:center">Sort</h1>--%>


</body>
</html>
