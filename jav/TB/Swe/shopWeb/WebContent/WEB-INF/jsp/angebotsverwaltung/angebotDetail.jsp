<%@ include file="/WEB-INF/jsp/layout/layout.jspf"%>

<tiles:insert beanName="shop.layout" flush="true">
	<tiles:put name="browserTitle" type="string">
		<fmt:message key="header.title.constant" />
		<fmt:message key="angebotdetail.header.title" />
	</tiles:put>

	<tiles:put name="content" type="string">
	<div id="table">
		<table>
			<tr>
				<th colspan="2">${angebotDetailForm.beschreibung}</th>
			</tr>			
			<tr>
				<td><html:img page="/images/${angebotDetailForm.bildUrl}.jpg" altKey="angebotdetail.picture.bike.alter" /></td>
				<td>
					<fmt:message key="angebotdetail.parts" />				
				<br/>
				<br/>
				<c:forEach var="teil" items="${angebotDetailForm.teil}">
					${teil.beschreibung}
					<br/>
				</c:forEach>
				<br/>
					<fmt:message key="angebotdetail.price" />
				<br/>
					<fmt:formatNumber value="${angebotDetailForm.preis}" type="currency" currencyCode="EUR"/>				
				</td>
			</tr>
			<tr>
				<td align="right" colspan="2">
					<html:link action="/AddItemToWarenkorb?mid=${angebotDetailForm.id}&anzahl=1">
					<html:img page="/images/buy.gif" altKey="angebotdetail.picture.buybutton.alter" />
					</html:link>
				</td>
			</tr>
		</table>
	</div>
	</tiles:put>
</tiles:insert>
