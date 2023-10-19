<%@ include file="/WEB-INF/jsp/layout/layout.jspf" %>

<tiles:insert beanName="shop.layout" flush="true">
	<tiles:put name="browserTitle" type="string">
		<fmt:message key="header.title.constant"/>
		<fmt:message key="benutzerverw.liste.header.title"/>
	</tiles:put>

	<tiles:put name="content" type="string">
		<h2><fmt:message key="benutzerverw.liste.heading"/></h2>
		<c:choose>
			<c:when test="${empty findBenutzerForm.benutzer}">
				<fmt:message key="keineBenutzer"/>
			</c:when>

			<c:otherwise>
				<div id="table">
					<table>
						<tr>
							<th><fmt:message key="benutzerverw.liste.username"/></th>
							<th><fmt:message key="benutzerverw.liste.surname"/></th>
							<th><fmt:message key="benutzerverw.liste.firstname"/></th>
							<th><fmt:message key="benutzerverw.liste.email"/></th>
							<th><fmt:message key="benutzerverw.liste.regtimestamp"/></th>
							<th><fmt:message key="benutzerverw.liste.dateofbirth"/></th>
							<th></th>
						</tr>
						<c:forEach var="benutzer" items="${findBenutzerForm.benutzer}">
						<tr>
							<td>${benutzer.id}</td>
							<td>${benutzer.nachname}</td>
							<td>${benutzer.vorname}</td>
							<td><a href="mailto:${benutzer.email}">${benutzer.email}</a></td>
							<td><fmt:formatDate value="${benutzer.regdatum}" type="both" dateStyle="medium"/></td>
							<td><fmt:formatDate value="${benutzer.geburtsdatum}" type="date" dateStyle="medium"/></td>
							<td>
								<html:link action="/FindBenutzer?user=${benutzer.id}"><html:img page="/images/edit.png" border="0" altKey="edit.alt"/></html:link>
								<html:link action="/DeleteBenutzer?id=${benutzer.id}"><html:img page="/images/del.png" border="0" altKey="delete.alt"/></html:link>
							</td>
						</tr>
						</c:forEach>
					</table>
				</div>
			</c:otherwise>
		</c:choose>

	</tiles:put>
</tiles:insert>