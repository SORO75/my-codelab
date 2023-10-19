package de.kipf.shop.util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.kipf.shop.teile.Lagerverwaltung;
import de.kipf.shop.teile.LagerverwaltungLocal;
import de.kipf.shop.teile.Modellverwaltung;
import de.kipf.shop.teile.ModellverwaltungLocal;
import de.kipf.shop.teile.Teileverwaltung;
import de.kipf.shop.teile.TeileverwaltungLocal;
import de.kipf.shop.vorgaenge.Auftragsverwaltung;
import de.kipf.shop.vorgaenge.AuftragsverwaltungLocal;
import de.kipf.shop.vorgaenge.Warenkorbverwaltung;
import de.kipf.shop.vorgaenge.WarenkorbverwaltungLocal;
import de.kipf.shop.personen.Benutzerverwaltung;
import de.kipf.shop.personen.BenutzerverwaltungLocal;

public class EjbUtil {
	private static final Log LOG = LogFactory.getLog(EjbUtil.class);

	/**
	 */
	public static Teileverwaltung getTeileverwaltung()
			throws EjbNotFoundException {
		Teileverwaltung tv = null;
		try {
			final Context ctx = new InitialContext();
			tv = (Teileverwaltung) ctx.lookup(Teileverwaltung.JNDI_NAME);
			ctx.close();
		} catch (NamingException e) {
			e.printStackTrace();
			LOG
					.error("Kein SessionBean mit Namen "
							+ Teileverwaltung.JNDI_NAME);
			throw new EjbNotFoundException(Teileverwaltung.JNDI_NAME);
		}
		return tv;
	}

	public static Teileverwaltung getTeileverwaltungLocal()
			throws EjbNotFoundException {
		Teileverwaltung tv = null;
		try {
			final Context ctx = new InitialContext();
			tv = (Teileverwaltung) ctx
					.lookup(TeileverwaltungLocal.JNDI_NAME_LOCAL);
			ctx.close();
		} catch (NamingException e) {
			e.printStackTrace();
			LOG.error("Kein SessionBean mit Namen "
					+ TeileverwaltungLocal.JNDI_NAME_LOCAL);
			throw new EjbNotFoundException(TeileverwaltungLocal.JNDI_NAME_LOCAL);
		}
		return tv;
	}

	/**
	 */
	public static Benutzerverwaltung getBenutzerverwaltung()
			throws EjbNotFoundException {
		Benutzerverwaltung bv = null;
		try {
			final Context ctx = new InitialContext();
			bv = (Benutzerverwaltung) ctx.lookup(Benutzerverwaltung.JNDI_NAME);
			ctx.close();
		} catch (NamingException e) {
			LOG.error("Kein SessionBean mit Namen "
					+ Benutzerverwaltung.JNDI_NAME);
			throw new EjbNotFoundException(Benutzerverwaltung.JNDI_NAME);
		}
		return bv;
	}

	public static BenutzerverwaltungLocal getBenutzerverwaltungLocal()
			throws EjbNotFoundException {
		BenutzerverwaltungLocal bv = null;
		try {
			final Context ctx = new InitialContext();
			bv = (BenutzerverwaltungLocal) ctx
					.lookup(BenutzerverwaltungLocal.JNDI_NAME_LOCAL);
			ctx.close();
		} catch (NamingException e) {
			LOG.error("Kein SessionBean mit Namen "
					+ BenutzerverwaltungLocal.JNDI_NAME_LOCAL);
			throw new EjbNotFoundException(
					BenutzerverwaltungLocal.JNDI_NAME_LOCAL);
		}
		return bv;
	}

	/**
	 */
	public static Auftragsverwaltung getAuftragsverwaltung()
			throws EjbNotFoundException {
		Auftragsverwaltung av = null;
		try {
			final Context ctx = new InitialContext();
			av = (Auftragsverwaltung) ctx.lookup(Auftragsverwaltung.JNDI_NAME);
			ctx.close();
		} catch (NamingException e) {
			LOG.error("Kein SessionBean mit Namen "
					+ Auftragsverwaltung.JNDI_NAME);
			throw new EjbNotFoundException(Auftragsverwaltung.JNDI_NAME);
		}
		return av;
	}

