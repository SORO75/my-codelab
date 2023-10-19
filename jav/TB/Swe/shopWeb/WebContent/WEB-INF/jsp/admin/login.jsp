<%@ include file="/WEB-INF/jsp/layout/layout.jspf" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>

<tiles:insert beanName="shop.layout" flush="true">
	<tiles:put name="browserTitle" type="string">
		<fmt:message key="header.title.constant"/>
		<fmt:message key="login.header.title"/>
	</tiles:put>

	<tiles:put name="content" type="string">
		<h2><fmt:message key="login.heading"/></h2>
		<div id="form">
			<form action="j_security_check" method="POST">
				<table>
					<tr>
						<td><label for="username"><fmt:message key="login.username.prompt"/></label></td>
						<td><input name="j_username" id="username" class="css" size="20" /></td>
					</tr>
					<tr>
						<td><label for="password"><fmt:message key="login.password.prompt"/></label></td>
						<td><input type="password" name="j_password" id="password" class="css" size="20" /></td>
					</tr>
					<tr>
						<td></td>
						<td><html:submit styleClass="css"><fmt:message key="login.submit"/></html:submit></td>
				</table>
				<fmt:message key="login.notice"/><br/>
				<html:link action="/CreateBenutzerForm"><fmt:message key="login.registrationnotice"/></html:link>
			</form>		
		</div>
	</tiles:put>
</tiles:insert>
