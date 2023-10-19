package de.kipf.shop.personen.ui;

import static de.kipf.shop.util.EjbConstants.BEGIN;
import static de.kipf.shop.util.EjbConstants.END;
import static de.kipf.shop.util.WebConstants.DATE_FORMAT;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.validator.ValidatorForm;

public class UpdateBenutzerForm extends ValidatorForm {
	private static final long serialVersionUID = 5821695470217910766L;
	private static final Log LOG = LogFactory.getLog(UpdateBenutzerForm.class);
    private static final boolean DEBUG = LOG.isDebugEnabled();
    private String version;
    private String benutzername;
    private String passwort;
    private String passwort2;
    private String gesperrt;
    private String anrede;
    private String vorname;
    private String nachname;
    private String strasse;
    private String plz;
    private String ort;
    private String email;
    private String geburtsdatum = DATE_FORMAT;
    private String regdatum;
    private String newsletter;
    private String[] benutzergruppen;
                                         
    public UpdateBenutzerForm() {
        super();
        if (DEBUG) LOG.debug(BEGIN);

        if (DEBUG) LOG.debug(END);
    }

	public String getBenutzername() {
		return benutzername;
	}

	public void setBenutzername(String benutzername) {
		this.benutzername = benutzername;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGeburtsdatum() {
		return geburtsdatum;
	}

	public void setGeburtsdatum(String geburtsdatum) {
		this.geburtsdatum = geburtsdatum;
	}

	public String getGesperrt() {
		return gesperrt;
	}

	public void setGesperrt(String gesperrt) {
		this.gesperrt = gesperrt;
	}

	public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public String getNewsletter() {
		return newsletter;
	}

	public void setNewsletter(String newsletter) {
		this.newsletter = newsletter;
	}

	public String getOrt() {
		return ort;
	}

	public void setOrt(String ort) {
		this.ort = ort;
	}

	public String getPasswort() {
		return passwort;
	}

	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}

	public String getPasswort2() {
		return passwort2;
	}

	public void setPasswort2(String passwort2) {
		this.passwort2 = passwort2;
	}

	public String getPlz() {
		return plz;
	}

	public void setPlz(String plz) {
		this.plz = plz;
	}

	public String getStrasse() {
		return strasse;
	}	
	
	public String getAnrede() {
		return anrede;
	}

	public void setAnrede(String anrede) {
		this.anrede = anrede;
	}

	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String[] getBenutzergruppen() {
		return benutzergruppen;
	}

	public void setBenutzergruppen(String[] benutzergruppen) {
		this.benutzergruppen = benutzergruppen;
	}

	public String getRegdatum() {
		return regdatum;
	}

	public void setRegdatum(String regdatum) {
		this.regdatum = regdatum;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}


//	@Override
//	public String toString() {
//		return benutzer.toString();
//	}
}
