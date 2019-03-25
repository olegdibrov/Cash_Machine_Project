<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>


<%--
  Created by IntelliJ IDEA.
  User: maild
  Date: 24.03.2019
  Time: 15:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Choose Bill</title>
</head>
<body>
<c:import url="../jsp/header.jsp" />

${openStatus}<br/>

<table>
    <c:forEach items="${bills}" var="bill">
        <tr>
            <form method="post" action="home?command=open_bill">
                <td>${bill.idBill}</td>
                <td>${bill.date}</td>
                <td>${bill.idUser}</td>
                <td><input type="hidden" name="id_bill" value="${bill.idBill}"></td>
                <td><input type="submit" value="open"></td>
            </form>
        </tr>
    </c:forEach>
</table>

<table cellpadding="5" cellspacing="5">
    <tr>
        <c:forEach begin="1" end="${noOfPages}" var="i">
            <c:choose>
                <c:when test="${currentPage eq i}">
                    <td>${i}</td>
                </c:when>
                <c:otherwise>
                    <td><a href="home?command=open_bill&page=${i}">${i}</a></td>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </tr>
</table>

</body>
</html>
