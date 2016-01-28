<%@ page import="ua.com.jon.review.Review" %>
<%@ page import="ua.com.jon.review.ReviewDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%ReviewDAO reviewDAO = new ReviewDAO();%>
<head>
    <title>Отзывы пользователей</title>
</head>
<body>
 <a href="http://localhost:8081/review/new.jsp">Добавить отзыв</a>
    <%for(Review r: reviewDAO.getAllReviews()){%>
 <h3><%=r.getUser().getNickName()%></h3>
 <font color="blue"><b><%=r.getDateFormatted()%></b></font><p></p>
 <%=r.getText()%>
 <%}%>
</body>
</html>