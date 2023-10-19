package de.kipf.shop.vorgaenge;

import static de.kipf.shop.util.EjbConstants.HASH_ALGORITHM;
import static de.kipf.shop.util.EjbConstants.HASH_CHARSET;
import static de.kipf.shop.util.EjbConstants.HASH_ENCODING;
import static de.kipf.shop.util.EjbConstants.BEGIN;
import static de.kipf.shop.util.EjbConstants.END;
import static de.kipf.shop.util.EjbConstants.PERSISTENCE_CONTEXT_SHOP;
import static de.kipf.shop.util.EjbConstants.LOGIN_DOMAIN_KIPF;
import static de.kipf.shop.util.EjbConstants.ROLLE_ADMIN;
import static de.kipf.shop.util.EjbConstants.ROLLE_MITARBEITER;
import static de.kipf.shop.util.EjbConstants.ROLLE_BENUTZER;
import static org.jboss.security.Util.createPasswordHash;

import de.kipf.shop.personen.Benutzerverwaltung;
import de.kipf.shop.personen.BenutzerverwaltungLocal;
import de.kipf.shop.personen.db.Benutzer;
import de.kipf.shop.personen.db.BenutzerNotFoundException;
import de.kipf.shop.personen.db.Benutzergruppe;
import de.kipf.shop.personen.db.BenutzergruppeNotFoundException;
import de.kipf.shop.personen.db.BenutzerverwaltungDAO;
import de.kipf.shop.vorgaenge.db.Auftrag;
import de.kipf.shop.vorgaenge.db.AuftragDeleteStatusException;
import de.kipf.shop.vorgaenge.db.AuftragNotFoundException;
import de.kipf.shop.vorgaenge.db.Auftragsstatus;
import de.kipf.shop.vorgaenge.db.Auftragsstatustyp;
import de.kipf.shop.vorgaenge.db.AuftragsstatustypNotFoundException;
import de.kipf.shop.vorgaenge.db.AuftragsverwaltungDAO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJBContext;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.annotation.security.SecurityDomain;

@Stateless
@Remote(Auftragsverwaltung.class)
@Local(AuftragsverwaltungLocal.class)
@RolesAllowed( { ROLLE_ADMIN, ROLLE_MITARBEITER, ROLLE_BENUTZER })
@SecurityDomain(LOGIN_DOMAIN_KIPF)
public class AuftragsverwaltungBean implements AuftragsverwaltungLocal {

	static final Log LOG = LogFactory.getLog(AuftragsverwaltungBean.class);

	static final boolean DEBUG = LOG.isDebugEnabled();

	static final boolean TRACE = LOG.isTraceEnabled();

	static final boolean ERROR = LOG.isErrorEnabled();

	private static final String AUFTRAGSSTATUS_EINGEGANGEN = "received";

	@PersistenceContext(name = PERSISTENCE_CONTEXT_SHOP)
	EntityManager em;

	// Für einen eventuellen Rollback notwendig!
	// @Resource
	// SessionContext sessionCtx;

	@Resource
	private EJBContext ctx;

	@PreDestroy
	public void preDestroy() {
		if (DEBUG)
			LOG.debug(AuftragsverwaltungBean.class.getName()
					+ " wird geloescht");
	}

	
	/**
	 * @return Alle persistenten Aufträge
	 */
	@RolesAllowed({ROLLE_ADMIN, ROLLE_MITARBEITER})
	public Collection<Auftrag> findAllAuftraege() {
		if (DEBUG)
			LOG.debug(BEGIN + "findAllAuftraege");

		final AuftragsverwaltungDAO dao = AuftragsverwaltungDAO.getInstance();
		final Collection<Auftrag> auftraege = dao.findAllAuftraege(em);

		if (DEBUG)
			LOG.debug(END + "findAllAuftraege");
		return auftraege;
	}
	
