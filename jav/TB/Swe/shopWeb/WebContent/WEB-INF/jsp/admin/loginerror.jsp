<%@ include file="/WEB-INF/jsp/layout/layout.jspf" %>

<tiles:insert beanName="shop.layout" flush="true">
	<tiles:put name="browserTitle" type="string">
		<fmt:message key="header.title.constant"/>
		<fmt:message key="loginerror.header.title"/>
	</tiles:put>

	<tiles:put name="content" type="string">
		<h2><fmt:message key="loginerror.heading"/></h2>
		<p id="error"><fmt:message key="loginerror.msg"/></p>
	</tiles:put>
</tiles:insert>