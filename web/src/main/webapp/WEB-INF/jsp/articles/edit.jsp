<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Редактирование статьи</title>
</head>
<body>
<b>Редактирование статьи</b>
<br/>
<table>
    <tr>
        <td>
            Редактирование:
        </td>
        <td>
            Предварительный просмотр:
        </td>
    </tr>
    <tr>
    <td>
        <div>
            <br/>
            <form:form action="/saveArticle" modelAttribute="article" method="post">
            <fieldset>
            <form:hidden path="id"/>
            <br/>
            <form:label for="title" path="title">Название:</form:label>
            <br/>
            <form:input id="title" type="text" value="${article.title}" path="title"/>
            <br/>
            <form:label for="text" path="text">Текст:</form:label>
            <br/>
                <textarea id="text" rows="25" cols="85" name="text">
                    ${article.text}
                </textarea>
            <br/>
            <input type="submit" value="Сохранить"/>
            <input type="submit" value="Отмена"/>
            </fieldset>
            </form:form>
        </div>
    </td>
    <td>
        <div>
            <textarea id="textPreview" rows="25" title="Предварительный просмотр:" cols="85">
                ${article.title}
            </textarea>
        </div>
    </td>
    </tr>
</table>
</body>
</html>