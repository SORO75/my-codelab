package de.kipf.shop.personen.ui;

import static org.apache.struts.actions.ActionDispatcher.MAPPING_FLAVOR;
import static de.kipf.shop.util.EjbConstants.BEGIN;
import static de.kipf.shop.util.EjbConstants.END;
import static de.kipf.shop.util.WebConstants.POST;
import static de.kipf.shop.util.WebConstants.FWD_START;
import static de.kipf.shop.util.WebConstants.FWD_CANCEL;
import static de.kipf.shop.util.WebConstants.FWD_UPDATE;
import static de.kipf.shop.util.WebConstants.LOGIN_NAME;
import static de.kipf.shop.util.WebConstants.REQUEST_PARAM_USER;
import static de.kipf.shop.util.WebConstants.TRUE;
import static de.kipf.shop.util.WebConstants.DATE_FORMAT;
import static de.kipf.shop.util.WebConstants.UPDATE_BENUTZER_FORM;
import static de.kipf.shop.util.EjbUtil.getBenutzerverwaltungLocal;
import static de.kipf.shop.personen.db.Benutzer.Geschlecht;
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

public class BenutzerverwaltungAction extends Action {
	private static final Log LOG = LogFactory
			.getLog(BenutzerverwaltungAction.class);

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

	/**
	 */
	@SuppressWarnings("unchecked")
	public ActionForward find(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (form == null) {
			LOG.error("find: Kein Form-Objekt");
			throw new AssertionError();
		}

		if (DEBUG)
			LOG.debug(BEGIN + "find: " + form);
		
		request = saveLoginInformation(request);

		final FindBenutzerForm findForm = (FindBenutzerForm) form;

		final BenutzerverwaltungLocal bv = getBenutzerverwaltungLocal();

		String target = findForm.getDarstellung();

		final String paramUser = request.getParameter(REQUEST_PARAM_USER);

		if (DEBUG)
			LOG.debug("PARAMETER user=" + paramUser);
		if (paramUser != null) {
			if (DEBUG)
				LOG.debug("Update-Anzeige");
			target = FWD_UPDATE;
			Benutzer benutzer = null;
			try {
				benutzer = bv.findBenutzer(paramUser);
				
				final UpdateBenutzerForm updateForm = new UpdateBenutzerForm();
				
				final String benutzername = benutzer.getBenutzername();
				final String version = Integer.toString(benutzer.getVersion());
				final String anrede = benutzer.getGeschlecht().equals(Geschlecht.m) ? Geschlecht.m.name() : Geschlecht.w.name(); 
				final String email = benutzer.getEmail();
				final String nachname = benutzer.getNachname();
				final String vorname = benutzer.getVorname();
				final String strasse = benutzer.getStrasse();
				final String plz = benutzer.getPlz();
				final String ort = benutzer.getOrt();
				final String newsletter = benutzer.getNewsletter() ? TRUE : null ;
				final String gesperrt = benutzer.getGesperrt() ? TRUE : null ;
				final String[] benutzergruppen = new String[benutzer.getBenutzergruppen().size()];
				int index = 0;
				for (Benutzergruppe bg : benutzer.getBenutzergruppen()) {
					benutzergruppen[index] = bg.getName();
					++index;
				}
				final SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT);
				final String geburtsdatum = df.format(benutzer.getGeburtsdatum());
				final String regdatum = df.format(benutzer.getRegdatum());
				
				updateForm.setBenutzername(benutzername);
				updateForm.setVersion(version);
				updateForm.setGesperrt(gesperrt);
				updateForm.setNachname(nachname);
				updateForm.setVorname(vorname);
				updateForm.setAnrede(anrede);
				updateForm.setStrasse(strasse);
				updateForm.setPlz(plz);
				updateForm.setOrt(ort);
				updateForm.setEmail(email);
				updateForm.setNewsletter(newsletter);
				updateForm.setGeburtsdatum(geburtsdatum);
				updateForm.setRegdatum(regdatum);
				updateForm.setBenutzergruppen(benutzergruppen);
				
				request.getSession().setAttribute(UPDATE_BENUTZER_FORM,updateForm);
			} catch (BenutzerNotFoundException e) {
				target = FWD_CANCEL;
			}
		
		} else {
			if (DEBUG)
				LOG.debug("Listen-Anzeige");
			final ArrayList<Benutzer> benutzerArrayList = (ArrayList<Benutzer>) bv
					.findAllBenutzer();
			Collections.sort(benutzerArrayList);
			findForm.setBenutzer(benutzerArrayList);	
		}

