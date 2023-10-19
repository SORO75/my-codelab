package de.kipf.shop.test;

import static de.kipf.shop.util.EjbUtil.getWarenkorbverwaltung;
import static de.kipf.shop.util.EjbUtil.getModellverwaltung;
import static de.kipf.shop.util.LoginUtil.LOGIN_DOMAIN_KIPF;
import static de.kipf.shop.util.LoginUtil.loadAuthConf;
import static de.kipf.shop.util.LoginUtil.login;
import static de.kipf.shop.util.LoginUtil.logout;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.security.auth.login.LoginContext;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.kipf.shop.personen.Benutzerverwaltung;
import de.kipf.shop.personen.db.Benutzer;
import de.kipf.shop.personen.db.BenutzerNotFoundException;
import de.kipf.shop.teile.db.Modell;
import de.kipf.shop.teile.db.ModellNotFoundException;
import de.kipf.shop.util.EjbNotFoundException;
import de.kipf.shop.util.EjbUtil;
import de.kipf.shop.vorgaenge.Auftragsverwaltung;
import de.kipf.shop.vorgaenge.WarenkorbNotFoundException;
import de.kipf.shop.vorgaenge.Warenkorbverwaltung;
import de.kipf.shop.vorgaenge.db.Auftrag;
import de.kipf.shop.vorgaenge.db.AuftragNotFoundException;
import de.kipf.shop.vorgaenge.db.Auftragsstatus;
import de.kipf.shop.vorgaenge.db.AuftragsstatustypNotFoundException;
import de.kipf.shop.vorgaenge.db.Warenkorb;
import de.kipf.shop.vorgaenge.db.WarenkorbPosition;
import de.kipf.shop.vorgaenge.db.WarenkorbPositionNotFoundException;

@SuppressWarnings("unused")
public class WarenkorbverwaltungTest {
	private static final String USERNAME = "tobi";
	private static final String PASSWORD = "tobi";

	private static final Long NICHT_EXISTIERENDER_WARENKORB = new Long(1999999);
	private static final Long EXISTIERENDER_WARENKORB = new Long(1);
	private static final Long EXISTIERENDES_MODELL = Long.valueOf(2L);

	private static final int ANZAHL = 4;

	private static Warenkorbverwaltung wwProxy;
	private static Warenkorb testWarenkorb;

	private LoginContext loginCtx;

	/**
	 */
	@BeforeClass
	public static void setupLoginAndProxy()
			throws UnsupportedEncodingException, EjbNotFoundException {
		loadAuthConf();
		wwProxy = getWarenkorbverwaltung();
		assertNotNull(wwProxy);
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
	public void insertWarenkorb() {
		Warenkorb warenkorb = new Warenkorb();
		warenkorb = wwProxy.insertWarenkorb(warenkorb);
		testWarenkorb = warenkorb;
		assertNotNull(warenkorb);
	}

	@Test(expected = WarenkorbNotFoundException.class)
	public void findWarenkorb() throws WarenkorbNotFoundException {
		wwProxy.findWarenkorbById(NICHT_EXISTIERENDER_WARENKORB);
	}

	@Test
	public void createWarenkorbPosition() throws ModellNotFoundException,
			EjbNotFoundException, WarenkorbNotFoundException {
		Modell modell = getModellverwaltung().findModellById(
				EXISTIERENDES_MODELL);
		wwProxy.addPositionToWarenkorb(wwProxy
				.findWarenkorbById(EXISTIERENDER_WARENKORB), modell, ANZAHL);
		assertNotNull(modell);
	}

	@Test
	public void testGesamtPreis() throws EjbNotFoundException,
			WarenkorbNotFoundException {
		Warenkorb warenkorb = wwProxy.findWarenkorbById(2L);
		float gesamtPreis = 0;
		gesamtPreis = wwProxy.gesamtPreisOfWarenkorb(warenkorb);
		assertTrue(gesamtPreis > 0);
	}

	@Test
	public void testWarenkorbZuAuftrag() throws WarenkorbNotFoundException,
			AuftragsstatustypNotFoundException, EjbNotFoundException,
			BenutzerNotFoundException, AuftragNotFoundException {
		Warenkorb warenkorb = wwProxy
				.findWarenkorbById(EXISTIERENDER_WARENKORB);
		Auftragsverwaltung av = EjbUtil.getAuftragsverwaltung();
		Benutzerverwaltung bv = EjbUtil.getBenutzerverwaltung();
		assertNotNull(warenkorb);
		Auftrag auftrag = new Auftrag();
		List<Auftragsstatus> auftragstati = new ArrayList<Auftragsstatus>();
		Auftragsstatus status = new Auftragsstatus(auftrag, av
				.findAuftragsstatustyp("received"), new Date());
		assertNotNull(status);
		auftragstati.add(status);

		Benutzer benutzer = bv.findBenutzer("susi");

		auftrag.setAuftragsstati(auftragstati);
		auftrag.setBenutzer(benutzer);
		auftrag = av.createAuftrag(auftrag);
		assertNotNull(auftrag);

		wwProxy.doWarenkorbZuAuftrag(warenkorb, auftrag);
	}

	@Test(expected = WarenkorbPositionNotFoundException.class)
	public void testDeletePosition() throws WarenkorbPositionNotFoundException {
		wwProxy.deleteWarenkorbPositionById(EXISTIERENDER_WARENKORB);
		wwProxy.deleteWarenkorbPositionById(EXISTIERENDER_WARENKORB);
	}
}
