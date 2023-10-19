package de.kipf.shop.vorgaenge.db;

import static de.kipf.shop.util.EjbConstants.BEGIN;
import static de.kipf.shop.util.EjbConstants.END;

import static de.kipf.shop.personen.db.Benutzer.ATTR_BENUTZERNAME;
import static de.kipf.shop.vorgaenge.db.Auftrag.AUFTRAEGE_ALL;
import static de.kipf.shop.vorgaenge.db.Auftrag.AUFTRAEGE_BY_BENUTZERNAME;
import static de.kipf.shop.vorgaenge.db.Auftrag.AUFTRAEGE_BY_UNREACHED_STATUS;
import static de.kipf.shop.vorgaenge.db.Auftrag.AUFTRAG_BY_ID;
import static de.kipf.shop.vorgaenge.db.Auftrag.ATTR_ID;
import static de.kipf.shop.vorgaenge.db.Auftragsstatustyp.ATTR_BEZEICHNUNG;
import static de.kipf.shop.vorgaenge.db.Auftragsstatustyp.AUFTRAGSSTATUSTYPEN_ALL;
import static de.kipf.shop.vorgaenge.db.Auftragsstatustyp.AUFTRAGSSTATUSTYP_BY_NAME;

import de.kipf.shop.util.EntityNotFoundException;
import de.kipf.shop.util.EntityUpdatedException;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * DB-Zugriffsschicht f&uuml;r das Package "de.kipf.shop.vorgaenge"
 */
public class AuftragsverwaltungDAO {

	private static AuftragsverwaltungDAO dao = new AuftragsverwaltungDAO();

	private static final Log LOG = LogFactory
			.getLog(AuftragsverwaltungDAO.class);

	private static final boolean DEBUG = LOG.isDebugEnabled();

	private static final boolean TRACE = LOG.isTraceEnabled();

	private AuftragsverwaltungDAO() {
		super();
	}

	public static AuftragsverwaltungDAO getInstance() {
		return dao;
	}

	/**
	 * @return Alle persistenten Auftraege
	 */
	@SuppressWarnings("unchecked")
	public List<Auftrag> findAllAuftraege(EntityManager em) {
		if (DEBUG)
			LOG.debug(BEGIN + "findAllAuftraege");

		Query query = em.createNamedQuery(AUFTRAEGE_ALL);
		List<Auftrag> auftraege = query.getResultList();
		if (auftraege == null)
			auftraege = new ArrayList<Auftrag>();

		if (DEBUG)
			LOG.debug(END + "findAllAuftraege");
		return auftraege;
	}

	/**
	 * @return Alle persistenten Auftragsstatustypen
	 */
	@SuppressWarnings("unchecked")
	public List<Auftragsstatustyp> findAllAuftragsstatustypen(EntityManager em) {
		if (DEBUG)
			LOG.debug(BEGIN + "findAllAuftragsstatustypen");

		Query query = em.createNamedQuery(AUFTRAGSSTATUSTYPEN_ALL);
		List<Auftragsstatustyp> auftragsstatustypen = query.getResultList();
		if (auftragsstatustypen == null)
			auftragsstatustypen = new ArrayList<Auftragsstatustyp>();

		if (DEBUG)
			LOG.debug(END + "findAllAuftragsstatustypen");
		return auftragsstatustypen;
	}

	/**
	 * Suche nach Aufträge vom gleichen Benutzer.
	 * 
	 * @param benutzername
	 *            Suchkriterium
	 * @return Liste mit gefundenen Datens&auml;tzen
	 * @throws AuftragNotFoundException
	 */
	@SuppressWarnings("unchecked")
	public List<Auftrag> findAuftaegeByBenutzername(EntityManager em, String benutzername)
			throws AuftragNotFoundException {
		if (DEBUG)
			LOG.debug(BEGIN + "findAuftaegeByBenutzer: " + benutzername);

		final Query query = em.createNamedQuery(AUFTRAEGE_BY_BENUTZERNAME);
		query.setParameter(ATTR_BENUTZERNAME, benutzername);
		final List<Auftrag> auftraege = query.getResultList();

		if (auftraege == null || auftraege.isEmpty()) {
			if (DEBUG)
				LOG.debug(END + "findAuftaegeByBenutzer: keine Datensaetze");
			throw new AuftragNotFoundException(ATTR_BENUTZERNAME, benutzername);
		}

		if (DEBUG)
			LOG.debug(END + "findAuftaegeByBenutzer: " + auftraege.size()
					+ " Anzahl Datensaetze");

		return auftraege;
	}

