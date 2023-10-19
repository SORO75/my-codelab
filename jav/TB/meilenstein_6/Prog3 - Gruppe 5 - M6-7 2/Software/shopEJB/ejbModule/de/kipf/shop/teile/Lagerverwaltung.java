package de.kipf.shop.teile;

import static de.kipf.shop.util.EjbConstants.BEAN_NAME_PRAEFIX;
import static de.kipf.shop.util.EjbConstants.BEAN_NAME_SUFFIX;

import de.kipf.shop.teile.db.Einzelteil;
import de.kipf.shop.teile.db.Lagerbuchung;

public interface Lagerverwaltung {
	static final String JNDI_NAME = BEAN_NAME_PRAEFIX
			+ Lagerverwaltung.class.getSimpleName() + BEAN_NAME_SUFFIX;

	/**
	 * Macht ein Objekt persistent
	 * 
	 * @param lb
	 *            das Objekt welches in die Datenbank eingef&uum;gt wird
	 * @return das Persistente Objekt
	 */
	public Lagerbuchung insertLagerbuchung(Lagerbuchung lb);
	
	/**
	 * Gibt den Lagerbestand eines Einzelteils zurück
	 * @param teil 
	 * @return Lagerbestand
	 */
	public Long lagerBestand(Einzelteil teil);
}
