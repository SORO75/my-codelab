package de.kipf.shop.personen.ui;

import org.apache.struts.util.ModuleException;

import de.kipf.shop.personen.BenutzerDeleteAuftragException;
import de.kipf.shop.personen.db.BenutzerNotFoundException;

public class DeleteBenutzerException extends ModuleException {
	
	private static final long serialVersionUID = -742768305469708414L;

	public DeleteBenutzerException(BenutzerNotFoundException e) {
		super("errors.benutzer.delete.notfound", e.getId());
	}
	
	public DeleteBenutzerException(BenutzerDeleteAuftragException e) {
		super("errors.benutzer.delete.hasorders", e.getBenutzerId());
	}
	
	public DeleteBenutzerException(Exception e) {
		super("errors.benutzer.delete.generalexecption", e.getLocalizedMessage());
	}

}

