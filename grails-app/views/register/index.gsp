<%--
  Created by IntelliJ IDEA.
  User: mpaulez
  Date: 3/21/16
  Time: 21:23
--%>


<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="Register">
    <g:set var="entityName" value="${message(code: 'register.label', default: 'Register')}" />
    <title><g:message code="default.list.label" args="[entityName]" /></title>
</head>
<body>
<a href="#list-register" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
    </ul>
</div>
<div id="list-register" class="content scaffold-list" role="main">
    <h1><g:message code="default.list.label" args="[entityName]" /></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <table>
        <thead>
        <tr>

        </tr>
        </thead>
        <tbody>
        <g:each in="${registerInstanceList}" status="i" var="registerInstance">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

            </tr>
        </g:each>
        </tbody>
    </table>
    <div class="pagination">
        <g:paginate total="${registerInstanceCount ?: 0}" />
    </div>
</div>
</body>
</html>
