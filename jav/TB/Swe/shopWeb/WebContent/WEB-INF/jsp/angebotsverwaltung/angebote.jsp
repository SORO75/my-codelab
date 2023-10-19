<%@ include file="/WEB-INF/jsp/layout/layout.jspf"%>

<tiles:insert beanName="shop.layout" flush="true">
	<tiles:put name="browserTitle" type="string">
		<fmt:message key="header.title.constant" />
		<fmt:message key="angebote.header.title" />
	</tiles:put>

	<tiles:put name="content" type="string">
		<c:choose>
			<c:when test="${empty angebotForm.modell}">
				<h2><fmt:message key="angebot.nooffers"/></h2>
			</c:when>

			<c:otherwise> 
				<h2><fmt:message key="angebote.heading"/></h2>
					<c:forEach var="modell" items="${angebotForm.modell}">
					<c:set var="typ" value="${modell.typ}" />
					<div id="table">
						<table>
							<tr>
								<th colspan="2">${typ.radtypbeschreibung}</th>
							</tr>
							<tr>
								<td>${modell.beschreibung}</td>
								<td><fmt:formatNumber value="${modell.preis}" type="currency" currencyCode="EUR"/></td>
							</tr>
							<tr>
								<td align="right" colspan="2">
								<html:link action="/AngebotDetail?id=${modell.id}">
									<fmt:message key="angebote.detail"/>
								</html:link>
								<html:link action="/AddItemToWarenkorb?mid=${modell.id}&anzahl=1">
									<html:img page="/images/buy.gif" altKey="angebote.picture.buybutton.alter" border="0" />
								</html:link>

								</td>
							</tr>
						</table>
						<html:img page="/images/${modell.bildUrl}.jpg" altKey="angebote.picture.bike.alter" />
					</div>
				</c:forEach>
			</c:otherwise>
		</c:choose>

	</tiles:put>
</tiles:insert>
