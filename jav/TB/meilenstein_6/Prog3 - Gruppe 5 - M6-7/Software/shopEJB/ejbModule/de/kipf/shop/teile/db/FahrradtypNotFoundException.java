package de.kipf.shop.teile.db;

public class FahrradtypNotFoundException extends Exception {
	private static final long serialVersionUID = 3139235321890682131L;

	public FahrradtypNotFoundException(String string) {
		super("Fahrradtyp nicht gefunden");
	}
}
