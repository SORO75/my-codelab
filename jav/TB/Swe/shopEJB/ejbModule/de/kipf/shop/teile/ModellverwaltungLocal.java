package de.kipf.shop.teile;

import static de.kipf.shop.util.EjbConstants.BEAN_NAME_PRAEFIX;
import static de.kipf.shop.util.EjbConstants.BEAN_NAME_SUFFIX_LOCAL;

public interface ModellverwaltungLocal extends Modellverwaltung {
	static final String JNDI_NAME_LOCAL = BEAN_NAME_PRAEFIX
	+ Modellverwaltung.class.getSimpleName() + BEAN_NAME_SUFFIX_LOCAL;
}
