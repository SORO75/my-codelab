package de.kipf.shop.vorgaenge;

import static de.kipf.shop.util.EjbConstants.BEAN_NAME_PRAEFIX;
import static de.kipf.shop.util.EjbConstants.BEAN_NAME_SUFFIX;

import java.util.List;

import de.kipf.shop.teile.db.Modell;
import de.kipf.shop.teile.db.ModellNotFoundException;
import de.kipf.shop.vorgaenge.db.Auftrag;
import de.kipf.shop.vorgaenge.db.AuftragNotFoundException;
import de.kipf.shop.vorgaenge.db.Warenkorb;
import de.kipf.shop.vorgaenge.db.WarenkorbPosition;
import de.kipf.shop.vorgaenge.db.WarenkorbPositionNotFoundException;

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
	
	/**
	 * F&uuml;gt einem Warenkorb eine Position hinzu
	 * @param korb
	 * @param modell
	 * @param anzahl
	 * @throws WarenkorbNotFoundException
	 * @throws ModellNotFoundException
	 */
	public void addPositionToWarenkorb(Warenkorb korb, Modell modell, int anzahl) throws WarenkorbNotFoundException, ModellNotFoundException;
	
	/**
	 * Liefert alle Positionen eines Warenkorbes zur&uuml;ck
	 * @param warenkorb
	 * @return Liste von WarenkorbPositionen
	 * @throws WarenkorbNotFoundException
	 */
	public List<WarenkorbPosition> getPositionenVonWarenkorb(Warenkorb warenkorb) throws WarenkorbNotFoundException;
	
	/**
	 * 
	 * @param id
	 * @throws WarenkorbPositionNotFoundException
	 */
	public void deleteWarenkorbPositionById(Long id) throws WarenkorbPositionNotFoundException;
	
	/**
	 * 
	 * @param warenkorb
	 * @return
	 * @throws WarenkorbNotFoundException
	 */
	public float gesamtPreisOfWarenkorb(Warenkorb warenkorb) throws WarenkorbNotFoundException;

	/**
	 * 
	 * @param warenkorb
	 * @param auftrag
	 * @throws WarenkorbNotFoundException
	 * @throws AuftragNotFoundException
	 */
	public void doWarenkorbZuAuftrag(Warenkorb warenkorb, Auftrag auftrag) throws WarenkorbNotFoundException, AuftragNotFoundException;
}