	/**
	 * Suche nach Auftrag mit unerreichtem Status
	 * 
	 * @param statusname
	 *            Suchkriterium
	 * @return Liste mit gefundenen Datens&auml;tzen
	 * @throws AuftragNotFoundException
	 */
	@SuppressWarnings("unchecked")
	public List<Auftrag> findAuftraegeByUnreachedStatus(EntityManager em,
			String statusname) throws AuftragNotFoundException {
		if (DEBUG)
			LOG.debug(BEGIN + "findAuftraegeByUnreachedStatus: " + statusname);

		final Query query = em
				.createNamedQuery(AUFTRAEGE_BY_UNREACHED_STATUS);
		query.setParameter(ATTR_BEZEICHNUNG, statusname);
		final List<Auftrag> auftraege = query.getResultList();

		if (auftraege == null || auftraege.isEmpty()) {
			if (DEBUG)
				LOG.debug(END
						+ "findAuftraegeByUnreachedStatus: keine Datensaetze");
			throw new AuftragNotFoundException(ATTR_BEZEICHNUNG, statusname);
		}

		if (DEBUG)
			LOG.debug(END + "findAuftraegeByUnreachedStatus: "
					+ auftraege.size() + " Anzahl Datensaetze");

		return auftraege;
	}

	/**
	 * Suche nach einem Auftragsstatustyp mit dessen Namen.
	 * 
	 * @param name
	 *            Suchkriterium
	 * @return Auftragsstatustyp
	 * @throws AuftragsstatustypNotFoundException
	 */
	@SuppressWarnings("unchecked")
	public Auftragsstatustyp findAuftragsstatustypByName(EntityManager em, String name)
			throws AuftragsstatustypNotFoundException {
		if (DEBUG)
			LOG.debug(BEGIN + "findAuftragsstatustypByName: " + name);

		final Query query = em.createNamedQuery(AUFTRAGSSTATUSTYP_BY_NAME);
		query.setParameter(ATTR_BEZEICHNUNG, name);
		final Auftragsstatustyp auftragsstatustyp = (Auftragsstatustyp) query
				.getSingleResult();

		if (auftragsstatustyp == null) {
			if (DEBUG)
				LOG.debug(END
						+ "findAuftragsstatustypByName: kein Datensatz gefunden");
			throw new AuftragsstatustypNotFoundException(ATTR_BEZEICHNUNG, name);
		}

		if (DEBUG)
			LOG.debug(END + "findAuftragsstatustypByName");

		return auftragsstatustyp;
	}

	/**
	 * Suche nach Auftrag mit der eindeutigen ID
	 * 
	 * @param id
	 *            ID
	 * @return Auftragsobjekt
	 * @throws AuftragNotFoundException
	 */
	@SuppressWarnings("unchecked")
	public Auftrag findAuftragById(EntityManager em,
			Long id) throws AuftragNotFoundException {
		if (DEBUG)
			LOG.debug(BEGIN + "findAuftragById: " + id);

		final Query query = em.createNamedQuery(AUFTRAG_BY_ID);
		query.setParameter(ATTR_ID, id);
		final Auftrag auftrag = (Auftrag) query
				.getSingleResult();

		if (auftrag == null) {
			if (DEBUG)
				LOG.debug(END
						+ "findAuftragById: kein Datensatz gefunden");
			throw new AuftragNotFoundException(id);
		}

		if (DEBUG)
			LOG.debug(END + "findAuftragById");

		return auftrag;
	}

