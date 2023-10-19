package de.kipf.shop.vorgaenge;

import static de.kipf.shop.util.EjbConstants.LOGIN_DOMAIN_KIPF;
import static de.kipf.shop.util.EjbConstants.PERSISTENCE_CONTEXT_SHOP;
import static de.kipf.shop.util.EjbConstants.ROLLE_MITARBEITER;
import static de.kipf.shop.util.EjbConstants.BEGIN;
import static de.kipf.shop.util.EjbConstants.END;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.annotation.security.SecurityDomain;

import de.kipf.shop.vorgaenge.db.Warenkorb;
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

	public Warenkorb insertWarenkorb(Warenkorb warenkorb) {
		if (DEBUG)
			LOG.debug(BEGIN + "insertWarenkorb " + warenkorb);
		WarenkorbverwaltungDAO dao = WarenkorbverwaltungDAO.getInstance();
		Warenkorb warenkorbPersistent = dao.insertWarenkorb(em, warenkorb);

		if (DEBUG)
			LOG.debug(END + "insertWarenkorb");
		return warenkorbPersistent;
	}

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
}
