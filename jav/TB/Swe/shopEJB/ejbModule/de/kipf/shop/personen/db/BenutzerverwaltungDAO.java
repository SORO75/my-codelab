package de.kipf.shop.personen.db;

import static de.kipf.shop.util.EjbConstants.BEGIN;
import static de.kipf.shop.util.EjbConstants.END;
import static de.kipf.shop.personen.db.Benutzer.BENUTZER_ALL;
import static de.kipf.shop.personen.db.Benutzer.BENUTZER_BY_NACHNAME;
import static de.kipf.shop.personen.db.Benutzer.BENUTZER_BY_NACHNAME_FETCH_AUFTRAEGE;
import static de.kipf.shop.personen.db.Benutzer.BENUTZER_BY_ID_FETCH_AUFTRAEGE;
import static de.kipf.shop.personen.db.Benutzer.ATTR_BENUTZERNAME;
import static de.kipf.shop.personen.db.Benutzer.ATTR_NACHNAME;
import static de.kipf.shop.personen.db.Benutzergruppe.BENUTZERGRUPPEN_ALL;
import static de.kipf.shop.personen.db.Benutzergruppe.BENUTZERGRUPPE_BY_NAME;
import static de.kipf.shop.personen.db.Benutzergruppe.BENUTZERGRUPPE_BY_NAME_FETCH_BENUTZER;
import static de.kipf.shop.personen.db.Benutzergruppe.BENUTZERGRUPPE_ALL_FETCH_BENUTZER;
import static de.kipf.shop.personen.db.Benutzergruppe.ATTR_NAME;

import de.kipf.shop.util.EntityNotFoundException;
import de.kipf.shop.util.EntityUpdatedException;
import de.kipf.shop.personen.BenutzerDeleteAuftragException;
import de.kipf.shop.personen.BenutzernameAlreadyExistsException;
import de.kipf.shop.personen.BenutzernameNotValidException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * DB-Zugriffsschicht f&uuml;r das Package "de.kipf.shop.personen"
 */
public class BenutzerverwaltungDAO {

	private static BenutzerverwaltungDAO dao = new BenutzerverwaltungDAO();

	private static final Log LOG = LogFactory.getLog(BenutzerverwaltungDAO.class);

	private static final boolean DEBUG = LOG.isDebugEnabled();

	private static final boolean TRACE = LOG.isTraceEnabled();

	private BenutzerverwaltungDAO() {
		super();
	}

	public static BenutzerverwaltungDAO getInstance() {
		return dao;
	}

	/**
	 * @return Alle persistenten Benutzer
	 */
	@SuppressWarnings("unchecked")
	public List<Benutzer> findAllBenutzer(EntityManager em) {
		if (DEBUG)
			LOG.debug(BEGIN + "findAllBenutzer");

		Query query = em.createNamedQuery(BENUTZER_ALL);
		List<Benutzer> benutzer = query.getResultList();
		if (benutzer == null)
			benutzer = new ArrayList<Benutzer>();

		if (DEBUG)
			LOG.debug(END + "findAllBenutzer");
		return benutzer;
	}
	
	/**
	 * @return Alle persistenten Benutzergruppen mit Benutzer
	 */
	@SuppressWarnings("unchecked")
	public List<Benutzergruppe> findAllBenutzergruppenMitBenutzer(EntityManager em) {
		if (DEBUG)
			LOG.debug(BEGIN + "findAllBenutzergruppenMitBenutzer");

		Query query = em.createNamedQuery(BENUTZERGRUPPE_ALL_FETCH_BENUTZER);
		List<Benutzergruppe> benutzergruppen = query.getResultList();
		if (benutzergruppen == null)
			benutzergruppen = new ArrayList<Benutzergruppe>();

		if (DEBUG)
			LOG.debug(END + "findAllBenutzergruppenMitBenutzer");
		return benutzergruppen;
	}
	
	/**
	 * @return Alle persistenten Benutzergruppen
	 */
	@SuppressWarnings("unchecked")
	public List<Benutzergruppe> findAllBenutzergruppen(EntityManager em) {
		if (DEBUG)
			LOG.debug(BEGIN + "findAllBenutzergruppen");

		Query query = em.createNamedQuery(BENUTZERGRUPPEN_ALL);
		List<Benutzergruppe> benutzergruppen = query.getResultList();
		if (benutzergruppen == null)
			benutzergruppen = new ArrayList<Benutzergruppe>();

		if (DEBUG)
			LOG.debug(END + "findAllBenutzergruppen");
		return benutzergruppen;
	}

