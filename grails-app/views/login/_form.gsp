<%@ page import="sca.Login" %>



<div class="fieldcontain ${hasErrors(bean: loginInstance, field: 'users', 'error')} ">
	<label for="users">
		<g:message code="login.users.label" default="Users" />
		
	</label>
	<g:select name="users" from="${sca.User.list()}" multiple="multiple" optionKey="id" size="5" value="${loginInstance?.users*.id}" class="many-to-many"/>

</div>

