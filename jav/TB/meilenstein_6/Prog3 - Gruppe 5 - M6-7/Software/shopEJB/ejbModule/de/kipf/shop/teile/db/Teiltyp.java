package de.kipf.shop.teile.db;

import static de.kipf.shop.util.EjbConstants.ERSTE_VERSION;
import static de.kipf.shop.util.EjbConstants.KEINE_ID;
import static javax.persistence.GenerationType.AUTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.QueryHint;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Entity
@Table(name =  "teiltyp")
@NamedQueries( {
		@NamedQuery(name = Teiltyp.ALLE_TEILTYPEN, query = "FROM Teiltyp t", hints = {
				@QueryHint(name = "org.hibernate.cacheable", value = "true"),
				@QueryHint(name = "org.hibernate.cacheRegion", value = "org.hibernate.cache.StandardQueryCache") }),
		@NamedQuery(name = Teiltyp.TEILTYP_BY_ID, query = "FROM Teiltyp t WHERE t.id=:id", hints = {
				@QueryHint(name = "org.hibernate.cacheable", value = "true"),
				@QueryHint(name = "org.hibernate.cacheRegion", value = "org.hibernate.cache.StandardQueryCache") }) })
public class Teiltyp implements java.io.Serializable {
	private static final long serialVersionUID = 6705664129790965019L;

	protected static final Log LOG = LogFactory.getLog(Teiltyp.class);
	protected static final boolean DEBUG = LOG.isDebugEnabled();
	protected static final boolean TRACE = LOG.isTraceEnabled();

	public static final String ALLE_TEILTYPEN = "teiltypen.alle";
	public static final String TEILTYP_BY_ID = "teiltypen.by.id";

	@Id
	@GeneratedValue(strategy = AUTO, generator = "teiltyp_sequence")
	@SequenceGenerator(name = "teiltyp_sequence", sequenceName = "teiltyp_tt_id_seq", allocationSize = 1)
	@Column(name = "tt_id", nullable = false)
	protected Long id = KEINE_ID;

	@Version
	protected int version = ERSTE_VERSION;

	@Column(name = "typbeschreibung", length = 100, nullable = false)
	protected String beschreibung = "";

	// @OneToMany(mappedBy = "teiltyp")
	// @OrderBy("id ASC")
	// protected List<Einzelteil> einzelteile = new ArrayList<Einzelteil>();

	public Teiltyp() {
		super();
	}

	@PostPersist
	protected void logDbId() {
		if (DEBUG)
			LOG.debug("Neuer Teiltyp mit ID=" + id + " : " + this);
	}

	@PostUpdate
	protected void logNeueVersion() {
		if (TRACE)
			LOG.trace("Teiltyp ID=" + id + " geaendert: neue Version="
					+ version);
	}

	public String getBeschreibung() {
		return beschreibung;
	}

	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	// public List<Einzelteil> getEinzelteile() {
	// return einzelteile;
	// }
	//
	// public void setEinzelteile(List<Einzelteil> einzelteile) {
	// this.einzelteile = einzelteile;
	// }

	@Override
	public String toString() {
		return "id=" + id + " ,version=" + version + " ,Beschreibung="
				+ beschreibung;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other)
			return true;
		if (other instanceof Teiltyp == false)
			return false;
		final Teiltyp t = (Teiltyp) other;
		return id.equals(t.id) && version == t.version
				&& beschreibung.equals(t.beschreibung);
	}

	@Override
	public int hashCode() {
		int result = 37 + version; // Version als Offset
		result ^= id.intValue(); // Bit-weise XOR
		result ^= beschreibung.hashCode();
		return result;
	}
}