	public static AuftragsverwaltungLocal getAuftragsverwaltungLocal()
			throws EjbNotFoundException {
		AuftragsverwaltungLocal av = null;
		try {
			final Context ctx = new InitialContext();
			av = (AuftragsverwaltungLocal) ctx
					.lookup(AuftragsverwaltungLocal.JNDI_NAME_LOCAL);
			ctx.close();
		} catch (NamingException e) {
			LOG.error("Kein SessionBean mit Namen "
					+ AuftragsverwaltungLocal.JNDI_NAME_LOCAL);
			throw new EjbNotFoundException(
					AuftragsverwaltungLocal.JNDI_NAME_LOCAL);
		}
		return av;
	}

	public static Lagerverwaltung getLagerverwaltung()
			throws EjbNotFoundException {
		Lagerverwaltung lv = null;
		try {
			final Context ctx = new InitialContext();
			lv = (Lagerverwaltung) ctx.lookup(Lagerverwaltung.JNDI_NAME);
			ctx.close();
		} catch (NamingException e) {
			e.printStackTrace();
			LOG
					.error("Kein SessionBean mit Namen "
							+ Lagerverwaltung.JNDI_NAME);
			throw new EjbNotFoundException(Lagerverwaltung.JNDI_NAME);
		}
		return lv;
	}

	public static Lagerverwaltung getLagerverwaltungLocal()
			throws EjbNotFoundException {
		Lagerverwaltung lv = null;
		try {
			final Context ctx = new InitialContext();
			lv = (Lagerverwaltung) ctx
					.lookup(LagerverwaltungLocal.JNDI_NAME_LOCAL);
			ctx.close();
		} catch (NamingException e) {
			e.printStackTrace();
			LOG.error("Kein SessionBean mit Namen "
					+ LagerverwaltungLocal.JNDI_NAME_LOCAL);
			throw new EjbNotFoundException(LagerverwaltungLocal.JNDI_NAME_LOCAL);
		}
		return lv;
	}

	public static Modellverwaltung getModellverwaltung()
			throws EjbNotFoundException {
		Modellverwaltung mv = null;
		try {
			final Context ctx = new InitialContext();
			mv = (Modellverwaltung) ctx.lookup(Modellverwaltung.JNDI_NAME);
			ctx.close();
		} catch (NamingException e) {
			e.printStackTrace();
			LOG.error("Kein SessionBean mit Namen "
					+ Modellverwaltung.JNDI_NAME);
			throw new EjbNotFoundException(Modellverwaltung.JNDI_NAME);
		}
		return mv;
	}

	public static Modellverwaltung getModellverwaltungLocal()
			throws EjbNotFoundException {
		Modellverwaltung mv = null;
		try {
			final Context ctx = new InitialContext();
			mv = (Modellverwaltung) ctx
					.lookup(ModellverwaltungLocal.JNDI_NAME_LOCAL);
			ctx.close();
		} catch (NamingException e) {
			e.printStackTrace();
			LOG.error("Kein SessionBean mit Namen "
					+ ModellverwaltungLocal.JNDI_NAME_LOCAL);
			throw new EjbNotFoundException(
					ModellverwaltungLocal.JNDI_NAME_LOCAL);
		}
		return mv;
	}

	public static Warenkorbverwaltung getWarenkorbverwaltung()
			throws EjbNotFoundException {
		Warenkorbverwaltung wv = null;
		try {
			final Context ctx = new InitialContext();
			wv = (Warenkorbverwaltung) ctx
					.lookup(Warenkorbverwaltung.JNDI_NAME);
			ctx.close();
		} catch (NamingException e) {
			e.printStackTrace();
			LOG.error("Kein SessionBean mit Namen "
					+ Warenkorbverwaltung.JNDI_NAME);
			throw new EjbNotFoundException(Warenkorbverwaltung.JNDI_NAME);
		}
		return wv;
	}

	public static Warenkorbverwaltung getWarenkorbverwaltungLocal()
			throws EjbNotFoundException {
		Warenkorbverwaltung wv = null;
		try {
			final Context ctx = new InitialContext();
			wv = (Warenkorbverwaltung) ctx
					.lookup(WarenkorbverwaltungLocal.JNDI_NAME_LOCAL);
			ctx.close();
		} catch (NamingException e) {
			e.printStackTrace();
			LOG.error("Kein SessionBean mit Namen "
					+ WarenkorbverwaltungLocal.JNDI_NAME_LOCAL);
			throw new EjbNotFoundException(WarenkorbverwaltungLocal.JNDI_NAME_LOCAL);
		}
		return wv;
	}
}
