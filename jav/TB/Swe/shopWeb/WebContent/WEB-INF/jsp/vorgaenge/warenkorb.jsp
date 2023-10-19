<%@ include file="/WEB-INF/jsp/layout/layout.jspf"%>

<tiles:insert beanName="shop.layout" flush="true">
	<tiles:put name="browserTitle" type="string">
		<fmt:message key="header.title.constant" />
		<fmt:message key="warenkorb.header.title" />
	</tiles:put>

	<tiles:put name="content" type="string">
		<h2><fmt:message key="warenkorb.heading" /></h2>
		<br />

		<c:set var="warenkorb" value="${warenkorbForm.warenkorb}" />
		<p>
			<fmt:message key="warenkorb.generated" />
			<fmt:formatDate value="${warenkorb.datum}" type="both" dateStyle="full" />
		</p>
		<br />

		<c:choose>
			<c:when test="${empty warenkorbForm.position}">
			<p>
				<fmt:message key="warenkorb.noitems" />
			</p>
			</c:when>

			<c:otherwise>
			<div id="table">
				<table>
					<tr>
						<th><fmt:message key="warenkorb.table.position" /></th>
						<th><fmt:message key="warenkorb.table.desc" /></th>
						<th><fmt:message key="warenkorb.table.amount" /></th>
						<th><fmt:message key="warenkorb.table.singleprice" /></th>
						<th><fmt:message key="warenkorb.table.price" /></th>
						<th><fmt:message key="warenkorb.table.delete" /></th>
					</tr>
					<c:set var="i" value="1" />
					<c:forEach var="position" items="${warenkorbForm.position}">
						<tr>
							<td>${i}</td>
							<c:set var="modell" value="${position.modellId}" />
							<td>${modell.beschreibung}</td>
							<td>${position.anzahl}</td>
							<td><fmt:formatNumber value="${modell.preis}" type="currency" currencyCode="EUR"/></td>
							<td><fmt:formatNumber value="${position.anzahl * modell.preis}" type="currency" currencyCode="EUR"/></td>
							<td><html:link
								action="/DeleteItemOfWarenkorb?mid=${position.id}">
								<html:img page="/images/trashcan.gif" altKey="warenkorb.table.delete.alter" />
							</html:link></td>
						</tr>
					<c:set var="i" value="${i+ 1}" />						
					</c:forEach>
					<tr>
						<th colspan="6" align="right">
							 <fmt:message key="warenkorb.price" />
							 <fmt:formatNumber value="${warenkorbForm.gesamtPreis}" type="currency" currencyCode="EUR"/>
						</th>
					</tr>
				</table>
			</div>
			</c:otherwise>
		</c:choose>

		<c:if test="${not empty warenkorbForm.position}">
		<p>
		<html:link	action="/ResetWarenkorb">
			<fmt:message key="warenkorb.reset" />
		</html:link>
		
		<c:choose>
		  <c:when test="${empty loginName}">
		    <html:link action="/Login?target=warenkorb"><fmt:message key="warenkorb.login" /></html:link>
		  </c:when>
			  <c:otherwise>
				<html:link	action="/ForwardKundeninfo">
					<fmt:message key="warenkorb.buy" />
				</html:link>
		    </c:otherwise>		
		</c:choose>
		</p>
		</c:if>
	</tiles:put>
</tiles:insert>
