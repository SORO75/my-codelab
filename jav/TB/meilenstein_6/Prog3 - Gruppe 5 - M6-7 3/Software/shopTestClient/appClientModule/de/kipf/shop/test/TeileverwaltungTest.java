package de.kipf.shop.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;

import static de.kipf.shop.util.LoginUtil.LOGIN_DOMAIN_KIPF;
import static de.kipf.shop.util.LoginUtil.loadAuthConf;
import static de.kipf.shop.util.LoginUtil.login;
import static de.kipf.shop.util.LoginUtil.logout;
import static de.kipf.shop.util.EjbUtil.getTeileverwaltung;

import java.io.UnsupportedEncodingException;
import javax.security.auth.login.LoginContext;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.kipf.shop.util.EjbNotFoundException;
import de.kipf.shop.teile.Teileverwaltung;
import de.kipf.shop.teile.db.Einzelteil;
import de.kipf.shop.teile.db.EinzelteilNotFoundException;
import de.kipf.shop.teile.db.Teiltyp;
import de.kipf.shop.teile.db.TeiltypNotFoundException;

public class TeileverwaltungTest {
	private static final String USERNAME = "tobi";
	private static final String PASSWORD = "tobi";

	private static final Long NICHT_VORHANDES_EINZELTEIL = Long.valueOf(10000);

	private static final Long TEILTYP = new Long(10);
	private static final String EINZELTEIL_BESCHREIBUNG = "Klickpedale Shimano";
	private static final float EINZELTEIL_PREIS = 29.33F;
	private static final float NEUER_EINZELTEIL_PREIS = 888.95F;
	private static Teileverwaltung twProxy;
	private LoginContext loginCtx;

	/**
	 */
	@BeforeClass
	public static void setupLoginAndProxy()
			throws UnsupportedEncodingException, EjbNotFoundException {
		loadAuthConf();
		twProxy = getTeileverwaltung();
		assertNotNull(twProxy);
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
	 * Testet ob die Exception geworfen wurde
	 */
	@Test(expected = EinzelteilNotFoundException.class)
	public void findEinzelteil() throws EinzelteilNotFoundException {
		twProxy.findEinzelteil(NICHT_VORHANDES_EINZELTEIL);
	}

	/**
	 * Testet Methode findAllEinzelteile()
	 */
	@Test
	public void findAllEinzelteile() {
		assertNotNull(twProxy.findAllEinzelteile());
	}

	/**
	 * Hier wird ein Einzelteil erzeugt und gleich ein Test gemacht ob es
	 * richtig abgespeichert wird
	 * 
	 * @throws TeiltypNotFoundException
	 * @throws EinzelteilNotFoundException
	 */
	@Test
	public void createEinzelteil() throws TeiltypNotFoundException,
			EinzelteilNotFoundException {
		Einzelteil teil = new Einzelteil();
		teil.setBeschreibung(EINZELTEIL_BESCHREIBUNG);
		teil.setPreis(EINZELTEIL_PREIS);
		teil.setTeiltyp(new Teiltyp());
		teil = twProxy.insertEinzelteil(teil, TEILTYP);
		Einzelteil neuesTeil = twProxy.findEinzelteil(teil.getId()); 
		assertEquals(neuesTeil, teil);
	}

	/**
	 * Erstellt ein Einzelteil und löscht es gleich wieder
	 * 
	 * @throws TeiltypNotFoundException
	 * @throws EinzelteilNotFoundException
	 */
	@Test(expected = EinzelteilNotFoundException.class)
	public void deleteEinzelteil() throws TeiltypNotFoundException,
			EinzelteilNotFoundException {
		Einzelteil teil = new Einzelteil();
		teil.setBeschreibung(EINZELTEIL_BESCHREIBUNG);
		teil.setPreis(EINZELTEIL_PREIS);
		teil.setTeiltyp(new Teiltyp());
		teil = twProxy.insertEinzelteil(teil, TEILTYP);
		twProxy.deleteEinzelteil(teil);

		Einzelteil neuesTeil = twProxy.findEinzelteil(teil.getId());
		assertNull(neuesTeil);
	}
	
	/**
	 * Preis von einem Einzelteil ändern
	 * @throws TeiltypNotFoundException 
	 * 
	 */
	@Test
	public void updatePreis() throws TeiltypNotFoundException {
		Einzelteil teil = new Einzelteil();
		teil.setBeschreibung(EINZELTEIL_BESCHREIBUNG);
		teil.setPreis(EINZELTEIL_PREIS);
		teil.setTeiltyp(new Teiltyp());
		teil = twProxy.insertEinzelteil(teil, TEILTYP);		
		teil.setPreis(NEUER_EINZELTEIL_PREIS);		
		
		teil = twProxy.updateEinzelteil(teil);
		assertEquals(teil.getPreis(),NEUER_EINZELTEIL_PREIS,0);
	}
}
