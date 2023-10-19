package de.kipf.shop.vorgaenge;

import static de.kipf.shop.util.EjbConstants.BEAN_NAME_PRAEFIX;
import static de.kipf.shop.util.EjbConstants.BEAN_NAME_SUFFIX;

import java.util.Collection;
import java.util.List;

import de.kipf.shop.vorgaenge.db.Auftrag;
import de.kipf.shop.vorgaenge.db.AuftragDeleteStatusException;
import de.kipf.shop.vorgaenge.db.AuftragNotFoundException;
import de.kipf.shop.vorgaenge.db.Auftragsstatustyp;
import de.kipf.shop.vorgaenge.db.AuftragsstatustypNotFoundException;

public interface Auftragsverwaltung {
	
	static final String JNDI_NAME = BEAN_NAME_PRAEFIX + Auftragsverwaltung.class.getSimpleName() + BEAN_NAME_SUFFIX;

	/**
	 * @return Alle persistenten Aufträge
	 */
	Collection<Auftrag> findAllAuftraege();
	
	/**
	 * Suche nach allen Aufträgen von einem Benutzer.
	 * @param benutzername Suchkriterium
	 * @return Liste mit gefundenen Datens&auml;tzen
	 * @throws AuftragNotFoundException 
	 */
	List<Auftrag> findAuftaegeByBenutzername(String benutzername) throws AuftragNotFoundException;

	/**
	 * Suche nach Aufträgen die den angegebenen Status noch nicht erreicht haben.
	 * @param auftragname Suchkriterium
	 * @return Liste mit gefundenen Datens&auml;tzen
	 * @throws AuftragNotFoundException 
	 */
	List<Auftrag> findAuftraegeByUnreachedStatus(String statusname) throws AuftragNotFoundException;
	
	/**
	 * Suche nach einem Auftrag mit gegebener ID.
	 * @param id Die ID des gesuchten Auftrags
	 * @return Das Auftragsobjekt oder null, wenn kein Auftrag mit dem gegebener ID existiert
	 * @throws AuftragNotFoundException 
	 */
	Auftrag findAuftragById(Long id) throws AuftragNotFoundException;

	/**
	 * Suche nach einem Auftragsstatustyp mit gegebenem Statusnamen.
	 * 
	 * @param statusname
	 *            Der Name des Status
	 * @return Das Statustypobjekt oder null, wenn kein Auftragsstatustyp mit dem
	 *         gegebenen Namen existiert
	 * @throws AuftragsstatustypNotFoundException
	 */
	Auftragsstatustyp findAuftragsstatustyp(String statusname) throws AuftragsstatustypNotFoundException;
	
	/**
	 * Ein Auftrag soll gel&ouml;scht werden.
	 * @param auftrag Zu l&ouml;schender Datensatz
	 * @throws AuftragDeleteStatusException falls parallel ein gleicher Datensatz ge&auml;ndert oder
	 * 		gel&ouml;scht wurde
	 */
	void deleteAuftrag(Auftrag auftrag) throws AuftragDeleteStatusException;
	
	/**
	 * Neuer Auftrag soll angelegt werden.
	 * @param auftrag Neue Auftragsndaten
	 * @return Die aktualisierten Auftrag
	 */
	Auftrag createAuftrag(Auftrag auftrag) throws AuftragsstatustypNotFoundException;

	/**
	 * Existierende Auftragsdaten sollen ge&auml;ndert werden.
	 * @param auftrag Zu &auml;ndernde Auftragsdaten. Die ID muss gesetzt sein.
	 * @return Der aktualisierte Auftrag
	 */
	Auftrag updateAuftrag(Auftrag auftrag) throws AuftragNotFoundException;
	
}
