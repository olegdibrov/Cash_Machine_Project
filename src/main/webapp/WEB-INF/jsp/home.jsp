<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<%--
  Created by IntelliJ IDEA.
  User: maild
  Date: 15.03.2019
  Time: 22:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cash Machine</title>
</head>
<body>
<c:import url="../jsp/header.jsp" />

<c:if test="${user.role == 2}">
    <li><a href="home?command=view_bill_page&bill=new_bill">New Bill</a></li>
    <li><a href="home?command=view_bill_page&bill=open_bill">Open Bill</a></li>
</c:if>

</body>
</html>
