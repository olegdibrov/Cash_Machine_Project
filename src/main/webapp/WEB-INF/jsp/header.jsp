<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"
          prefix="c" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<fmt:bundle basename="localization.msg">
    <fmt:message key="login" var="loginLoc"/>
    <fmt:message key="registration" var="registrationLoc"/>
    <fmt:message key="logout" var="logoutLoc"/>
    <fmt:message key="home" var="homeLoc"/>
    <fmt:message key="greeting" var="greetingLoc"/>
</fmt:bundle>


<style>
    ul {
        padding: 0;
    }
    li {
        display: inline;
    }
</style>

<h2 align="center"><fmt:message key="name" var="nameLoc"/></h2>

<nav>
    <ul>
        <div align="left">
            <c:choose>
                <c:when test="${empty user}">

                    <li><a href="home?command=view_login_page">${loginLoc}</a></li>
                    <li><a href="home?command=view_registration_page">${registrationLoc}</a></li>
                </c:when>
                <c:otherwise>
                    <li>${greetingLoc}, ${user.login}! (<a href="home?command=logout">${logoutLoc}</a>)</li>
                </c:otherwise>
            </c:choose>
            <li><a href="home?command=view_home_page">${homeLoc}</a></li>
        </div>
        <div align="right">
            <li><a class="nav-link" href="home?command=change_localization&locale=en_US">ENG</a></li>
            <li><a class="nav-link" href="home?command=change_localization&locale=ru_RU">RUS</a></li>
        </div>
    </ul>
</nav>

${msg}
<br/>