	/**
	 * Suche nach Benutzer mit gleichem Nachnamen.
	 * 
	 * @param nachname Suchkriterium
	 * @return Liste mit gefundenen Datens&auml;tzen
	 * @throws BenutzerNotFoundException
	 */
	@SuppressWarnings("unchecked")
	public List<Benutzer> findBenutzerByName(EntityManager em, String nachname) throws BenutzerNotFoundException {
		if (DEBUG) LOG.debug(BEGIN + "findBenutzerByNachname: " + nachname);

		final Query query = em.createNamedQuery(BENUTZER_BY_NACHNAME);
		query.setParameter(ATTR_NACHNAME, nachname);
		final List<Benutzer> benutzer = query.getResultList();

		if (benutzer == null || benutzer.isEmpty()) {
			if (DEBUG) LOG.debug(END + "findBenutzerByNachname: keine Datensaetze");
			throw new BenutzerNotFoundException(ATTR_NACHNAME, nachname);
		}

		if (DEBUG) LOG.debug(END + "findBenutzerByNachname: " + benutzer.size() + " Anzahl Datensaetze");

		return benutzer;
	}

	/**
	 * Suche nach Benutzer mit gleichem Nachnamen einschlie&szlich ihrer
	 * Auftr&auml;ge.
	 * 
	 * @param nachname Suchkriterium
	 * @return Liste mit gefundenen Datens&auml;tzen
	 * @throws BenutzerNotFoundException
	 */
	@SuppressWarnings("unchecked")
	public List<Benutzer> findBenutzerMitAuftraegeByName(EntityManager em, String nachname) throws BenutzerNotFoundException {
		if (DEBUG) LOG.debug(BEGIN + "findBenutzerMitAuftraegeByName: " + nachname);

		final Query query = em.createNamedQuery(BENUTZER_BY_NACHNAME_FETCH_AUFTRAEGE);
		query.setParameter(ATTR_NACHNAME, nachname);
		final List<Benutzer> benutzer = query.getResultList();

		if (benutzer == null || benutzer.isEmpty()) {
			if (DEBUG) LOG.debug(END + "findBenutzerMitAuftraegeByName: keine Datensaetze");
			throw new BenutzerNotFoundException(ATTR_NACHNAME, nachname);
		}

		if (DEBUG) LOG.debug(END + "findBenutzerMitAuftraegeByName: " + benutzer.size() + " Anzahl Datensaetze");

		return benutzer;
	}

	/**
	 * Suche nach einer Benutzergruppe mit ihrem Namen.
	 * 
	 * @param name Suchkriterium
	 * @return Benutzergruppenobjekt
	 * @throws BenutzergruppeNotFoundException
	 */
	@SuppressWarnings("unchecked")
	public Benutzergruppe findBenutzergruppeByName(EntityManager em, String name) throws BenutzergruppeNotFoundException {
		if (DEBUG) LOG.debug(BEGIN + "findBenutzergruppeByName: " + name);

		final Query query = em.createNamedQuery(BENUTZERGRUPPE_BY_NAME);
		query.setParameter(ATTR_NAME, name);
		final Benutzergruppe benutzergruppe = (Benutzergruppe) query.getSingleResult();

		if (benutzergruppe == null ) {
			if (DEBUG) LOG.debug(END + "findBenutzergruppeByName: kein Datensatz gefunden");
			throw new BenutzergruppeNotFoundException(ATTR_NAME, name);
		}

		if (DEBUG) LOG.debug(END + "findBenutzergruppeByName");

		return benutzergruppe;
	}

	/**
	 * Suche nach einer Benutzergruppe mit ihrem Namen einschlie&szlich ihrer
	 * Benutzer
	 * 
	 * @param name Suchkriterium
	 * @return Benutzergruppenobjekt
	 * @throws BenutzergruppeNotFoundException
	 */
	@SuppressWarnings("unchecked")
	public Benutzergruppe findBenutzergruppeMitBenutzerByName(EntityManager em, String name) throws BenutzergruppeNotFoundException {
		if (DEBUG) LOG.debug(BEGIN + "findBenutzergruppeByName: " + name);

		final Query query = em.createNamedQuery(BENUTZERGRUPPE_BY_NAME);
		query.setParameter(ATTR_NAME, name);
		final Benutzergruppe benutzergruppe = (Benutzergruppe) query.getSingleResult();

		if (benutzergruppe == null ) {
			if (DEBUG) LOG.debug(END + "findBenutzergruppeByName: kein Datensatz gefunden");
			throw new BenutzergruppeNotFoundException(ATTR_NAME, name);
		}

		if (DEBUG) LOG.debug(END + "findBenutzergruppeByName");

		return benutzergruppe;
	}
	
