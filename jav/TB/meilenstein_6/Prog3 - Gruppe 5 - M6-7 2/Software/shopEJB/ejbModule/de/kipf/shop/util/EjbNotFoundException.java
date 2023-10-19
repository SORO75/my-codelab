package de.kipf.shop.util;

public class EjbNotFoundException extends Exception {

	private static final long serialVersionUID = 2278681639641559302L;

	public EjbNotFoundException(String jndiName) {
		super("Kein SessionBean mit dem Namen " + jndiName + " gefunden");
	}
}
