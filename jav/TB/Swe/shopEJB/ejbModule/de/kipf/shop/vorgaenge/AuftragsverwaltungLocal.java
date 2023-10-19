package de.kipf.shop.vorgaenge;

import static de.kipf.shop.util.EjbConstants.BEAN_NAME_PRAEFIX;
import static de.kipf.shop.util.EjbConstants.BEAN_NAME_SUFFIX_LOCAL;

public interface AuftragsverwaltungLocal extends Auftragsverwaltung {
	static final String JNDI_NAME_LOCAL = BEAN_NAME_PRAEFIX + Auftragsverwaltung.class.getSimpleName() + BEAN_NAME_SUFFIX_LOCAL;
}
