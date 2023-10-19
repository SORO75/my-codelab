package de.kipf.shop.personen;

import java.io.Serializable;

import javax.ejb.ApplicationException;

import de.kipf.shop.personen.db.Benutzer;

@ApplicationException(rollback=true)
public class BenutzerDeleteAuftragException extends Exception {

	private static final long serialVersionUID = 1893781856758202847L;
	
	private Serializable benutzerId;
	
	private int anzahlAuftraege;
	
	public BenutzerDeleteAuftragException(Benutzer benutzer) {
		super("Benutzer mit ID=" + benutzer.getId() + " kann nicht geloescht werden: " +
			  benutzer.getAuftraege().size() + " Aufträge");
		this.benutzerId = benutzer.getId();
		this.anzahlAuftraege = benutzer.getAuftraege().size();
	}

	public Serializable getBenutzerId() {
		return benutzerId;
	}
	public int getAnzahlAuftraege() {
		return anzahlAuftraege;
	}

}
