package de.kipf.shop.teile;

import java.util.Collection;

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

import de.kipf.shop.teile.db.Einzelteil;
import de.kipf.shop.teile.db.EinzelteilNotFoundException;
import de.kipf.shop.teile.db.TeileverwaltungDAO;
import de.kipf.shop.teile.db.Teiltyp;
import de.kipf.shop.teile.db.TeiltypNotFoundException;
import static de.kipf.shop.util.EjbConstants.PERSISTENCE_CONTEXT_SHOP;
import static de.kipf.shop.util.EjbConstants.ROLLE_MITARBEITER;
import static de.kipf.shop.util.EjbConstants.LOGIN_DOMAIN_KIPF;
import static de.kipf.shop.util.EjbConstants.BEGIN;
import static de.kipf.shop.util.EjbConstants.END;

@Stateless
@Remote(Teileverwaltung.class)
@Local(TeileverwaltungLocal.class)
@RolesAllowed(ROLLE_MITARBEITER)
@SecurityDomain(LOGIN_DOMAIN_KIPF)
public class TeileverwaltungBean implements Teileverwaltung {
	protected static final Log LOG = LogFactory
			.getLog(TeileverwaltungBean.class);
	protected static final boolean DEBUG = LOG.isDebugEnabled();
	protected static final boolean TRACE = LOG.isTraceEnabled();

	@PersistenceContext(name = PERSISTENCE_CONTEXT_SHOP)
	private EntityManager em;

	@PermitAll
	public Collection<Einzelteil> findAllEinzelteile() {
		if (DEBUG)
			LOG.debug(BEGIN + "findAllEinzelteile");
		final TeileverwaltungDAO dao = TeileverwaltungDAO.getInstance();
		final Collection<Einzelteil> einzelteile = dao.findAllEinzelteile(em);

		if (DEBUG)
			LOG.debug(END + "findAllEinzelteile");
		return einzelteile;
	}

	public Einzelteil insertEinzelteil(Einzelteil einzelteil, Long teiltyp)
			throws TeiltypNotFoundException {
		if (DEBUG)
			LOG.debug(BEGIN + "createEinzelteil " + einzelteil);
		final TeileverwaltungDAO dao = TeileverwaltungDAO.getInstance();
		einzelteil.setTeiltyp(dao.findTeiltypById(em, teiltyp));
		Einzelteil teil = null;
		teil = dao.insertEinzelteil(em, einzelteil);

		if (DEBUG)
			LOG.debug(END + "createEinzelteil " + einzelteil);
		return teil;
	}

	public Einzelteil findEinzelteil(Long id)
			throws EinzelteilNotFoundException {
		if (DEBUG)
			LOG.debug(BEGIN + "findEinzelteil " + id);
		final TeileverwaltungDAO dao = TeileverwaltungDAO.getInstance();
		final Einzelteil einzelteil = dao.findEinzelteil(em, id);

		if (DEBUG)
			LOG.debug(END + "findEinzelteil " + id);
		return einzelteil;
	}

	public void deleteEinzelteil(Einzelteil teil) {
		if (DEBUG)
			LOG.debug(BEGIN + "deleteEinzelteil id=" + teil.getId());
		final TeileverwaltungDAO dao = TeileverwaltungDAO.getInstance();

		dao.deleteEinzelteil(em, teil);

		if (DEBUG)
			LOG.debug(END + "deleteEinzelteil");
	}

	public Einzelteil updateEinzelteil(Einzelteil teil) {
		if (DEBUG)
			LOG.debug(BEGIN + "updateEinzelteil id=" + teil.getId());
		Einzelteil einzelteil = null;
		final TeileverwaltungDAO dao = TeileverwaltungDAO.getInstance();
		einzelteil = dao.updateEinzelteil(em, teil);

		if (DEBUG)
			LOG.debug(END + "updateEinzelteil");
		return einzelteil;
	}
	
	@PermitAll
	public Collection<Teiltyp> findAllTeiltypen() {
		if (DEBUG)
			LOG.debug(BEGIN + "findAllTeiltypen");
		final TeileverwaltungDAO dao = TeileverwaltungDAO.getInstance();
		final Collection<Teiltyp> teiltypen = dao.findAllTeiltypen(em);

		if (DEBUG)
			LOG.debug(END + "findAllTeiltypen");
		return teiltypen;
	}
}
