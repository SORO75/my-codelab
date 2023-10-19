<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>

<%@ include file="/WEB-INF/jsp/layout/layout.jspf" %>
<%@ taglib tagdir="/WEB-INF/tags/" prefix="shop" %>

<sql:query var="benutzer">
	SELECT vorname, nachname FROM shop.benutzer WHERE benutzername = ?
	<sql:param value="${loginName}"/>
</sql:query>

<tiles:insert beanName="shop.layout" flush="true">
	<tiles:put name="browserTitle" type="string">
		<fmt:message key="header.title.constant"/>
		<fmt:message key="index.header.title"/>
	</tiles:put>
	<tiles:put name="content" type="string">
		<h2>
		   <fmt:message key="index.heading"/><c:forEach var="benu" items="${benutzer.rows}">, ${benu.vorname} ${benu.nachname}</c:forEach>
		</h2>
		<p> 
		  <fmt:message key="index.content"/>
		  <br/>
		  <img src="images/modell_5.jpg" align="middle"/>
		</p>		

	<shop:angebot />
	</tiles:put>
</tiles:insert>