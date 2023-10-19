<%@ include file="/WEB-INF/jsp/layout/layout.jspf" %>

<tiles:insert beanName="shop.layout" flush="true">
	<tiles:put name="browserTitle" type="string">
		<fmt:message key="header.title.constant"/>
		<fmt:message key="access-denied.header.title"/>
	</tiles:put>

	<tiles:put name="content" type="string">
		<h2><fmt:message key="access-denied.heading"/></h2>
		<p id="error"><fmt:message key="access-denied.msg"/></p>
	</tiles:put>
</tiles:insert>
