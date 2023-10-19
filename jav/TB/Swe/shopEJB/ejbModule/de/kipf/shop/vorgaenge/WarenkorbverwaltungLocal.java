package de.kipf.shop.vorgaenge;

import static de.kipf.shop.util.EjbConstants.BEAN_NAME_PRAEFIX;
import static de.kipf.shop.util.EjbConstants.BEAN_NAME_SUFFIX_LOCAL;

public interface WarenkorbverwaltungLocal extends Warenkorbverwaltung {
	static final String JNDI_NAME_LOCAL = BEAN_NAME_PRAEFIX
	+ Warenkorbverwaltung.class.getSimpleName() + BEAN_NAME_SUFFIX_LOCAL;
}
