package de.kipf.shop.teile.db;

import static de.kipf.shop.util.EjbConstants.ERSTE_VERSION;
import static de.kipf.shop.util.EjbConstants.KEINE_ID;
import static javax.persistence.GenerationType.AUTO;
import static javax.persistence.TemporalType.TIMESTAMP;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.QueryHint;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Version;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Entity
@Table(name = "lager")
@NamedQueries( { @NamedQuery(name = Lagerbuchung.LAGERBEWEGUNG_EINES_TEILS, query = "SELECT sum(l.menge) from Lagerbuchung l WHERE l.buchungsart=:art AND l.einzelteil.id=:typ", hints = {
		@QueryHint(name = "org.hibernate.cacheable", value = "true"),
		@QueryHint(name = "org.hibernate.cacheRegion", value = "org.hibernate.cache.StandardQueryCache") }) })
public class Lagerbuchung implements java.io.Serializable {
	private static final long serialVersionUID = 2976420147370734212L;

	protected static final Log LOG = LogFactory.getLog(Lagerbuchung.class);
	protected static final boolean DEBUG = LOG.isDebugEnabled();
	protected static final boolean TRACE = LOG.isTraceEnabled();

	public static final String LAGERBEWEGUNG_EINES_TEILS = "lagerbestand.by.teil";
	public static final Character ZUGANG = 's';
	public static final Character ABGANG = 'h';

	@Id
	@GeneratedValue(strategy = AUTO, generator = "lager_sequence")
	@SequenceGenerator(name = "lager_sequence", sequenceName = "lager_l_id_seq", allocationSize = 1)
	@Column(name = "l_id", nullable = false)
	protected Long id = KEINE_ID;

	@Version
	protected int version = ERSTE_VERSION;

	@ManyToOne
	@JoinColumn(name = "teilenr_fk")
	protected Einzelteil einzelteil;

	@Column(nullable = false)
	@Temporal(TIMESTAMP)
	protected Date buchungsdatum;

	@Column(nullable = false)
	protected Character buchungsart;

	@Column(nullable = false)
	protected int menge;

	public Lagerbuchung() {
		super();
	}

	@PostPersist
	protected void logDbId() {
		if (DEBUG)
			LOG.debug("Neue Lagerbuchung mit ID=" + id + " : " + this);
	}

	@PostUpdate
	protected void logNeueVersion() {
		if (TRACE)
			LOG.trace("Lagerbuchung ID=" + id + " geaendert: neue Version="
					+ version);
	}

	public Character getBuchungsart() {
		return buchungsart;
	}

	public void setBuchungsart(Character buchungsart) {
		this.buchungsart = buchungsart;
	}

	public Date getBuchungsdatum() {
		return buchungsdatum;
	}

	public void setBuchungsdatum(Date buchungsdatum) {
		this.buchungsdatum = buchungsdatum;
	}

	public Einzelteil getEinzelteil() {
		return einzelteil;
	}

	public void setEinzelteil(Einzelteil einzelteil) {
		this.einzelteil = einzelteil;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getMenge() {
		return menge;
	}

	public void setMenge(int menge) {
		this.menge = menge;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "id=" + id + ", version=" + version + " ,Einzelteilid="
				+ einzelteil.id + " ,Buchungsart=" + buchungsart + " ,Menge="
				+ menge;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other)
			return true;
		if (other instanceof Lagerbuchung == false)
			return false;

		final Lagerbuchung l = (Lagerbuchung) other;
		return id.equals(l.getId()) && version == (l.getVersion())
				&& einzelteil.id.equals(l.getId())
				&& buchungsdatum.equals(l.getBuchungsdatum())
				&& buchungsart.equals(l.getBuchungsart())
				&& menge == l.getMenge();
	}

	@Override
	public int hashCode() {
		int result = 37 + version; // Version als Offset
		result ^= id.intValue(); // Bit-weise XOR
		result ^= buchungsdatum.hashCode();
		result ^= menge;
		return result;
	}
}