	/**
	 * Suche nach allen Aufträgen von einem Benutzer.
	 * @param benutzername Suchkriterium
	 * @return Liste mit gefundenen Datens&auml;tzen
	 * @throws AuftragNotFoundException 
	 */
	@RolesAllowed({ROLLE_ADMIN, ROLLE_MITARBEITER, ROLLE_BENUTZER})
	public List<Auftrag> findAuftaegeByBenutzername(String benutzername) throws AuftragNotFoundException {
		
		if (!(ctx.isCallerInRole(ROLLE_ADMIN) || ctx.isCallerInRole(ROLLE_MITARBEITER))) {
			benutzername = ctx.getCallerPrincipal().toString();
		}
		
		if (DEBUG)
			LOG.debug(BEGIN + "findAuftaegeByBenutzername: " + benutzername);

		final AuftragsverwaltungDAO dao = AuftragsverwaltungDAO.getInstance();
		final List<Auftrag> auftraege = dao.findAuftaegeByBenutzername(em, benutzername);

		if (DEBUG)
			LOG.debug(END + "findAuftaegeByBenutzername: " + benutzername);
		return auftraege;
	}
	
	/**
	 * Suche nach Aufträgen die den angegebenen Status noch nicht erreicht haben.
	 * @param auftragname Suchkriterium
	 * @return Liste mit gefundenen Datens&auml;tzen
	 * @throws AuftragNotFoundException 
	 */
	@RolesAllowed({ROLLE_ADMIN, ROLLE_MITARBEITER})
	public List<Auftrag> findAuftraegeByUnreachedStatus(String statusname) throws AuftragNotFoundException {
		if (DEBUG)
			LOG.debug(BEGIN + "findAuftraegeByUnreachedStatus: " + statusname);

		final AuftragsverwaltungDAO dao = AuftragsverwaltungDAO.getInstance();
		final List<Auftrag> auftraege = dao.findAuftraegeByUnreachedStatus(em, statusname);

		if (DEBUG)
			LOG.debug(END + "findAuftraegeByUnreachedStatus: " + statusname);
		return auftraege;
	}
	
	/**
	 * Suche nach einem Auftrag mit gegebener ID.
	 * @param id Die ID des gesuchten Auftrags
	 * @return Das Auftragsobjekt oder null, wenn kein Auftrag mit dem gegebener ID existiert
	 * @throws AuftragNotFoundException 
	 */
	@RolesAllowed({ROLLE_ADMIN, ROLLE_MITARBEITER})
	public Auftrag findAuftragById(Long id) throws AuftragNotFoundException {
		if (DEBUG)
			LOG.debug(BEGIN + "findAuftragById: " + id);

		final AuftragsverwaltungDAO dao = AuftragsverwaltungDAO.getInstance();
		final Auftrag auftrag = dao.findAuftragById(em, id);

		if (DEBUG)
			LOG.debug(END + "findAuftragById: " + id);
		return auftrag;
	}
	
	/**
	 * Suche nach einem Auftragsstatustyp mit gegebenem Statusnamen.
	 * 
	 * @param statusname
	 *            Der Name des Status
	 * @return Das Statustypobjekt oder null, wenn kein Auftragsstatustyp mit dem
	 *         gegebenen Namen existiert
	 * @throws AuftragsstatustypNotFoundException
	 */
	@RolesAllowed({ROLLE_ADMIN, ROLLE_MITARBEITER, ROLLE_BENUTZER})
	public Auftragsstatustyp findAuftragsstatustyp(String statusname) throws AuftragsstatustypNotFoundException {
		if (DEBUG)
			LOG.debug(BEGIN + "findAuftragsstatustyp: " + statusname);

		final AuftragsverwaltungDAO dao = AuftragsverwaltungDAO.getInstance();
		final Auftragsstatustyp auftragsstatustyp = dao.findAuftragsstatustypByName(em, statusname);

		if (DEBUG)
			LOG.debug(END + "findAuftragsstatustyp: " + statusname);
		return auftragsstatustyp;
	}

