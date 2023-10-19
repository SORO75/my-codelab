package de.kipf.shop.teile;

import static de.kipf.shop.util.EjbConstants.BEAN_NAME_PRAEFIX;
import static de.kipf.shop.util.EjbConstants.BEAN_NAME_SUFFIX_LOCAL;

public interface LagerverwaltungLocal extends Lagerverwaltung {
	static final String JNDI_NAME_LOCAL = BEAN_NAME_PRAEFIX
	+ Lagerverwaltung.class.getSimpleName() + BEAN_NAME_SUFFIX_LOCAL;
}
