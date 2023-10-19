<%@ include file="/WEB-INF/jsp/layout/layout.jspf" %>

<tiles:insert beanName="shop.layout" flush="true">
	<tiles:put name="browserTitle" type="string">
		<fmt:message key="header.title.constant"/>
		<fmt:message key="auftragsverw.liste.header.title"/>
	</tiles:put>

	<tiles:put name="content" type="string">
		<h2><fmt:message key="auftragsverw.liste.heading"/></h2>
		<c:choose>
			<c:when test="${empty auftraegeListe}">
				<p><fmt:message key="auftragsverw.liste.noorders"/></p>
			</c:when>

			<c:otherwise>
				<c:forEach var="auftrag" items="${auftraegeListe}">
					<div id="table">
						<table>
							<tr>
								<th colspan="2"><fmt:message key="auftragsverw.liste.orderheader"/> ${auftrag.id}</th>
							</tr>							
							<tr>
								<th><fmt:message key="auftragsverw.liste.status.timestamp"/></th>
								<th><fmt:message key="auftragsverw.liste.status.description"/></th>
							</tr>
							<c:forEach var="astatus" items="${auftrag.auftragsstati}">
							<tr>
								<td><fmt:formatDate value="${astatus.zeitstempel}" type="both" dateStyle="medium"/></td>
								<td><fmt:message key="auftragsverw.liste.status.${astatus.pk.status.bezeichnung}"/></td>
							</tr>
							</c:forEach>
						</table>
					</div>
				</c:forEach>
			</c:otherwise>
		</c:choose>

	</tiles:put>
</tiles:insert>