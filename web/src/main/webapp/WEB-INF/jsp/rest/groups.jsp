<?xml version="1.0" encoding="UTF-8"?>
<%--
  Created by IntelliJ IDEA.
  User: al1
  Date: 13/3/14
  Time: 8:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="application/xml;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<groups>
    <c:forEach var="group" items="${groups}">
        <group>
            <name>${group.name}</name>
            <group-status>${group.status}</group-status>
            <repository-url>${group.repository}</repository-url>
            <tasks>
                <c:forEach var="task" items="${group.tasks}">
                    <task>
                        <id>${task.id}</id>
                        <user>
                            <name>${task.userName}</name>
                        </user>
                        <task-template>
                            <name>${task.templateName}</name>
                            <test-name>${task.testClassName}</test-name>
                            <description>${task.templateDesk}</description>
                        </task-template>
                        <task-status>${task.status}</task-status>
                        <module-suffix>${task.suffix}</module-suffix>
                    </task>
                </c:forEach>
            </tasks>
            <users>
                <c:forEach var="user" items="${group.users}">
                    <user>
                        <id>${user.id}</id>
                        <userName>${user.userName}</userName>
                        <admin>${user.admin}</admin>
                        <ignore>${user.ignore}</ignore>
                    </user>
                </c:forEach>
            </users>
        </group>
    </c:forEach>
</groups>