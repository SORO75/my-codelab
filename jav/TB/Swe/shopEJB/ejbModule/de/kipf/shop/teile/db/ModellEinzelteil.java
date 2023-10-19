package de.kipf.shop.teile.db;

import static de.kipf.shop.util.EjbConstants.ERSTE_VERSION;
import static de.kipf.shop.util.EjbConstants.KEINE_ID;
import static javax.persistence.GenerationType.AUTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Entity
@Table(name = "modell_einzelteil")
public class ModellEinzelteil implements java.io.Serializable {
	private static final long serialVersionUID = -5388026539558307334L;

	protected static final Log LOG = LogFactory.getLog(ModellEinzelteil.class);
	protected static final boolean DEBUG = LOG.isDebugEnabled();
	protected static final boolean TRACE = LOG.isTraceEnabled();

	private static final int DEFAULT_MENGE = 1;
	
	@Id
	@GeneratedValue(strategy = AUTO, generator = "modell_einzelteil_sequence")
	@SequenceGenerator(name = "modell_einzelteil_sequence", sequenceName = "modell_einzelteil_me_id_seq", allocationSize = 1)
	@Column(name = "me_id", nullable = false)
	protected Long id = KEINE_ID;

	@ManyToOne
	@JoinColumn(name = "m_id_fk")
	protected Modell modellId;

	@ManyToOne
	@JoinColumn(name = "teile_id_fk")
	protected Einzelteil teilId;
		
	@Version
	protected int version = ERSTE_VERSION;

	@Column(nullable = false)
	protected int menge = DEFAULT_MENGE;

	public ModellEinzelteil() {
		super();
	}

	@PostPersist
	protected void logDbId() {
		if (DEBUG)
			LOG.debug("Neues ModellEinzelteil mit ID=" + id + " : " + this);
	}

	@PostUpdate
	protected void logNeueVersion() {
		if (TRACE)
			LOG.trace("ModellEinzelteil ID=" + id + " geaendert: neue Version="
					+ version);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Modell getModellId() {
		return modellId;
	}

	public void setModellId(Modell modellId) {
		this.modellId = modellId;
	}

	public Einzelteil getTeilId() {
		return teilId;
	}

	public void setTeilId(Einzelteil teilId) {
		this.teilId = teilId;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public int getMenge() {
		return menge;
	}

	public void setMenge(int menge) {
		this.menge = menge;
	}

	@Override
	public String toString() {
		return "id=" + id + " ,version=" + version + " ,menge=" + menge
				+ " ,modellId=" + modellId.getId() + " ,teilId="
				+ teilId.getId();
	}

	@Override
	public boolean equals(Object other) {
		if (this == other)
			return true;
		if (other instanceof ModellEinzelteil == false)
			return false;
		final ModellEinzelteil m = (ModellEinzelteil) other;
		return id.equals(m.id) && version == m.version
				&& modellId.equals(m.modellId) && teilId.equals(m.teilId);
	}
	
	@Override
	public int hashCode() {
		int result = 37 + version; // Version als Offset
		result ^= id.intValue(); // Bit-weise XOR
		result ^= menge;
		result ^= modellId.hashCode();
		result ^= teilId.hashCode();
		return result;
	}
}
