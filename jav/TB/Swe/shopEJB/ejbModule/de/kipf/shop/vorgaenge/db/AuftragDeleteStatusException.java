package de.kipf.shop.vorgaenge.db;

import java.io.Serializable;

public class AuftragDeleteStatusException extends Exception {

	private static final long serialVersionUID = -4018913815482428514L;

	private Serializable auftragId;
	
	private int anzahlStati;
	
	public AuftragDeleteStatusException(Auftrag auftrag) {
		super("Auftrag mit ID=" + auftrag.getId() + " kann nicht geloescht werden: " +
			  auftrag.getAuftragsstati().size() + " Stati");
		this.auftragId = auftrag.getId();
		this.anzahlStati = auftrag.getAuftragsstati().size();
	}

	public Serializable getAuftragId() {
		return auftragId;
	}
	public int getAnzahlStati() {
		return anzahlStati;
	}

}
