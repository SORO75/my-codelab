package de.kipf.shop.angebotsverwaltung.ui;

import static de.kipf.shop.util.EjbConstants.BEGIN;
import static de.kipf.shop.util.EjbConstants.END;

import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;

import de.kipf.shop.teile.db.AngebotsModell;

public class AngebotForm extends ActionForm {
	private static final long serialVersionUID = 187975966666427100L;

    private static final Log LOG = LogFactory.getLog(AngebotForm.class);
    private static final boolean DEBUG = LOG.isDebugEnabled();
    private ArrayList<AngebotsModell> modell = new ArrayList<AngebotsModell>();
                                     
    public AngebotForm() {
        super();
        if (DEBUG) LOG.debug(BEGIN);

 
        
        if (DEBUG) LOG.debug(END);
    }

	public ArrayList<AngebotsModell> getModell() {
		return modell;
	}

	public void setModell(ArrayList<AngebotsModell> modell) {
		this.modell = modell;
	}
	
	@Override
	public String toString() {
		return modell.toString();
	}
}
