<%--
  Created by IntelliJ IDEA.
  User: al1
  Date: 3/2/13
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="includes.jsp" %>
</head>
<body>

<div id="container" class="container-fluid">
    <%@include file="header.jsp" %>


    <div class="well">
        <div class="bar-info">
            Перечень вариантов скачивания:
        </div>
        <%--<a class="icon file" draggable="true" href="ftp://193.169.189.240/Git-1.9.4-preview20140815.exe">Git-1.9.4-preview20140815.exe</a>--%>
<%--        <ul class="well nav nav-pills nav-stacked">
            <li class="slideable">--%>

                <ul class="well nav nav-pills nav-stacked">
                    <c:forEach var="url" items="${urls}">
                        <li>
                            <a href="${url.value}">${url.key}</a>
                        </li>
                    </c:forEach>
                </ul>
<%--            </li>
        </ul>--%>


<%--        <div class="bar-info">
            Перечень вариантов скачивания:
        </div>--%>

        <ul class="well nav nav-pills nav-stacked">
<%--
            <li>
                <a href="files/jdk-7u25-windows-i586">jdk 7u25 windows i586</a>
            </li>
            <li>
                <a href="files/jdk-7u25-windows-x64">jdk 7u25 windows x64</a>
            </li>
            <li>
                <a href="files/eclipse-java-kepler-R-win32">Eclipse kepler 32bit</a>
            </li>
            <li>
                <a href="files/eclipse-java-kepler-R-win32-x86_64">Eclipse kepler 64bit</a>
            </li>
--%>
            <li>
                <%--<a href="files/netbeans-7_3_1-javase-windows">NetBeans 7.3.1</a>--%>
                <a href="https://netbeans.org/downloads/">NetBeans link</a>
            </li>
            <li>
                <%--<a href="files/ideaIU-12_0_4">Idea Community Edition 12.0.4</a>--%>
                <a href="http://www.jetbrains.com/idea/download/download_thanks.jsp">Idea link</a>
            </li>
<%--
            <li>
                <a href="files/hibernate_reference">Документация Hibernate 3.6</a>
            </li>
--%>
            <li>
                <a href="files/hibernate_reference4">Документация Hibernate 4.3.4</a>
            </li>
            <li>
                <a href="files/spring-framework-reference">Документация Spring 3.2</a>
            </li>
<%--
            <li>
                <a href="files/ideaIU-12_1_4">Idea Ultimate 12.1.4</a>
            </li>
--%>
            <%--<li>--%>
                <%--<a href="${ftpAddress}/libs/Oracle JDBC Driver/ojdbc14.jar">JDBC Driver Oracle</a>--%>
            <%--</li>--%>
<%--
            <li>
                <a href="files/OracleXE">Oracle XE 10g</a>
            </li>
--%>

<%--
            <li>
                <a href="files/apache-tomcat-7_0_41">Tomcat 7.0.41</a>
            </li>
--%>
<%--            <li>
                <a href="files/apache-maven-2_2_1-bin">Maven 2.2.1</a>
            </li>--%>
            <%--<li>--%>
                <%--<a href="${ftpAddress}/libs/jstl/jstl-api-1.2.jar">jstl api</a>--%>
            <%--</li>--%>
            <%--<li>--%>
                <%--<a href="${ftpAddress}/libs/jstl/jstl-impl-1.2.jar">jstl impl</a>--%>
            <%--</li>--%>

        </ul>

    </div>

    <footer id="footer"></footer>
</div>

</body>
</html>
