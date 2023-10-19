package de.kipf.shop.teile;

import static de.kipf.shop.util.EjbConstants.LOGIN_DOMAIN_KIPF;
import static de.kipf.shop.util.EjbConstants.PERSISTENCE_CONTEXT_SHOP;
import static de.kipf.shop.util.EjbConstants.ROLLE_MITARBEITER;
import static de.kipf.shop.util.EjbConstants.BEGIN;
import static de.kipf.shop.util.EjbConstants.END;

import java.util.Collection;
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

import de.kipf.shop.teile.db.AngebotsModell;
import de.kipf.shop.teile.db.Einzelteil;
import de.kipf.shop.teile.db.EinzelteilNotFoundException;
import de.kipf.shop.teile.db.Fahrradtyp;
import de.kipf.shop.teile.db.Modell;
import de.kipf.shop.teile.db.FahrradtypNotFoundException;
import de.kipf.shop.teile.db.ModellNotFoundException;
import de.kipf.shop.teile.db.ModellverwaltungDAO;

@Stateless
@Remote(Modellverwaltung.class)
@Local(ModellverwaltungLocal.class)
@RolesAllowed(ROLLE_MITARBEITER)
@SecurityDomain(LOGIN_DOMAIN_KIPF)
public class ModellverwaltungBean implements Modellverwaltung {
	protected static final Log LOG = LogFactory
			.getLog(ModellverwaltungBean.class);
	protected static final boolean DEBUG = LOG.isDebugEnabled();
	protected static final boolean TRACE = LOG.isTraceEnabled();

	@PersistenceContext(name = PERSISTENCE_CONTEXT_SHOP)
	private EntityManager em;

	public Modell insertModell(Modell modell, Long fahrradtyp)
			throws FahrradtypNotFoundException {
		if (DEBUG)
			LOG.debug(BEGIN + "insertModell");
		ModellverwaltungDAO dao = ModellverwaltungDAO.getInstance();
		modell.setTyp(dao.findFahrradtypById(em, fahrradtyp));
		final Modell modellPersistent = dao.insertModell(em, modell);

		if (DEBUG)
			LOG.debug(END + "insertModell");
		return modellPersistent;
	}

	@PermitAll
	public Collection<Fahrradtyp> findAllFahrradtypen() {
		if (DEBUG)
			LOG.debug(BEGIN + "findAllFahrradtypen");
		ModellverwaltungDAO dao = ModellverwaltungDAO.getInstance();
		Collection<Fahrradtyp> fahrradtypen = dao.findAllFahrradtypen(em);

		if (DEBUG)
			LOG.debug(END + "findAllFahrradtypen");
		return fahrradtypen;
	}

	public void addEinzelteilToModell(Modell modell, Einzelteil teil)
			throws ModellNotFoundException, EinzelteilNotFoundException {
		if (DEBUG)
			LOG.debug(BEGIN + "addEinzelteilToModell Modellid="
					+ modell.getId() + " Einzelteilid=" + teil.getId());
		ModellverwaltungDAO dao = ModellverwaltungDAO.getInstance();
		dao.addEinzelteilToModell(em, modell, teil);

		if (DEBUG)
			LOG.debug(END + "addEinzelteilToModell");
	}

	public void addEinzelteilToModell(Modell modell,
			Collection<Einzelteil> teile) throws ModellNotFoundException,
			EinzelteilNotFoundException {
		if (DEBUG)
			LOG.debug(BEGIN + "addEinzelteilToModell Modellid="
					+ modell.getId() + " Einzelteile: " + teile);
		ModellverwaltungDAO dao = ModellverwaltungDAO.getInstance();
		dao.addEinzelteilToModell(em, modell, teile);

		if (DEBUG)
			LOG.debug(END + "addEinzelteilToModell");
	}

	public List<Modell> findAllModelle() {
		if (DEBUG)
			LOG.debug(BEGIN + "findAllModelle");
		ModellverwaltungDAO dao = ModellverwaltungDAO.getInstance();
		List<Modell> modelle = dao.findAllModelle(em);

		if (DEBUG)
			LOG.debug(END + "findAllModelle");
		return modelle;
	}

	@PermitAll
	public List<AngebotsModell> findAllAngebotsModelle() {
		if (DEBUG)
			LOG.debug(BEGIN + "findAllAngebotsModelle");
		ModellverwaltungDAO dao = ModellverwaltungDAO.getInstance();
		List<AngebotsModell> modelle = dao.findAllAngebotsModelle(em);

		if (DEBUG)
			LOG.debug(END + "findAllAngebotsModelle");
		return modelle;
	}

	@PermitAll
	public Modell findModellById(Long modellId) throws ModellNotFoundException {
		if (DEBUG)
			LOG.debug(BEGIN + "findModellById id=" + modellId);
		ModellverwaltungDAO dao = ModellverwaltungDAO.getInstance();
		Modell modell = dao.findModellById(em, modellId);

		if (DEBUG)
			LOG.debug(END + "findModellById");
		return modell;
	}

	@PermitAll
	public List<Einzelteil> findAllEinzelteileOfModell(Modell modell)
			throws ModellNotFoundException {
		if (DEBUG)
			LOG
					.debug(BEGIN + "findAllEinzelteileOfModell id="
							+ modell.getId());
		ModellverwaltungDAO dao = ModellverwaltungDAO.getInstance();
		List<Einzelteil> teile = dao.findAllEinzelteileOfModell(em, modell);

		if (DEBUG)
			LOG.debug(END + "findAllEinzelteileOfModell");
		return teile;
	}

	@PermitAll
	public AngebotsModell findAktuellesAngebot() {
		if (DEBUG)
			LOG.debug(BEGIN + "findAktuellesAngebot");
		ModellverwaltungDAO dao = ModellverwaltungDAO.getInstance();
		AngebotsModell modell = dao.findAktuellesAngebot(em);

		if (DEBUG)
			LOG.debug(END + "findAktuellesAngebot");
		return modell;
	}

	@PermitAll
	public List<AngebotsModell> searchModell(Fahrradtyp typ,
			String radbezeichnung, float maxpreis) {
		if (DEBUG)
			LOG.debug(BEGIN + "findAktuellesAngebot");
		List<AngebotsModell> modelle = null;
		ModellverwaltungDAO dao = ModellverwaltungDAO.getInstance();
		modelle = dao.searchModell(em, typ, radbezeichnung, maxpreis);

		if (DEBUG)
			LOG.debug(END + "findAktuellesAngebot");
		return modelle;
	}
}
