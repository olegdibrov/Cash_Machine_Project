<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page isELIgnored="false" %>


<fmt:bundle basename="localization.msg" prefix="login.">
    <fmt:message key="login" var="loginLoc"/>
    <fmt:message key="password" var="passwordLoc"/>
    <fmt:message key="submit" var="submitLoc"/>
</fmt:bundle>

<html>
<head>
    <title>Login</title>
</head>
<body>
<c:import url="../jsp/header.jsp"/>

<form method="post" action="home?command=view_login_page">

    <table>
        <tr>
            <td align="left">
                ${loginLoc}
            </td>
            <td align="right">
                <input placeholder="${loginLoc}" id="Login" name="Login" required="required"
                       minlength="5" maxlength="20"/>
            </td>
        </tr>
        <tr>
            <td align="left">
                ${passwordLoc}
            </td>
            <td align="right">
                <input type="password" placeholder="${passwordLoc}" id="Password" name="Password" required="required"
                       minlength="5" maxlength="20"/>
            </td>
        </tr>
    </table>
    <div class="col-md-4">
        <button id="singlebutton" name="singlebutton" class="btn btn-primary">${submitLoc}</button>
    </div>
</form>

</body>
</html>