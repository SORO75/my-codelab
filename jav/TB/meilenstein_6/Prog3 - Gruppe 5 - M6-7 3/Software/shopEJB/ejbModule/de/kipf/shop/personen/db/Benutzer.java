package de.kipf.shop.personen.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.QueryHint;
import static javax.persistence.CascadeType.ALL;

import static javax.persistence.EnumType.STRING;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import static javax.persistence.TemporalType.TIMESTAMP;
import static javax.persistence.FetchType.EAGER;
import javax.persistence.Version;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.kipf.shop.vorgaenge.db.Auftrag;
import static de.kipf.shop.util.EjbConstants.ERSTE_VERSION;

@Entity
@Table(name = "benutzer")
@NamedQueries( {
		@NamedQuery(name = Benutzer.BENUTZER_ALL, query = "FROM Benutzer b", hints = {
				@QueryHint(name = "org.hibernate.cacheable", value = "true"),
				@QueryHint(name = "org.hibernate.cacheRegion", value = "org.hibernate.cache.StandardQueryCache") }),
		@NamedQuery(name = Benutzer.BENUTZER_BY_NACHNAME, query = "FROM Benutzer b WHERE b.nachname = :"
				+ Benutzer.ATTR_NACHNAME, hints = {
				@QueryHint(name = "org.hibernate.cacheable", value = "true"),
				@QueryHint(name = "org.hibernate.cacheRegion", value = "org.hibernate.cache.StandardQueryCache") }),
		@NamedQuery(name = Benutzer.BENUTZER_BY_NACHNAME_FETCH_AUFTRAEGE, query = "SELECT DISTINCT b FROM Benutzer b LEFT JOIN FETCH b.auftraege a WHERE b.nachname = :"
				+ Benutzer.ATTR_NACHNAME),
		@NamedQuery(name = Benutzer.BENUTZER_BY_ID_FETCH_AUFTRAEGE, query = "FROM Benutzer b LEFT JOIN FETCH b.auftraege a WHERE b.benutzername = :"
				+ Benutzer.ATTR_BENUTZERNAME) })
public class Benutzer implements java.io.Serializable {

	private static final long serialVersionUID = -7561595073400275018L;

	protected static final Log LOG = LogFactory.getLog(Benutzer.class);

	protected static final boolean DEBUG = LOG.isDebugEnabled();

	protected static final boolean TRACE = LOG.isTraceEnabled();

	public static final String ATTR_BENUTZERNAME = "Benutzername";

	public static final String ATTR_NACHNAME = "Nachname";

	public static final String BENUTZER_ALL = "benutzer.all";

	public static final String BENUTZER_BY_NACHNAME = "benutzer.byNachname";

	public static final String BENUTZER_BY_NACHNAME_FETCH_AUFTRAEGE = "benutzer.byNachname.fetchAuftraege";

	public static final String BENUTZER_BY_ID_FETCH_AUFTRAEGE = "benutzer.byId.fetchAuftraege";

	protected static final boolean DEFAULT_GESPERRT = false;

	protected static final boolean DEFAULT_NEWSLETTER = false;

	public static enum Geschlecht {
		m, w
	};

	@Id
	@Column(length = 20, nullable = false)
	protected String benutzername;

	@Version
	protected int version = ERSTE_VERSION;

	@ManyToMany(mappedBy = "benutzer", cascade=ALL, fetch=EAGER)
	// @OrderBy("bg_id ASC") // Für Set wohl nicht möglich/nötig
	protected Set<Benutzergruppe> benutzergruppen;

	@OneToMany(mappedBy = "benutzer")
	@OrderBy("id ASC")
	protected List<Auftrag> auftraege = new ArrayList<Auftrag>();

	//@Column(nullable = false)
	@Temporal(TIMESTAMP)
	protected Date regdatum;

	@Column(name = "passwort", length = 44, nullable = false)
	protected String passwortHash;

	@Column(nullable = false)
	protected boolean gesperrt = DEFAULT_GESPERRT;

	@Column(length = 20, nullable = false)
	protected String vorname;

	@Column(length = 20, nullable = false)
	protected String nachname;

	@Column(nullable = false)
	@Enumerated(STRING)
	protected Geschlecht geschlecht;

	@Column(length = 50, nullable = false)
	protected String strasse;

	@Column(length = 6, nullable = false)
	protected String plz;

	@Column(length = 5, nullable = false)
	protected String ort;

