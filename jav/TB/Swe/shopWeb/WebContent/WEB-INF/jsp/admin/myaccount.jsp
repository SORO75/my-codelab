<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib tagdir="/WEB-INF/tags/" prefix="shop" %>

<%@ include file="/WEB-INF/jsp/layout/layout.jspf" %>

<shop:login />
<sql:query var="benutzer">
	SELECT vorname, nachname FROM shop.benutzer WHERE benutzername = ?
	<sql:param value="${loginName}"/>
</sql:query>

<tiles:insert beanName="shop.layout" flush="true">
	<tiles:put name="browserTitle" type="string">
		<fmt:message key="header.title.constant"/>
		<fmt:message key="myaccount.header.title"/>
	</tiles:put>
	<tiles:put name="content" type="string">
		<h2>
		   <fmt:message key="myaccount.heading"/> <c:forEach var="benu" items="${benutzer.rows}">${benu.vorname} ${benu.nachname}</c:forEach>
		</h2>
		<p id="submenue"> 
		  <html:link action="/BenutzerProfile"><fmt:message key="myaccount.submenue.myprofile"/></html:link>
		  <html:link action="/FindAuftraege"><fmt:message key="myaccount.submenue.myorders"/></html:link>
		</p>		

	</tiles:put>
</tiles:insert>