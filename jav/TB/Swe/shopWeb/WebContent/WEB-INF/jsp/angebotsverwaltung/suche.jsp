<%@ include file="/WEB-INF/jsp/layout/layout.jspf"%>

<tiles:insert beanName="shop.layout" flush="true">
	<tiles:put name="browserTitle" type="string">
		<fmt:message key="header.title.constant" />
		<fmt:message key="suche.header.title" />
	</tiles:put>

	<tiles:put name="content" type="string">
		<h2><fmt:message key="suche.heading" /></h2>
		<p>
		<fmt:message key="suche.beginning" />
		</p>
		
		<html:javascript formName="suchForm"/>
		
		<p>
		<div id="form">
		<html:form action="/Suche" onsubmit="return validateSuchForm(this);">
			<html:select name="suchForm" property="indexTyp" styleId="fahrradtypen" size="1" styleClass="css">
				<c:forEach var="typ" items="${suchForm.fahrradtypen}">
					<html:option value="${typ.id}">${typ.radtypbeschreibung}</html:option>
				</c:forEach>
			</html:select>
			<fmt:message key="suche.desc" />
			<html:text property="bezeichnung" size="27" styleId="bezeichnung" styleClass="css" />
			<html:errors property="bezeichnung"/>
			<fmt:message key="suche.maxprice" />						
			<html:text property="preis" size="7" styleId="preis" styleClass="css" />
			<html:errors property="preis"/>
			<br />
			<html:submit styleClass="css"><fmt:message key="suche.button.search" /></html:submit>
		</html:form>
		</div>
		</p>
	</tiles:put>
</tiles:insert>
