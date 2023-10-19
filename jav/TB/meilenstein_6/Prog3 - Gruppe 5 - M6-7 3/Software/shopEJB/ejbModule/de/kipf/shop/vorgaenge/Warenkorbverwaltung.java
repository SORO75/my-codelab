package de.kipf.shop.vorgaenge;

import static de.kipf.shop.util.EjbConstants.BEAN_NAME_PRAEFIX;
import static de.kipf.shop.util.EjbConstants.BEAN_NAME_SUFFIX;
import de.kipf.shop.vorgaenge.db.Warenkorb;

public interface Warenkorbverwaltung {
	static final String JNDI_NAME = BEAN_NAME_PRAEFIX
			+ Warenkorbverwaltung.class.getSimpleName() + BEAN_NAME_SUFFIX;
	
	/**
	 * F&uuml;gt diesen Warenkorb als Datensatz in die Tabelle Warenkorb
	 * @param warenkorb
	 * @return Der persistente Warenkorb
	 */
	public Warenkorb insertWarenkorb(Warenkorb warenkorb);

	/**
	 * Sucht einen bestimmten Datensatz
	 * @param primarykey
	 * @return
	 * @throws WarenkorbNotFoundException
	 */
	public Warenkorb findWarenkorbById(Long primarykey) throws WarenkorbNotFoundException;
}
