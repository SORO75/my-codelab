<%@ tag display-name="Benutzergruppen als Combobox"
        body-content="empty" %>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>

<sql:query var="bgs">
	SELECT DISTINCT name FROM shop.benutzergruppe ORDER BY name
</sql:query>
<html:select property="benutzergruppen" styleId="benutzergruppen" size="5" multiple="multiple">
	<c:forEach var="bg" items="${bgs.rows}">
		<html:option value="${bg.name}">${bg.name}</html:option>
	</c:forEach>
</html:select> 
