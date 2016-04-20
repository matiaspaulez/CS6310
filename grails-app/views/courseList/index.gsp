<%--
  Created by IntelliJ IDEA.
  User: mpaulez
  Date: 4/19/16
  Time: 21:09
--%>

<%@ page import="sca.Student" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="CourseList">
    <g:set var="entityName" value="${message(code: 'provider.label', default: 'Provider')}" />
    <title><g:message code="default.list.label" args="[entityName]" />dkdkdk</title>
</head>
<body>
<a href="#list-provider" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
    </ul>
</div>
<div id="list-provider" class="content scaffold-list" role="main">
    <h1><g:message code="default.list.label" args="[entityName]" /></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <table>
        <thead>
        <tr>

            <g:sortableColumn property="documents" title="${message(code: 'provider.documents.label', default: 'Documents')}" />

            <g:sortableColumn property="firstName" title="${message(code: 'provider.firstName.label', default: 'First Name')}" />

            <g:sortableColumn property="lastName" title="${message(code: 'provider.lastName.label', default: 'Last Name')}" />

            <g:sortableColumn property="patients" title="${message(code: 'provider.patients.label', default: 'Patients')}" />

            <g:sortableColumn property="userName" title="${message(code: 'provider.userName.label', default: 'User Name')}" />

        </tr>
        </thead>
        <tbody>
        <g:each in="${providerInstanceList}" status="i" var="providerInstance">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                <td><g:link action="show" id="${providerInstance.id}">${fieldValue(bean: providerInstance, field: "documents")}</g:link></td>

                <td>${fieldValue(bean: providerInstance, field: "firstName")}</td>

                <td>${fieldValue(bean: providerInstance, field: "lastName")}</td>

                <td>${fieldValue(bean: providerInstance, field: "patients")}</td>

                <td>${fieldValue(bean: providerInstance, field: "userName")}</td>

            </tr>
        </g:each>
        </tbody>
    </table>
    <div class="pagination">
        <g:paginate total="${providerInstanceCount ?: 0}" />
    </div>
</div>
</body>
</html>
