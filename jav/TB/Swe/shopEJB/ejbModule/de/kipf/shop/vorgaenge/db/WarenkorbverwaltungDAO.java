package de.kipf.shop.vorgaenge.db;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.kipf.shop.teile.db.Modell;
import de.kipf.shop.teile.db.ModellNotFoundException;
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

	public void addPositionToWarenkorb(EntityManager em, Warenkorb korb,
			Modell modell, int anzahl) throws WarenkorbNotFoundException,
			ModellNotFoundException {
		if (DEBUG)
			LOG.debug(BEGIN + "addPositionToWarenkorb Warenkorb id="
					+ korb.getId() + " Modell id=" + modell.getId()
					+ " Anzahl=" + anzahl);
		final Warenkorb managedKorb = em.find(Warenkorb.class, korb.getId());
		if (managedKorb == null) {
			LOG.warn("Warenkorb mit id=" + korb.getId() + "existiert nicht");
			throw new WarenkorbNotFoundException("Warenkorb"
					+ " nicht vorhanden");
		}

		final Modell managedModell = em.find(Modell.class, modell.getId());
		if (managedModell == null) {
			LOG.warn("Modell existiert nicht");
			throw new ModellNotFoundException("Modell nicht vorhanden");
		}

		WarenkorbPosition position = new WarenkorbPosition();
		position.setModellId(managedModell);
		position.setWarenkorbId(managedKorb);
		position.setAnzahl(anzahl);
		em.persist(position);

		if (DEBUG)
			LOG.debug(END + "addPositionToWarenkorb");
	}

	@SuppressWarnings("unchecked")
	public List<WarenkorbPosition> getPositionVonWarenkorb(EntityManager em,
			Warenkorb warenkorb) throws WarenkorbNotFoundException {
		if (DEBUG)
			LOG.debug(BEGIN + "getPositionenVonWarenkorb warenkorbid="
					+ warenkorb.getId());
		final Warenkorb managedKorb = em.find(Warenkorb.class, warenkorb
				.getId());
		if (managedKorb == null) {
			LOG.warn("getPositionVonWarenkorb Position mit id="
					+ warenkorb.getId() + " nicht gefunden");
			throw new WarenkorbNotFoundException("Position mit id="
					+ warenkorb.getId() + " nicht gefunden");
		}
		Query query = em.createNamedQuery(Warenkorb.WARENKORB_POSITIONEN);
		query.setParameter("wid", managedKorb);
		List<WarenkorbPosition> position = query.getResultList();

		if (DEBUG)
			LOG.debug(END + "getPositionVonWarenkorb");
		return position;
	}

	public void deleteWarenkorbPositionById(EntityManager em, Long id)
			throws WarenkorbPositionNotFoundException {
		if (DEBUG)
			LOG.debug(BEGIN
					+ "deleteWarenkorbPositionById warenkorbPositionid=" + id);
		final WarenkorbPosition managedPosition = em.find(
				WarenkorbPosition.class, id);
		if (managedPosition == null) {
			LOG.warn("deleteWarenkorbPositionById Position mit id=" + id
					+ " nicht gefunden");
			throw new WarenkorbPositionNotFoundException("Position mit id="
					+ id + " nicht gefunden");
		}
		em.remove(managedPosition);

		if (DEBUG)
			LOG.debug(END + "deleteWarenkorbPositionById");
	}
	
	public float gesamtPreisOfWarenkorb(EntityManager em, Warenkorb warenkorb) throws WarenkorbNotFoundException {
		if (DEBUG)
			LOG.debug(BEGIN + "gesamtPreisOfWarenkorb id=" + warenkorb.getId());
		final Warenkorb managedKorb = em.find(Warenkorb.class, warenkorb
				.getId());
		if (managedKorb == null) {
			LOG.warn("getPositionVonWarenkorb Warenkorb mit id="
					+ warenkorb.getId() + " nicht gefunden");
			throw new WarenkorbNotFoundException("Warenkorb mit id="
					+ warenkorb.getId() + " nicht gefunden");
		}		
		
		Query query = em.createNamedQuery(Warenkorb.WARENKORB_GESAMTPREIS);
		query.setParameter("wid", managedKorb);
		float gesamtPreis = 0;
		Double dbl =  (Double) query.getSingleResult();
		gesamtPreis = dbl==null?0F:dbl.floatValue();
		
		if (DEBUG)
			LOG.debug(END + "gesamtPreisOfWarenkorb");
		return gesamtPreis;
	}
	
	public void doWarenkorbZuAuftrag(EntityManager em, Warenkorb warenkorb, Auftrag auftrag) throws WarenkorbNotFoundException, AuftragNotFoundException {
		if (DEBUG)
			LOG.debug(BEGIN + "doWarenkorbZuAuftrag Wid=" + warenkorb.getId() + " , Aid" + auftrag.getId());
		final Warenkorb managedKorb = em.find(Warenkorb.class, warenkorb
				.getId());
		if (managedKorb == null) {
			LOG.warn("doWarenkorbZuAuftrag Warenkorb mit id="
					+ warenkorb.getId() + " nicht gefunden");
			throw new WarenkorbNotFoundException("Warenkorb mit id="
					+ warenkorb.getId() + " nicht gefunden");
		}	
		final Auftrag managedAuftrag = em.find(Auftrag.class, auftrag
				.getId());
		if (managedAuftrag == null) {
			LOG.warn("doWarenkorbZuAuftrag Auftrag mit id="
					+ auftrag.getId() + " nicht gefunden");
			throw new AuftragNotFoundException("Auftrag mit id="
					+ auftrag.getId() + " nicht gefunden");
		}	
		managedKorb.setAuftrag(auftrag);
		em.merge(managedKorb);		
		
		if (DEBUG)
			LOG.debug(END + "doWarenkorbZuAuftrag");
	}
}
