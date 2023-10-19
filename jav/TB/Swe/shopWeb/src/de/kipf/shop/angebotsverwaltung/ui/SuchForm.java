package de.kipf.shop.angebotsverwaltung.ui;

import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.validator.ValidatorForm;

import de.kipf.shop.teile.Modellverwaltung;
import de.kipf.shop.teile.db.AngebotsModell;
import de.kipf.shop.teile.db.Fahrradtyp;
import de.kipf.shop.util.EjbNotFoundException;
import de.kipf.shop.util.EjbUtil;

public class SuchForm extends ValidatorForm {
	private static final long serialVersionUID = 4430397200085530864L;
	private static final Log LOG = LogFactory
			.getLog(AngebotsverwaltungServlet.class);

	private ArrayList<Fahrradtyp> fahrradtypen = new ArrayList<Fahrradtyp>(3);
	private int indexTyp = -1;
	private String bezeichnung;
	private String preis = "";
	private ArrayList<AngebotsModell> resultModell;

	public SuchForm() {
		super();
		Fahrradtyp typ = new Fahrradtyp();
		typ.setRadtypbeschreibung("Alle Typen");
		typ.setId(Long.valueOf(0L));
		fahrradtypen.add(typ);
		
		Modellverwaltung mv;
		try {
			mv = EjbUtil.getModellverwaltungLocal();
			ArrayList<Fahrradtyp> typen = new ArrayList<Fahrradtyp>(mv
					.findAllFahrradtypen());
			if (!(typen==null)) {
				for(Fahrradtyp f: typen) {
					fahrradtypen.add(f);
				}
			}
		} catch (EjbNotFoundException e) {
			LOG.fatal("SuchForm Constructor " + e);
		}

	}

	public ArrayList<Fahrradtyp> getFahrradtypen() {
		return fahrradtypen;
	}

	public void setFahrradtypen(ArrayList<Fahrradtyp> fahrradtypen) {
		this.fahrradtypen = fahrradtypen;
	}

	public void addFahrradtypen(Fahrradtyp typ) {
		this.fahrradtypen.add(typ);
	}

	public int getIndexTyp() {
		return indexTyp;
	}

	public void setIndexTyp(int indexTyp) {
		this.indexTyp = indexTyp;
	}

	public String getBezeichnung() {
		return bezeichnung;
	}

	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}

	public String getPreis() {
		return preis;
	}

	public void setPreis(String preis) {
		this.preis = preis;
	}

	public ArrayList<AngebotsModell> getResultModell() {
		return resultModell;
	}

	public void setResultModell(ArrayList<AngebotsModell> resultModell) {
		this.resultModell = resultModell;
	}
}
