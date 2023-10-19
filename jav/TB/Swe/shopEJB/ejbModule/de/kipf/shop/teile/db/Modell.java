package de.kipf.shop.teile.db;

import static de.kipf.shop.util.EjbConstants.ERSTE_VERSION;
import static de.kipf.shop.util.EjbConstants.KEINE_ID;
import static javax.persistence.GenerationType.AUTO;
import static javax.persistence.TemporalType.TIMESTAMP;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
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
import javax.persistence.Temporal;
import javax.persistence.Version;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.kipf.shop.vorgaenge.db.WarenkorbPosition;

@Entity
@Table(name = "modell")
@Inheritance
@DiscriminatorColumn(name = "art", length = 1)
@NamedQueries( {
		@NamedQuery(name = Modell.ALLE_MODELLE, query = "FROM Modell m", hints = {
				@QueryHint(name = "org.hibernate.cacheable", value = "true"),
				@QueryHint(name = "org.hibernate.cacheRegion", value = "org.hibernate.cache.StandardQueryCache") }),
		@NamedQuery(name = Modell.ALLE_ANGEBOTS_MODELLE, query = "FROM AngebotsModell m", hints = {
				@QueryHint(name = "org.hibernate.cacheable", value = "true"),
				@QueryHint(name = "org.hibernate.cacheRegion", value = "org.hibernate.cache.StandardQueryCache") }),
		@NamedQuery(name = Modell.MODELL_BY_ID, query = "FROM Modell m WHERE m.id=:id", hints = {
				@QueryHint(name = "org.hibernate.cacheable", value = "true"),
				@QueryHint(name = "org.hibernate.cacheRegion", value = "org.hibernate.cache.StandardQueryCache") }),
		@NamedQuery(name = Modell.MODELL_BY_NAME, query = "FROM Modell m WHERE m.beschreibung LIKE :name", hints = {
				@QueryHint(name = "org.hibernate.cacheable", value = "true"),
				@QueryHint(name = "org.hibernate.cacheRegion", value = "org.hibernate.cache.StandardQueryCache") }),
		@NamedQuery(name = Modell.EINZELTEILE_VON_MODELL, query = "SELECT me.teilId FROM ModellEinzelteil me WHERE me.modellId =:modell", hints = {
				@QueryHint(name = "org.hibernate.cacheable", value = "true"),
				@QueryHint(name = "org.hibernate.cacheRegion", value = "org.hibernate.cache.StandardQueryCache") }) 
})
public abstract class Modell implements Serializable {
	protected static final Log LOG = LogFactory.getLog(Modell.class);
	protected static final boolean DEBUG = LOG.isDebugEnabled();
	protected static final boolean TRACE = LOG.isTraceEnabled();

	public static final String ANGEBOTSMODELL = "a";
	public static final String KUNDENMODELL = "k";

	public final static String ALLE_MODELLE = "modell.alle";
	public final static String ALLE_ANGEBOTS_MODELLE = "modell.alle";
	public final static String MODELL_BY_ID = "modell.by.id";
	public final static String MODELL_BY_NAME = "modell.by.name";
	public final static String EINZELTEILE_VON_MODELL = "einzelteile.von.modell";
	public final static String AKTUELLES_ANGEBOT = "aktuelles.angebot";

	@Id
	@GeneratedValue(strategy = AUTO, generator = "modell_sequence")
	@SequenceGenerator(name = "modell_sequence", sequenceName = "modell_m_id_seq", allocationSize = 1)
	@Column(name = "m_id", nullable = false)
	protected Long id = KEINE_ID;

	@Version
	protected int version = ERSTE_VERSION;

	@ManyToOne
	@JoinColumn(name = "typ_id_fk")
	protected Fahrradtyp typ;

	@Column(length = 200, nullable = false)
	protected String beschreibung = "";

	@Column(nullable = false)
	@Temporal(TIMESTAMP)
	protected Date datum;

	@Column(nullable = false)
	@Temporal(TIMESTAMP)
	protected Date startzeitpunkt;

	@Column(nullable = false)
	@Temporal(TIMESTAMP)
	protected Date endzeitpunkt;

	@Column(nullable = false)
	protected float preis;

	@Column(name = "bild")
	protected String bildUrl;

	@OneToMany(mappedBy = "modellId")
	@OrderBy("id ASC")
	protected List<ModellEinzelteil> modellEinzelteile = new ArrayList<ModellEinzelteil>();

	@OneToMany(mappedBy = "modellId")
	@OrderBy("id ASC")
	protected List<WarenkorbPosition> position = new ArrayList<WarenkorbPosition>();

	public Modell() {
		super();
	}

	@PostPersist
	protected void logDbId() {
		if (DEBUG)
			LOG.debug("Neues Modell mit ID=" + id + " : " + this);
	}

	@PostUpdate
	protected void logNeueVersion() {
		if (TRACE)
			LOG
					.trace("Modell ID=" + id + " geaendert: neue Version="
							+ version);
	}

	abstract public String getArt();

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

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public Date getEndzeitpunkt() {
		return endzeitpunkt;
	}

	public void setEndzeitpunkt(Date endzeitpunkt) {
		this.endzeitpunkt = endzeitpunkt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<ModellEinzelteil> getModellEinzelteile() {
		return modellEinzelteile;
	}

	public void setModellEinzelteile(List<ModellEinzelteil> modellEinzelteile) {
		this.modellEinzelteile = modellEinzelteile;
	}

	public List<WarenkorbPosition> getPosition() {
		return position;
	}

	public void setPosition(List<WarenkorbPosition> position) {
		this.position = position;
	}

	public float getPreis() {
		return preis;
	}

	public void setPreis(float preis) {
		this.preis = preis;
	}

	public Date getStartzeitpunkt() {
		return startzeitpunkt;
	}

	public void setStartzeitpunkt(Date startzeitpunkt) {
		this.startzeitpunkt = startzeitpunkt;
	}

	public Fahrradtyp getTyp() {
		return typ;
	}

	public void setTyp(Fahrradtyp typ) {
		this.typ = typ;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "id=" + id + " ,version=" + version + " ,Typ=" + getArt()
				+ " ,Art=" + typ.getRadtypbeschreibung() + " ,Beschreibung="
				+ beschreibung + " ,Datum=" + datum + " ,Startzeitpunkt="
				+ startzeitpunkt + " ,Endzeitpunkt=" + endzeitpunkt
				+ " ,Preis=" + preis + " ,BildUrl" + bildUrl;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other)
			return true;
		if (other instanceof Modell == false)
			return false;
		final Modell m = (Modell) other;
		return id.equals(m.id) && version == m.version && typ.equals(m.typ)
				&& beschreibung.equals(m.beschreibung) && datum.equals(m.datum)
				&& startzeitpunkt.equals(m.startzeitpunkt)
				&& endzeitpunkt.equals(m.endzeitpunkt) && preis == m.preis
				&& bildUrl.equals(m.bildUrl)
				&& modellEinzelteile.equals(m.modellEinzelteile)
				&& position.equals(m.position);
	}

	@Override
	public int hashCode() {
		int result = 37 + version; // Version als Offset
		result ^= id.intValue(); // Bit-weise XOR
		result ^= beschreibung.hashCode();
		result ^= (int) preis;
		result ^= datum.hashCode();
		result ^= modellEinzelteile.hashCode();
		return result;
	}
}
