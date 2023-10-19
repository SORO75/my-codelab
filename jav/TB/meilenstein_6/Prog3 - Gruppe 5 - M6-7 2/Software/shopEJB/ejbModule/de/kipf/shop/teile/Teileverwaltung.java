package de.kipf.shop.teile;

import static de.kipf.shop.util.EjbConstants.BEAN_NAME_PRAEFIX;
import static de.kipf.shop.util.EjbConstants.BEAN_NAME_SUFFIX;

import java.util.Collection;

import de.kipf.shop.teile.db.Einzelteil;
import de.kipf.shop.teile.db.EinzelteilNotFoundException;
import de.kipf.shop.teile.db.Teiltyp;
import de.kipf.shop.teile.db.TeiltypNotFoundException;

public interface Teileverwaltung {
	static final String JNDI_NAME = BEAN_NAME_PRAEFIX
			+ Teileverwaltung.class.getSimpleName() + BEAN_NAME_SUFFIX;

	/**
	 * @return Liefert alle vorhandenen Einzelteile zurück
	 */
	public Collection<Einzelteil> findAllEinzelteile();

	/**
	 * Ein neues Einzelteil wird persistent
	 * 
	 * @param einzelteil
	 *            Neues Einzelteil
	 * @param teiltyp
	 *            der Teiltyp dem dieses Einzelteil zugeordnet ist
	 * @return das Neue Einzelteil
	 */
	public Einzelteil insertEinzelteil(Einzelteil einzelteil, Long teiltyp)
			throws TeiltypNotFoundException;

	/**
	 * Sucht ein persistentes Einzelteil
	 * 
	 * @param id
	 *            Id-Nr des Einzelteils
	 * @return das gefundene Einzelteil
	 * @throws EinzelteilNotFoundException
	 *             wird geworfen wenn kein Einzelteil gefunden wurde
	 */
	public Einzelteil findEinzelteil(Long id)
			throws EinzelteilNotFoundException;

	/**
	 * Löscht das übergegene Teil, sofern es in der Datenbank existiert und
	 * nicht konkurriend aktualisiert wurde
	 * 
	 * @param teil
	 *            das zu löschende Einzelteil
	 */
	public void deleteEinzelteil(Einzelteil teil);

	/**
	 * In dieser Methode wird ein persistenter Datensatz aktualisiert
	 * 
	 * @param teil
	 *            das Teil was upgedated wird
	 * @return Das aktualisierte Objekt
	 */
	public Einzelteil updateEinzelteil(Einzelteil teil);
	
	/**
	 * Liefert alle persistenten Teiltypen zurück
	 * @return Collection von Teiltyp
	 */
	public Collection<Teiltyp> findAllTeiltypen();

}
