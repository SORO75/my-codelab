package de.kipf.shop.teile.db;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import static de.kipf.shop.teile.db.Modell.ANGEBOTSMODELL;

@Entity 
@DiscriminatorValue(ANGEBOTSMODELL)
public class AngebotesModell extends Modell {
	private static final long serialVersionUID = 4788576660827991818L;
	
	@Override	
	public String getArt() {	
		return ANGEBOTSMODELL;
	}
}
