package de.kipf.shop.vorgaenge.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.QueryHint;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;
import javax.persistence.Table;
import static javax.persistence.GenerationType.AUTO;

import static de.kipf.shop.util.EjbConstants.KEINE_ID;
import static de.kipf.shop.util.EjbConstants.ERSTE_VERSION;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Entity
@Table(name = "auftragsstatustyp")
@NamedQueries( {
		@NamedQuery(name = Auftragsstatustyp.AUFTRAGSSTATUSTYPEN_ALL, query = "FROM Auftragsstatustyp t", hints = {
				@QueryHint(name = "org.hibernate.cacheable", value = "true"),
				@QueryHint(name = "org.hibernate.cacheRegion", value = "org.hibernate.cache.StandardQueryCache") }),
		@NamedQuery(name = Auftragsstatustyp.AUFTRAGSSTATUSTYP_BY_NAME, query = "FROM Auftragsstatustyp t WHERE t.bezeichnung = :" + Auftragsstatustyp.ATTR_BEZEICHNUNG)
})
public class Auftragsstatustyp implements java.io.Serializable {

	private static final long serialVersionUID = 2265937106197444219L;

	protected static final Log LOG = LogFactory.getLog(Auftragsstatustyp.class);

	protected static final boolean DEBUG = LOG.isDebugEnabled();

	protected static final boolean TRACE = LOG.isTraceEnabled();
	
	public static final String ATTR_BEZEICHNUNG = "Bezeichnung";
	
	public static final String AUFTRAGSSTATUSTYPEN_ALL = "auftragsstatustyp.all";
	
	public static final String AUFTRAGSSTATUSTYP_BY_NAME = "auftragsstatustyp.byName";
	

	@Id
	@GeneratedValue(strategy = AUTO, generator = "statustyp_sequence_name")
	@SequenceGenerator(name = "statustyp_sequence_name", sequenceName = "auftragsstatustyp_status_id_seq", allocationSize = 1)
	@Column(name = "status_id", nullable = false)
	protected Long id = KEINE_ID;

	@Version
	protected int version = ERSTE_VERSION;

	@Column(length = 50, nullable = false, unique = true)
	protected String bezeichnung;

	public Auftragsstatustyp() {
		super();
	}

	@PrePersist
	protected void prePersist() {
	}

	@PostPersist
	protected void logDbId() {
		if (DEBUG)
			LOG.debug("Neuer Auftragsstatustyp mit id=" + id + " : " + this);
	}

	@PreUpdate
	protected void preUpdate() {
	}

	@PostUpdate
	protected void logNeueVersion() {
		if (TRACE)
			LOG.trace("Auftragsstatustyp id=" + id
					+ " geaendert: neue Version=" + version);
	}

	public String getBezeichnung() {
		return bezeichnung;
	}

	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
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

	@Override
	public String toString() {
		return "{id=" + id + ", version=" + version + ", bezeichnung="
				+ bezeichnung + "}";
	}

	@Override
	public boolean equals(Object other) {
		if (this == other)
			return true;
		if (other instanceof Auftragsstatustyp == false)
			return false;

		final Auftragsstatustyp at = (Auftragsstatustyp) other;
		return id.equals(at.id) && version == at.version
				&& bezeichnung.equals(at.bezeichnung);
	}

	@Override
	public int hashCode() {
		int result = 37 + version;
		result ^= id.hashCode();
		result ^= bezeichnung.hashCode();
		return result;
	}

}
