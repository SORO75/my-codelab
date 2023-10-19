package de.kipf.shop.vorgaenge.db;

import static de.kipf.shop.personen.db.Benutzer.ATTR_BENUTZERNAME;

import java.io.Serializable;

public class AuftragNotFoundException extends Exception {

	private static final long serialVersionUID = 6248237339270700327L;

	private Serializable id;

	private String benutzername;

	public AuftragNotFoundException(Serializable id) {
		super("Keine Benutzergruppe gefunden");
		this.id = id;
	}
	
	public AuftragNotFoundException(String schluessel, String wert) {
		super("Keine Benutzergruppe gefunden mit " + schluessel + "=" + wert);
		if (schluessel.equals(ATTR_BENUTZERNAME))
			this.benutzername = wert;
	}

	public Serializable getId() {
		return id;
	}

	public String getBenutzername() {
		return benutzername;
	}
}
