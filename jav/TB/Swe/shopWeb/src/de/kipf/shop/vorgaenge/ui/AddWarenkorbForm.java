package de.kipf.shop.vorgaenge.ui;

import org.apache.struts.action.ActionForm;

public class AddWarenkorbForm extends ActionForm {
	private static final long serialVersionUID = -5895491273300005438L;

	private int mid;
	private int anzahl;
	
	public AddWarenkorbForm() {
		super();
	}

	public int getAnzahl() {
		return anzahl;
	}

	public void setAnzahl(int anzahl) {
		this.anzahl = anzahl;
	}

	public int getMid() {
		return mid;
	}

	public void setMid(int mid) {
		this.mid = mid;
	}	
}
