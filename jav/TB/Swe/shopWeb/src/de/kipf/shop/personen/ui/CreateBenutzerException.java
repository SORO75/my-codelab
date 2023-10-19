package de.kipf.shop.personen.ui;

import org.apache.struts.util.ModuleException;

import de.kipf.shop.personen.BenutzernameAlreadyExistsException;
import de.kipf.shop.personen.BenutzernameNotValidException;
import de.kipf.shop.personen.PasswordNotValidException;

public class CreateBenutzerException extends ModuleException {
	private static final long serialVersionUID = 3598450662343654479L;

	public CreateBenutzerException(BenutzernameNotValidException e) {
		super("errors.benutzer.create.usernamenotvalid");
	}
	
	public CreateBenutzerException(BenutzernameAlreadyExistsException e) {
		super("errors.benutzer.create.usernamealreadyexists");
	}
	
	public CreateBenutzerException(PasswordMismatchException e) {
		super("errors.benutzer.create.passwordmismatch");
	}
	
	public CreateBenutzerException(PasswordNotValidException e) {
		super("errors.benutzer.create.passwordnotvalid", e.getReason());
	}
	
	public CreateBenutzerException(Exception e) {
		super("errors.benutzer.create.generalexecption", e.getLocalizedMessage());
	}

}