		if (DEBUG)
			LOG.debug(END + "find");

		// Weiterleitung an Target-JSP
		return mapping.findForward(target);
	}

	/**
	 */
	@SuppressWarnings("unchecked")
	public ActionForward findOwnProfile(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (form == null) {
			LOG.error("find: Kein Form-Objekt");
			throw new AssertionError();
		}

		if (DEBUG)
			LOG.debug(BEGIN + "find: " + form);
		
		request = saveLoginInformation(request);

		final BenutzerverwaltungLocal bv = getBenutzerverwaltungLocal();
	
			String target = FWD_UPDATE;
			Benutzer benutzer = null;
			try {
				final String aktuellerBenutzer = (String) request.getSession().getAttribute(LOGIN_NAME);
				benutzer = bv.findBenutzer(aktuellerBenutzer);
				
				final UpdateBenutzerForm updateForm = new UpdateBenutzerForm();
				
				final String benutzername = benutzer.getBenutzername();
				final String version = Integer.toString(benutzer.getVersion());
				final String anrede = benutzer.getGeschlecht().equals(Geschlecht.m) ? Geschlecht.m.name() : Geschlecht.w.name(); 
				final String email = benutzer.getEmail();
				final String nachname = benutzer.getNachname();
				final String vorname = benutzer.getVorname();
				final String strasse = benutzer.getStrasse();
				final String plz = benutzer.getPlz();
				final String ort = benutzer.getOrt();
				final String newsletter = benutzer.getNewsletter() ? TRUE : null ;
				final String gesperrt = benutzer.getGesperrt() ? TRUE : null ;
				final String[] benutzergruppen = new String[benutzer.getBenutzergruppen().size()];
				int index = 0;
				for (Benutzergruppe bg : benutzer.getBenutzergruppen()) {
					benutzergruppen[index] = bg.getName();
					++index;
				}
				final SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT);
				final String geburtsdatum = df.format(benutzer.getGeburtsdatum());
				final String regdatum = df.format(benutzer.getRegdatum());
				
				updateForm.setBenutzername(benutzername);
				updateForm.setVersion(version);
				updateForm.setGesperrt(gesperrt);
				updateForm.setNachname(nachname);
				updateForm.setVorname(vorname);
				updateForm.setAnrede(anrede);
				updateForm.setStrasse(strasse);
				updateForm.setPlz(plz);
				updateForm.setOrt(ort);
				updateForm.setEmail(email);
				updateForm.setNewsletter(newsletter);
				updateForm.setGeburtsdatum(geburtsdatum);
				updateForm.setRegdatum(regdatum);
				updateForm.setBenutzergruppen(benutzergruppen);
				
				request.getSession().setAttribute(UPDATE_BENUTZER_FORM,updateForm);
			} catch (BenutzerNotFoundException e) {
				target = FWD_CANCEL;
			}
		
		
		if (DEBUG)
			LOG.debug(END + "findOwnProfile");

		// Weiterleitung an Target-JSP
		return mapping.findForward(target);
	}

	
	/**
	 */
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (form == null) {
			LOG.error("delete: Kein Form-Objekt");
			throw new AssertionError();
		}

		if (DEBUG)
			LOG.debug(BEGIN + "delete: " + form);
		
		request = saveLoginInformation(request);

		// Request-Parameter ermitteln
		DeleteBenutzerForm deleteForm = (DeleteBenutzerForm) form;
		final String id = deleteForm.getId();
		// Anwendungskern aufrufen
		try {
			final BenutzerverwaltungLocal bv = getBenutzerverwaltungLocal();
			final Benutzer benutzer = bv.findBenutzerMitAuftraege(id);
			bv.deleteBenutzer(benutzer);
		} catch (BenutzerNotFoundException e) {
			throw new DeleteBenutzerException(e);
		} catch (BenutzerDeleteAuftragException e) {
			throw new DeleteBenutzerException(e);
		} catch (Exception e) {
			throw new DeleteBenutzerException(e);
		}

		if (DEBUG)
			LOG.debug(END + "delete");

		// Weiterleitung an Target-JSP
		return mapping.findForward(FWD_START);
	}

	// /**
	// */
	// public ActionForward deleteSingle(ActionMapping mapping,
	// ActionForm form,
	// HttpServletRequest request,
	// HttpServletResponse response)
	// throws Exception {
	// if (DEBUG) LOG.debug(BEGIN + "deleteSingle");
	//
	// // Request-Parameter ermitteln
	// final String indexStr = request.getParameter(REQUEST_PARAM_INDEX);
	// final int index = Integer.parseInt(indexStr);
	//		
	// final FindBenutzerForm findForm = (FindBenutzerForm)
	// request.getSession().getAttribute(FIND_KUNDEN_FORM);
	// final List<Kunde> kunden = findForm.getKunde();
	// final Kunde kunde = kunden.get(index);
	//
	// // Anwendungskern aufrufen
	// final KundenverwaltungLocal kv = getKundenverwaltungLocal();
	// kv.deleteKunde(kunde);
	//
	// if (DEBUG) LOG.debug(END + "deleteSingle");
	//
	// // Weiterleitung an Target-JSP
	// return mapping.findForward(FWD_START);
	// }

	/**
	 */
	public ActionForward create(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (form == null) {
			LOG.error("create: Kein Form-Objekt");
			throw new AssertionError();
		}

		if (DEBUG)
			LOG.debug(BEGIN + "create: " + form);

		// Request-Parameter ermitteln
		final CreateBenutzerForm createForm = (CreateBenutzerForm) form;
		final Benutzer b = new Benutzer();

		if (!(createForm.getPasswort().equals(createForm.getPasswort2())))
			throw new CreateBenutzerException(new PasswordMismatchException());

		final BenutzerverwaltungLocal bv = getBenutzerverwaltungLocal();

		final String benutzername = createForm.getBenutzername();
		final String passwort = createForm.getPasswort();
		final String nachname = createForm.getNachname();
		final String vorname = createForm.getVorname();
		final Geschlecht geschlecht = createForm.getAnrede().equals(
				Geschlecht.m.name()) ? Geschlecht.m : Geschlecht.w;
		final String strasse = createForm.getStrasse();
		final String email = createForm.getEmail();
		Date geburtsdatum = null;

		SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
		try {
			geburtsdatum = dateFormat.parse(createForm.getGeburtsdatum());
		} catch (ParseException e) {
			throw new AssertionError();
		}

		final boolean gesperrt = TRUE
				.equalsIgnoreCase(createForm.getGesperrt()) ? true : false;
		final boolean newsletter = TRUE.equalsIgnoreCase(createForm
				.getNewsletter()) ? true : false;
		final String ort = createForm.getOrt();
		final String plz = createForm.getPlz();

		final Set<Benutzergruppe> benutzergruppen = new HashSet<Benutzergruppe>();
		if (createForm.getBenutzergruppen() != null) {
			for (String bg : createForm.getBenutzergruppen())
				benutzergruppen.add(bv.findBenutzergruppe(bg));
			b.setBenutzergruppen(benutzergruppen);
		}

		b.setBenutzername(benutzername);
		b.setNachname(nachname);
		b.setVorname(vorname);
		b.setGeschlecht(geschlecht);
		b.setStrasse(strasse);
		b.setEmail(email);
		b.setGeburtsdatum(geburtsdatum);
		b.setGesperrt(gesperrt);
		b.setNewsletter(newsletter);
		b.setOrt(ort);
		b.setPlz(plz);
		b.setRegdatum(new Date());

		try {
			bv.setPassword(b, passwort);
			bv.createBenutzer(b);
		} catch (PasswordNotValidException e) {
			throw new CreateBenutzerException(e);
		} catch (BenutzernameNotValidException e) {
			throw new CreateBenutzerException(e);
		} catch (BenutzernameAlreadyExistsException e) {
			throw new CreateBenutzerException(e);
		} catch (Exception e) {
			throw new CreateBenutzerException(e);
		}

		if (DEBUG)
			LOG.debug(END + "create " + b + ", Gruppenanzahl="
					+ b.getBenutzergruppen().size());

		// Weiterleitung an Target-JSP
		return mapping.findForward(FWD_START);
	}

	 /**
	 */
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (form == null) {
			LOG.error("update: Kein Form-Objekt");
			throw new AssertionError();
		}

		if (DEBUG)
			LOG.debug(BEGIN + "update: " + form);
		
		request = saveLoginInformation(request);

		// Wenn es nicht mit POST aufgerufen wurde, so 
		// ist es nicht im Workflow aufgerufen worden!
		if (!request.getMethod().equalsIgnoreCase(POST))
			throw new UpdateBenutzerException(new WorkflowException());
		