	/**
	 * Ein Auftrag soll gel&ouml;scht werden.
	 * 
	 * @param auftrag
	 *            Zu l&ouml;schender Datensatz
	 * @throws AuftragDeleteAuftragException
	 *             falls parallel ein gleicher Datensatz ge&auml;ndert oder
	 *             gel&ouml;scht wurde
	 */
	public void deleteAuftrag(EntityManager em, Auftrag auftrag)
	throws AuftragDeleteStatusException {
		if (DEBUG)
			LOG.debug(BEGIN + "deleteAuftrag");

		if (auftrag == null) {
			if (DEBUG)
				LOG.debug(END + "deleteAuftrag: Kein Auftrag übergeben");
			return;
		}
		
		final Auftrag managedAuftrag = em.find(Auftrag.class, auftrag
				.getId());

		// Wurde der Auftrag konkurrierend geloescht?
		if (managedAuftrag == null) {
			LOG
					.warn("Zu loeschender Auftrag " + auftrag
							+ " existiert nicht");
			if (DEBUG)
				LOG
						.debug(END
								+ "deleteAuftrag: Zu loeschender Auftrag existiert nicht");
			throw new EntityNotFoundException(auftrag.getId(), auftrag
					.getClass().getSimpleName());
		}

		// Wurde der Auftrag konkurrierend aktualisiert?
		if (managedAuftrag.getVersion() > auftrag.getVersion()) {
			LOG.warn("Zu loeschender Auftrag " + auftrag
					+ " konkurrierend aktualisiert");
			if (DEBUG)
				LOG
						.debug(END
								+ "deleteAuftrag: Zu loeschender Auftrag konkurrierend aktualisiert");
			throw new EntityUpdatedException(auftrag.getId(), auftrag
					.getClass().getSimpleName());
		}

		if (managedAuftrag.auftragsstati.size() > 0) {
			LOG.warn("Zu loeschender Auftrag " + auftrag
					+ " enhält Stati");
			if (DEBUG)
				LOG
						.debug(END
								+ "deleteAuftrag: Zu loeschender Auftrag enhält Stati");
			throw new AuftragDeleteStatusException(auftrag);
		}
		em.remove(managedAuftrag);

		if (DEBUG)
			LOG.debug(END + "deleteAuftrag");
	}

	/**
	 * Neuer Auftrag soll angelegt werden.
	 * 
	 * @param auftrag
	 *            Neue Auftragsdaten
	 * @return Der aktualisierte Auftrag
	 * @throws BenutzernameNotValidException
	 *             Falls der Benutzer keinen g&uuml;ltigen Benutername angegeben
	 *             hat
	 * @throws BenutzernameAlreadyExistsException
	 *             Falls der Benutzername schon existiert
	 */
	public Auftrag insertAuftrag(EntityManager em, Auftrag auftrag) {
		if (DEBUG)
			LOG.debug(BEGIN + "insertAuftrag");

		if (auftrag == null) {
			if (DEBUG)
				LOG.debug(END + "insertAuftrag: Kein Auftrag übergeben");
			return null;
		}

		if (TRACE)
			LOG.trace("Neuer persistenter Datensatz: " + auftrag);
		em.persist(auftrag);

		if (DEBUG)
			LOG.debug(END + "insertAuftrag");

		return auftrag;

	}

	/**
	 * Existierende Auftragsdaten sollen ge&auml;ndert werden.
	 * 
	 * @param auftrag
	 *            Zu &auml;ndernde Auftragsdaten. Die ID muss gesetzt
	 *            sein.
	 * @return Die aktualisierte Auftrag
	 */
	public Auftrag updateAuftrag(EntityManager em, Auftrag auftrag) {
		if (DEBUG)
			LOG.debug(BEGIN + "updateAuftrag");

		if (auftrag == null) {
			if (DEBUG)
				LOG.debug(END + "updateAuftrag: Kein Auftrag übergeben");
			return auftrag;
		}

		// Wurde der Auftrag konkurrierend geloescht?
		// merge() legt bei Bedarf einen NEUEN Datensatz an
		final Auftrag managedAuftrag = em.find(Auftrag.class, auftrag
				.getId());
		if (managedAuftrag == null) {
			LOG.warn("Zu aktualisierender Auftrag " + auftrag
					+ " existiert nicht");
			if (DEBUG)
				LOG.debug(END + "updateAuftrag: Zu aktualisierender Auftrag existiert nicht");
			throw new EntityNotFoundException(auftrag.getId(), auftrag
					.getClass().getSimpleName());
		}

		// Wurde der Auftrag konkurrierend aktualisiert?
		if (managedAuftrag.getVersion() > auftrag.getVersion()) {
			LOG.warn("Zu aktualisierender Auftrag " + auftrag
					+ " konkurrierend aktualisiert");
			if (DEBUG)
				LOG.debug(END + "updateAuftrag: Zu aktualisierender Auftrag konkurrierend aktualisiert");
			throw new EntityUpdatedException(auftrag.getId(), auftrag
					.getClass().getSimpleName());
		}

		auftrag = em.merge(auftrag);

		if (DEBUG)
			LOG.debug(END + "updateKunden");
		return auftrag;
	}

}
