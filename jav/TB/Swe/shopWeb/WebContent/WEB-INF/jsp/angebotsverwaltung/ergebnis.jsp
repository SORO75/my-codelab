<%@ include file="/WEB-INF/jsp/layout/layout.jspf"%>

<tiles:insert beanName="shop.layout" flush="true">
	<tiles:put name="browserTitle" type="string">
		<fmt:message key="header.title.constant" />
		<fmt:message key="ergebnis.header.title" />
	</tiles:put>

	<tiles:put name="content" type="string">
		<h2><fmt:message key="ergebnis.heading" /></h2>
		<p>
		<div id="table">
		<table>
		<c:forEach var="modell" items="${suchForm.resultModell}">
			<c:set var="typ" value="${modell.typ}" />

				<tr>
					<th colspan="2">${typ.radtypbeschreibung}</th>

					<td>${modell.beschreibung}</td>
					<td><fmt:formatNumber value="${modell.preis}" type="currency" currencyCode="EUR"/></td>

					<td>
					<html:link action="/AngebotDetail?id=${modell.id}">
						<fmt:message key="angebote.detail"/>
					</html:link>
					</td>
				</tr>
		</c:forEach>	
		</table>	
		</div>							
		</p>
		<c:if test="${empty suchForm.resultModell}">
			<p>
				<fmt:message key="ergebnis.noresult"/>				
			</p>
		</c:if>
	</tiles:put>
</tiles:insert>