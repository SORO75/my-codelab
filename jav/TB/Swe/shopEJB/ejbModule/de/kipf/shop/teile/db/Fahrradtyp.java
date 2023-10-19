package de.kipf.shop.teile.db;

import static de.kipf.shop.util.EjbConstants.ERSTE_VERSION;
import static de.kipf.shop.util.EjbConstants.KEINE_ID;
import static javax.persistence.GenerationType.AUTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.QueryHint;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Entity
@Table(name = "fahrradtyp")
@NamedQueries( {
		@NamedQuery(name = Fahrradtyp.ALLE_FAHRRADTYPEN, query = "FROM Fahrradtyp f", hints = {
				@QueryHint(name = "org.hibernate.cacheable", value = "true"),
				@QueryHint(name = "org.hibernate.cacheRegion", value = "org.hibernate.cache.StandardQueryCache") }),
		@NamedQuery(name = Fahrradtyp.FAHRRADTYP_BY_ID, query = "FROM Fahrradtyp f WHERE f.id=:id", hints = {
				@QueryHint(name = "org.hibernate.cacheable", value = "true"),
				@QueryHint(name = "org.hibernate.cacheRegion", value = "org.hibernate.cache.StandardQueryCache") }) })
public class Fahrradtyp implements Serializable {
	private static final long serialVersionUID = -4042356380946117159L;
	
	protected static final Log LOG = LogFactory.getLog(Fahrradtyp.class);
	protected static final boolean DEBUG = LOG.isDebugEnabled();
	protected static final boolean TRACE = LOG.isTraceEnabled();

	public static final String ALLE_FAHRRADTYPEN = "fahrradtyp.alle";
	public static final String FAHRRADTYP_BY_ID = "fahrradtyp.by.id";
	
	@Id
	@GeneratedValue(strategy = AUTO, generator = "fahrradtyp_sequence")
	@SequenceGenerator(name = "fahrradtyp_sequence", sequenceName = "fahrradtyp_typ_id_seq", allocationSize = 1)
	@Column(name = "typ_id", nullable = false)
	protected Long id = KEINE_ID;

	@Version
	protected int version = ERSTE_VERSION;

	@Column(length = 50, nullable = false)
	protected String radtypbeschreibung = "";

	@OneToMany(mappedBy = "typ")
	@OrderBy("id ASC")
	protected List<Modell> modelle = new ArrayList<Modell>();
	
	public Fahrradtyp() {
		super();
	}

	@PostPersist
	protected void logDbId() {
		if (DEBUG) LOG.debug("Neuer Fahrradtyp mit ID=" + id + " : " + this);
	}

	@PostUpdate
	protected void logNeueVersion() {
		if (TRACE) LOG.trace("Fahrradtyp ID=" + id + " geaendert: neue Version=" + version);
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Modell> getModelle() {
		return modelle;
	}

	public void setModelle(List<Modell> modelle) {
		this.modelle = modelle;
	}

	public String getRadtypbeschreibung() {
		return radtypbeschreibung;
	}

	public void setRadtypbeschreibung(String radtypbeschreibung) {
		this.radtypbeschreibung = radtypbeschreibung;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "id=" + id + " ,version=" + version + " ,Radtypbeschreibung="
				+ radtypbeschreibung;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other)
			return true;
		if (other instanceof Fahrradtyp == false)
			return false;
		final Fahrradtyp f = (Fahrradtyp) other;
		return id.equals(f.id) && version == f.version
				&& radtypbeschreibung.equals(f.radtypbeschreibung)
				&& modelle.equals(f.modelle);
	}

	@Override
	public int hashCode() {
		int result = 37 + version; // Version als Offset
		result ^= id.intValue(); // Bit-weise XOR
		result ^= radtypbeschreibung.hashCode();
		return result;
	}
}
