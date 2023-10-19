package de.kipf.shop.vorgaenge.db;

import static de.kipf.shop.personen.db.Benutzergruppe.ATTR_NAME;

import java.io.Serializable;

public class AuftragsstatustypNotFoundException extends Exception {

	private static final long serialVersionUID = 4210000048405415653L;

	private Serializable id;

	private String name;

	public AuftragsstatustypNotFoundException(Serializable id) {
		super("Keinen Auftragsstatustyp gefunden");
		this.id = id;
	}
	
	public AuftragsstatustypNotFoundException(String schluessel, String wert) {
		super("Keinen Auftragsstatustyp gefunden mit " + schluessel + "=" + wert);
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
