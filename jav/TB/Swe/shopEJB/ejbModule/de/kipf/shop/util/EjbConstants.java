package de.kipf.shop.util;

public interface EjbConstants {
	// JPA
	static final String PERSISTENCE_CONTEXT_SHOP = "shopPersistence";
	static final Long KEINE_ID = Long.valueOf(-1);
	static final int ERSTE_VERSION = 0;

	// Passwort-Verschluesselung
	static final String HASH_ALGORITHM = "SHA-256";
	static final String HASH_ENCODING = "base64";
	static final String HASH_CHARSET = "UTF-8";
	
	// Security
	static final String LOGIN_DOMAIN_KIPF = "kipf.shop";
	static final String ROLLE_MITARBEITER = "Sachbearbeiter";
	static final String ROLLE_ADMIN = "Administrator";
	static final String ROLLE_BENUTZER = "Kunde";

	// Ausgaben fuer Logging
	static final String BEGIN = "BEGIN ";
	static final String END = "END ";
	
	// JNDI-Namen
	static String BEAN_NAME_PRAEFIX = "shop/";
	static String BEAN_NAME_SUFFIX = "Bean/remote";
	static String BEAN_NAME_SUFFIX_LOCAL = "Bean/local";
}