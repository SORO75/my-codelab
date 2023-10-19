package de.kipf.shop.personen.db;

import static de.kipf.shop.personen.db.Benutzer.ATTR_NACHNAME;

import java.io.Serializable;

public class BenutzerNotFoundException extends Exception {
	private static final long serialVersionUID = -3261107492807164957L;

	private Serializable id;

	private String nachname;

	public BenutzerNotFoundException(Serializable id) {
		super("Keinen Benutzer gefunden");
		this.id = id;
	}
	
	public BenutzerNotFoundException(String schluessel, String wert) {
		super("Keinen Benutzer gefunden mit " + schluessel + "=" + wert);
		if (schluessel.equals(ATTR_NACHNAME))
			this.nachname = wert;
	}

	public Serializable getId() {
		return id;
	}

	public String getNachname() {
		return nachname;
	}

}
