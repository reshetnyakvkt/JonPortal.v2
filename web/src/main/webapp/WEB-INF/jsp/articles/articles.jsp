<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title></title>
</head>
<body>
<b>Статьи</b>
<br/>
<table>
    <c:forEach var="currentArticle" items="${articles}">
        <tr>
            <td>
                <input type="hidden" value="${currentArticle.id}">
            </td>
            <td><a href="/articleEdit?id=${currentArticle.id}" >${currentArticle.title}</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>