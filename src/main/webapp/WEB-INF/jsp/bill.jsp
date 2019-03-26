<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: maild
  Date: 21.03.2019
  Time: 20:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <title>Bill</title>
</head>
<body>
<c:import url="../jsp/header.jsp"/>

<div>
    <h1>Bill #${id_bill} </h1>
</div>

${paymentStatus}<br/>

<table>
    <c:forEach items="${products}" var="product">
        <c:if test="${product.quantity > 0}">
            <tr>
                <form method="post" action="home?command=add_products_to_bill">
                    <td>${product.id}</td>
                    <td>${product.name}</td>
                    <td>${product.cost}</td>
                    <td><input type="number" name="quantity" value="1" min="1"
                               max=${product.quantity} style="width:35px">
                    </td>
                    <td><input type="submit" value="+" style="width:25px"></td>
                    <td><input type="hidden" name="id_product" value="${product.id}"></td>
                </form>
            </tr>
        </c:if>
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
                    <td><a href="home?command=view_bill_page&page=${i}">${i}</a></td>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </tr>
</table>
<form method="post" action="home?command=view_products_in_bill">
    <input type="submit" value="View products in bill" style="width:85px"></td>
</form>


</body>
</html>
