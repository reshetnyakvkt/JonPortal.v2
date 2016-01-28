<?xml version="1.0" encoding="UTF-8"?>
<%--
  Created by IntelliJ IDEA.
  User: al1
  Date: 8/8/13
  Time: 3:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="application/xml;charset=UTF-8" language="java" %>

<task>
    <user>
        <name>${id}</name>
        <group>
            <name>name</name>
            <group-status>true</group-status>
            <repository-url>repo</repository-url>
        </group>
    </user>
    <task-template>
        <name>taskName</name>
        <description>taskDesc</description>
    </task-template>
    <task-status>
        TEST
    </task-status>
</task>