//		 Request-Parameter ermitteln
		final UpdateBenutzerForm updateForm = (UpdateBenutzerForm) form;

		if (!(updateForm.getPasswort().equals(updateForm.getPasswort2())))
			throw new UpdateBenutzerException(new PasswordMismatchException());

		final BenutzerverwaltungLocal bv = getBenutzerverwaltungLocal();
		final Benutzer b = bv.findBenutzer(updateForm.getBenutzername());

		final String passwort = updateForm.getPasswort();
		final String nachname = updateForm.getNachname();
		final String vorname = updateForm.getVorname();
		final Geschlecht geschlecht = updateForm.getAnrede().equals(
				Geschlecht.m.name()) ? Geschlecht.m : Geschlecht.w;
		final String strasse = updateForm.getStrasse();
		final String email = updateForm.getEmail();
		final int version = Integer.parseInt(updateForm.getVersion());
		Date geburtsdatum = null;

		SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
		try {
			geburtsdatum = dateFormat.parse(updateForm.getGeburtsdatum());
		} catch (ParseException e) {
			throw new AssertionError();
		}

		boolean gesperrt = TRUE.equalsIgnoreCase(updateForm.getGesperrt()) ? true : false;
		boolean newsletter = TRUE.equalsIgnoreCase(updateForm.getNewsletter()) ? true : false;
		if (request.getParameter("gesperrt") == null) gesperrt = false;
		if (request.getParameter("newsletter") == null) newsletter = false;
		final String ort = updateForm.getOrt();
		final String plz = updateForm.getPlz();

		final Set<Benutzergruppe> benutzergruppen = new HashSet<Benutzergruppe>();

		if (request.getParameter("benutzergruppen") != null) {
			for (String bg : updateForm.getBenutzergruppen())
				benutzergruppen.add(bv.findBenutzergruppe(bg));
		}
		b.setBenutzergruppen(benutzergruppen);
		
		b.setVersion(version);
		b.setNachname(nachname);
		b.setVorname(vorname);
		b.setGeschlecht(geschlecht);
		b.setStrasse(strasse);
		b.setEmail(email);
		b.setGeburtsdatum(geburtsdatum);
		b.setGesperrt(gesperrt);
		b.setNewsletter(newsletter);
		b.setOrt(ort);
		b.setPlz(plz);

		try {
			if (DEBUG) LOG.debug("Objektübergabe: " + b);
			if (passwort.length() > 0)
				bv.setPassword(b, passwort);
			bv.updateBenutzer(b);
		} catch (PasswordNotValidException e) {
			throw new UpdateBenutzerException(e);
		} catch (BenutzerNotFoundException e) {
			throw new UpdateBenutzerException(e);
		} catch (EntityUpdatedException e) {
			throw new UpdateBenutzerException(e);
		} catch (Exception e) {
			throw new UpdateBenutzerException(e);
		}

		if (DEBUG)
			LOG.debug(END + "update");

		// Weiterleitung an Target-JSP
		return mapping.findForward(FWD_START);
	}
}
