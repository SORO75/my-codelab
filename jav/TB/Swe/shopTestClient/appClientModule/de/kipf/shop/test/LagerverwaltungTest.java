package de.kipf.shop.test;

import static de.kipf.shop.util.EjbUtil.getTeileverwaltung;
import static de.kipf.shop.util.EjbUtil.getLagerverwaltung;
import static de.kipf.shop.util.LoginUtil.LOGIN_DOMAIN_KIPF;
import static de.kipf.shop.util.LoginUtil.loadAuthConf;
import static de.kipf.shop.util.LoginUtil.login;
import static de.kipf.shop.util.LoginUtil.logout;
import static de.kipf.shop.teile.db.Lagerbuchung.ABGANG;
import static de.kipf.shop.teile.db.Lagerbuchung.ZUGANG;
import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.security.auth.login.LoginContext;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.kipf.shop.teile.Lagerverwaltung;
import de.kipf.shop.teile.db.Einzelteil;
import de.kipf.shop.teile.db.EinzelteilNotFoundException;
import de.kipf.shop.teile.db.Lagerbuchung;
import de.kipf.shop.util.EjbNotFoundException;

public class LagerverwaltungTest {
	private static final String USERNAME = "tobi";
	private static final String PASSWORD = "tobi";	
	
	private static final Long EINZELTEIL_ID = new Long(1);
	private static final Long EINZELTEIL_ID_BESTAND_CHECK = new Long(7);
	private static final int ZUGANG_MENGE = 150;

	private static Lagerverwaltung lwProxy;
	private LoginContext loginCtx;

	/**
	 */
	@BeforeClass
	public static void setupLoginAndProxy()
			throws UnsupportedEncodingException, EjbNotFoundException {
		loadAuthConf();
		lwProxy = getLagerverwaltung();
		assertNotNull(lwProxy);
	}

	/**
	 */
	@Before
	public void loginClient() {
		loginCtx = login(LOGIN_DOMAIN_KIPF, USERNAME, PASSWORD);
	}

	/**
	 */
	@After
	public void logoutClient() {
		logout(loginCtx);
	}
	
	/**
	 * eine Buchung-Datensatz einf&uuml;gen
	 * @throws EjbNotFoundException 
	 * @throws EinzelteilNotFoundException 
	 *
	 */
	@Test
	public void insertBuchung() throws EinzelteilNotFoundException, EjbNotFoundException {
		Lagerbuchung lb = new Lagerbuchung();
		lb.setBuchungsart(ABGANG);
		lb.setBuchungsdatum(new Date());
		lb.setMenge(1);
		Einzelteil teil = getTeileverwaltung().findEinzelteil(EINZELTEIL_ID);
		assertNotNull(teil);
		lb.setEinzelteil(teil);
		lb = lwProxy.insertLagerbuchung(lb);
		assertNotNull(lb);
	}
	
	@Test
	public void checkLagerbestand() throws EinzelteilNotFoundException, EjbNotFoundException {
		final Einzelteil teil = getTeileverwaltung().findEinzelteil(EINZELTEIL_ID_BESTAND_CHECK);
		long bestandAlt = lwProxy.lagerBestand(teil);				
		assertNotNull(teil);
		Lagerbuchung lb = new Lagerbuchung();
		lb.setBuchungsart(ZUGANG);
		lb.setBuchungsdatum(new Date());
		lb.setMenge(ZUGANG_MENGE);
		lb.setEinzelteil(teil);		
		lb = lwProxy.insertLagerbuchung(lb);
		long bestandNeu = lwProxy.lagerBestand(teil);
		
		assertTrue(bestandAlt < bestandNeu);
	}
	
}
