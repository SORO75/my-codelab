package de.kipf.shop.personen.ui;

import org.apache.struts.util.ModuleException;

public class BenutzerverwaltungNotFoundException extends ModuleException {

	private static final long serialVersionUID = -857880225278771613L;

	public BenutzerverwaltungNotFoundException() {
		super("errors.benutzerverwaltung.notfound");
	}
}
