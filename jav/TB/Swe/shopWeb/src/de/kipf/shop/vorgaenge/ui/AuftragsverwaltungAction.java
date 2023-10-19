package de.kipf.shop.vorgaenge.ui;

import static org.apache.struts.actions.ActionDispatcher.MAPPING_FLAVOR;
import static de.kipf.shop.util.EjbConstants.BEGIN;
import static de.kipf.shop.util.EjbConstants.END;
import static de.kipf.shop.util.WebConstants.POST;
import static de.kipf.shop.util.WebConstants.FWD_START;
import static de.kipf.shop.util.WebConstants.FWD_CANCEL;
import static de.kipf.shop.util.WebConstants.FWD_UPDATE;
import static de.kipf.shop.util.WebConstants.FWD_LISTE;
import static de.kipf.shop.util.WebConstants.LOGIN_NAME;
import static de.kipf.shop.util.WebConstants.REQUEST_PARAM_USER;
import static de.kipf.shop.util.WebConstants.TRUE;
import static de.kipf.shop.util.WebConstants.DATE_FORMAT;
import static de.kipf.shop.util.WebConstants.AUFTRAEGE_LIST;
import static de.kipf.shop.util.EjbUtil.getAuftragsverwaltungLocal;
import static de.kipf.shop.admin.ui.LoginAction.saveLoginInformation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.ActionDispatcher;

import de.kipf.shop.personen.BenutzerDeleteAuftragException;
import de.kipf.shop.personen.BenutzernameAlreadyExistsException;
import de.kipf.shop.personen.BenutzernameNotValidException;
import de.kipf.shop.personen.BenutzerverwaltungLocal;
import de.kipf.shop.personen.PasswordNotValidException;
import de.kipf.shop.personen.db.Benutzer;
import de.kipf.shop.personen.db.BenutzerNotFoundException;
import de.kipf.shop.personen.db.Benutzergruppe;
import de.kipf.shop.util.EntityUpdatedException;
import de.kipf.shop.util.WorkflowException;
import de.kipf.shop.vorgaenge.AuftragsverwaltungLocal;
import de.kipf.shop.vorgaenge.db.Auftrag;
import de.kipf.shop.vorgaenge.db.AuftragNotFoundException;

public class AuftragsverwaltungAction extends Action {
	private static final Log LOG = LogFactory
			.getLog(AuftragsverwaltungAction.class);

	private static final boolean DEBUG = LOG.isDebugEnabled();

	private final ActionDispatcher dispatcher = new ActionDispatcher(this,
			MAPPING_FLAVOR);

	/**
	 */
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return dispatcher.execute(mapping, form, request, response);
	}

	/**
	 */
	public ActionForward cancelled(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (form == null) {
			LOG.error("cancelled: Kein Form-Objekt");
			throw new AssertionError();
		}

		if (DEBUG)
			LOG.debug(BEGIN + "cancelled");
		form.reset(mapping, request);
		if (DEBUG)
			LOG.debug(END + "cancelled");

		return mapping.findForward(FWD_CANCEL); // siehe struts-config.xml
	}

//	/**
//	 */
//	@SuppressWarnings("unchecked")
//	public ActionForward find(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response)
//			throws Exception {
//		if (form == null) {
//			LOG.error("find: Kein Form-Objekt");
//			throw new AssertionError();
//		}
//
//		if (DEBUG)
//			LOG.debug(BEGIN + "find: " + form);
//		
//		request = saveLoginInformation(request);
//
//		final FindBenutzerForm findForm = (FindBenutzerForm) form;
//
//		final BenutzerverwaltungLocal bv = getBenutzerverwaltungLocal();
//
//		String target = findForm.getDarstellung();
//
//		final String paramUser = request.getParameter(REQUEST_PARAM_USER);
//
//		if (DEBUG)
//			LOG.debug("PARAMETER user=" + paramUser);
//		if (paramUser != null) {
//			if (DEBUG)
//				LOG.debug("Update-Anzeige");
//			target = FWD_UPDATE;
//			Benutzer benutzer = null;
//			try {
//				benutzer = bv.findBenutzer(paramUser);
//				
//				final UpdateBenutzerForm updateForm = new UpdateBenutzerForm();
//				
//				final String benutzername = benutzer.getBenutzername();
//				final String version = Integer.toString(benutzer.getVersion());
//				final String anrede = benutzer.getGeschlecht().equals(Geschlecht.m) ? Geschlecht.m.name() : Geschlecht.w.name(); 
//				final String email = benutzer.getEmail();
//				final String nachname = benutzer.getNachname();
//				final String vorname = benutzer.getVorname();
//				final String strasse = benutzer.getStrasse();
//				final String plz = benutzer.getPlz();
//				final String ort = benutzer.getOrt();
//				final String newsletter = benutzer.getNewsletter() ? TRUE : null ;
//				final String gesperrt = benutzer.getGesperrt() ? TRUE : null ;
//				final String[] benutzergruppen = new String[benutzer.getBenutzergruppen().size()];
//				int index = 0;
//				for (Benutzergruppe bg : benutzer.getBenutzergruppen()) {
//					benutzergruppen[index] = bg.getName();
//					++index;
//				}
//				final SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT);
//				final String geburtsdatum = df.format(benutzer.getGeburtsdatum());
//				final String regdatum = df.format(benutzer.getRegdatum());
//				
//				updateForm.setBenutzername(benutzername);
//				updateForm.setVersion(version);
//				updateForm.setGesperrt(gesperrt);
//				updateForm.setNachname(nachname);
//				updateForm.setVorname(vorname);
//				updateForm.setAnrede(anrede);
//				updateForm.setStrasse(strasse);
//				updateForm.setPlz(plz);
//				updateForm.setOrt(ort);
//				updateForm.setEmail(email);
//				updateForm.setNewsletter(newsletter);
//				updateForm.setGeburtsdatum(geburtsdatum);
//				updateForm.setRegdatum(regdatum);
//				updateForm.setBenutzergruppen(benutzergruppen);
//				
//				request.getSession().setAttribute(UPDATE_BENUTZER_FORM,updateForm);
//			} catch (BenutzerNotFoundException e) {
//				target = FWD_CANCEL;
//			}
//		
//		} else {
//			if (DEBUG)
//				LOG.debug("Listen-Anzeige");
//			final ArrayList<Benutzer> benutzerArrayList = (ArrayList<Benutzer>) bv
//					.findAllBenutzer();
//			Collections.sort(benutzerArrayList);
//			findForm.setBenutzer(benutzerArrayList);	
//		}
//
//		if (DEBUG)
//			LOG.debug(END + "find");
//
//		// Weiterleitung an Target-JSP
//		return mapping.findForward(target);
//	}

	/**
	 */
	@SuppressWarnings("unchecked")
	public ActionForward findOwnOrders(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		if (DEBUG)
			LOG.debug(BEGIN + "findOwnAuftraege");
		
		request = saveLoginInformation(request);

		final AuftragsverwaltungLocal av = getAuftragsverwaltungLocal();
	
			String target = FWD_LISTE;
			List<Auftrag> auftraege = null;
			try {
				final String aktuellerBenutzer = (String) request.getSession().getAttribute(LOGIN_NAME);
				auftraege = av.findAuftaegeByBenutzername(aktuellerBenutzer);
										
				request.getSession().setAttribute(AUFTRAEGE_LIST,auftraege);
			} catch (AuftragNotFoundException e) {
				target = FWD_LISTE;
			}
		
		
		if (DEBUG)
			LOG.debug(END + "findOwnAuftraege");

		// Weiterleitung an Target-JSP
		return mapping.findForward(target);
	}

}
