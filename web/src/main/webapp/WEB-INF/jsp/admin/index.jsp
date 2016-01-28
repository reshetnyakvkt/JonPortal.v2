<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <!--                                           -->
    <!-- Any title is fine                         -->
    <!--                                           -->
    <title>Wrapper HTML for application</title>

    <!--                                           -->
    <!-- Use normal html, such as style            -->
    <!--                                           -->
    <style>
        body, td, a, div, .p {
            font-family: arial, sans-serif
        }

        div, td {
            color: #000000
        }

        a:link, .w, .w a:link {
            color: #0000cc
        }

        a:visited {
            color: #551a8b
        }

        a:active {
            color: #ff0000
        }
    </style>

</head>

<!--                                           -->
<!-- The body can have arbitrary html, or      -->
<!-- you can leave the body empty if you want  -->
<!-- to create a completely dynamic ui         -->
<!--                                           -->
<body>

<!--                                            -->
<!-- This script is required bootstrap stuff.   -->
<!-- You can put it in the HEAD, but startup    -->
<!-- is slightly faster if you include it here. -->
<!--                                            -->
<script type="text/javascript" language="javascript"
        src="/admin/admin.nocache.js"></script>

<!-- OPTIONAL: include this if you want history support -->
<iframe id="__gwt_historyFrame" style="width:0;height:0;border:0"></iframe>

<table align=center>
    <div id="slot1"></div>
    <div id="slot2"></div>
    <div id="slot3"></div>
</table>
</body>
</html>