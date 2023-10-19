package de.kipf.shop.admin.ui;

import static de.kipf.shop.util.EjbConstants.BEGIN;
import static de.kipf.shop.util.EjbConstants.END;
import static de.kipf.shop.util.WebConstants.FWD_START;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


final public class LogoutAction extends Action {
	private static final Log LOG = LogFactory.getLog(LogoutAction.class);
	private static final boolean DEBUG = LOG.isDebugEnabled();

	@Override
	public ActionForward execute(ActionMapping mapping,
		                         ActionForm form,
		                         HttpServletRequest request,
		                         HttpServletResponse response)
		                 throws Exception {
		if (DEBUG) LOG.debug(BEGIN + "execute");

		HttpSession session = request.getSession();
		session.invalidate();

		if (DEBUG) LOG.debug(END + "execute");

		// Weiterleitung an Target-JSP
		return mapping.findForward(FWD_START);
	}
}