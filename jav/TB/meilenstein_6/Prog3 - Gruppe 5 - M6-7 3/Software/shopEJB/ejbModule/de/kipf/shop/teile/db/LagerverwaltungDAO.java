package de.kipf.shop.teile.db;

import static de.kipf.shop.teile.db.Lagerbuchung.LAGERBEWEGUNG_EINES_TEILS;
import static de.kipf.shop.util.EjbConstants.BEGIN;
import static de.kipf.shop.util.EjbConstants.END;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LagerverwaltungDAO {
	protected static final Log LOG = LogFactory
			.getLog(LagerverwaltungDAO.class);
	protected static final boolean DEBUG = LOG.isDebugEnabled();
	protected static final boolean TRACE = LOG.isTraceEnabled();

	private static LagerverwaltungDAO dao = new LagerverwaltungDAO();

	private LagerverwaltungDAO() {

	}

	public static LagerverwaltungDAO getInstance() {
		return dao;
	}

	@SuppressWarnings("unchecked")
	public Lagerbuchung insertLagerbuchung(EntityManager em, Lagerbuchung lb) {
		if (DEBUG)
			LOG.debug(BEGIN + "insertLagerbuchung Teil id=" + lb.einzelteil.id
					+ " Menge=" + lb.menge);
		Lagerbuchung buchung = lb;
		em.persist(lb);

		if (DEBUG)
			LOG.debug(END + "insertLagerbuchung");
		return buchung;
	}

	// TODO Funktionalität ins Bean verlagern
	public Long lagerBestand(EntityManager em, Einzelteil teil) {
		if (DEBUG)
			LOG.debug(BEGIN + "lagerBestand von Einzelteil mit id="
					+ teil.getId());
		Long bestand = null;

		bestand = getZugaenge(em, teil) - getAbgaenge(em, teil);

		if (DEBUG)
			LOG.debug(END + "lagerBestand summe=" + bestand);
		return bestand;
	}

	private Long getZugaenge(EntityManager em, Einzelteil teil) {
		if (DEBUG)
			LOG.debug(BEGIN + "getZugaenge von Einzelteil mit id="
					+ teil.getId());
		Long zugang = null;
		final Query query = em.createNamedQuery(LAGERBEWEGUNG_EINES_TEILS);
		query.setParameter("art", new Character('s'));
		query.setParameter("typ", teil.getId());
		zugang = (Long) query.getSingleResult();

		if (DEBUG)
			LOG.debug(END + "getZugaenge summe=" + zugang);
		return zugang == null ? 0 : zugang;
	}

	private Long getAbgaenge(EntityManager em, Einzelteil teil) {
		if (DEBUG)
			LOG.debug(BEGIN + "getAbgaenge von Einzelteil mit id="
					+ teil.getId());
		Long abgang = null;
		final Query query = em.createNamedQuery(LAGERBEWEGUNG_EINES_TEILS);
		query.setParameter("art", new Character('h'));
		query.setParameter("typ", teil.getId());
		abgang = (Long) query.getSingleResult();

		if (DEBUG)
			LOG.debug(END + "getAbgaenge summe=" + abgang);
		return abgang == null ? 0 : abgang;
	}
}
