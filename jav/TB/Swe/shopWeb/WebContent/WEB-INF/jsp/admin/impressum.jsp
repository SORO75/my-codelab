<%@ include file="/WEB-INF/jsp/layout/layout.jspf" %>

<tiles:insert beanName="shop.layout" flush="true">
	<tiles:put name="browserTitle" type="string">
		<fmt:message key="header.title.constant"/>
		<fmt:message key="impress.header.title"/>
	</tiles:put>
	<tiles:put name="content" type="string">
		<h2>
		   <fmt:message key="impress.heading"/>
		</h2>
		<p> 
		  <fmt:message key="impress.content"/>
		</p>		

	</tiles:put>
</tiles:insert>