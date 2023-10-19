package de.kipf.shop.personen;

import static de.kipf.shop.util.EjbConstants.BEAN_NAME_PRAEFIX;
import static de.kipf.shop.util.EjbConstants.BEAN_NAME_SUFFIX_LOCAL;

public interface BenutzerverwaltungLocal extends Benutzerverwaltung {
	
	static final String JNDI_NAME_LOCAL = BEAN_NAME_PRAEFIX + Benutzerverwaltung.class.getSimpleName() + BEAN_NAME_SUFFIX_LOCAL;

}
