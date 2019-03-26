<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:bundle basename="localization.msg">
    <fmt:message key="registration" var="registrationLoc"/>
</fmt:bundle>
<html>
<head>
    <title>Admin Page</title>
</head>
<body>
<c:import url="../jsp/header.jsp"/>

<h1>Admin Page</h1>
<div>
    <div>
        <li><a href="home?command=view_registration_page">${registrationLoc}</a></li>
    </div>
</div>

</body>
</html>
