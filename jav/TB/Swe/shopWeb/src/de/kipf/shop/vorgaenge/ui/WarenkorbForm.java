package de.kipf.shop.vorgaenge.ui;

import static de.kipf.shop.util.EjbConstants.BEGIN;
import static de.kipf.shop.util.EjbConstants.END;

import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;

import de.kipf.shop.vorgaenge.db.Warenkorb;
import de.kipf.shop.vorgaenge.db.WarenkorbPosition;

public class WarenkorbForm extends ActionForm {
	private static final long serialVersionUID = -7528323236053914296L;

	private static final Log LOG = LogFactory.getLog(WarenkorbForm.class);
	private static final boolean DEBUG = LOG.isDebugEnabled();

	private int id = -1;
	private int mid = -1;
	private int anzahlPositionen = 0;
	private float gesamtPreis = 0;
	private Warenkorb warenkorb;
	private ArrayList<WarenkorbPosition> position;

	public WarenkorbForm() {
		super();
		if (DEBUG)
			LOG.debug(BEGIN + "Constructor WarenkorbForm");

		if (DEBUG)
			LOG.debug(END + "Constructor WarenkorbForm");
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Warenkorb getWarenkorb() {
		anzahlPositionen++;
		return warenkorb;
	}

	public void setWarenkorb(Warenkorb warenkorb) {
		this.warenkorb = warenkorb;
	}	
	
	public int getAnzahlPositionen() {		
		return this.anzahlPositionen;
	}
	
	public void setAnzahlPositionen(int anzahl) {
		this.anzahlPositionen = anzahl;
	}
	
	public int getMid() {
		return mid;
	}

	public void setMid(int id) {
		this.mid = id;
	}

	public ArrayList<WarenkorbPosition> getPosition() {
		return position;
	}

	public void setPosition(ArrayList<WarenkorbPosition> position) {
		this.position = position;
	}

	public float getGesamtPreis() {
		return gesamtPreis;
	}

	public void setGesamtPreis(float gesamtPreis) {
		this.gesamtPreis = gesamtPreis;
	}		
	
	@Override
	public String toString() {
		return "id=" + id + " ,mid=" + mid + " ,anzahlpositionen" + anzahlPositionen;
	}
}
