<%@ tag display-name="Login Infos an Sie Sitzung haengen" body-content="empty"
	import="de.kipf.shop.admin.ui.LoginAction"%>
<% 
	request = LoginAction.saveLoginInformation(request);
%>

