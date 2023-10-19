package de.kipf.shop.personen.ui;

import org.apache.struts.util.ModuleException;

import de.kipf.shop.personen.PasswordNotValidException;
import de.kipf.shop.util.EntityUpdatedException;
import de.kipf.shop.util.WorkflowException;

public class UpdateBenutzerException extends ModuleException {

	private static final long serialVersionUID = -2971606948583210099L;

	public UpdateBenutzerException(PasswordMismatchException e) {
		super("errors.benutzer.update.passwordmismatch");
	}
	
	public UpdateBenutzerException(PasswordNotValidException e) {
		super("errors.benutzer.update.passwordnotvalid", e.getReason());
	}
	
	public UpdateBenutzerException(EntityUpdatedException e) {
		super("errors.benutzer.update.concurrentupdate");
	}
	
	public UpdateBenutzerException(WorkflowException e) {
		super("errors.workflowuse");
	}
	
	public UpdateBenutzerException(Exception e) {
		super("errors.benutzer.update.generalexecption", e.getLocalizedMessage());
	}
	
}
