package de.kipf.shop.teile;

import static de.kipf.shop.util.EjbConstants.BEAN_NAME_PRAEFIX;
import static de.kipf.shop.util.EjbConstants.BEAN_NAME_SUFFIX;

import java.util.Collection;
import java.util.List;

import de.kipf.shop.teile.db.Einzelteil;
import de.kipf.shop.teile.db.EinzelteilNotFoundException;
import de.kipf.shop.teile.db.Fahrradtyp;
import de.kipf.shop.teile.db.FahrradtypNotFoundException;
import de.kipf.shop.teile.db.Modell;
import de.kipf.shop.teile.db.ModellNotFoundException;

public interface Modellverwaltung {
	static final String JNDI_NAME = BEAN_NAME_PRAEFIX
			+ Modellverwaltung.class.getSimpleName() + BEAN_NAME_SUFFIX;

	/**
	 * Ein Modell wird persistent
	 * 
	 * @param modell
	 *            das Modell
	 * @param fahrradtyp
	 *            der zugeh&ouml;rige Fahrradtyp
	 * @return das persistente Modell
	 * @throws FahrradtypNotFoundException
	 */
	public Modell insertModell(Modell modell, Long fahrradtyp)
			throws FahrradtypNotFoundException;

	/**
	 * Liefert alle persistenten Fahrradtypen zur&uuml;ck
	 * 
	 * @return Collection von Fahrradtyp
	 */
	public Collection<Fahrradtyp> findAllFahrradtypen();

	/**
	 * F&uuml;gt ein Einzelteil einem Modell hinzu
	 * 
	 * @param modell
	 * @param teil
	 * @throws ModellNotFoundException
	 * @throws EinzelteilNotFoundException
	 */
	public void addEinzelteilToModell(Modell modell, Einzelteil teil)
			throws ModellNotFoundException, EinzelteilNotFoundException;
	/**
	 * F&uuml;gt mehrere Einzelteile einem Modell hinzu
	 * @param modell
	 * @param teile Collection vom Typ Teil
	 * @throws ModellNotFoundException
	 * @throws EinzelteilNotFoundException
	 */
	public void addEinzelteilToModell(Modell modell,
			Collection<Einzelteil> teile) throws ModellNotFoundException,
			EinzelteilNotFoundException;

	/**
	 * Liefert alle Persistenten Modelle zur&uuml;ck
	 * @return Collection vom Typ Modell
	 */
	public List<Modell> findAllModelle();
	
	/**
	 * Sucht ein bestimmtes persistentes Einzelteil
	 * @param modellId
	 * @return
	 * @throws ModellNotFoundException
	 */
	public Modell findModellById(Long modellId) throws ModellNotFoundException;
	
	/**
	 * Liefert die Einzelteile eines Modelles
	 * @param modell
	 * @return
	 * @throws ModellNotFoundException
	 */
	public List<Einzelteil> findAllEinzelteileOfModell(Modell modell) throws ModellNotFoundException;
}
