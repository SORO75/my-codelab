package de.kipf.shop.test;

import static de.kipf.shop.util.EjbConstants.ROLLE_BENUTZER;
import static de.kipf.shop.util.EjbConstants.ROLLE_ADMIN;
import static de.kipf.shop.util.EjbUtil.getBenutzerverwaltung;
import static de.kipf.shop.util.LoginUtil.LOGIN_DOMAIN_KIPF;
import static de.kipf.shop.util.LoginUtil.loadAuthConf;
import static de.kipf.shop.util.LoginUtil.login;
import static de.kipf.shop.util.LoginUtil.logout;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import de.kipf.shop.personen.BenutzerDeleteAuftragException;
import de.kipf.shop.personen.BenutzernameAlreadyExistsException;
import de.kipf.shop.personen.BenutzernameNotValidException;
import de.kipf.shop.personen.Benutzerverwaltung;
import de.kipf.shop.personen.PasswordNotValidException;
import de.kipf.shop.personen.db.Benutzer;
import de.kipf.shop.personen.db.BenutzerNotFoundException;
import de.kipf.shop.personen.db.Benutzergruppe;
import de.kipf.shop.personen.db.BenutzergruppeNotFoundException;
import de.kipf.shop.personen.db.Benutzer.Geschlecht;
import de.kipf.shop.util.EjbNotFoundException;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.EJBAccessException;
import javax.security.auth.login.FailedLoginException;
import javax.security.auth.login.LoginContext;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class BenutzerverwaltungTest {
	private static final String USERNAME_SACHBEARBEITER = "sachbearbeiter";

	private static final String PASSWORD_SACHBEARBEITER = "sachbearbeiter";

	private static final String USERNAME_USER = "user";
	
	private static final String USERNAME_USER_LOCKED = "gesperrt";

	private static final String PASSWORD_USER = "user";

	private static final String PASSWORD_FALSCH = "wrongpassword";

	private static final String USERNAME_ADMIN = "admin";

	private static final String PASSWORD_ADMIN = "admin";

	private static final String VORHANDENER_KUNDE_NACHNAME = "Kirchner";

	private static final String NICHT_VORHANDENER_KUNDE_NACHNAME = "Marx";

	private static final Long NACHNAME_AENDERN_KUNDE_ID = Long.valueOf(4);

	private static final String NACHNAME_AENDERN_NEUER_NACHNAME = "Doppel-Name";

	private static final String NACHNAME_AENDERN_NEUER_NACHNAME_ROLLBACK = "Deltaz";

	private static final Long KUNDE_ID_MIT_KONTAKTEN = Long.valueOf(1);

	private static final Long KUNDE_ID_OHNE_KONTAKTE = Long.valueOf(2);

	private static final Long VORHANDENER_KUNDE_ID_PASSWORD = Long.valueOf(4);

	private static final String VORHANDENER_KUNDE_USERNAME = "DeDi-001";

	private static final String VORHANDENER_KUNDE_NEUES_PASSWORD = "P1";

	private static final String NEUER_BENUTZER_VORNAME = "Manfred";

	private static final String NEUER_BENUTZER_NACHNAME = "Mustermann";

	private static final String NEUER_BENUTZER_BENUTZERNAME = "m.mustermann";
	
	private static final String NEUER_BENUTZER_BENUTZERNAME_HASH = "B9yLHUi0/Piu2NsrFVEfwB3QD9LHEjByctuxTSNh0X8=";

	private static final String NEUER_BENUTZER_EMAIL = "no@email.com";

	private static final Date NEUER_BENUTZER_GEBDATUM = new Date();

	private static final Geschlecht NEUER_BENUTZER_GESCHLECHT = Benutzer.Geschlecht.m;

	private static final boolean NEUER_BENUTZER_GESPERRT = false;

	private static final boolean NEUER_BENUTZER_NEWSLETTER = false;

	private static final String NEUER_BENUTZER_ORT = "Stuttgart";

	private static final String NEUER_BENUTZER_PLZ = "70173";

	private static final Date NEUER_BENUTZER_REGDATUM = new Date();

	private static final String NEUER_BENUTZER_STRASSE = "Bolzstr. 8";

	private static final String PASSWORTHASH_AENDERN = "XXXXXXXXXX";

	private static final String DELETE_BENUTZER_ID_OHNE_AUFTRAEGE = "user";

	private static final String DELETE_BENUTZER_ID_MIT_AUFTRAEGE = "susi";

	private static Benutzerverwaltung bvProxy;

	private LoginContext loginCtx;

	/**
	 */
	@BeforeClass
	public static void setupLoginAndProxy()
			throws UnsupportedEncodingException, EjbNotFoundException {
		loadAuthConf();
		bvProxy = getBenutzerverwaltung();
		assertNotNull(bvProxy);
	}

	/**
	 */
	@Before
	public void loginClient() {
		loginCtx = login(LOGIN_DOMAIN_KIPF, USERNAME_SACHBEARBEITER,
				PASSWORD_SACHBEARBEITER);
	}

	/**
	 */
	@After
	public void logoutClient() {
		logout(loginCtx);
	}

	@Test
	public void connectionAsSachbearbeiterTest() {
		final String str = "hallo";
		String result = bvProxy.simpleConnectionTest(str);
		assertEquals(result, str);
	}

	@Test
	public void connectionAsAdminTest() {
		logout(loginCtx);
		loginCtx = login(LOGIN_DOMAIN_KIPF, USERNAME_ADMIN, PASSWORD_ADMIN);

		final String str = "hallo";
		String result = bvProxy.simpleConnectionTest(str);
		assertEquals(result, str);
	}

	@Test
	public void connectionAsBenutzerTest() {
		logout(loginCtx);
		loginCtx = login(LOGIN_DOMAIN_KIPF, USERNAME_USER, PASSWORD_USER);

		final String str = "hallo";
		bvProxy.simpleConnectionTest(str);
	}
	
	@Test
	public void denyConnectionAsLockedBenutzerTest() {
		logout(loginCtx);
		loginCtx = login(LOGIN_DOMAIN_KIPF, USERNAME_USER_LOCKED, PASSWORD_USER);

		final String str = "hallo";
		bvProxy.simpleConnectionTest(str);
	}

	@Test
	public void connectionAsNobodyTest() {
		logout(loginCtx);

		final String str = "hallo";
		bvProxy.simpleConnectionTest(str);
	}

	/**
	 */
	@Test
	public void findBenutzerMitNachnameVorhanden()
			throws BenutzerNotFoundException {
		final List<Benutzer> benutzer = bvProxy
				.findBenutzerByName(VORHANDENER_KUNDE_NACHNAME);
		for (Benutzer b : benutzer) {
			assertEquals(VORHANDENER_KUNDE_NACHNAME, b.getNachname());
		}
	}

	/**
	 */
	@Test(expected = BenutzerNotFoundException.class)
	public void findBenutzerMitNachnameNichtVorhanden()
			throws BenutzerNotFoundException {
		bvProxy.findBenutzerByName(NICHT_VORHANDENER_KUNDE_NACHNAME);
	}

	// TODO Probleme beim Vergleich!
	@Test
	public void createBenutzer() throws BenutzergruppeNotFoundException,
			PasswordNotValidException, BenutzernameNotValidException,
			BenutzernameAlreadyExistsException, BenutzerNotFoundException {
		final Benutzergruppe benutzerRolle = bvProxy
				.findBenutzergruppe(ROLLE_BENUTZER);
		final Benutzergruppe adminRolle = bvProxy
				.findBenutzergruppe(ROLLE_ADMIN);
		final Set<Benutzergruppe> benutzerRolleSet = new HashSet<Benutzergruppe>();
		benutzerRolleSet.add(benutzerRolle);
		final Set<Benutzergruppe> adminRolleSet = new HashSet<Benutzergruppe>();
		adminRolleSet.add(adminRolle);

		Benutzer neuerBenutzer = new Benutzer();
		neuerBenutzer.setNachname(NEUER_BENUTZER_NACHNAME);
		neuerBenutzer.setVorname(NEUER_BENUTZER_VORNAME);
		neuerBenutzer.setBenutzername(NEUER_BENUTZER_BENUTZERNAME);
		neuerBenutzer.setBenutzergruppen(adminRolleSet);
		neuerBenutzer.setEmail(NEUER_BENUTZER_EMAIL);
		neuerBenutzer.setGeburtsdatum(NEUER_BENUTZER_GEBDATUM);
		neuerBenutzer.setGeschlecht(NEUER_BENUTZER_GESCHLECHT);
		neuerBenutzer.setGesperrt(NEUER_BENUTZER_GESPERRT);
		neuerBenutzer.setNewsletter(NEUER_BENUTZER_NEWSLETTER);
		neuerBenutzer.setOrt(NEUER_BENUTZER_ORT);
		neuerBenutzer.setPlz(NEUER_BENUTZER_PLZ);
		neuerBenutzer.setRegdatum(NEUER_BENUTZER_REGDATUM);
		neuerBenutzer.setStrasse(NEUER_BENUTZER_STRASSE);
		neuerBenutzer.setPasswortHash(NEUER_BENUTZER_BENUTZERNAME_HASH);
		Benutzer angelegterBenutzer = bvProxy.createBenutzer(neuerBenutzer);
		
		Benutzer validBenutzer = new Benutzer();
		validBenutzer.setVorname(NEUER_BENUTZER_VORNAME);
		validBenutzer.setVorname(NEUER_BENUTZER_VORNAME);
		validBenutzer.setBenutzername(NEUER_BENUTZER_BENUTZERNAME);
		validBenutzer.setPasswortHash(NEUER_BENUTZER_BENUTZERNAME_HASH);
		validBenutzer.setBenutzergruppen(benutzerRolleSet);
		validBenutzer.setEmail(NEUER_BENUTZER_EMAIL);
		validBenutzer.setGeburtsdatum(NEUER_BENUTZER_GEBDATUM);
		validBenutzer.setGeschlecht(NEUER_BENUTZER_GESCHLECHT);
		validBenutzer.setGesperrt(NEUER_BENUTZER_GESPERRT);
		validBenutzer.setNewsletter(NEUER_BENUTZER_NEWSLETTER);
		validBenutzer.setOrt(NEUER_BENUTZER_ORT);
		validBenutzer.setPlz(NEUER_BENUTZER_PLZ);
		validBenutzer.setRegdatum(NEUER_BENUTZER_REGDATUM);
		validBenutzer.setStrasse(NEUER_BENUTZER_STRASSE);

		Benutzer invalidBenutzer = new Benutzer();
		invalidBenutzer.setVorname(NEUER_BENUTZER_VORNAME);
		invalidBenutzer.setVorname(NEUER_BENUTZER_VORNAME);
		invalidBenutzer.setBenutzername(NEUER_BENUTZER_BENUTZERNAME);
		invalidBenutzer.setPasswortHash(NEUER_BENUTZER_BENUTZERNAME_HASH);
		invalidBenutzer.setBenutzergruppen(adminRolleSet);
		invalidBenutzer.setBenutzergruppen(benutzerRolleSet);
		invalidBenutzer.setEmail(NEUER_BENUTZER_EMAIL);
		invalidBenutzer.setGeburtsdatum(NEUER_BENUTZER_GEBDATUM);
		invalidBenutzer.setGeschlecht(NEUER_BENUTZER_GESCHLECHT);
		invalidBenutzer.setGesperrt(NEUER_BENUTZER_GESPERRT);
		invalidBenutzer.setNewsletter(NEUER_BENUTZER_NEWSLETTER);
		invalidBenutzer.setOrt(NEUER_BENUTZER_ORT);
		invalidBenutzer.setPlz(NEUER_BENUTZER_PLZ);
		invalidBenutzer.setRegdatum(NEUER_BENUTZER_REGDATUM);
		invalidBenutzer.setStrasse(NEUER_BENUTZER_STRASSE);

		//TODO seltsame NullPointerException.
		//assertEquals(angelegterBenutzer, validBenutzer);
	}

	@Test(expected = EJBAccessException.class)
	public void denyChangeBenutzergruppe()
			throws BenutzergruppeNotFoundException, PasswordNotValidException,
			BenutzerNotFoundException {
		final Benutzergruppe benutzerRolle = bvProxy
				.findBenutzergruppe(ROLLE_BENUTZER);
		final Benutzergruppe adminRolle = bvProxy
				.findBenutzergruppe(ROLLE_ADMIN);
		final Set<Benutzergruppe> benutzerRolleSet = new HashSet<Benutzergruppe>();
		benutzerRolleSet.add(benutzerRolle);
		final Set<Benutzergruppe> adminRolleSet = new HashSet<Benutzergruppe>();
		adminRolleSet.add(adminRolle);

		Benutzer benutzer = bvProxy.setBenutzergruppen(USERNAME_SACHBEARBEITER,
				adminRolleSet);
		assertTrue(benutzer.getBenutzergruppen().equals(adminRolleSet));
	}

	@Test
	public void changeBenutzergruppe() throws BenutzergruppeNotFoundException,
			PasswordNotValidException, BenutzerNotFoundException {
		logout(loginCtx);
		loginCtx = login(LOGIN_DOMAIN_KIPF, USERNAME_ADMIN, PASSWORD_ADMIN);

		final Benutzergruppe benutzerRolle = bvProxy
				.findBenutzergruppe(ROLLE_BENUTZER);
		final Benutzergruppe adminRolle = bvProxy
				.findBenutzergruppe(ROLLE_ADMIN);
		final Set<Benutzergruppe> benutzerRolleSet = new HashSet<Benutzergruppe>();
		benutzerRolleSet.add(benutzerRolle);
		final Set<Benutzergruppe> adminRolleSet = new HashSet<Benutzergruppe>();
		adminRolleSet.add(adminRolle);

		Benutzer benutzer = bvProxy.setBenutzergruppen(USERNAME_SACHBEARBEITER,
				adminRolleSet);
		assertEquals(benutzer.getBenutzergruppen().iterator().next(), adminRolleSet.iterator().next());
	}

	@Test
	public void changeNachname() throws BenutzerNotFoundException {
		Benutzer benutzer = bvProxy.findBenutzer(USERNAME_SACHBEARBEITER);
		benutzer.setNachname(NACHNAME_AENDERN_NEUER_NACHNAME);
		benutzer = bvProxy.updateBenutzer(benutzer);

		assertEquals(benutzer.getNachname(), NACHNAME_AENDERN_NEUER_NACHNAME);
	}

	@Test
	public void tryChangePassword() throws BenutzerNotFoundException,
			BenutzergruppeNotFoundException {
		Benutzer benutzer = bvProxy.findBenutzer(USERNAME_SACHBEARBEITER);
		final String alterHash = benutzer.getPasswortHash();

		benutzer.setPasswortHash(PASSWORTHASH_AENDERN);

		benutzer = bvProxy.updateBenutzer(benutzer);

		assertEquals(benutzer.getPasswortHash(), alterHash);
	}

	@Test
	public void tryChangeRoles() throws BenutzerNotFoundException,
			BenutzergruppeNotFoundException {
		Benutzer benutzer = bvProxy.findBenutzer(USERNAME_SACHBEARBEITER);
		final Set<Benutzergruppe> alteBenutzergruppen = benutzer
				.getBenutzergruppen();

		final Benutzergruppe adminRolle = bvProxy
				.findBenutzergruppe(ROLLE_ADMIN);
		final Set<Benutzergruppe> adminRolleSet = new HashSet<Benutzergruppe>();
		adminRolleSet.add(adminRolle);
		
		benutzer.setBenutzergruppen(adminRolleSet);

		benutzer = bvProxy.updateBenutzer(benutzer);

		assertEquals(benutzer.getBenutzergruppen().iterator().next(), alteBenutzergruppen.iterator().next());
	}
	
	@Test
	public void changeRolesAsAdmin() throws BenutzerNotFoundException,
			BenutzergruppeNotFoundException {
		logout(loginCtx);
		loginCtx = login(LOGIN_DOMAIN_KIPF, USERNAME_ADMIN, PASSWORD_ADMIN);
		
		Benutzer benutzer = bvProxy.findBenutzer(USERNAME_SACHBEARBEITER);

		final Benutzergruppe adminRolle = bvProxy
				.findBenutzergruppe(ROLLE_ADMIN);
		final Set<Benutzergruppe> adminRolleSet = new HashSet<Benutzergruppe>();
		adminRolleSet.add(adminRolle);

		benutzer.setBenutzergruppen(adminRolleSet);

		benutzer = bvProxy.updateBenutzer(benutzer);
		
		adminRolle.setVersion(adminRolle.getVersion() + 1);

		assertEquals(benutzer.getBenutzergruppen().iterator().next(), adminRolleSet.iterator().next());
	}

//	@Test
//	public void denyChangeUserPasswordAsUser() throws PasswordNotValidException, BenutzerNotFoundException {
//		final Benutzer benutzerVorher = bvProxy.findBenutzer(DELETE_BENUTZER_ID_MIT_AUFTRAEGE);
//		bvProxy.setPassword(benutzerVorher, PASSWORTHASH_AENDERN);
//		final Benutzer benutzerNachher = bvProxy.findBenutzer(DELETE_BENUTZER_ID_MIT_AUFTRAEGE);
//		
//		assertEquals(benutzerVorher.getPasswortHash(), benutzerNachher.getPasswortHash());
//		assertFalse(bvProxy.checkPassword(benutzerNachher, PASSWORTHASH_AENDERN));
//	}
//	
//	@Test
//	public void changeOwnPasswordAsUser() throws BenutzerNotFoundException,
//			PasswordNotValidException {
//		final Benutzer benutzerVorher = bvProxy
//				.findBenutzer(USERNAME_SACHBEARBEITER);
//		bvProxy.setPassword(benutzerVorher, PASSWORTHASH_AENDERN);
//		final Benutzer benutzerNachher = bvProxy
//				.findBenutzer(USERNAME_SACHBEARBEITER);
//		assertNotNull(benutzerVorher);
//		assertNotNull(benutzerNachher);
//
//		assertFalse(benutzerVorher.getPasswortHash().equals(
//				benutzerNachher.getPasswortHash()));
//		assertTrue(bvProxy.checkPassword(benutzerNachher, PASSWORTHASH_AENDERN));
//
//		logout(loginCtx);
//		loginCtx = login(LOGIN_DOMAIN_KIPF, USERNAME_SACHBEARBEITER,
//				PASSWORTHASH_AENDERN);
//
//		bvProxy.setPassword(benutzerNachher, PASSWORD_SACHBEARBEITER);
//	}
//
//	@Test
//	public void changeUserPasswordAsAdmin() throws BenutzerNotFoundException,
//			PasswordNotValidException {
//		logout(loginCtx);
//		loginCtx = login(LOGIN_DOMAIN_KIPF, USERNAME_ADMIN, PASSWORD_ADMIN);
//
//		final Benutzer benutzerVorher = bvProxy
//				.findBenutzer(DELETE_BENUTZER_ID_MIT_AUFTRAEGE);
//		bvProxy.setPassword(benutzerVorher, PASSWORTHASH_AENDERN);
//		final Benutzer benutzerNachher = bvProxy
//				.findBenutzer(DELETE_BENUTZER_ID_MIT_AUFTRAEGE);
//
//		assertFalse(benutzerVorher.getPasswortHash().equals(
//				benutzerNachher.getPasswortHash()));
//		assertTrue(bvProxy.checkPassword(benutzerNachher, PASSWORTHASH_AENDERN));
//	}

	/**
	 * @throws BenutzerNotFoundException
	 * @throws BenutzerDeleteAuftragException
	 */
	@Test
	public void deleteBenutzerOhneAuftraege() throws BenutzerNotFoundException,
			BenutzerDeleteAuftragException {
		logout(loginCtx);
		loginCtx = login(LOGIN_DOMAIN_KIPF, USERNAME_ADMIN, PASSWORD_ADMIN);

		final Collection<Benutzer> benutzerVorher = bvProxy.findAllBenutzer();

		final Benutzer benutzer = bvProxy
				.findBenutzerMitAuftraege(DELETE_BENUTZER_ID_OHNE_AUFTRAEGE);
		assertNotNull(benutzer);

		bvProxy.deleteBenutzer(benutzer);

		final Collection<Benutzer> benutzerNachher = bvProxy.findAllBenutzer();

		assertEquals(benutzerVorher.size() - 1, benutzerNachher.size());
	}

	/**
	 */
	@Test(expected = BenutzerDeleteAuftragException.class)
	public void deleteBenutzerMitAuftrag()
			throws BenutzerDeleteAuftragException, BenutzerNotFoundException {
		logout(loginCtx);
		loginCtx = login(LOGIN_DOMAIN_KIPF, USERNAME_ADMIN, PASSWORD_ADMIN);

		final Benutzer benutzer = bvProxy
				.findBenutzerMitAuftraege(DELETE_BENUTZER_ID_MIT_AUFTRAEGE);
		assertNotNull(benutzer);

		bvProxy.deleteBenutzer(benutzer);
	}

}