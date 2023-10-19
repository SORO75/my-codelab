package de.kipf.shop.teile.db;

import static de.kipf.shop.teile.db.Fahrradtyp.ALLE_FAHRRADTYPEN;
import static de.kipf.shop.teile.db.Fahrradtyp.FAHRRADTYP_BY_ID;
import static de.kipf.shop.teile.db.Modell.ALLE_MODELLE;
import static de.kipf.shop.teile.db.Modell.ANGEBOTSMODELL;
import static de.kipf.shop.teile.db.Modell.ALLE_ANGEBOTS_MODELLE;
import static de.kipf.shop.teile.db.Modell.EINZELTEILE_VON_MODELL;
import static de.kipf.shop.util.EjbConstants.BEGIN;
import static de.kipf.shop.util.EjbConstants.END;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ModellverwaltungDAO {
	protected static final Log LOG = LogFactory
			.getLog(ModellverwaltungDAO.class);
	protected static final boolean DEBUG = LOG.isDebugEnabled();
	protected static final boolean TRACE = LOG.isTraceEnabled();

	private static ModellverwaltungDAO dao = new ModellverwaltungDAO();

	private ModellverwaltungDAO() {

	}

	public static ModellverwaltungDAO getInstance() {
		return dao;
	}

	public Modell insertModell(EntityManager em, Modell modell) {
		if (DEBUG)
			LOG.debug(BEGIN + ("insertModell mit id=" + modell.id));
		em.persist(modell);

		if (DEBUG)
			LOG.debug(END + ("insertModell mit id="));
		return modell;
	}

	@SuppressWarnings("unchecked")
	public Fahrradtyp findFahrradtypById(EntityManager em, Long modellTypId)
			throws FahrradtypNotFoundException {
		if (DEBUG)
			LOG.debug(BEGIN + "findModelltypById id=" + modellTypId);
		Query query = em.createNamedQuery(FAHRRADTYP_BY_ID);
		query.setParameter("id", modellTypId);
		Fahrradtyp radTyp = null;
		try {
			radTyp = (Fahrradtyp) query.getSingleResult();
		} catch (NoResultException e) {
			LOG.warn("Modelltyp mit ID=" + modellTypId + " nicht gefunden");
			throw new FahrradtypNotFoundException("Modelltyp mit ID"
					+ modellTypId + "nicht gefunden");
		}

		if (DEBUG)
			LOG.debug(END + "findModelltypById id=" + radTyp.getId());
		return radTyp;
	}

	@SuppressWarnings("unchecked")
	public List<Fahrradtyp> findAllFahrradtypen(EntityManager em) {
		if (DEBUG)
			LOG.debug(BEGIN + "findAllFahrradtypen");
		final Query query = em.createNamedQuery(ALLE_FAHRRADTYPEN);
		List<Fahrradtyp> fahrradtypen = query.getResultList();

		if (DEBUG)
			LOG.debug(END + "findAllFahrradtypen");
		return fahrradtypen;
	}

	public void addEinzelteilToModell(EntityManager em, Modell modell,
			Einzelteil teil) throws ModellNotFoundException,
			EinzelteilNotFoundException {
		if (DEBUG)
			LOG.debug(BEGIN + "addEinzelteilToModell Modellid=" + modell.id
					+ " Einzelteilid=" + teil.id);
		final Einzelteil managedTeil = em.find(Einzelteil.class, teil.getId());
		if (managedTeil == null) {
			LOG.warn("Teil das Modell hinzugefügt werden soll existiert nicht");
			throw new EinzelteilNotFoundException("Einzelteil"
					+ " nicht vorhanden");
		}

		final Modell managedModell = em.find(Modell.class, modell.getId());
		if (managedModell == null) {
			LOG.warn("Modell existiert nicht");
			throw new ModellNotFoundException("Modell nicht vorhanden");
		}

		ModellEinzelteil modellTeil = new ModellEinzelteil();
		modellTeil.setModellId(managedModell);
		modellTeil.setTeilId(managedTeil);
		em.persist(modellTeil);

		if (DEBUG)
			LOG.debug(END + "addEinzelteilToModell");
	}

	public void addEinzelteilToModell(EntityManager em, Modell modell,
			Collection<Einzelteil> teile) throws ModellNotFoundException,
			EinzelteilNotFoundException {
		if (DEBUG)
			LOG.debug(BEGIN + "addEinzelteilToModell Modellid=" + modell.id
					+ " Einzelteilid=" + teile);
		final Modell managedModell = em.find(Modell.class, modell.getId());
		if (managedModell == null) {
			LOG.warn("Modell existiert nicht");
			throw new ModellNotFoundException("Modell nicht vorhanden");
		}

		Einzelteil managedTeil = null;
		for (Einzelteil teil : teile) {
			managedTeil = em.find(Einzelteil.class, teil.getId());
			if (managedTeil == null) {
				LOG
						.warn("Teil das Modell hinzugefügt werden soll existiert nicht");
				throw new EinzelteilNotFoundException("Einzelteil"
						+ " nicht vorhanden");
			}
			ModellEinzelteil modellTeil = new ModellEinzelteil();
			modellTeil.setModellId(managedModell);
			modellTeil.setTeilId(managedTeil);
			em.persist(modellTeil);
		}

		if (DEBUG)
			LOG.debug(END + "addEinzelteilToModell");
	}

	@SuppressWarnings("unchecked")
	public List<Modell> findAllModelle(EntityManager em) {
		if (DEBUG)
			LOG.debug(BEGIN + "findAllModelle");
		final Query query = em.createNamedQuery(ALLE_MODELLE);
		List<Modell> modelle = query.getResultList();

		if (DEBUG)
			LOG.debug(END + "findAllModelle");
		return modelle;
	}

	@SuppressWarnings("unchecked")
	public List<AngebotsModell> findAllAngebotsModelle(EntityManager em) {
		if (DEBUG)
			LOG.debug(BEGIN + "findAllAngebotsModelle");
		final Query query = em.createNamedQuery(ALLE_ANGEBOTS_MODELLE);
		List<Modell> mod = query.getResultList();
		List<AngebotsModell> modelle = new ArrayList<AngebotsModell>();
		for (Modell m : mod) {
			if (m.getArt().equals(ANGEBOTSMODELL)) {
				modelle.add((AngebotsModell) m);
			}
		}

		if (DEBUG)
			LOG.debug(END + "findAllAngebotsModelle");
		return modelle;
	}

	public Modell findModellById(EntityManager em, Long modellId)
			throws ModellNotFoundException {
		if (DEBUG)
			LOG.debug(BEGIN + "findModellById id=" + modellId);
		Modell modell = em.find(Modell.class, modellId);
		if (modell == null) {
			LOG.warn("Modell mit id=" + modellId + "gibts nicht");
			throw new ModellNotFoundException("Modell mit id=" + modellId);
		}

		if (DEBUG)
			LOG.debug(END + "findModellById");
		return modell;
	}

	@SuppressWarnings("unchecked")
	public List<Einzelteil> findAllEinzelteileOfModell(EntityManager em,
			Modell modell) throws ModellNotFoundException {
		if (DEBUG)
			LOG
					.debug(BEGIN + "findAllEinzelteileOfModell id="
							+ modell.getId());
		Modell managedModell = em.find(Modell.class, modell.getId());
		if (managedModell == null) {
			LOG.warn("Modell mit id=" + modell.getId() + " gibts nicht");
			throw new ModellNotFoundException("Modell mit id=" + modell.getId());
		}
		final Query query = em.createNamedQuery(EINZELTEILE_VON_MODELL);
		query.setParameter("modell", managedModell);
		List<Einzelteil> einzelteile = query.getResultList();

		if (DEBUG)
			LOG.debug(END + "findAllEinzelteileOfModell");
		return einzelteile;
	}

	@SuppressWarnings("unchecked")
	public AngebotsModell findAktuellesAngebot(EntityManager em) {
		if (DEBUG)
			LOG.debug(BEGIN + "findAktuellesAngebot");
		AngebotsModell modell = null;
		String sqlQueryStr = "SELECT m1.m_id FROM shop.modell m1 WHERE"
				+ " m1.datum=(SELECT max(m.datum) "
				+ "FROM shop.modell m WHERE m.art='a')";
		final Query sqlQuery = em.createNativeQuery(sqlQueryStr);
		Long id = Long.valueOf(sqlQuery.getSingleResult().toString());
		try {
			modell = (AngebotsModell) this.findModellById(em, id);
		} catch (ModellNotFoundException me) {
			LOG.warn("findAktuellesAngebot kein Angebotsmodell vorhanden");
		}
		if (DEBUG)
			LOG.debug(END + "findAktuellesAngebot");
		return modell;
	}

	@SuppressWarnings("unchecked")
	public List<AngebotsModell> searchModell(EntityManager em, Fahrradtyp typ,
			String radbezeichnung, float maxpreis) {
		if (DEBUG)
			LOG.debug(BEGIN + "searchModell");
		List<AngebotsModell> modelle = null;
		final StringBuilder sb = new StringBuilder("FROM AngebotsModell a");

		if (!(typ == null) || !(radbezeichnung == null) || !(maxpreis == 0)) {
			sb.append(" WHERE ");
			if (!(typ == null)) {
				sb.append("a.typ=:typ ");
			}
			if (!(radbezeichnung == null)) {
				if (!(typ == null))
					sb.append("AND ");
				sb.append("a.beschreibung LIKE '%");
				sb.append(radbezeichnung);
				sb.append("%'");
			}			
			if (maxpreis > 0) {
				if (!(typ == null && radbezeichnung == null))
					sb.append("AND ");
				sb.append(" a.preis <= ");
				sb.append(maxpreis);
			}
		}

		final Query query = em.createQuery(sb.toString());
		if (!(typ == null))
			query.setParameter("typ", typ);
		modelle = query.getResultList();
		if (DEBUG)
			LOG.debug(END + "searchModell");
		return modelle;
	}
}
