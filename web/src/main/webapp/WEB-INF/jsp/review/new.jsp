<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавить новый отзыв</title>
</head>
<body>
<form action="http://localhost:8081/addreview" method="post">
   <h3>Nickname: </h3> <input type="text" value="input nickname" name="nickname"/><br>
   <h3>E-mail: </h3> <input type="text" value="input e-mail" name="email"/><br>
   <h3>Your review: </h3><textarea type="text" cols="40" rows=8 name="text"></textarea>
    <input type="submit" value="Send">
</form>
</body>
</html>