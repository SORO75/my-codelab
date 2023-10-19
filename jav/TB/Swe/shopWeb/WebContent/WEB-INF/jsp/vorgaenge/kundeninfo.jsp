<%@ include file="/WEB-INF/jsp/layout/layout.jspf"%>

<tiles:insert beanName="shop.layout" flush="true">
	<tiles:put name="browserTitle" type="string">
		<fmt:message key="header.title.constant" />
		<fmt:message key="kundeninfo.header.title" />
	</tiles:put>

	<tiles:put name="content" type="string">
		<h2><fmt:message key="kundeninfo.headline" /></h2>		
		<p>
		<html:link action="/BenutzerProfile">
			<fmt:message key="kundeninfo.link.data" />
		</html:link>
		</p>

		<p>
		<html:link action="/WarenkorbZuAuftrag">
			<fmt:message key="kundeninfo.link.forward" />			
		</html:link>
		<p>
	</tiles:put>
</tiles:insert>
