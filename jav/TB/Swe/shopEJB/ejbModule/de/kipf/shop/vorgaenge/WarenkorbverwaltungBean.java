package de.kipf.shop.vorgaenge;

import static de.kipf.shop.util.EjbConstants.LOGIN_DOMAIN_KIPF;
import static de.kipf.shop.util.EjbConstants.PERSISTENCE_CONTEXT_SHOP;
import static de.kipf.shop.util.EjbConstants.ROLLE_MITARBEITER;
import static de.kipf.shop.util.EjbConstants.ROLLE_BENUTZER;
import static de.kipf.shop.util.EjbConstants.BEGIN;
import static de.kipf.shop.util.EjbConstants.END;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.annotation.security.SecurityDomain;

import de.kipf.shop.teile.db.Modell;
import de.kipf.shop.teile.db.ModellNotFoundException;
import de.kipf.shop.vorgaenge.db.Auftrag;
import de.kipf.shop.vorgaenge.db.AuftragNotFoundException;
import de.kipf.shop.vorgaenge.db.Warenkorb;
import de.kipf.shop.vorgaenge.db.WarenkorbPosition;
import de.kipf.shop.vorgaenge.db.WarenkorbPositionNotFoundException;
import de.kipf.shop.vorgaenge.db.WarenkorbverwaltungDAO;

@Stateless
@Remote(Warenkorbverwaltung.class)
@Local(WarenkorbverwaltungLocal.class)
@RolesAllowed(ROLLE_MITARBEITER)
@SecurityDomain(LOGIN_DOMAIN_KIPF)
public class WarenkorbverwaltungBean implements Warenkorbverwaltung {
	protected static final Log LOG = LogFactory
			.getLog(WarenkorbverwaltungBean.class);
	protected static final boolean DEBUG = LOG.isDebugEnabled();
	protected static final boolean TRACE = LOG.isTraceEnabled();

	@PersistenceContext(name = PERSISTENCE_CONTEXT_SHOP)
	private EntityManager em;

	@PermitAll
	public Warenkorb insertWarenkorb(Warenkorb warenkorb) {
		if (DEBUG)
			LOG.debug(BEGIN + "insertWarenkorb " + warenkorb);
		WarenkorbverwaltungDAO dao = WarenkorbverwaltungDAO.getInstance();
		Warenkorb warenkorbPersistent = dao.insertWarenkorb(em, warenkorb);

		if (DEBUG)
			LOG.debug(END + "insertWarenkorb");
		return warenkorbPersistent;
	}

	@PermitAll
	public Warenkorb findWarenkorbById(Long primaryKey)
			throws WarenkorbNotFoundException {
		if (DEBUG)
			LOG.debug(BEGIN + "findWarenkorbById id=" + primaryKey);
		WarenkorbverwaltungDAO dao = WarenkorbverwaltungDAO.getInstance();
		final Warenkorb warenkorb = dao.findWarenkorbById(em, primaryKey);

		if (DEBUG)
			LOG.debug(END + "findWarenkorbById " + warenkorb);
		return warenkorb;
	}

	@PermitAll
	public void addPositionToWarenkorb(Warenkorb korb, Modell modell, int anzahl)
			throws WarenkorbNotFoundException, ModellNotFoundException {
		if (DEBUG)
			LOG.debug(BEGIN + "addPositionToWarenkorb Warenkorb id="
					+ korb.getId() + " Modell id=" + modell.getId());
		WarenkorbverwaltungDAO dao = WarenkorbverwaltungDAO.getInstance();
		dao.addPositionToWarenkorb(em, korb, modell, anzahl);

		if (DEBUG)
			LOG.debug(END + "addPositionToWarenkorb");
	}

	@PermitAll
	public List<WarenkorbPosition> getPositionenVonWarenkorb(Warenkorb warenkorb)
			throws WarenkorbNotFoundException {
		if (DEBUG)
			LOG.debug(BEGIN + "getPositionenVonWarenkorb warenkorbid="
					+ warenkorb.getId());
		WarenkorbverwaltungDAO dao = WarenkorbverwaltungDAO.getInstance();
		List<WarenkorbPosition> position = null;
		position = dao.getPositionVonWarenkorb(em, warenkorb);
		
		if (DEBUG)
			LOG.debug(END + "getPositionenVonWarenkorb");
		return position;
	}

	@PermitAll
	public void deleteWarenkorbPositionById(Long id) throws WarenkorbPositionNotFoundException {
		if (DEBUG)
			LOG.debug(BEGIN + "deleteWarenkorbPositionById");
		WarenkorbverwaltungDAO dao = WarenkorbverwaltungDAO.getInstance();
		dao.deleteWarenkorbPositionById(em, id);
		
		if (DEBUG)
			LOG.debug(END + "deleteWarenkorbPositionById");
	}

	@PermitAll
	public float gesamtPreisOfWarenkorb(Warenkorb warenkorb) throws WarenkorbNotFoundException {
		if (DEBUG)
			LOG.debug(BEGIN + "gesamtPreisOfWarenkorb");
		WarenkorbverwaltungDAO dao = WarenkorbverwaltungDAO.getInstance();
//		ModellverwaltungDAO daoModell = ModellverwaltungDAO.getInstance();
		
		List <WarenkorbPosition> positionen = dao.getPositionVonWarenkorb(em, warenkorb);
		float gesamtPreis = 0;
		for (WarenkorbPosition p : positionen) {
			gesamtPreis += p.getAnzahl() * p.getModellId().getPreis();
		}		
		
//		gesamtPreis = dao.gesamtPreisOfWarenkorb(em, warenkorb);
		if (DEBUG)
			LOG.debug(END + "gesamtPreisOfWarenkorb");
		return gesamtPreis;
	}

	@RolesAllowed(ROLLE_BENUTZER)
	public void doWarenkorbZuAuftrag(Warenkorb warenkorb, Auftrag auftrag) throws WarenkorbNotFoundException, AuftragNotFoundException {
		if (DEBUG)
			LOG.debug(BEGIN + "doWarenkorbZuAuftrag Wid=" + warenkorb.getId() + " , Aid" + auftrag.getId());
		WarenkorbverwaltungDAO dao = WarenkorbverwaltungDAO.getInstance();
		dao.doWarenkorbZuAuftrag(em, warenkorb, auftrag);
		
		if (DEBUG)
			LOG.debug(END + "doWarenkorbZuAuftrag");
	}
}
