package de.kipf.shop.util;

public interface WebConstants {		
	static final String TRUE = "true";
	static final String POST = "POST";	
	static final String GET = "GET";	
	
	// Name von SessionAttributen
	static final String LOGIN_NAME = "loginName";
	static final String AUFTRAEGE_LIST = "auftraegeListe";
	static final String WARENKORB_NAME = "warenkorb";
	static final String SUCHEN_NAME = "suche";
	
	// Validierung und Datenformat
	static final String DATE_FORMAT = "dd.MM.yyyy";

	// Targets fuer <forward>
	static final String FWD_START = "start";
	static final String FWD_CANCEL = "cancel";
	static final String FWD_ANGEBOTE = "angebote";
	static final String FWD_ANGEBOT_DETAIL = "angebotDetail";
	static final String FWD_WARENKORB = "warenkorb";
	static final String FWD_FIND = "find";
	static final String FWD_LISTE = "liste";
	static final String FWD_UPDATE = "update";
	static final String FWD_KUNDENINFO = "kundeninfo";
	static final String FWD_BESTELLUNG = "bestellung";
	static final String FWD_ERGEBNIS = "ergebnis";
	static final String FWD_SUCHE = "suche";
	
	static final String FWD_INDEX = "index";

	// Namen von Request-Parametern
	static final String REQUEST_PARAM_INDEX = "index";
	static final String REQUEST_PARAM_USER = "user";

	// Namen von FormBeans
	static final String FIND_BENUTZER_FORM = "findBenutzerForm";
	static final String UPDATE_BENUTZER_FORM = "updateBenutzerForm";
		
	// Namen von FormBeans
//	static final String FIND_KUNDEN_FORM = "findKundenForm";
//	static final String SHOW_ALLE_ANGEBOTE = "showAllAngebote";	
}
