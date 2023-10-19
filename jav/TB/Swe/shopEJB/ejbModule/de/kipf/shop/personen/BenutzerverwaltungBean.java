package de.kipf.shop.personen;

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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJBAccessException;
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
@Remote(Benutzerverwaltung.class)
@Local(BenutzerverwaltungLocal.class)
//@RolesAllowed( { ROLLE_ADMIN, ROLLE_MITARBEITER, ROLLE_BENUTZER })
@SecurityDomain(LOGIN_DOMAIN_KIPF)
public class BenutzerverwaltungBean implements BenutzerverwaltungLocal {

	static final Log LOG = LogFactory.getLog(BenutzerverwaltungBean.class);

	static final boolean DEBUG = LOG.isDebugEnabled();

	static final boolean TRACE = LOG.isTraceEnabled();

	static final boolean ERROR = LOG.isErrorEnabled();

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
			LOG.debug(BenutzerverwaltungBean.class.getName()
					+ " wird geloescht");
	}

	public String simpleConnectionTest(String str) {
		return str;
	}

	/**
	 * Ermittelt alle persistenten Benutzer
	 * 
	 * @return Alle persistenten Benutzer
	 */
	@RolesAllowed({ROLLE_ADMIN, ROLLE_MITARBEITER})
	public Collection<Benutzer> findAllBenutzer() {
		if (DEBUG)
			LOG.debug(BEGIN + "findAllBenutzer");

		final BenutzerverwaltungDAO dao = BenutzerverwaltungDAO.getInstance();
		final Collection<Benutzer> benutzer = dao.findAllBenutzer(em);

		if (DEBUG)
			LOG.debug(END + "findAllBenutzer");
		return benutzer;
	}

	/**
	 * Ermittelt alle persistenten Benutzer
	 * 
	 * @return Alle persistenten Benutzergruppen mit Benutzer
	 */
	@RolesAllowed({ROLLE_ADMIN, ROLLE_MITARBEITER})
	public Collection<Benutzergruppe> findAllBenutzergruppenMitBenutzer() {
		if (DEBUG)
			LOG.debug(BEGIN + "findAllBenutzergruppenMitBenutzer");

		final BenutzerverwaltungDAO dao = BenutzerverwaltungDAO.getInstance();
		final Collection<Benutzergruppe> benutzergruppen = dao.findAllBenutzergruppenMitBenutzer(em);

		if (DEBUG)
			LOG.debug(END + "findAllBenutzergruppenMitBenutzer");
		return benutzergruppen;
	}
	
	/**
	 * Suche nach Benutzer mit gleichem Nachnamen.
	 * 
	 * @param nachname
	 *            Suchkriterium
	 * @return Liste mit gefundenen Datens&auml;tzen
	 * @throws BenutzerNotFoundException
	 */
	@RolesAllowed({ROLLE_ADMIN, ROLLE_MITARBEITER})
	public List<Benutzer> findBenutzerByName(String nachname)
			throws BenutzerNotFoundException {
		if (DEBUG)
			LOG.debug(BEGIN + "findBenutzerByName: " + nachname);

		final BenutzerverwaltungDAO dao = BenutzerverwaltungDAO.getInstance();
		final List<Benutzer> benutzer = dao.findBenutzerByName(em, nachname);

		if (DEBUG)
			LOG.debug(END + "findBenutzerByName: " + nachname);
		return benutzer;
	}

	/**
	 * Suche nach Benutzer mit gleichem Nachnamen einschlie&szlich ihrer
	 * Auftr&auml;ge.
	 * 
	 * @param nachname
	 *            Suchkriterium
	 * @return Liste mit gefundenen Datens&auml;tzen
	 * @throws BenutzerNotFoundException
	 */
	@RolesAllowed({ROLLE_ADMIN, ROLLE_MITARBEITER})
	public List<Benutzer> findBenutzerMitAuftraegeByName(String nachname)
			throws BenutzerNotFoundException {
		if (DEBUG)
			LOG.debug(BEGIN + "findBenutzerMitAuftraegeByName: " + nachname);

		final BenutzerverwaltungDAO dao = BenutzerverwaltungDAO.getInstance();
		final List<Benutzer> benutzer = dao.findBenutzerMitAuftraegeByName(em,
				nachname);

		if (DEBUG)
			LOG.debug(END + "findBenutzerMitAuftraegeByName: " + nachname);
		return benutzer;
	}

	/**
	 * Suche nach einem Benutzer mit gegebenem Benutzernamen.
	 * 
	 * @param benutzername
	 *            Der Benutzername des gesuchten Benutzers
	 * @return Das Benutzerobjekt oder null, wenn kein Benutzer mit dem
	 *         gegebenen Benutzernamen existiert
	 * @throws BenutzerNotFoundException
	 */
	@RolesAllowed({ROLLE_ADMIN, ROLLE_MITARBEITER, ROLLE_BENUTZER})
	public Benutzer findBenutzer(String benutzername)
			throws BenutzerNotFoundException {
		if (DEBUG)
			LOG.debug(BEGIN + "findBenutzer: " + benutzername);

		if (!(ctx.isCallerInRole(ROLLE_ADMIN) || ctx.isCallerInRole(ROLLE_MITARBEITER))) {
			benutzername = ctx.getCallerPrincipal().toString();
		}
		
		final BenutzerverwaltungDAO dao = BenutzerverwaltungDAO.getInstance();
		final Benutzer benutzer = dao.findBenutzer(em, benutzername);

		if (DEBUG)
			LOG.debug(END + "findBenutzer: " + benutzername);
		return benutzer;
	}

	/**
	 * Suche nach einer Benutzergruppe mit gegebenem Benutzergruppennamen.
	 * 
	 * @param benutzergruppenname
	 *            Der Benutzergruppenname der gesuchten Benutzergruppe
	 * @return Das Benutzergruppenobjekt oder null, wenn keine Benutzergruppe
	 *         mit dem gegebenen Benutzergruppennamen existiert
	 * @throws BenutzergruppeNotFoundException
	 */
	@RolesAllowed({ROLLE_ADMIN, ROLLE_MITARBEITER})
	public Benutzergruppe findBenutzergruppe(String benutzergruppenname)
			throws BenutzergruppeNotFoundException {
		if (DEBUG)
			LOG.debug(BEGIN + "findBenutzergruppe: " + benutzergruppenname);

		final BenutzerverwaltungDAO dao = BenutzerverwaltungDAO.getInstance();
		final Benutzergruppe benutzergruppe = dao.findBenutzergruppeByName(em,
				benutzergruppenname);

		if (DEBUG)
			LOG.debug(END + "findBenutzergruppe: " + benutzergruppenname);
		return benutzergruppe;
	}

	/**
	 * Suche nach einer Benutzergruppe mit gegebenem Benutzergruppennamen inkl
	 * derren Benutzer.
	 * 
	 * @param benutzergruppenname
	 *            Der Benutzergruppenname der gesuchten Benutzergruppe
	 * @return Das Benutzergruppenobjekt oder null, wenn keine Benutzergruppe
	 *         mit dem gegebenen Benutzergruppennamen existiert
	 * @throws BenutzergruppeNotFoundException
	 */
	@RolesAllowed({ROLLE_ADMIN, ROLLE_MITARBEITER})
	public Benutzergruppe findBenutzergruppeMitBentutzer(
			String benutzergruppenname) throws BenutzergruppeNotFoundException {
		if (DEBUG)
			LOG.debug(BEGIN + "findBenutzergruppeMitBentutzer: "
					+ benutzergruppenname);

		final BenutzerverwaltungDAO dao = BenutzerverwaltungDAO.getInstance();
		final Benutzergruppe benutzergruppe = dao
				.findBenutzergruppeMitBenutzerByName(em, benutzergruppenname);

		if (DEBUG)
			LOG.debug(END + "findBenutzergruppeMitBentutzer: "
					+ benutzergruppenname);
		return benutzergruppe;
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
	@RolesAllowed({ROLLE_ADMIN, ROLLE_MITARBEITER})
	public Benutzer findBenutzerMitAuftraege(String benutzername)
			throws BenutzerNotFoundException {
		if (DEBUG)
			LOG.debug(BEGIN + "findBenutzerMitAuftraege: " + benutzername);

		final BenutzerverwaltungDAO dao = BenutzerverwaltungDAO.getInstance();
		final Benutzer benutzer = dao
				.findBenutzerMitAuftraege(em, benutzername);

		if (DEBUG)
			LOG.debug(END + "findBenutzerMitAuftraege: " + benutzername);
		return benutzer;
	}

	/**
	 * Eine Menge von Benutzern soll gel&ouml;scht werden.
	 * 
	 * @param benutzer
	 *            Zu l&ouml;schende Datens&auml;tze
	 * @throws BenutzerDeleteAuftragException
	 *             falls parallel ein gleicher Datensatz ge&auml;ndert oder
	 *             gel&ouml;scht wurde
	 */
	@RolesAllowed(ROLLE_ADMIN)
	public void deleteBenutzer(Collection<Benutzer> benutzer)
			throws BenutzerDeleteAuftragException {
		if (DEBUG)
			LOG.debug(BEGIN + "deleteBenutzer");

		if (benutzer == null || benutzer.isEmpty()) {
			if (DEBUG)
				LOG.debug(END + "deleteBenutzer: leere Collection");
			return;
		}

		for (Benutzer b : benutzer) {
			// Contraints beachten!
			final boolean hasAuftraege = hasAuftraege(b);
			if (hasAuftraege == true) {
				if (DEBUG)
					LOG.debug(END + "deleteBenutzer: "
							+ b.getAuftraege().size() + " Aufträge vorhanden");
				throw new BenutzerDeleteAuftragException(b);
			}
		}

		final BenutzerverwaltungDAO dao = BenutzerverwaltungDAO.getInstance();
		dao.deleteBenutzer(em, benutzer);

		if (DEBUG)
			LOG.debug(END + "deleteBenutzer: " + benutzer.size()
					+ " Datensaetze geloescht");
	}

	/**
	 * Neue Benutzer sollen angelegt werden.
	 * 
	 * @param benutzer
	 *            Neue Benutzerndaten
	 * @throws BenutzernameNotValidException
	 *             Falls der Benutzer keinen g&uuml;ltigen Benutername angegeben
	 *             hat
	 * @throws BenutzernameAlreadyExistsException
	 *             Falls der Benutzername schon existiert
	 */
	@RolesAllowed({ROLLE_ADMIN, ROLLE_MITARBEITER})
	public Collection<Benutzer> createBenutzer(Collection<Benutzer> benutzer)
			throws BenutzernameNotValidException,
			BenutzergruppeNotFoundException, BenutzernameAlreadyExistsException {
		if (DEBUG)
			LOG.debug(BEGIN + "createBenutzer");
		if (benutzer == null || benutzer.isEmpty()) {
			if (DEBUG)
				LOG.debug(END + "createBenutzer: leere Collection");
			return new ArrayList<Benutzer>(0);
		}

		// Werden alle Constraints beim Einfuegen gewahrt? (hier nicht nötig)

		for (Benutzer b : benutzer) {
			createBenutzer(b);
		}

		if (DEBUG)
			LOG.debug(END + "createBenutzer");
		return benutzer;
	}

	// /**
	// * Existierende Benutzerdaten sollen ge&auml;ndert werden.
	// *
	// * @param benutzer
	// * Zu &auml;ndernde Benutzerdaten. Der Benutzername muss gesetzt
	// * sein.
	// * @return Die Menge der aktualisierten Benutzer
	// */
	// @RolesAllowed({ROLLE_ADMIN, ROLLE_MITARBEITER})
	// public Collection<Benutzer> updateBenutzer(Collection<Benutzer> benutzer)
	// throws BenutzerNotFoundException {
	// if (DEBUG)
	// LOG.debug(BEGIN + "updateBenutzer");
	//
	// final BenutzerverwaltungDAO dao = BenutzerverwaltungDAO.getInstance();
	// benutzer = dao.updateBenutzer(em, benutzer);
	//
	// if (DEBUG)
	// LOG.debug(END + "updateBenutzer");
	// return benutzer;
	// }

	/**
	 * Ein Benutzern soll gel&ouml;scht werden.
	 * 
	 * @param benutzer
	 *            Zu l&ouml;schender Datensatz
	 * @throws BenutzerDeleteAuftragException
	 *             falls parallel ein gleicher Datensatz ge&auml;ndert oder
	 *             gel&ouml;scht wurde
	 */
	@RolesAllowed(ROLLE_ADMIN)
	public void deleteBenutzer(Benutzer benutzer)
			throws BenutzerDeleteAuftragException {
		if (DEBUG)
			LOG.debug(BEGIN + "deleteBenutzer: " + benutzer.getBenutzername());

		if (benutzer == null) {
			if (DEBUG)
				LOG.debug(END + "deleteBenutzer: kein Benutzer übergeben");
			return;
		}

		final boolean hasAuftraege = hasAuftraege(benutzer);
		if (hasAuftraege == true) {
			if (DEBUG)
				LOG.debug(END + "deleteBenutzer: "
						+ benutzer.getAuftraege().size()
						+ " Aufträge vorhanden");
			throw new BenutzerDeleteAuftragException(benutzer);
		}

		final BenutzerverwaltungDAO dao = BenutzerverwaltungDAO.getInstance();
		dao.deleteBenutzer(em, benutzer);

		if (DEBUG)
			LOG.debug(END + "deleteBenutzer: " + benutzer.getBenutzername()
					+ " geloescht");
	}

	/**
	 * Neuer Benutzer soll angelegt werden.
	 * 
	 * @param benutzer
	 *            Neue Benutzerndaten
	 * @return Die aktualisierten Benutzer
	 * @throws BenutzernameNotValidException
	 *             Falls der Benutzer keinen g&uuml;ltigen Benutername angegeben
	 *             hat
	 * @throws BenutzernameAlreadyExistsException
	 *             Falls der Benutzername schon existiert
	 */
	
	public Benutzer createBenutzer(Benutzer benutzer)
			throws BenutzernameNotValidException,
			BenutzergruppeNotFoundException, BenutzernameAlreadyExistsException {
		if (DEBUG)
			LOG.debug(BEGIN + "createBenutzer");
		if (benutzer == null) {
			if (DEBUG)
				LOG.debug(END + "createBenutzer: kein Benutzer übergeben");
			return benutzer;
		}

		// Werden alle Constraints beim Einfuegen gewahrt? (hier nicht nötig)
		Benutzer vorhandenerBenutzer = null;
		try {
			vorhandenerBenutzer = findBenutzer(benutzer.getBenutzername());
		} catch (BenutzerNotFoundException e) {
			// Alles ok! Es darf kein Benutzer geben!
		}
		if (vorhandenerBenutzer != null) 
			throw new BenutzernameAlreadyExistsException();
		
		// Nur Admins dürfen Rollen setzen, sonst keiner!
		if (!ctx.isCallerInRole(ROLLE_ADMIN)) {
			final Set<Benutzergruppe> benutzergruppen = new HashSet<Benutzergruppe>();
			// Benutzergruppe
			final Benutzergruppe bg = findBenutzergruppe(ROLLE_BENUTZER);
			benutzergruppen.add(bg);
			
			benutzer.setBenutzergruppen(benutzergruppen);
			bg.getBenutzer().add(benutzer);
		}

		final BenutzerverwaltungDAO dao = BenutzerverwaltungDAO.getInstance();
		if (TRACE)
			LOG.trace("Benutzer-Objekt vor der Speicherung: " + benutzer);
		benutzer = dao.insertBenutzer(em, benutzer);
		
		if (ctx.isCallerInRole(ROLLE_ADMIN)) {
			if (DEBUG)
				LOG.debug("Vom Admin übergebene Gruppenanzahl=" + benutzer.getBenutzergruppen().size());
			for (Benutzergruppe bg : benutzer.getBenutzergruppen()) {
				this.findBenutzergruppe(bg.getName()).getBenutzer().add(benutzer);
			}
		}
		
		
		if (DEBUG)
			LOG.debug(END + "createBenutzer: benutzer=" + benutzer + ", Anzahl Benutzergruppen=" + benutzer.getBenutzergruppen().size() + ", benutzergruppe=" + benutzer.getBenutzergruppen().iterator().next());
		return benutzer;
	}

	/**
	 * Existierende Benutzerdaten sollen ge&auml;ndert werden.
	 * 
	 * @param benutzer
	 *            Zu &auml;ndernde Benutzerdaten. Der Benutzername muss gesetzt
	 *            sein.
	 * @return Die aktualisierte Benutzer
	 * @throws BenutzerNotFoundException
	 */
	@RolesAllowed({ROLLE_ADMIN, ROLLE_MITARBEITER, ROLLE_BENUTZER})
	public Benutzer updateBenutzer(Benutzer benutzer)
			throws BenutzerNotFoundException {
		if (DEBUG)
			LOG.debug(BEGIN + "updateBenutzer");

		//SECURITY: Benutzer dürfen nur sich selbst ändern!
		if (!(ctx.isCallerInRole(ROLLE_ADMIN) || ctx.isCallerInRole(ROLLE_MITARBEITER))) {
			final String aktuellerBenutzer = ctx.getCallerPrincipal().toString();
			if (!aktuellerBenutzer.equals(benutzer.getBenutzername()))
				throw new EJBAccessException();
		}
		
		final BenutzerverwaltungDAO dao = BenutzerverwaltungDAO.getInstance();

		Benutzer daoBenutzer = dao.findBenutzer(em, benutzer.getBenutzername());

		// PasswordHash und Gebnutzergruppen, dürfen hier nicht verändert
		// werden, außer man ist Admin!
		if (!ctx.isCallerInRole(ROLLE_ADMIN)) {
			benutzer.setBenutzergruppen(daoBenutzer.getBenutzergruppen());
			benutzer.setPasswortHash(daoBenutzer.getPasswortHash());
		} 
		
		benutzer = dao.updateBenutzer(em, benutzer);
		
		if (ctx.isCallerInRole(ROLLE_ADMIN)) {
			if (DEBUG)
				LOG.debug("Vom Admin übergebene Gruppenanzahl=" + benutzer.getBenutzergruppen().size());
			for (Benutzergruppe bg : findAllBenutzergruppenMitBenutzer()) {
				if (bg.getBenutzer().contains(benutzer)) bg.getBenutzer().remove(benutzer);
			}
			for (Benutzergruppe bg : benutzer.getBenutzergruppen()) {
				try {
					this.findBenutzergruppe(bg.getName()).getBenutzer().add(benutzer);
				} catch (BenutzergruppeNotFoundException e) {
					continue;
				}
			}
		}

		if (DEBUG)
			LOG.debug(END + "updateBenutzer");
		return benutzer;

	}

	/**
	 * Aktualisierung des Passworts eines Benutzers
	 * 
	 * @param benutzer
	 *            Benutzerobjekt
	 * @param password
	 *            neues, noch unverschl&uuml;sseltes Passwort
	 * @return Der aktualisierte Benutzer
	 */
	public Benutzer setPassword(Benutzer benutzer, String password)
			throws PasswordNotValidException {
		if (DEBUG)
			LOG.debug(BEGIN + "setPassword: " + benutzer);

		if (benutzer == null) {
			if (DEBUG)
				LOG.debug(BEGIN + "setPassword: kein Benutzer übergeben");
			return benutzer;
		} else if (password == null) {
			if (DEBUG)
				LOG.debug(BEGIN + "setPassword: kein Passwort übergeben");
			return benutzer;
		} else if (password.length() < PasswordNotValidException.MIN_LENGTH) {
			if (DEBUG)
				LOG.debug(BEGIN + "setPassword: "
						+ PasswordNotValidException.ExceptionReason.pwdToShort);
			throw new PasswordNotValidException(
					PasswordNotValidException.ExceptionReason.pwdToShort);
		}

		for (String bad : PasswordNotValidException.PASSWORD_BLACK_LIST) {
			if (bad.toLowerCase().equals(password.toLowerCase()))
				throw new PasswordNotValidException(
						PasswordNotValidException.ExceptionReason.pwdBlackList);
		}

		String verschluesselt = createPasswordHash(HASH_ALGORITHM,
				HASH_ENCODING, HASH_CHARSET, null, password);
		benutzer.setPasswortHash(verschluesselt);
		
		if (DEBUG)
			LOG.debug(END + "setPassword: " + benutzer.getPasswortHash());
		return benutzer;
	}

	/**
	 * Aktualisierung der Benutzergruppe eines Benutzers
	 * 
	 * @param benutzername
	 *            Benutzername eines Benutzers
	 * @param Collection
	 *            von Benutzergruppen
	 * @return Der aktualisierte Benutzer
	 */
	@RolesAllowed(ROLLE_ADMIN)
	public Benutzer setBenutzergruppen(String benutzername,
			Set<Benutzergruppe> benutzergruppen)
			throws BenutzerNotFoundException {
		if (DEBUG)
			LOG.debug(BEGIN + "setBenutzergruppen für benutzernamen="
					+ benutzername);

		if (benutzername == null) {
			if (DEBUG)
				LOG.debug(BEGIN
						+ "setBenutzergruppen: keinen Benutzernamen übergeben");
			return null;
		} else if (benutzergruppen == null || benutzergruppen.isEmpty()) {
			if (DEBUG)
				LOG
						.debug(BEGIN
								+ "setBenutzergruppen: keine Benutzergruppen übergeben");
			return null;
		}

		final BenutzerverwaltungDAO dao = BenutzerverwaltungDAO.getInstance();
		Benutzer benutzer = dao.findBenutzer(em, benutzername);
		benutzer.setBenutzergruppen(benutzergruppen);
		benutzer = dao.updateBenutzer(em, benutzer);

		if (DEBUG)
			LOG.debug(END + "setBenutzergruppen");
		return benutzer;
	}

	/**
	 * Hinzuf&uuml;gen einer Benutzergruppe zu einem Benutzer
	 * 
	 * @param Benutzername
	 *            eines Benutzers
	 * @param Set
	 *            von Benutzergruppen
	 * @return Der aktualisierte Benutzer
	 */
	@RolesAllowed(ROLLE_ADMIN)
	public Benutzer addBenutzergruppen(String benutzername,
			Benutzergruppe benutzergruppe) throws BenutzerNotFoundException {
		if (DEBUG)
			LOG.debug(BEGIN + "setBenutzergruppen für benutzernamen="
					+ benutzername);

		if (benutzername == null) {
			if (DEBUG)
				LOG.debug(BEGIN
						+ "setBenutzergruppen: keinen Benutzernamen übergeben");
			return null;
		} else if (benutzergruppe == null) {
			if (DEBUG)
				LOG.debug(BEGIN
						+ "setBenutzergruppen: keine Benutzergruppe übergeben");
			return null;
		}

		final BenutzerverwaltungDAO dao = BenutzerverwaltungDAO.getInstance();
		Benutzer benutzer = dao.findBenutzer(em, benutzername);
		benutzer.getBenutzergruppen().add(benutzergruppe);
		benutzer = dao.updateBenutzer(em, benutzer);

		if (DEBUG)
			LOG.debug(END + "setBenutzergruppen");
		return benutzer;
	}

	/**
	 * Vergleich des verschluesselten Benutzer-Passwortes mit einem anderen
	 * Passwort im Klartext.
	 * 
	 * @param benutzer
	 *            Benutzerobjekt
	 * @param password
	 *            Das unverschl&uuml;sselte Passwort im Klartext
	 * @return true, falls das unverschl&uuml;sselte Passwort die gleiche
	 *         Codierung ergibt; false ansonsten.
	 */
	public boolean checkPassword(Benutzer benutzer, String password) {
		if (benutzer == null || password == null)
			return false;

		String verschluesselt = createPasswordHash(HASH_ALGORITHM,
				HASH_ENCODING, HASH_CHARSET, null, password);

		return verschluesselt.equals(benutzer.getPasswortHash());
	}

	/**
	 * Wird ben&ouml;tigt f&uuml;r die Constraintsbeachtung
	 */
	private boolean hasAuftraege(Benutzer benutzer) {
		if (DEBUG)
			LOG.debug(BEGIN + "checkDeleteBenutzer: " + benutzer);
		boolean result = false;

		// Gibt es den Benutzer und hat er einen Auftrag und mehr
		if (benutzer != null && !benutzer.getAuftraege().isEmpty())
			result = true;

		if (DEBUG)
			LOG.debug(END + "checkDeleteBenutzer: " + result);
		return result;
	}

}
