package de.kipf.shop.personen;

public class PasswordNotValidException extends Exception {

	private static final long serialVersionUID = -1766503232402360717L;
	
	public static enum ExceptionReason { pwdToShort, pwdBlackList };
	
	public static final String[] PASSWORD_BLACK_LIST = { "test", "hallo", "passwort", "password" };
	
	public static final int MIN_LENGTH = 6;
	
	public PasswordNotValidException(ExceptionReason reason) {
		super(reason.toString());
	}
	
}
