package de.kipf.shop.teile.db;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import static de.kipf.shop.teile.db.Modell.KUNDENMODELL;

@Entity 
@DiscriminatorValue(KUNDENMODELL)
public class KundenModell extends Modell {	
	private static final long serialVersionUID = -3824529750863640661L;

	@Override
	public String getArt() {
		return KUNDENMODELL;
	}

}
