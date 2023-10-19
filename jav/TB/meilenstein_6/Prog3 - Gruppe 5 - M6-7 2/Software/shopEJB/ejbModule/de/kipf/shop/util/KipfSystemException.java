package de.kipf.shop.util;

public class KipfSystemException extends RuntimeException {

	private static final long serialVersionUID = -1302729731451627451L;

	public static final String KV_IS_NULL = "Das SessionBean fuer Kundenverwaltung ist null";

	public KipfSystemException(String msg) {
		super("Interner Fehler: " + msg);
	}
}
