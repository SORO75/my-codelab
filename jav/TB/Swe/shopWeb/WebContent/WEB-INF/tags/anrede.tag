<%@ tag display-name="Anrede als Combobox"
        body-content="empty" %>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html:select property="anrede" styleId="anrede">
	<html:option value="m"><fmt:message key="benutzerverw.create.title.male"/></html:option>
	<html:option value="w"><fmt:message key="benutzerverw.create.title.female"/></html:option>
</html:select> 