	/**
	 * Suche nach einem Benutzer mit gegebenem Benutzernamen.
	 * 
	 * @param benutzername Der Benutzername des gesuchten Benutzers
	 * @return Das Benutzerobjekt oder null, wenn kein Benutzer mit dem gegebenen Benutzernamen existiert
	 * @throws BenutzerNotFoundException
	 */
	public Benutzer findBenutzer(EntityManager em, String benutzername) throws BenutzerNotFoundException {
		if (DEBUG) LOG.debug(BEGIN + "findBenutzer: " + benutzername);

		final Benutzer benutzer = em.find(Benutzer.class, benutzername);
		if (benutzer == null) {
			if (DEBUG) LOG.debug(END + "findBenutzer: kein Datensatz");
			throw new BenutzerNotFoundException(ATTR_BENUTZERNAME, benutzername);
		}

		if (DEBUG) LOG.debug(END + "findBenutzer: " + benutzer);
		
		return benutzer;
	}

	/**
	 * Suche nach einem Benutzer mit gegebenem Benutzernamen einschlie&szlich
	 * der Auftr&auml;ge.
	 * 
	 * @param benutzername
	 *            Der Benutzername des gesuchten Benutzers
	 * @return Das Benutzerobjekt oder null, wenn kein Benutzer mit dem
	 *         gegebenen Benutzernamen existiert
	 * @throws KundeNotFoundException
	 */
	public Benutzer findBenutzerMitAuftraege(EntityManager em, String benutzername) throws BenutzerNotFoundException {
		if (DEBUG) LOG.debug(BEGIN + "findBenutzerMitAuftraege: " + benutzername);

		final Query query = em.createNamedQuery(BENUTZER_BY_ID_FETCH_AUFTRAEGE);
		query.setParameter(ATTR_BENUTZERNAME, benutzername);

		Benutzer benutzer = null;
		try {
			benutzer = (Benutzer) query.getSingleResult();
		} catch (NoResultException e) {
			if (DEBUG) LOG.debug(END + "findBenutzerMitAuftraege: kein Datensatz");
			throw new BenutzerNotFoundException(ATTR_BENUTZERNAME, benutzername);
		}

		if (DEBUG) LOG.debug(END + "findBenutzerMitAuftraege: " + benutzer);
		
		return benutzer;
	}

	/**
	 * Eine Menge von Benutzern soll gel&ouml;scht werden.
	 * @param benutzer Zu l&ouml;schende Datens&auml;tze
	 * @throws BenutzerDeleteAuftragException falls parallel ein gleicher 
	 *         Datensatz ge&auml;ndert oder gel&ouml;scht wurde
	 */
	public void deleteBenutzer(EntityManager em, Collection<Benutzer> benutzer) throws BenutzerDeleteAuftragException {
		if (DEBUG) LOG.debug(BEGIN + "deleteBenutzer");

		if (benutzer == null || benutzer.isEmpty()) {
			if (DEBUG) LOG.debug(END + "deleteBenutzer: leere Collection");
			return;
		}

		for (Benutzer b : benutzer) {
			deleteBenutzer(em, b);
		}

		if (DEBUG)
			LOG.debug(END + "deleteKunden");
	}

	/**
	 * Neue Benutzer sollen angelegt werden.
	 * 
	 * @param benutzer Neue Benutzerndaten
	 * @throws BenutzernameNotValidException 
	 *             Falls der Benutzer keinen g&uuml;ltigen Benutername angegeben hat
	 * @throws BenutzernameAlreadyExistsException
	 *             Falls der Benutzername schon existiert
	 */
	// Unnötig! 
//	public Collection<Benutzer> insertBenutzer(EntityManager em, Collection<Benutzer> benutzer) {
//		if (DEBUG) LOG.debug(BEGIN + "insertBenutzer");
//
//		if (benutzer == null || benutzer.isEmpty()) {
//			if (DEBUG) LOG.debug(END + "insertBenutzer: leere Collection");
//			return null;
//		}
//
//		for (Benutzer b : benutzer) {
//			insertBenutzer(em, b);
//		}
//		
//		if (DEBUG) LOG.debug(END + "insertBenutzer");
//		
//		return benutzer;
//		
//	}

