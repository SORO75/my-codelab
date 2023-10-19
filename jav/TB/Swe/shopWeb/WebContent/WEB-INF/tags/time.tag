<%@ tag display-name="Aktuelle Uhrzeit" body-content="empty"
	import="java.util.Date, java.text.DateFormat"%>
<%=DateFormat.getTimeInstance().format(new Date())%>
