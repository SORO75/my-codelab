package de.kipf.shop.util;

import static de.kipf.shop.util.EjbConstants.BEGIN;
import static de.kipf.shop.util.EjbConstants.END;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class LoginUtil {
	private static final String UTF_8 = "UTF-8";
	private static final String SYS_PROP_AUTH_CONFIG = "java.security.auth.login.config";
	private static final String AUTH_CONF = "auth.conf";
	public static final String LOGIN_DOMAIN_KIPF = "shopClient";
	private static final Log LOG = LogFactory.getLog(LoginUtil.class);
	private static final boolean DEBUG = LOG.isDebugEnabled();

	/**
	 */
	static class HskaCallbackHandler implements CallbackHandler {
		private String username;
		private char[] password;

		/**
		 */
		public HskaCallbackHandler(String username, String password) {
			super();
			this.username = username;
			this.password = password.toCharArray();
		}

		/**
		 */
		public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
			for (int i = 0; i < callbacks.length; i++) {
				if (callbacks[i] instanceof NameCallback) {
					final NameCallback nc = (NameCallback) callbacks[i];
					nc.setName(username);
				}
				else if (callbacks[i] instanceof PasswordCallback) {
					final PasswordCallback pc = (PasswordCallback) callbacks[i];
					pc.setPassword(password);
				}
				else {
					LOG.warn("Unzulaessiger Callback: " + callbacks[i]);
					throw new UnsupportedCallbackException(callbacks[i], "Unzulaessiger Callback");
				}
			}
		}
	}
	
	public static void loadAuthConf() throws UnsupportedEncodingException {
		final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		final URL authConf = classLoader.getResource(AUTH_CONF);
		final String path = URLDecoder.decode(authConf.toExternalForm(), UTF_8);
		System.setProperty(SYS_PROP_AUTH_CONFIG, path);
	}

	/**
	 */
	public static LoginContext login(String loginCtxName, String username, String password) {
		if (DEBUG) LOG.debug(BEGIN + "login: " + loginCtxName + ", " + username + ", " + password);
		LoginContext loginCtx = null;
		final CallbackHandler callbackHandler = new HskaCallbackHandler(username, password);
		try {
			loginCtx = new LoginContext(loginCtxName, callbackHandler);
			loginCtx.login();
		}
		catch (LoginException e) {
			LOG.warn(e.getMessage());
		}
		if (DEBUG) LOG.debug(END + "login");
		return loginCtx;
	}
	
	/**
	 */
	public static void logout(LoginContext loginCtx) {
		if (DEBUG) LOG.debug(BEGIN + "logout");
		if (loginCtx == null) {
			if (DEBUG) LOG.debug(END + "logout: kein LoginContext");
			return;
		}

		try {
			loginCtx.logout();
		}
		catch (LoginException e) {
			LOG.warn(e.getMessage());
		}
		if (DEBUG) LOG.debug(END + "logout");
	}
}