	/**
	 * Existierende Benutzerdaten sollen ge&auml;ndert werden. NUR wegen Prog3:
	 * mengenwertige Schnittstelle, weil die komplizierter ist als nur einen
	 * Benutzer zu &auml;ndern.
	 * 
	 * @param benutzer
	 *            Zu &auml;ndernde Benutzerdaten. Der Benutzername muss gesetzt
	 *            sein.
	 * @return Die Menge der aktualisierten Benutzer
	 */
	public Collection<Benutzer> updateBenutzer(EntityManager em, Collection<Benutzer> benutzer) {
		if (DEBUG) LOG.debug(BEGIN + "updateBenutzer");

		if (benutzer == null || benutzer.isEmpty()) {
			if (DEBUG) LOG.debug(END + "updateBenutzer: leere Collection");
			return new ArrayList<Benutzer>(0);
		}

		Collection<Benutzer> aktualisierteBenutzer = new ArrayList<Benutzer>(benutzer.size());
		for (Benutzer b : benutzer) {
			b = updateBenutzer(em, b);
			aktualisierteBenutzer.add(b);
		}

		if (DEBUG) LOG.debug(END + "updateKunden");
		return aktualisierteBenutzer;
	}

	/**
	 * Ein Benutzern soll gel&ouml;scht werden.
	 * 
	 * @param benutzer
	 *            Zu l&ouml;schender Datensatz
	 * @throws BenutzerDeleteAuftragException
	 *             falls parallel ein gleicher Datensatz ge&auml;ndert oder
	 *             gel&ouml;scht wurde
	 */
	public void deleteBenutzer(EntityManager em, Benutzer benutzer)
			throws BenutzerDeleteAuftragException {
		if (DEBUG) LOG.debug(BEGIN + "deleteBenutzer");

		if (benutzer == null) {
			if (DEBUG) LOG.debug(END + "deleteBenutzer: Kein Benutzer übergeben");
			return;
		}

		final Benutzer managedBenutzer = em.find(Benutzer.class, benutzer.getId());

		// Wurde der Benutzer konkurrierend geloescht?
		if (managedBenutzer == null) {
			LOG.warn("Zu loeschender Benutzer " + benutzer + " existiert nicht");
			if (DEBUG) LOG.debug(END + "deleteBenutzer: Zu loeschender Benutzer existiert nicht");
			throw new EntityNotFoundException(benutzer.getId(), benutzer.getClass().getSimpleName());
		}

		// Wurde der Benutzer konkurrierend aktualisiert?
		if (managedBenutzer.getVersion() > benutzer.getVersion()) {
			LOG.warn("Zu loeschender Benutzer " + benutzer + " konkurrierend aktualisiert");
			if (DEBUG) LOG.debug(END + "deleteBenutzer: Zu loeschender Benutzer konkurrierend aktualisiert");
			throw new EntityUpdatedException(benutzer.getId(), benutzer.getClass().getSimpleName());
		}
		
		for(Benutzergruppe bg : managedBenutzer.benutzergruppen) {
			bg.benutzer.remove(managedBenutzer);
			updateBenutzergruppe(em, bg);
		}
		em.remove(managedBenutzer);

		if (DEBUG) LOG.debug(END + "deleteKunden");
	}

	/**
	 * Neuer Benutzer soll angelegt werden.
	 * 
	 * @param benutzer Neue Benutzerndaten
	 * @return Die aktualisierten Benutzer
	 * @throws BenutzernameNotValidException
	 *             Falls der Benutzer keinen g&uuml;ltigen Benutername angegeben
	 *             hat
	 * @throws BenutzernameAlreadyExistsException
	 *             Falls der Benutzername schon existiert
	 */
	public Benutzer insertBenutzer(EntityManager em, Benutzer benutzer) {
		if (DEBUG) LOG.debug(BEGIN + "insertBenutzer");

		if (benutzer == null) {
			if (DEBUG) LOG.debug(END + "insertBenutzer: Kein Benutzer übergeben");
			return null;
		}
		
		if (TRACE) LOG.trace("Neuer persistenter Datensatz: " + benutzer);
		em.persist(benutzer);
		
		if (DEBUG) LOG.debug(END + "insertBenutzer");
		
//		 TODO Warum unveränderte Rückgabe?
		return benutzer;
		
	}

