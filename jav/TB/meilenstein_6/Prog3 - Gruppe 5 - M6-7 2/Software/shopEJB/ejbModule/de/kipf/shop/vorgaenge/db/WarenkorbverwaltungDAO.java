package de.kipf.shop.vorgaenge.db;

import javax.persistence.EntityManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.kipf.shop.vorgaenge.WarenkorbNotFoundException;

import static de.kipf.shop.util.EjbConstants.BEGIN;
import static de.kipf.shop.util.EjbConstants.END;

public class WarenkorbverwaltungDAO {
	protected static final Log LOG = LogFactory
			.getLog(WarenkorbverwaltungDAO.class);
	protected static final boolean DEBUG = LOG.isDebugEnabled();
	protected static final boolean TRACE = LOG.isTraceEnabled();

	private static WarenkorbverwaltungDAO dao = new WarenkorbverwaltungDAO();

	private WarenkorbverwaltungDAO() {

	}

	public static WarenkorbverwaltungDAO getInstance() {
		return dao;
	}

	public Warenkorb insertWarenkorb(EntityManager em, Warenkorb warenkorb) {
		if (DEBUG)
			LOG.debug(BEGIN + "insertWarenkorb " + warenkorb);
		em.persist(warenkorb);

		if (DEBUG)
			LOG.debug(END + "insertWarenkorb " + warenkorb);
		return warenkorb;
	}

	public Warenkorb findWarenkorbById(EntityManager em, Long primaryKey)
			throws WarenkorbNotFoundException {
		if (DEBUG)
			LOG.debug(BEGIN + "findWarenkorbById id=" + primaryKey);
		final Warenkorb warenkorb = em.find(Warenkorb.class, primaryKey);
		if (warenkorb == null) {
			LOG.warn("Warenkorb mit id" + primaryKey + " nicht gefunden!");
			throw new WarenkorbNotFoundException("Warenkorb mit id"
					+ primaryKey + " nicht gefunden!");
		}

		if (DEBUG)
			LOG.debug(END + "findWarenkorbById " + warenkorb);
		return warenkorb;
	}
}
