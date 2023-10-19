<%@ tag display-name="Das neueste Angebot auf der Startseite" body-content="empty"
	import="java.util.Collection,
			de.kipf.shop.teile.db.AngebotsModell,
			de.kipf.shop.teile.Modellverwaltung,
			de.kipf.shop.util.EjbUtil"
%>
			
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%
	Modellverwaltung mv = EjbUtil.getModellverwaltungLocal();
	AngebotsModell angebot = mv.findAktuellesAngebot();
	request.setAttribute("angebot", angebot);	
%>


	<h3><fmt:message key="index.angebotdesmonats"/> ${angebot.beschreibung}</h3>
	<p>
		<html:img page="/images/${angebot.bildUrl}.jpg" altKey="angebotdetail.picture.bike.alter" />
		<br />
		<html:link action="/AngebotDetail?id=${angebot.id}">
			<fmt:message key="angebote.detail"/>
		</html:link>
	</p>
