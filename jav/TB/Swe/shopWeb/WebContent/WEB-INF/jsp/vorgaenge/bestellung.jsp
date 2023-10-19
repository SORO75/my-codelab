<%@ include file="/WEB-INF/jsp/layout/layout.jspf"%>

<tiles:insert beanName="shop.layout" flush="true">
	<tiles:put name="browserTitle" type="string">
		<fmt:message key="header.title.constant" />
		<fmt:message key="bestellung.header.title" />
	</tiles:put>

	<tiles:put name="content" type="string">
		<h2><fmt:message key="bestellung.header.title" /></h2>


		<p>
		<fmt:message key="bestellung.body.begin" />		 
			 <fmt:formatNumber value="${warenkorbForm.gesamtPreis}" type="currency" currencyCode="EUR"/>		
		<fmt:message key="bestellung.body.end" />
		<p>
	</tiles:put>
</tiles:insert>
