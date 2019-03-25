<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Stock</title>
</head>
<body>
<c:import url="../jsp/header.jsp"/>

${product_status}<br/>

<table>
    <c:forEach items="${products}" var="product">
        <tr>
            <form method="post" action="home?command=update_product_info">
                <td>${product.id}</td>
                <td><input type="text" name="name" value="${product.name}" maxlength="45" style="width:200px"></td>
                <td><input type="text" name="description" value="${product.description}" maxlength="45"
                           style="width:200px"></td>
                <td><input type="number" name="cost" min="1" value=${product.cost} maxlength="11" style="width:60px"></td>
                <td><input type="number" name="quantity" min="0" value=${product.quantity} maxlength="11" style="width:60px">
                </td>
                <td><input type="submit" value="update" style="width:80px"></td>
                <td><input type="hidden" name="id" value="${product.id}"></td>
            </form>
        </tr>
    </c:forEach>
</table>
<table>
    <h3>Create new product</h3>
    <tr>
        <form method="post" action="home?command=create_product">
            <td><input type="text" name="name" maxlength="45" style="width:200px"></td>
            <td><input type="text" name="description" maxlength="45"
                       style="width:200px"></td>
            <td><input type="number" name="cost" min="1"  maxlength="11" style="width:60px"></td>
            <td><input type="number" name="quantity" min="0" maxlength="11" style="width:60px">
            </td>
            <td><input type="submit" value="add" style="width:80px"></td>
        </form>
    </tr>
</table>


</body>
</html>
