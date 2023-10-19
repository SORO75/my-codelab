package de.kipf.shop.angebotsverwaltung.ui;

//import static de.kipf.shop.util.EjbConstants.BEGIN;
//import static de.kipf.shop.util.EjbConstants.END;

import java.util.ArrayList;

//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;

import de.kipf.shop.teile.db.Einzelteil;

public class AngebotDetailForm extends ActionForm {
	private static final long serialVersionUID = 4874784218347663000L;
	
//	private static final Log LOG = LogFactory.getLog(AngebotDetailForm.class);
	
    private int id = -1;    
    private String beschreibung;
    private String bildUrl;
    private float preis;
    private ArrayList<Einzelteil> teil;    
    
    
	public AngebotDetailForm() {
        super();
    }
	
	public void setId(int id) {		
		this.id = id;
	}
	
	public int getId() {		
		return this.id;
	}

	public String getBeschreibung() {
		return beschreibung;
	}

	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}

	public String getBildUrl() {
		return bildUrl;
	}

	public void setBildUrl(String bildUrl) {
		this.bildUrl = bildUrl;
	}

	public float getPreis() {
		return preis;
	}

	public void setPreis(float preis) {
		this.preis = preis;
	}

	public ArrayList<Einzelteil> getTeil() {
		return teil;
	}

	public void setTeil(ArrayList<Einzelteil> teil) {
		this.teil = teil;
	}
}
