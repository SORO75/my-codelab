package de.kipf.shop.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import static de.kipf.shop.util.LoginUtil.LOGIN_DOMAIN_KIPF;
import static de.kipf.shop.util.LoginUtil.loadAuthConf;
import static de.kipf.shop.util.LoginUtil.login;
import static de.kipf.shop.util.LoginUtil.logout;
import static de.kipf.shop.util.EjbUtil.getModellverwaltung;
import static de.kipf.shop.util.EjbUtil.getTeileverwaltung;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.security.auth.login.LoginContext;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.kipf.shop.util.EjbNotFoundException;
import de.kipf.shop.teile.Modellverwaltung;
import de.kipf.shop.teile.Teileverwaltung;
import de.kipf.shop.teile.db.AngebotsModell;
import de.kipf.shop.teile.db.Einzelteil;
import de.kipf.shop.teile.db.EinzelteilNotFoundException;
import de.kipf.shop.teile.db.FahrradtypNotFoundException;
import de.kipf.shop.teile.db.KundenModell;
import de.kipf.shop.teile.db.Modell;
import de.kipf.shop.teile.db.ModellNotFoundException;
import de.kipf.shop.teile.db.Teiltyp;
import de.kipf.shop.teile.db.TeiltypNotFoundException;

public class ModellverwaltungTest {
	private static final String USERNAME = "tobi";
	private static final String PASSWORD = "tobi";
	private static final Long MODELL_TYP = new Long(1);
	private static final Long MODELL_TYP_FALSCH = new Long(100000000);

	private static final Long EINZELTEIL_ID = new Long(17);
	private static final Long EXISTIERENDES_MODELL = new Long(1);
	private static final Long NICHT_EXISTIERENDES_MODELL = new Long(20000);

	private static Modellverwaltung mwProxy;
	private LoginContext loginCtx;

	/**
	 * wird innerhalb dieser Klasse in verschiedenen Testmethoden benutzt
	 */
	private static Modell testModell;

	/**
	 */
	@BeforeClass
	public static void setupLoginAndProxy()
			throws UnsupportedEncodingException, EjbNotFoundException {
		loadAuthConf();
		mwProxy = getModellverwaltung();
		assertNotNull(mwProxy);
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

	@Test
	public void createModell() throws FahrradtypNotFoundException {
		Modell modell = new AngebotsModell();
		modell.setBeschreibung("Mein Modell");
		modell.setBildUrl(null);
		modell.setDatum(new Date());
		modell.setPreis(999.99F);
		modell.setStartzeitpunkt(new Date());
		modell.setEndzeitpunkt(new Date());
		modell = mwProxy.insertModell(modell, MODELL_TYP);
		testModell = modell;
		assertNotNull(modell);
	}

	@Test(expected = FahrradtypNotFoundException.class)
	public void createModellException() throws FahrradtypNotFoundException {
		Modell modell = new AngebotsModell();
		modell.setBeschreibung("Mein Modell");
		modell.setBildUrl(null);
		modell.setDatum(new Date());
		modell.setPreis(999.99F);
		modell.setStartzeitpunkt(new Date());
		modell.setEndzeitpunkt(new Date());
		modell = mwProxy.insertModell(modell, MODELL_TYP_FALSCH);
		assertNotNull(modell);
	}

	@Test
	public void addTeilToModell() throws EinzelteilNotFoundException,
			EjbNotFoundException, ModellNotFoundException {
		Einzelteil teil = getTeileverwaltung().findEinzelteil(EINZELTEIL_ID);
		mwProxy.addEinzelteilToModell(testModell, teil);
		assertTrue(true);
	}

	@Test(expected = ModellNotFoundException.class)
	public void addTeilToModellException() throws EinzelteilNotFoundException,
			EjbNotFoundException, ModellNotFoundException {
		Einzelteil teil = getTeileverwaltung().findEinzelteil(EINZELTEIL_ID);
		Modell modell = new AngebotsModell();
		modell.setBeschreibung("Mein Modell");
		modell.setBildUrl(null);
		modell.setDatum(new Date());
		modell.setPreis(999.99F);
		modell.setStartzeitpunkt(new Date());
		modell.setEndzeitpunkt(new Date());
		mwProxy.addEinzelteilToModell(modell, teil);
		assertTrue(true);
	}

	@Test
	public void findAllModelle() {
		List<Modell> modelle = mwProxy.findAllModelle();
		assertNotNull(modelle);
	}

	@Test
	public void findModell() throws ModellNotFoundException {
		Modell modell = mwProxy.findModellById(EXISTIERENDES_MODELL);
		assertNotNull(modell);
	}

	@Test(expected = ModellNotFoundException.class)
	public void findModellException() throws ModellNotFoundException {
		Modell modell = mwProxy.findModellById(NICHT_EXISTIERENDES_MODELL);
		assertNull(modell);
	}

	@Test
	public void findEinzelteileVonModell() throws ModellNotFoundException {
		List<Einzelteil> einzelteile = mwProxy
				.findAllEinzelteileOfModell(mwProxy.findModellById(EXISTIERENDES_MODELL));
		assertNotNull(einzelteile);
	}

	@Test(expected = ModellNotFoundException.class)
	public void findEinzelteileVonModellException()
			throws ModellNotFoundException {
		Modell modell = new KundenModell();
		modell.setBeschreibung("Ein Modell");
		modell.setBildUrl(null);
		modell.setDatum(new Date());
		modell.setPreis(349.99F);
		modell.setStartzeitpunkt(new Date());
		modell.setEndzeitpunkt(new Date());
		List<Einzelteil> einzelteile = mwProxy
				.findAllEinzelteileOfModell(modell);		
	}
}