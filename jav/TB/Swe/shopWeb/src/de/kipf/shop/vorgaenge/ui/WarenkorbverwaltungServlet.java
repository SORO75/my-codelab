package de.kipf.shop.vorgaenge.ui;

import static de.kipf.shop.util.EjbConstants.BEGIN;
import static de.kipf.shop.util.EjbConstants.END;
import static de.kipf.shop.util.WebConstants.FWD_WARENKORB;
import static de.kipf.shop.util.WebConstants.FWD_KUNDENINFO;
import static de.kipf.shop.util.WebConstants.FWD_BESTELLUNG;
import static de.kipf.shop.util.WebConstants.WARENKORB_NAME;
import static de.kipf.shop.util.WebConstants.LOGIN_NAME;
import static org.apache.struts.actions.ActionDispatcher.MAPPING_FLAVOR;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.ActionDispatcher;

import de.kipf.shop.personen.Benutzerverwaltung;
import de.kipf.shop.personen.db.Benutzer;
import de.kipf.shop.teile.Modellverwaltung;
import de.kipf.shop.teile.db.ModellNotFoundException;
import de.kipf.shop.util.EjbNotFoundException;
import de.kipf.shop.util.EjbUtil;
import de.kipf.shop.vorgaenge.Auftragsverwaltung;
import de.kipf.shop.vorgaenge.WarenkorbNotFoundException;
import de.kipf.shop.vorgaenge.Warenkorbverwaltung;
import de.kipf.shop.vorgaenge.db.Auftrag;
import de.kipf.shop.vorgaenge.db.Auftragsstatus;
import de.kipf.shop.vorgaenge.db.Warenkorb;
import de.kipf.shop.vorgaenge.db.WarenkorbPosition;

public class WarenkorbverwaltungServlet extends Action {
	private static final Log LOG = LogFactory
			.getLog(WarenkorbverwaltungServlet.class);
	private static final boolean DEBUG = LOG.isDebugEnabled();
	private final ActionDispatcher dispatcher = new ActionDispatcher(this,
			MAPPING_FLAVOR);

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (DEBUG)
			LOG.debug(BEGIN + "execute");

		final HttpSession session = request.getSession();
		WarenkorbForm wf = (WarenkorbForm) session.getAttribute(WARENKORB_NAME);

		if (wf == null) {
			if (DEBUG)
				LOG.debug("execute Warenkorb ist null");
			Warenkorb warenkorb = new Warenkorb();
			Warenkorbverwaltung	wv = EjbUtil.getWarenkorbverwaltungLocal();			
			warenkorb = wv.insertWarenkorb(warenkorb);
			wf = (WarenkorbForm) form;
			wf.setWarenkorb(warenkorb);
			wf.setId(wf.getWarenkorb().getId().intValue());
			session.setAttribute(WARENKORB_NAME, wf);
		}