	@Column(nullable = false)
	@Temporal(TIMESTAMP)
	protected Date geburtsdatum;

	@Column(length = 50, nullable = false)
	protected String email;

	@Column(nullable = false)
	protected boolean newsletter = DEFAULT_NEWSLETTER;

	@PrePersist
	protected void prePersist() {
//		regdatum = new Date();
		if (DEBUG) LOG.debug("PrePersist: " + this);
	}

	@PostPersist
	protected void logDbId() {
		if (DEBUG)
			LOG.debug("Neuer Benutzer mit benutzername=" + benutzername + " : "
					+ this);
	}

	@PreUpdate
	protected void preUpdate() {
	}

	@PostUpdate
	protected void logNeueVersion() {
		if (TRACE)
			LOG.trace("Benutzer benutzername=" + benutzername
					+ " geaendert: neue Version=" + version);
	}

	public Benutzer() {
		super();
	}

	public String getId() {
		return getBenutzername();
	}

	public String getBenutzername() {
		return benutzername;
	}

	public void setBenutzername(String benutzername) {
		this.benutzername = benutzername;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getGeburtsdatum() {
		return geburtsdatum;
	}

	public void setGeburtsdatum(Date geburtsdatum) {
		this.geburtsdatum = geburtsdatum;
	}

	public Geschlecht getGeschlecht() {
		return geschlecht;
	}

	public void setGeschlecht(Geschlecht geschlecht) {
		this.geschlecht = geschlecht;
	}

	public boolean isGesperrt() {
		return gesperrt;
	}

	public void setGesperrt(boolean gesperrt) {
		this.gesperrt = gesperrt;
	}

	public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public boolean isNewsletter() {
		return newsletter;
	}

	public void setNewsletter(boolean newsletter) {
		this.newsletter = newsletter;
	}

	public String getOrt() {
		return ort;
	}

	public void setOrt(String ort) {
		this.ort = ort;
	}

	public String getPasswortHash() {
		return passwortHash;
	}

	public void setPasswortHash(String passwortHash) {
		this.passwortHash = passwortHash;
	}

	public String getPlz() {
		return plz;
	}

	public void setPlz(String plz) {
		this.plz = plz;
	}

	public Date getRegdatum() {
		return regdatum;
	}

	public void setRegdatum(Date regdatum) {
		this.regdatum = regdatum;
	}

	public String getStrasse() {
		return strasse;
	}

	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public Set<Benutzergruppe> getBenutzergruppen() {
		return benutzergruppen;
	}

	public void setBenutzergruppen(Set<Benutzergruppe> benutzergruppen) {
		this.benutzergruppen = benutzergruppen;
	}

	public List<Auftrag> getAuftraege() {
		return auftraege;
	}

	public void setAuftraege(List<Auftrag> auftraege) {
		this.auftraege = auftraege;
	}

	@Override
	public String toString() {
		return "{benutzername=" + benutzername + ", version=" + version
				+ ", nachname=" + nachname + ", vorname=" + vorname
				+ ", geschlecht=" + geschlecht + ", regdatum=" + regdatum
				+ ", passwortHash=" + passwortHash + ", gesperrt=" + gesperrt
				+ ", strasse=" + strasse + ", plz=" + plz + ", ort=" + ort
				+ ", geburtsdatum=" + geburtsdatum + ", email=" + email
				+ ", newsletter=" + newsletter + "}";
	}

	@Override
	public boolean equals(Object other) {
		if (this == other)
			return true;
		if (other instanceof Benutzer == false)
			return false;

		final Benutzer b = (Benutzer) other;
		return benutzername.equals(b.benutzername) && version == b.version
				&& regdatum.equals(b.regdatum)
				&& passwortHash.equals(b.passwortHash)
				&& gesperrt == b.gesperrt && vorname.equals(b.vorname)
				&& nachname.equals(b.nachname)
				&& geschlecht.equals(b.geschlecht) && strasse.equals(b.strasse)
				&& plz.equals(b.plz) && ort.equals(b.ort)
				&& geburtsdatum.equals(b.geburtsdatum) && email.equals(b.email)
				&& newsletter == b.newsletter;
		}

	@Override
	public int hashCode() {
		int result = 37 + version;
		result ^= benutzername.hashCode();
		result ^= nachname.hashCode();
		result ^= vorname.hashCode();
		result ^= email.hashCode();
		return result;
	}

}
