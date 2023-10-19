package de.kipf.shop.teile.db;

import static de.kipf.shop.util.EjbConstants.ERSTE_VERSION;
import static de.kipf.shop.util.EjbConstants.KEINE_ID;
import static javax.persistence.GenerationType.AUTO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
import javax.persistence.Entity;

@Entity
@Table(name = "einzelteil")
@NamedQueries( {
		@NamedQuery(name = Einzelteil.ALLE_EINZELTEILE, query = "FROM Einzelteil e", hints = {
				@QueryHint(name = "org.hibernate.cacheable", value = "true"),
				@QueryHint(name = "org.hibernate.cacheRegion", value = "org.hibernate.cache.StandardQueryCache") }),
		@NamedQuery(name = Einzelteil.EINZELTEIL_BY_ID, query = "FROM Einzelteil e WHERE e.id=:id", hints = {
				@QueryHint(name = "org.hibernate.cacheable", value = "true"),
				@QueryHint(name = "org.hibernate.cacheRegion", value = "org.hibernate.cache.StandardQueryCache") }) })
public class Einzelteil implements java.io.Serializable {
	private static final long serialVersionUID = 3754600477578663000L;

	protected static final Log LOG = LogFactory.getLog(Einzelteil.class);
	protected static final boolean DEBUG = LOG.isDebugEnabled();
	protected static final boolean TRACE = LOG.isTraceEnabled();

	public static final String ALLE_EINZELTEILE = "einzelteile.alle";
	public static final String EINZELTEIL_BY_ID = "einzelteil.by.id";

	@Id
	@GeneratedValue(strategy = AUTO, generator = "einzelteil_sequence")
	@SequenceGenerator(name = "einzelteil_sequence", sequenceName = "einzelteil_teile_id_seq", allocationSize = 1)
	@Column(name = "teile_id", nullable = false)
	protected Long id = KEINE_ID;

	@Version
	protected int version = ERSTE_VERSION;

	@Column(length = 50, nullable = false)
	protected String beschreibung = "";

	@Column(nullable = false)
	protected float preis;

	@ManyToOne
	@JoinColumn(name = "tt_id_fk")
	protected Teiltyp teiltyp;

	@OneToMany(mappedBy = "einzelteil")
	@OrderBy("id ASC")
	protected List<Lagerbuchung> lagerbuchungen = new ArrayList<Lagerbuchung>();

	// @OneToMany(mappedBy = "teilId")
	// @OrderBy("id ASC")
	// protected List<ModellEinzelteil> modellEinzelteil = new
	// ArrayList<ModellEinzelteil>();

	public Einzelteil() {
		super();
	}

	@PostPersist
	protected void logDbId() {
		if (DEBUG)
			LOG.debug("Neues Einzelteil mit ID=" + id + " : " + this);
	}

	@PostUpdate
	protected void logNeueVersion() {
		if (TRACE)
			LOG.trace("Einzelteil ID=" + id + " geaendert: neue Version="
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

	public List<Lagerbuchung> getLagerbuchungen() {
		return lagerbuchungen;
	}

	public void setLagerbuchungen(List<Lagerbuchung> lagerbuchungen) {
		this.lagerbuchungen = lagerbuchungen;
	}

	public float getPreis() {
		return preis;
	}

	public void setPreis(float preis) {
		this.preis = preis;
	}

	public Teiltyp getTeiltyp() {
		return teiltyp;
	}

	public void setTeiltyp(Teiltyp teiltyp) {
		this.teiltyp = teiltyp;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "id=" + id + " ,version=" + version + " ,Beschreibung="
				+ beschreibung + " Preis=" + preis + " ,Teiltypid="
				+ teiltyp.getId();
	}

	@Override
	public boolean equals(Object other) {
		if (this == other)
			return true;
		if (other instanceof Einzelteil == false)
			return false;
		final Einzelteil e = (Einzelteil) other;
		return id.equals(e.id) && (version == e.version)
				&& beschreibung.equals(e.getBeschreibung()) && (preis == e.preis)
				&& teiltyp.equals(e.getTeiltyp())
				/*&& lagerbuchungen.equals(e.getLagerbuchungen())*/;
	}

	@Override
	public int hashCode() {
		int result = 37 + version; // Version als Offset
		result ^= id.intValue(); // Bit-weise XOR
		result ^= beschreibung.hashCode();
		result ^= (int) preis;
		result ^= teiltyp.hashCode();
		return result;
	}
}
