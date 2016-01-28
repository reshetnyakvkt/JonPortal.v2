<%--
  Created by IntelliJ IDEA.
  User: al1
  Date: 22.08.15
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
  <link href="/img/buttonRun.png" rel="icon" type="image/x-icon">
  <%--<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css" rel="stylesheet" media="screen">--%>
  <link href="/css/bootstrap.min.css" rel="stylesheet" media="screen">
  <%--<link href="//cdn.datatables.net/1.10.7/css/jquery.dataTables.css" rel="stylesheet" media="screen">--%>
  <link href="//cdn.datatables.net/plug-ins/1.10.7/integration/bootstrap/3/dataTables.bootstrap.css" rel="stylesheet" media="screen">
  <%--<link href="//editor.datatables.net/examples/resources/bootstrap/editor.bootstrap.css" rel="stylesheet" media="screen">--%>
  <link href="/css/codemirror/codemirror.css" rel="stylesheet" media="screen">
  <link href="/css/custom.css" rel="stylesheet" media="screen">
  <link href="/css/codemirror/vibrant-ink.css" rel="stylesheet" media="screen">
  <title>Работы студентов</title>
</head>
<body>
<div id="container" class="container-fluid">
  <!-- Nav tabs -->


  <%@include file="header.jsp" %>
  <br/>

  <div class="row well">

    <div class="col-md-12">

      <div class="panel-group" id="bstAccordion">
        <div class="panel panel-default">
          <div class="panel-heading">
            <h4 class="panel-title">
              <table id="bsttable_id" class="table table-striped table-bordered">
                <tbody>
                  <tr>
                    <th>Исполнитель</th>
                    <td>Дмитрий Мирошников. Курс: ООП. Выпуск 22.08.15</td>
                  </tr>
                  <tr>
                    <th>Задание</th>
                    <td>
                      <p>Задание: Создать трёхмерную модель вставки элемента в бинарное дерево поиска.</p>
                      <p>В качестве графической среды использовать JavaFX.</p>
                    </td>
                  </tr>
                </tbody>
              </table>
              <a data-toggle="collapse" data-parent="#tableAccordion" href="#bstcollapseTable"><u>Показать</u></a>
            </h4>
          </div>
          <div id="bstcollapseTable" class="panel-collapse collapse">
            <div>
              <div class="well">
                <p><a href="solutions/BinarySearchTree.zip"><u>Скачать исходный код</u></a></p>
                <img src="solutions/bs_shot1.png">
                <img src="solutions/bs_shot2.png">
              </div>
            </div>
          </div>
        </div>
      </div>

      <div class="panel-group" id="tableAccordion">
        <div class="panel panel-default">
          <div class="panel-heading">
            <h4 class="panel-title">
              <table id="table_id" class="table table-striped table-bordered">
                <tbody>
                  <tr>
                    <th>Исполнитель</th>
                    <td>Дмитрий Мирошников. Курс: ООП. Выпуск 22.08.15</td>
                  </tr>
                  <tr>
                    <th>Задание</th>
                    <td>
                      <p>Задание: Создать трёхмерную анимацию разных методов сортировки.</p>
                      <p>В качестве графической среды использовать JavaFX. Анимацию реализовать с помощью потоков.</p>
                    </td>
                  </tr>
                </tbody>
              </table>
              <a data-toggle="collapse" data-parent="#tableAccordion" href="#collapseTable"><u>Показать</u></a>
            </h4>
          </div>
          <div id="collapseTable" class="panel-collapse collapse">
            <div>
              <div class="well">
                <p><a href="solutions/VisualSorting.zip"><u>Скачать исходный код</u></a></p>
                <img src="solutions/dm_shot1.PNG">
                <img src="solutions/dm_shot2.PNG">
              </div>
            </div>
          </div>
        </div>
      </div>

      <div class="panel-group" id="akAccordion">
        <div class="panel panel-default">
          <div class="panel-heading">
            <h4 class="panel-title">
              <table id="ak_id" class="table table-striped table-bordered">
                <tbody>
                  <tr>
                    <th>Исполнитель</th>
                    <td>Александр Козакевич. Курс: ООП. Выпуск 22.08.15</td>
                  </tr>
                  <tr>
                    <th>Задание</th>
                    <td>
                      <p>Задание: Создать трёхмерную анимацию разных методов сортировки.</p>
                      <p>В качестве графической среды использовать JavaFX. Анимацию реализовать с помощью потоков.</p>
                    </td>
                  </tr>
                </tbody>
              </table>
              <a data-toggle="collapse" data-parent="#akAccordion" href="#akTable"><u>Показать</u></a>
            </h4>
          </div>
          <div id="akTable" class="panel-collapse collapse">
            <div>
              <div class="well">
                <p><a href="solutions/MoveFigures_fin.rar"><u>Скачать исходный код</u></a></p>
                <img src="solutions/ak_shot1.png">
                <img src="solutions/ak_shot2.png">
              </div>
            </div>
          </div>
        </div>
      </div>

    </div>

  </div>

  <footer id="footer"></footer>
  <script data-main="/js/cabinet/main" src="/js/require.js"></script>

</div>
</body>
</html>
