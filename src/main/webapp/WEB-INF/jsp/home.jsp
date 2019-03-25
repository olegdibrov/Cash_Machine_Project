<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cash Machine</title>
</head>
<body>
<c:import url="../jsp/header.jsp" />

<c:if test="${user.role == 2 || user.role == 3}">
    <li><a href="home?command=view_bill_page">New Bill</a></li>
    <li><a href="home?command=open_bill">Open Bill</a></li>
</c:if>

<c:if test="${user.role == 4}">
<li><a href="home?command=view_stock_page">Update product info</a></li>
</c:if>

</body>
</html>
