package de.kipf.shop.teile.db;

import static de.kipf.shop.teile.db.Einzelteil.ALLE_EINZELTEILE;
import static de.kipf.shop.teile.db.Einzelteil.EINZELTEIL_BY_ID;
import static de.kipf.shop.teile.db.Teiltyp.ALLE_TEILTYPEN;
import static de.kipf.shop.teile.db.Teiltyp.TEILTYP_BY_ID;
import static de.kipf.shop.util.EjbConstants.BEGIN;
import static de.kipf.shop.util.EjbConstants.END;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.kipf.shop.util.EntityNotFoundException;
import de.kipf.shop.util.EntityUpdatedException;

final public class TeileverwaltungDAO {
	protected static final Log LOG = LogFactory
			.getLog(TeileverwaltungDAO.class);
	protected static final boolean DEBUG = LOG.isDebugEnabled();
	protected static final boolean TRACE = LOG.isTraceEnabled();

	private static TeileverwaltungDAO dao = new TeileverwaltungDAO();

	private TeileverwaltungDAO() {

	}

	public static TeileverwaltungDAO getInstance() {
		return dao;
	}

	@SuppressWarnings("unchecked")
	public List<Einzelteil> findAllEinzelteile(EntityManager em) {
		if (DEBUG)
			LOG.debug(BEGIN + "findAllEinzelteile");

		Query query = em.createNamedQuery(ALLE_EINZELTEILE);
		List<Einzelteil> einzelteile = query.getResultList();
		if (einzelteile == null)
			einzelteile = new ArrayList<Einzelteil>();

		if (DEBUG)
			LOG.debug(END + "findAllEinzelteile");
		return einzelteile;
	}

	public Einzelteil insertEinzelteil(EntityManager em, Einzelteil teil) {
		if (DEBUG)
			LOG.debug(BEGIN + "insertEinzelteil " + teil.toString());
		em.persist(teil);

		if (DEBUG)
			LOG.debug(END + "insertEinzelteil");
		return teil;
	}

	@SuppressWarnings("unchecked")
	public Einzelteil findEinzelteil(EntityManager em, Long id)
			throws EinzelteilNotFoundException {
		if (DEBUG)
			LOG.debug(BEGIN + "findEinzelteil id=" + id);
		Query query = em.createNamedQuery(EINZELTEIL_BY_ID);
		query.setParameter("id", id);
		List<Einzelteil> einzelteile = query.getResultList();
		if (einzelteile == null || einzelteile.isEmpty())
			throw new EinzelteilNotFoundException(id);

		if (DEBUG)
			LOG.debug(END + "findEinzelteil");
		return einzelteile.get(0);
	}

	@SuppressWarnings("unchecked")
	public List<Teiltyp> findAllTeiltypen(EntityManager em) {
		if (DEBUG)
			LOG.debug(BEGIN + "findAllTeiltypen");

		Query query = em.createNamedQuery(ALLE_TEILTYPEN);
		List<Teiltyp> teiltypListe = query.getResultList();

		if (DEBUG)
			LOG.debug(END + "findAllTeiltypen");
		return teiltypListe;
	}

	@SuppressWarnings("unchecked")
	public Teiltyp findTeiltypById(EntityManager em, Long teiltypId)
			throws TeiltypNotFoundException {
		if (DEBUG)
			LOG.debug(BEGIN + "findTeiltypById id" + teiltypId);

		Query query = em.createNamedQuery(TEILTYP_BY_ID);
		query.setParameter("id", teiltypId);
		Teiltyp teiltyp = null;
		try {
			teiltyp = (Teiltyp) query.getSingleResult();
		} catch (NoResultException e) {
			LOG.warn("Teiltyp mit ID=" + teiltypId + " nicht gefunden");
			throw new TeiltypNotFoundException("Teiltyp mit ID" + teiltypId
					+ "nicht gefunden");
		}

		if (DEBUG)
			LOG.debug(END + "findTeiltypById id=" + teiltyp.getId());
		return teiltyp;
	}

	public void deleteEinzelteil(EntityManager em, Einzelteil teil) {
		if (DEBUG)
			LOG.debug(BEGIN + "deleteEinzelteil id" + teil.id);

		final Einzelteil managedTeil = em.find(Einzelteil.class, teil.getId());
		if (managedTeil == null) {
			LOG.warn(END
					+ "deleteEinzelteil: Zu löschendes Teil nicht vorhanden");
			throw new EntityNotFoundException(teil.id, teil.getClass(), teil
					.getClass().getSimpleName());
		}

		if (managedTeil.version > teil.version) {
			LOG
					.warn(END
							+ "deleteEinzelteil: Zu löschendes Teil wurde aktualisiert");
			throw new EntityUpdatedException(teil.id, teil.getClass(), teil
					.getClass().getSimpleName());
		}
		em.remove(managedTeil);

		if (DEBUG)
			LOG.debug(END + "deleteEinzelteil");
	}

	public Einzelteil updateEinzelteil(EntityManager em, Einzelteil teil) {
		if (DEBUG)
			LOG.debug(BEGIN + "updateEinzelteil id" + teil.id);
		final Einzelteil managedTeil = em.find(Einzelteil.class, teil.getId());
		if (managedTeil == null) {
			LOG.warn(END + "deleteEinzelteil: Zu aktualisierendes Teil "
					+ "nicht vorhanden");
			throw new EntityNotFoundException(teil.id, teil.getClass(), teil
					.getClass().getSimpleName());
		}

		if (managedTeil.version > teil.version) {
			LOG.warn(END + "deleteEinzelteil: Zu aktualisierendes Teil "
					+ "wurde konkurrierend aktualisiert");
			throw new EntityUpdatedException(teil.id, teil.getClass(), teil
					.getClass().getSimpleName());
		}

		final Einzelteil einzelteilNeu = em.merge(teil);
		if (DEBUG)
			LOG.debug(END + "updateEinzelteil");
		return einzelteilNeu;
	}
}