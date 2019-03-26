<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Products in bill</title>
</head>
<body>
<c:import url="../jsp/header.jsp"/>

${paymentStatus}<br/>

<table>
    <c:forEach items="${products}" var="product">
        <tr>
            <form method="post" action="home?command=remove_product_from_bill">
                <td>${product.idPayment}</td>
                <td>${product.quantity}</td>
                <td>${product.product.name}</td>
                <td>${product.product.cost}</td>
                <td><input type="hidden" name="id_payment" value="${product.idPayment}"></td>
                <c:if test="${user.id == 3}">
                    <td><input type="submit" value="remove"></td>
                </c:if>
            </form>
        </tr>
    </c:forEach>
</table>
<c:if test="${user.id == 3}">
<form method="post" action="home?command=cancel_bill">
    <input type="submit" value="Cancel bill">
</form>
</c:if>
<form method="post" action="home?command=view_bill_page">
    <input type="submit" value="Back">
</form>

<form method="post" action="home?command=close_bill">
    <input type="submit" value="Close bill">
</form>

</body>
</html>
