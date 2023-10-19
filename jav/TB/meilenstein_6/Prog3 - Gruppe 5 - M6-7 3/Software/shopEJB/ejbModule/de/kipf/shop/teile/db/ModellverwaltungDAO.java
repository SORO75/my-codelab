package de.kipf.shop.teile.db;

import static de.kipf.shop.teile.db.Fahrradtyp.ALLE_FAHRRADTYPEN;
import static de.kipf.shop.teile.db.Fahrradtyp.FAHRRADTYP_BY_ID;
import static de.kipf.shop.teile.db.Modell.ALLE_MODELLE;
import static de.kipf.shop.teile.db.Modell.EINZELTEILE_VON_MODELL;
import static de.kipf.shop.util.EjbConstants.BEGIN;
import static de.kipf.shop.util.EjbConstants.END;

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
	public List<Einzelteil> findAllEinzelteileOfModell(EntityManager em, Modell modell) throws ModellNotFoundException {
		if(DEBUG)			
			LOG.debug(BEGIN + "findAllEinzelteileOfModell id="+ modell.getId());
		Modell managedModell = em.find(Modell.class, modell.getId());
		if (managedModell == null) {
			LOG.warn("Modell mit id=" + modell.getId() + " gibts nicht");
			throw new ModellNotFoundException("Modell mit id=" + modell.getId());
		}
		final Query query = em.createNamedQuery(EINZELTEILE_VON_MODELL);
		query.setParameter("modell", managedModell);		
		List<Einzelteil> einzelteile = query.getResultList();
		
		if(DEBUG)			
			LOG.debug(END + "findAllEinzelteileOfModell");
		return einzelteile;
	}
}