	/**
	 * Ein Auftrag soll gel&ouml;scht werden.
	 * @param auftrag Zu l&ouml;schender Datensatz
	 * @throws AuftragDeleteStatusException falls parallel ein gleicher Datensatz ge&auml;ndert oder
	 * 		gel&ouml;scht wurde
	 */
	@RolesAllowed({ROLLE_ADMIN, ROLLE_MITARBEITER})
	public void deleteAuftrag(Auftrag auftrag) throws AuftragDeleteStatusException {
		if (DEBUG)
			LOG.debug(BEGIN + "deleteAuftrag: " + auftrag.getId());

		if (auftrag == null) {
			if (DEBUG)
				LOG.debug(END + "deleteAuftrag: kein Auftrag übergeben");
			return;
		}

		final boolean hasStati = hasStati(auftrag);
		if (hasStati == true) {
			if (DEBUG)
				LOG.debug(END + "deleteAuftrag: "
						+ auftrag.getAuftragsstati().size()
						+ " Stati vorhanden");
			throw new AuftragDeleteStatusException(auftrag);
		}

		final AuftragsverwaltungDAO dao = AuftragsverwaltungDAO.getInstance();
		dao.deleteAuftrag(em, auftrag);

		if (DEBUG)
			LOG.debug(END + "deleteAuftrag: " + auftrag.getId()
					+ " geloescht");
	}
	
	/**
	 * Neuer Auftrag soll angelegt werden.
	 * @param auftrag Neue Auftragsndaten
	 * @return Die aktualisierten Auftrag
	 * @throws AuftragsstatustypNotFoundException 
	 */
	@RolesAllowed({ROLLE_ADMIN, ROLLE_MITARBEITER, ROLLE_BENUTZER})
	public Auftrag createAuftrag(Auftrag auftrag) throws AuftragsstatustypNotFoundException {
		if (DEBUG)
			LOG.debug(BEGIN + "createAuftrag");
		if (auftrag == null) {
			if (DEBUG)
				LOG.debug(END + "createAuftrag: kein Auftrag übergeben");
			return auftrag;
		}

		// Werden alle Constraints beim Einfuegen gewahrt? (hier nicht nötig)

		final Auftragsstatustyp asEingegangen = this.findAuftragsstatustyp(AUFTRAGSSTATUS_EINGEGANGEN);
		final List<Auftragsstatus> auftragsStati = new ArrayList<Auftragsstatus>();
		final Auftragsstatus auftragsStatus = new Auftragsstatus(auftrag, asEingegangen, new Date());
		auftragsStati.add(auftragsStatus);
		auftrag.setAuftragsstati(auftragsStati);
		
		final AuftragsverwaltungDAO dao = AuftragsverwaltungDAO.getInstance();
		if (TRACE)
			LOG.trace("Auftrag-Objekt vor der Speicherung: " + auftrag);
		auftrag = dao.insertAuftrag(em, auftrag);

		if (DEBUG)
			LOG.debug(END + "createAuftrag: auftrag=" + auftrag + ", Anzahl Stati=" + auftrag.getAuftragsstati().size() + ", 1. Status=" + auftrag.getAuftragsstati().iterator().next());
		return auftrag;
	}
	
	/**
	 * Existierende Auftragsdaten sollen ge&auml;ndert werden.
	 * @param auftrag Zu &auml;ndernde Auftragsdaten. Die ID muss gesetzt sein.
	 * @return Der aktualisierte Auftrag
	 */
	@RolesAllowed({ROLLE_ADMIN, ROLLE_MITARBEITER})
	public Auftrag updateAuftrag(Auftrag auftrag) throws AuftragNotFoundException {
		if (DEBUG)
			LOG.debug(BEGIN + "updateAuftrag");

		final AuftragsverwaltungDAO dao = AuftragsverwaltungDAO.getInstance();
		auftrag = dao.updateAuftrag(em, auftrag);

		if (DEBUG)
			LOG.debug(END + "updateAuftrag");
		return auftrag;

	}

	/**
	 * Wird ben&ouml;tigt f&uuml;r die Constraintsbeachtung
	 */
	private boolean hasStati(Auftrag auftrag) {
		if (DEBUG)
			LOG.debug(BEGIN + "checkDeleteAuftrag: " + auftrag);
		boolean result = false;

		if (auftrag != null && !auftrag.getAuftragsstati().isEmpty())
			result = true;

		if (DEBUG)
			LOG.debug(END + "checkDeleteBenutzer: " + result);
		return result;
	}

}
