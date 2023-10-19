package de.kipf.shop.angebotsverwaltung.ui;

import java.util.ArrayList;

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

import de.kipf.shop.teile.Modellverwaltung;
import de.kipf.shop.teile.db.AngebotsModell;
import de.kipf.shop.teile.db.Einzelteil;
import de.kipf.shop.teile.db.Fahrradtyp;
import de.kipf.shop.teile.db.Modell;
import de.kipf.shop.util.EjbNotFoundException;
import de.kipf.shop.util.EjbUtil;

import static de.kipf.shop.util.EjbConstants.BEGIN;
import static de.kipf.shop.util.EjbConstants.END;
import static de.kipf.shop.util.WebConstants.FWD_ANGEBOTE;
import static de.kipf.shop.util.WebConstants.FWD_ANGEBOT_DETAIL;
import static de.kipf.shop.util.WebConstants.FWD_ERGEBNIS;
import static de.kipf.shop.util.WebConstants.FWD_SUCHE;
import static de.kipf.shop.util.WebConstants.SUCHEN_NAME;
import static org.apache.struts.actions.ActionDispatcher.MAPPING_FLAVOR;

public class AngebotsverwaltungServlet extends Action {
	private static final Log LOG = LogFactory
			.getLog(AngebotsverwaltungServlet.class);
	private static final boolean DEBUG = LOG.isDebugEnabled();
	private final ActionDispatcher dispatcher = new ActionDispatcher(this,
			MAPPING_FLAVOR);

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return dispatcher.execute(mapping, form, request, response);
	}

	public ActionForward showAngebote(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			 {
		if (form == null) {
			LOG.error("execute: Kein Form-Objekt");
			throw new AssertionError();
		}
		if (DEBUG)
			LOG.debug(BEGIN + "showAngebote: " + form);

		AngebotForm angebotForm = (AngebotForm) form;
		Modellverwaltung mv;
		try {
			mv = EjbUtil.getModellverwaltungLocal();
			angebotForm.setModell(new ArrayList<AngebotsModell>(mv
					.findAllAngebotsModelle()));
		} catch (EjbNotFoundException e) {

		}		

		if (DEBUG)
			LOG.debug(END + "showAngebote");

		// Weiterleitung an Target-JSP
		return mapping.findForward(FWD_ANGEBOTE);
	}

	public ActionForward showAngebotDetail(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (DEBUG)
			LOG.debug(BEGIN + "showAngebotDetail");
		AngebotDetailForm af = (AngebotDetailForm) form;
		Modellverwaltung mv = EjbUtil.getModellverwaltungLocal();

		Modell modell = mv.findModellById(new Long(af.getId()));

		af.setBeschreibung(modell.getBeschreibung());
		af.setBildUrl(modell.getBildUrl());
		af.setPreis(modell.getPreis());
		af.setTeil(new ArrayList<Einzelteil>(mv
				.findAllEinzelteileOfModell(modell)));

		if (DEBUG)
			LOG.debug(END + "showAngebotDetail");
		return mapping.findForward(FWD_ANGEBOT_DETAIL);
	}

	public ActionForward showSuche(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (form == null) {
			LOG.error("execute: Kein Form-Objekt");
			throw new AssertionError();
		}
		if (DEBUG)
			LOG.debug(BEGIN + "showSuche: " + form);
		SuchForm sf = (SuchForm) form;		
		final HttpSession session = request.getSession();
		session.setAttribute(SUCHEN_NAME, sf);				

		if (DEBUG)
			LOG.debug(END + "showSuche");

		return mapping.findForward(FWD_SUCHE);
	}

	public ActionForward suche(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (form == null) {
			LOG.error("execute: Kein Form-Objekt");
			throw new AssertionError();
		}
		if (DEBUG)
			LOG.debug(BEGIN + "suche");		

		SuchForm sf = (SuchForm) form;
		Modellverwaltung mv = EjbUtil.getModellverwaltungLocal();
		
		Fahrradtyp typ = null;
		if (!(sf.getIndexTyp()==0))
			typ = sf.getFahrradtypen().get(sf.getIndexTyp());
		
		String bezeichnung = null;
		if (!(sf.getBezeichnung()==null || sf.getBezeichnung().length()==0))
			bezeichnung = sf.getBezeichnung();
		
		float maxpreis = 0;
		if (!(sf.getPreis()==null || sf.getPreis().length()==0))
			maxpreis = Float.parseFloat(sf.getPreis());
		
		ArrayList<AngebotsModell> resultModell = new ArrayList<AngebotsModell>(mv.searchModell(typ, bezeichnung, maxpreis));
		sf.setResultModell(resultModell);
		
		//Form zurücksetzen
		sf.setPreis(null);
		sf.setBezeichnung(null);
		
		if (DEBUG)
			LOG.debug(END + "suche");
		return mapping.findForward(FWD_ERGEBNIS);
	}

}
