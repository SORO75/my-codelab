package de.kipf.shop.vorgaenge.db;

import static de.kipf.shop.util.EjbConstants.ERSTE_VERSION;
import static de.kipf.shop.util.EjbConstants.KEINE_ID;
import static javax.persistence.GenerationType.AUTO;
import static javax.persistence.TemporalType.TIMESTAMP;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Version;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.kipf.shop.teile.db.Modell;

@Entity
@Table(name = "warenkorb")
public class Warenkorb implements java.io.Serializable {
	private static final long serialVersionUID = -5375687918337847552L;

	protected static final Log LOG = LogFactory.getLog(Warenkorb.class);
	protected static final boolean DEBUG = LOG.isDebugEnabled();
	protected static final boolean TRACE = LOG.isTraceEnabled();

	@Id
	@GeneratedValue(strategy = AUTO, generator = "warenkorb_sequence")
	@SequenceGenerator(name = "warenkorb_sequence", sequenceName = "warenkorb_w_id_seq", allocationSize = 1)
	@Column(name = "w_id", nullable = false)
	protected Long id = KEINE_ID;

	@Version
	protected int version = ERSTE_VERSION;

	@Column(nullable = false)
	@Temporal(TIMESTAMP)
	protected Date datum = new Date();

	@OneToOne
	@JoinColumn(name = "a_id_fk")
	protected Auftrag auftrag;

	@OneToMany(mappedBy = "warenkorbId")
	@OrderBy("id ASC")
	protected List<WarenkorbPosition> position;

	public Warenkorb() {
		super();
	}

	@PostPersist
	protected void logDbId() {
		if (DEBUG)
			LOG.debug("Neuer Warenkorb mit ID=" + id + " : " + this);
	}

	@PostUpdate
	protected void logNeueVersion() {
		if (TRACE)
			LOG.trace("Warenkorb ID=" + id + " geaendert: neue Version="
					+ version);
	}

	public Auftrag getAuftrag() {
		return auftrag;
	}

	public void setAuftrag(Auftrag auftrag) {
		this.auftrag = auftrag;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<WarenkorbPosition> getPosition() {
		return position;
	}

	public void setPosition(List<WarenkorbPosition> position) {
		this.position = position;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	@Override
	public String toString() {
		if (auftrag == null)
			return "id=" + id + " ,version=" + version + " ,Datum=" + datum
					+ " ,Auftrag=-1";
		return "id=" + id + " ,version=" + version + " ,Datum=" + datum
				+ " ,Auftrag=" + auftrag.getId();
	}

	@Override
	public boolean equals(Object other) {
		if (this == other)
			return true;
		if (other instanceof Warenkorb == false)
			return false;
		final Warenkorb w = (Warenkorb) other;
		return id.equals(w.id) && version == w.version && datum.equals(w.datum)
				&& auftrag.equals(w.auftrag) && position.equals(w.position);
	}

	@Override
	public int hashCode() {
		int result = 37 + version; // Version als Offset
		result ^= id.intValue(); // Bit-weise XOR
		result ^= datum.hashCode();
		return result;
	}
}
