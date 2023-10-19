package de.kipf.shop.util;

import static org.jboss.security.Util.createPasswordHash;

/**
 * In Anlehnung an org.jboss.test.PasswordHasher von Scott Stark
 */
public class PasswordHasher {
	final static String USAGE = "Usage: hashAlgorithm hashEncoding [hashCharset] password";

	public static void main(String[] args) {
		if ((args.length != 3 && args.length != 4) || args[0].startsWith("-h"))
			throw new IllegalStateException(USAGE);

		String hashAlgorithm = args[0];
		String hashEncoding = args[1];
		
		String hashCharset = null;
		String password = null;
		switch (args.length) {
			case 4:
				hashCharset = args[2];
				password = args[3];
				break;
			case 3:
				password = args[2];
				break;
		}

		final String passwordHash = createPasswordHash(hashAlgorithm, hashEncoding,	hashCharset, null, password);
		System.out.println("passwordHash >>>" + passwordHash + "<<<");
	}
}