		if (DEBUG)
			LOG.debug(END + "execute");
		return dispatcher.execute(mapping, form, request, response);
	}

	public ActionForward showWarenkorb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (DEBUG)
			LOG.debug(BEGIN + "showWarenkorb");

		if (DEBUG)
			LOG.debug(END + "showWarenkorb");
		return mapping.findForward(FWD_WARENKORB);
	}

	public ActionForward addItemToWarenkorb(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws AngebotException
			  {
		if (DEBUG)
			LOG.debug(BEGIN + "addItemToWarenkorb");

		final HttpSession session = request.getSession();

		WarenkorbForm wf = (WarenkorbForm) session.getAttribute(WARENKORB_NAME);

		int modellId = Integer.parseInt(request.getParameter("mid"));
		int anzahl = Integer.parseInt(request.getParameter("anzahl"));
		
		try {
			Warenkorbverwaltung wv = EjbUtil.getWarenkorbverwaltungLocal();
			Modellverwaltung mv = EjbUtil.getModellverwaltungLocal();
			wv.addPositionToWarenkorb(wf.getWarenkorb(), mv
					.findModellById(new Long(modellId)), anzahl);
			wf.setPosition(new ArrayList<WarenkorbPosition>(wv
					.getPositionenVonWarenkorb(wf.getWarenkorb())));
			wf.setGesamtPreis(wv.gesamtPreisOfWarenkorb(wf.getWarenkorb()));
		} catch (ModellNotFoundException e) {
			throw new AngebotException(e);
		} catch (WarenkorbNotFoundException e) {
			throw new AngebotException(e);
		} catch (EjbNotFoundException e) {
			throw new AngebotException(e);
		}
		
		
		session.setAttribute(WARENKORB_NAME, wf);
		if (DEBUG)
			LOG.debug(END + "addItemToWarenkorb");
		return mapping.findForward(FWD_WARENKORB);
	}

	public ActionForward deleteItemOfWarenkorb(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (DEBUG)
			LOG.debug(BEGIN + "deleteItemToWarenkorb");
		Warenkorbverwaltung wv = EjbUtil.getWarenkorbverwaltungLocal();

		long id = Long.parseLong(request.getParameter("mid"));
		wv.deleteWarenkorbPositionById(Long.valueOf(id));

		final HttpSession session = request.getSession();
		WarenkorbForm wf = (WarenkorbForm) session.getAttribute(WARENKORB_NAME);
		wf.setPosition(new ArrayList<WarenkorbPosition>(wv
				.getPositionenVonWarenkorb(wf.getWarenkorb())));
		wf.setGesamtPreis(wv.gesamtPreisOfWarenkorb(wf.getWarenkorb()));

		if (DEBUG)
			LOG.debug(END + "deleteItemToWarenkorb");
		return mapping.findForward(FWD_WARENKORB);
	}

	public ActionForward resetWarenkorb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (DEBUG)
			LOG.debug(BEGIN + "resetWarenkorb");
		final HttpSession session = request.getSession();
		WarenkorbForm wf = (WarenkorbForm) session.getAttribute(WARENKORB_NAME);

		// Neuen Warenkorb Persistent machen
		Warenkorb warenkorb = null;
		try {
			Warenkorbverwaltung wv = EjbUtil.getWarenkorbverwaltungLocal();
			warenkorb = new Warenkorb();
			warenkorb = wv.insertWarenkorb(warenkorb);
		} catch (EjbNotFoundException e) {
			throw new AngebotException(e);
		}

		// Warenkorb dem warenkorbForm hinzufügen
		wf.setWarenkorb(warenkorb);
		wf.setAnzahlPositionen(0);
		wf.setPosition(new ArrayList<WarenkorbPosition>());

		if (DEBUG)
			LOG.debug(END + "resetWarenkorb");
		return mapping.findForward(FWD_WARENKORB);
	}

	public ActionForward forwardKundeninfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (DEBUG)
			LOG.debug(BEGIN + "showWarenkorb");

		if (DEBUG)
			LOG.debug(END + "showWarenkorb");
		return mapping.findForward(FWD_KUNDENINFO);
	}

	public ActionForward warenkorbZuAuftrag(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (DEBUG)
			LOG.debug(BEGIN + "showWarenkorb");
		
		Warenkorbverwaltung wv = EjbUtil.getWarenkorbverwaltungLocal();
		Auftragsverwaltung av = EjbUtil.getAuftragsverwaltungLocal();
		Benutzerverwaltung bv = EjbUtil.getBenutzerverwaltungLocal();
		
		Auftrag auftrag = new Auftrag();
		List<Auftragsstatus> auftragstati = new ArrayList<Auftragsstatus>();
		Auftragsstatus status = new Auftragsstatus(auftrag, av
				.findAuftragsstatustyp("received"), new Date());
		auftragstati.add(status);

		final HttpSession session = request.getSession();
		Benutzer benutzer = bv.findBenutzer(session.getAttribute(LOGIN_NAME)
				.toString());

		auftrag.setAuftragsstati(auftragstati);
		auftrag.setBenutzer(benutzer);
		auftrag = av.createAuftrag(auftrag);

		WarenkorbForm wf = (WarenkorbForm) session.getAttribute(WARENKORB_NAME);
		Warenkorb warenkorb = wf.getWarenkorb();
		warenkorb = wv.findWarenkorbById(warenkorb.getId());
		wv.doWarenkorbZuAuftrag(warenkorb, auftrag);

		// Warenkorb zurücksetzen
		warenkorb = new Warenkorb();
		warenkorb = wv.insertWarenkorb(warenkorb);

		// Warenkorb dem warenkorbForm hinzufügen
		wf.setWarenkorb(warenkorb);
		wf.setAnzahlPositionen(0);
		wf.setPosition(new ArrayList<WarenkorbPosition>());

		if (DEBUG)
			LOG.debug(END + "showWarenkorb");
		return mapping.findForward(FWD_BESTELLUNG);
	}
}
