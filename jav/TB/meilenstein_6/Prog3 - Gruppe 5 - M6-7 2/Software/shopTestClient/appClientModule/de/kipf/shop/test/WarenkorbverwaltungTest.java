package de.kipf.shop.test;

import static de.kipf.shop.util.EjbUtil.getWarenkorbverwaltung;
import static de.kipf.shop.util.LoginUtil.LOGIN_DOMAIN_KIPF;
import static de.kipf.shop.util.LoginUtil.loadAuthConf;
import static de.kipf.shop.util.LoginUtil.login;
import static de.kipf.shop.util.LoginUtil.logout;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.UnsupportedEncodingException;

import javax.security.auth.login.LoginContext;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.kipf.shop.util.EjbNotFoundException;
import de.kipf.shop.vorgaenge.WarenkorbNotFoundException;
import de.kipf.shop.vorgaenge.Warenkorbverwaltung;
import de.kipf.shop.vorgaenge.db.Warenkorb;

public class WarenkorbverwaltungTest {
	private static final String USERNAME = "tobi";
	private static final String PASSWORD = "tobi";

	private static final Long NICHT_EXISTIERENDER_WARENKORB = new Long(1999999);

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
		Warenkorb warenkorb = wwProxy
				.findWarenkorbById(NICHT_EXISTIERENDER_WARENKORB);		
	}

}
