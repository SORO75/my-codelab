package de.kipf.shop.personen.db;

import static de.kipf.shop.personen.db.Benutzergruppe.ATTR_NAME;

import java.io.Serializable;

public class BenutzergruppeNotFoundException extends Exception {

	private static final long serialVersionUID = 2615913351515072991L;

	private Serializable id;

	private String name;

	public BenutzergruppeNotFoundException(Serializable id) {
		super("Keine Benutzergruppe gefunden");
		this.id = id;
	}
	
	public BenutzergruppeNotFoundException(String schluessel, String wert) {
		super("Keine Benutzergruppe gefunden mit " + schluessel + "=" + wert);
		if (schluessel.equals(ATTR_NAME))
			this.name = wert;
	}

	public Serializable getId() {
		return id;
	}

	public String getName() {
		return name;
	}

}
