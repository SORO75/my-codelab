package de.kipf.shop.personen.ui;

import static de.kipf.shop.util.WebConstants.FWD_LISTE;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import de.kipf.shop.personen.db.Benutzer;

public class FindBenutzerForm extends ActionForm {
	private static final long serialVersionUID = 7756881389965392258L;
	private String darstellung = FWD_LISTE;
    private ArrayList<Benutzer> benutzer = new ArrayList<Benutzer>();

    public FindBenutzerForm() {
        super();
    }

    public String getDarstellung() {
        return darstellung;
    }
    public void setDarstellung(String darstellung) {
        this.darstellung = darstellung;
    }
    public ArrayList<Benutzer> getBenutzer() {
        return benutzer;
    }
    public void setBenutzer(ArrayList<Benutzer> benutzer) {
        this.benutzer = benutzer;
    }
	
//	@Override
//	public String toString() {
//		return "{nachname=" + nachname + ", darstellung=" + darstellung +
//		       ", kunde=" + kunde + "}";
//	}

	@Override
	/** Ruecksetzen der Attribute ist nicht unbedingt sinnvoll, da das FormBean
	 *  fuer die gesamte Session, also insbesondere fuer den naechsten Request,
	 *  genutzt werden soll.
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
//		darstellung = FWD_LISTE;
	}
}