package de.kipf.shop.vorgaenge.ui;

import org.apache.struts.util.ModuleException;

import de.kipf.shop.teile.db.ModellNotFoundException;
import de.kipf.shop.util.EjbNotFoundException;
import de.kipf.shop.vorgaenge.WarenkorbNotFoundException;

public class AngebotException extends ModuleException {
	private static final long serialVersionUID = -3452933425353891797L;
	
	public AngebotException(ModellNotFoundException e) {
		super("errors.vorgang.angebot.modellnotfound", e.getMessage());
	}

	public AngebotException(WarenkorbNotFoundException e) {
		super("errors.vorgang.angebot.warenkorbnotfound");
	}

	public AngebotException(EjbNotFoundException e) {
		super("errors.vorgang.angebot.ejbnotfound");
	}
}