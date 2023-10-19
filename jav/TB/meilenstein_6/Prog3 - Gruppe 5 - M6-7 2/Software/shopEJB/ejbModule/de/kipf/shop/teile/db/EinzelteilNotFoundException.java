package de.kipf.shop.teile.db;

import java.io.Serializable;

public class EinzelteilNotFoundException extends Exception {
	private static final long serialVersionUID = -5886765907199503838L;
	private Serializable id;
	
	public EinzelteilNotFoundException(Serializable id) {
		super("Einzelteil nicht gefunden");
		this.id=id;
	}

	public Serializable getId() {
		return id;
	}
}