	/**
	 * Existierende Benutzerdaten sollen ge&auml;ndert werden.
	 * 
	 * @param benutzer
	 *            Zu &auml;ndernde Benutzerdaten. Der Benutzername muss gesetzt
	 *            sein.
	 * @return Die aktualisierte Benutzer
	 */
	public Benutzer updateBenutzer(EntityManager em, Benutzer benutzer) {
		if (DEBUG) LOG.debug(BEGIN + "updateBenutzer");

		if (benutzer == null) {
			if (DEBUG) LOG.debug(END + "updateBenutzer: Kein Benutzer übergeben");
			return benutzer;
		}

		// Wurde der Benutzer konkurrierend geloescht?
		// merge() legt bei Bedarf einen NEUEN Datensatz an
		final Benutzer managedBenutzer = em.find(Benutzer.class, benutzer.getId());
		if (managedBenutzer == null) {
			LOG.warn("Zu aktualisierender Benutzer " + benutzer + " existiert nicht");
			if (DEBUG) LOG.debug(END + "updateBenutzer: Zu aktualisierender Benutzer existiert nicht");
			throw new EntityNotFoundException(benutzer.getId(), benutzer.getClass().getSimpleName());
		}
			
		// Wurde der Benutzer konkurrierend aktualisiert?
		if (managedBenutzer.getVersion() > benutzer.getVersion()) {
			LOG.warn("Zu aktualisierender Benutzer " + benutzer + " konkurrierend aktualisiert");
			if (DEBUG) LOG.debug(END + "updateBenutzer: Zu aktualisierender Benutzer konkurrierend aktualisiert");
			throw new EntityUpdatedException(benutzer.getId(), benutzer.getClass().getSimpleName());
		}
			
		benutzer = em.merge(benutzer);

		if (DEBUG) LOG.debug(END + "updateKunden");
		return benutzer;
	}
	
	/**
	 * Existierende Benutzergruppendaten sollen ge&auml;ndert werden.
	 * 
	 * @param benutzergruppe
	 *            Zu &auml;ndernde Benutzergruppendaten.
	 * @return Die aktualisierte Benutzergruppe
	 */
	public Benutzergruppe updateBenutzergruppe(EntityManager em, Benutzergruppe benutzergruppe) {
		if (DEBUG) LOG.debug(BEGIN + "updateBenutzergruppe");

		if (benutzergruppe == null) {
			if (DEBUG) LOG.debug(END + "updateBenutzergruppe: Keine Benutzergruppe übergeben");
			return benutzergruppe;
		}

		// Wurde die Benutzergruppe konkurrierend geloescht?
		// merge() legt bei Bedarf einen NEUEN Datensatz an
		final Benutzergruppe managedBenutzergruppe = em.find(Benutzergruppe.class, benutzergruppe.getId());
		if (managedBenutzergruppe == null) {
			LOG.warn("Zu aktualisierende Benutzergruppe " + benutzergruppe + " existiert nicht");
			if (DEBUG) LOG.debug(END + "updateBenutzergruppe: Zu aktualisierende Benutzergruppe existiert nicht");
			throw new EntityNotFoundException(benutzergruppe.getId(), benutzergruppe.getClass().getSimpleName());
		}
			
		// Wurde der Benutzer konkurrierend aktualisiert?
		if (managedBenutzergruppe.getVersion() > benutzergruppe.getVersion()) {
			LOG.warn("Zu aktualisierende Benutzergruppe " + benutzergruppe + " konkurrierend aktualisiert");
			if (DEBUG) LOG.debug(END + "updateBenutzergruppe: Zu aktualisierende Benutzergruppe konkurrierend aktualisiert");
			throw new EntityUpdatedException(benutzergruppe.getId(), benutzergruppe.getClass().getSimpleName());
		}
			
		benutzergruppe = em.merge(benutzergruppe);

		if (DEBUG) LOG.debug(END + "updateBenutzergruppe");
		return benutzergruppe;
	}


}
