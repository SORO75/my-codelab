package de.kipf.shop.admin.ui;

import static de.kipf.shop.util.EjbConstants.BEGIN;
import static de.kipf.shop.util.EjbConstants.END;
import static de.kipf.shop.util.EjbConstants.ROLLE_ADMIN;
import static de.kipf.shop.util.WebConstants.FWD_START;
import static de.kipf.shop.util.WebConstants.LOGIN_NAME;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

final public class LoginAction extends Action {
	private static final Log LOG = LogFactory.getLog(LoginAction.class);
	private static final boolean DEBUG = LOG.isDebugEnabled();

	@Override
	public ActionForward execute(ActionMapping mapping,
		                         ActionForm form,
		                         HttpServletRequest request,
		                         HttpServletResponse response)
		                 throws Exception {
		if (DEBUG) LOG.debug(BEGIN + "execute");

		request = saveLoginInformation(request);
	
		String target = request.getParameter("target");				
		if (DEBUG) LOG.debug(END + "execute");
		if (!(target == null))
			return mapping.findForward(target);
		return mapping.findForward(FWD_START);
	}
	
	public static HttpServletRequest saveLoginInformation( HttpServletRequest request) {
		final HttpSession session = request.getSession();
		
		session.setAttribute(LOGIN_NAME, request.getRemoteUser());
		session.setAttribute(ROLLE_ADMIN, request.isUserInRole(ROLLE_ADMIN));
		
		return request;
	}
}
