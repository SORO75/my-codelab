package de.kipf.shop.personen;

import static de.kipf.shop.util.EjbConstants.BEAN_NAME_PRAEFIX;
import static de.kipf.shop.util.EjbConstants.BEAN_NAME_SUFFIX;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import de.kipf.shop.personen.db.Benutzer;
import de.kipf.shop.personen.db.BenutzerNotFoundException;
import de.kipf.shop.personen.db.Benutzergruppe;
import de.kipf.shop.personen.db.BenutzergruppeNotFoundException;

public interface Benutzerverwaltung {
	
	static final String JNDI_NAME = BEAN_NAME_PRAEFIX + Benutzerverwaltung.class.getSimpleName() + BEAN_NAME_SUFFIX;

	String simpleConnectionTest(String str);
	
	/**
	 * @return Alle persistenten Benutzer
	 */
	Collection<Benutzer> findAllBenutzer();
	
	/**
	 * Suche nach Benutzer mit gleichem Nachnamen.
	 * @param nachname Suchkriterium
	 * @return Liste mit gefundenen Datens&auml;tzen
	 * @throws BenutzerNotFoundException 
	 */
	List<Benutzer> findBenutzerByName(String nachname) throws BenutzerNotFoundException;

	/**
	 * Suche nach Benutzer mit gleichem Nachnamen einschlie&szlich ihrer Auftr&auml;ge.
	 * @param nachname Suchkriterium
	 * @return Liste mit gefundenen Datens&auml;tzen
	 * @throws BenutzerNotFoundException 
	 */
	List<Benutzer> findBenutzerMitAuftraegeByName(String nachname) throws BenutzerNotFoundException;
	
	/**
	 * Suche nach einem Benutzer mit gegebenem Benutzernamen.
	 * @param benutzername Der Benutzername des gesuchten Benutzers
	 * @return Das Benutzerobjekt oder null, wenn kein Benutzer mit dem gegebenen Benutzernamen existiert
	 * @throws BenutzerNotFoundException 
	 */
	Benutzer findBenutzer(String benutzername) throws BenutzerNotFoundException;

	/**
	 * Suche nach einem Benutzer mit gegebenem Benutzernamen einschlie&szlich der Auftr&auml;ge.
	 * @param benutzername Der Benutzername des gesuchten Benutzers
	 * @return Das Benutzerobjekt oder null, wenn kein Benutzer mit dem gegebenen Benutzernamen existiert
	 * @throws KundeNotFoundException 
	 */
	Benutzer findBenutzerMitAuftraege(String benutzername) throws BenutzerNotFoundException;
	
	/**
	 * Suche nach einer Benutzergruppe mit gegebenem Benutzergruppennamen.
	 * 
	 * @param benutzergruppenname
	 *            Der Benutzergruppenname der gesuchten Benutzergruppe
	 * @return Das Benutzergruppenobjekt oder null, wenn keine Benutzergruppe mit dem
	 *         gegebenen Benutzergruppennamen existiert
	 * @throws BenutzergruppeNotFoundException
	 */
	Benutzergruppe findBenutzergruppe(String benutzergruppenname) throws BenutzergruppeNotFoundException;
	
	/**
	 * Suche nach einer Benutzergruppe mit gegebenem Benutzergruppennamen inkl derren Benutzer.
	 * 
	 * @param benutzergruppenname
	 *            Der Benutzergruppenname der gesuchten Benutzergruppe
	 * @return Das Benutzergruppenobjekt oder null, wenn keine Benutzergruppe mit dem
	 *         gegebenen Benutzergruppennamen existiert
	 * @throws BenutzergruppeNotFoundException
	 */
	Benutzergruppe findBenutzergruppeMitBentutzer(String benutzergruppenname) throws BenutzergruppeNotFoundException; 
	
	/**
	 * Eine Menge von Benutzern soll gel&ouml;scht werden.
	 * @param benutzer Zu l&ouml;schende Datens&auml;tze
	 * @throws BenutzerDeleteAuftragException falls parallel ein gleicher Datensatz ge&auml;ndert oder
	 * 		gel&ouml;scht wurde
	 */
	void deleteBenutzer(Collection<Benutzer> benutzer)	throws BenutzerDeleteAuftragException;
	
