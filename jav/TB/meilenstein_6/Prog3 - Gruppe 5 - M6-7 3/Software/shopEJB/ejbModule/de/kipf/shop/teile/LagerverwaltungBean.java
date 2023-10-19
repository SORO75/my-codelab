package de.kipf.shop.teile;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.annotation.security.SecurityDomain;

import de.kipf.shop.teile.db.Einzelteil;
import de.kipf.shop.teile.db.Lagerbuchung;
import de.kipf.shop.teile.db.LagerverwaltungDAO;
import static de.kipf.shop.util.EjbConstants.PERSISTENCE_CONTEXT_SHOP;
import static de.kipf.shop.util.EjbConstants.ROLLE_MITARBEITER;
import static de.kipf.shop.util.EjbConstants.LOGIN_DOMAIN_KIPF;
import static de.kipf.shop.util.EjbConstants.BEGIN;
import static de.kipf.shop.util.EjbConstants.END;

@Stateless
@Remote(Lagerverwaltung.class)
@Local(LagerverwaltungLocal.class)
@RolesAllowed(ROLLE_MITARBEITER)
@SecurityDomain(LOGIN_DOMAIN_KIPF)
public class LagerverwaltungBean implements Lagerverwaltung {
	protected static final Log LOG = LogFactory
			.getLog(LagerverwaltungBean.class);
	protected static final boolean DEBUG = LOG.isDebugEnabled();
	protected static final boolean TRACE = LOG.isTraceEnabled();

	@PersistenceContext(name = PERSISTENCE_CONTEXT_SHOP)
	private EntityManager em;

	public Lagerbuchung insertLagerbuchung(Lagerbuchung lb) {
		if (DEBUG)
			LOG.debug(BEGIN + "insertLagerbuchung von Einzelteilid="
					+ lb.getEinzelteil().getId());
		final LagerverwaltungDAO dao = LagerverwaltungDAO.getInstance();
		Lagerbuchung buchung = dao.insertLagerbuchung(em, lb);

		if (DEBUG)
			LOG.debug(END + "insertLagerbuchung id="+ lb.getId());
		return buchung;
	}

	public Long lagerBestand(Einzelteil teil) {
		if (DEBUG)
			LOG.debug(BEGIN + "lagerBestand von Einzelteil mit id="
					+ teil.getId());
		final LagerverwaltungDAO dao = LagerverwaltungDAO.getInstance();
		Long bestand = dao.lagerBestand(em, teil);
				
		if (DEBUG)
			LOG.debug(END + "lagerBestand");
		return bestand;
	}
}