	/**
	 * Neue Benutzer sollen angelegt werden.
	 * @param benutzer Neue Benutzerndaten
	 * @throws BenutzernameNotValidException Falls der Benutzer keinen g&uuml;ltigen Benutername angegeben hat
	 * @throws BenutzernameAlreadyExistsException Falls der Benutzername schon existiert
	 */
	Collection<Benutzer> createBenutzer(Collection<Benutzer> benutzer) throws BenutzernameNotValidException, BenutzernameAlreadyExistsException, BenutzergruppeNotFoundException;

//	/**
//	 * Existierende Benutzerdaten sollen ge&auml;ndert werden.
//	 * @param benutzer Zu &auml;ndernde Benutzerdaten. Der Benutzername muss gesetzt sein.
//	 * @return Die Menge der aktualisierten Benutzer
//	 */
//	Collection<Benutzer> updateBenutzer(Collection<Benutzer> benutzer) throws BenutzerNotFoundException;
	
	/**
	 * Ein Benutzern soll gel&ouml;scht werden.
	 * @param benutzer Zu l&ouml;schender Datensatz
	 * @throws BenutzerDeleteAuftragException falls parallel ein gleicher Datensatz ge&auml;ndert oder
	 * 		gel&ouml;scht wurde
	 */
	void deleteBenutzer(Benutzer benutzer) throws BenutzerDeleteAuftragException;
	
	/**
	 * Neuer Benutzer soll angelegt werden.
	 * @param benutzer Neue Benutzerndaten
	 * @return Die aktualisierten Benutzer
	 * @throws BenutzernameNotValidException Falls der Benutzer keinen g&uuml;ltigen Benutername angegeben hat
	 * @throws BenutzernameAlreadyExistsException Falls der Benutzername schon existiert
	 */
	Benutzer createBenutzer(Benutzer benutzer) throws BenutzernameNotValidException, BenutzernameAlreadyExistsException, BenutzergruppeNotFoundException;

	/**
	 * Existierende Benutzerdaten sollen ge&auml;ndert werden.
	 * @param benutzer Zu &auml;ndernde Benutzerdaten. Der Benutzername muss gesetzt sein.
	 * @return Die aktualisierte Benutzer
	 */
	Benutzer updateBenutzer(Benutzer benutzer) throws BenutzerNotFoundException;
	
	/**
	 * Aktualisierung des Passworts eines Benutzers
	 * @param benutzer Benutzerobjekt
	 * @param password neues, noch unverschl&uuml;sseltes Passwort
	 * @return Der aktualisierte Benutzer
	 */
	Benutzer setPassword(Benutzer benutzer, String password) throws PasswordNotValidException;

	/**
	 * Vergleich des verschluesselten Benutzer-Passwortes mit einem anderen
	 * Passwort im Klartext.
	 * @param benutzer Benutzerobjekt
	 * @param password Das unverschl&uuml;sselte Passwort im Klartext
	 * @return true, falls das unverschl&uuml;sselte Passwort die gleiche
	 *         Codierung ergibt; false ansonsten.
	 */
	boolean checkPassword(Benutzer benutzer, String password);
	
	/**
	 * Hinzuf&uuml;gen einer Benutzergruppe zu einem Benutzer
	 * 
	 * @param Benutzername eines Benutzers
	 * @param Set von Benutzergruppen
	 * @return Der aktualisierte Benutzer
	 */
	Benutzer addBenutzergruppen(String benutzername, Benutzergruppe benutzergruppe) throws BenutzerNotFoundException;
	
	/**
	 * Aktualisierung der Benutzergruppe eines Benutzers
	 * 
	 * @param benutzername
	 *            Benutzername eines Benutzers
	 * @param Collection
	 *            von Benutzergruppen
	 * @return Der aktualisierte Benutzer
	 */
	Benutzer setBenutzergruppen(String benutzername, Set<Benutzergruppe> benutzergruppen) throws BenutzerNotFoundException; 
	
}
