package de.kipf.shop.vorgaenge.db;

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

import de.kipf.shop.teile.db.Modell;

@Entity
@Table(name = "warenkorb_position")
public class WarenkorbPosition implements java.io.Serializable {
	private static final long serialVersionUID = -5135076100461408278L;

	protected static final Log LOG = LogFactory.getLog(WarenkorbPosition.class);
	protected static final boolean DEBUG = LOG.isDebugEnabled();
	protected static final boolean TRACE = LOG.isTraceEnabled();

	@Id
	@GeneratedValue(strategy = AUTO, generator = "modell_einzelteil_sequence")
	@SequenceGenerator(name = "modell_einzelteil_sequence", sequenceName = "modell_einzelteil_me_id_seq", allocationSize = 1)
	@Column(name = "wp_id", nullable = false)
	protected Long id = KEINE_ID;

	@ManyToOne
	@JoinColumn(name = "m_id")
	protected Modell modellId;

	@ManyToOne
	@JoinColumn(name = "w_id")
	protected Warenkorb warenkorbId;

	@Version
	protected int version = ERSTE_VERSION;

	@Column(nullable = false)
	protected int anzahl;

	public WarenkorbPosition() {
		super();
	}

	@PostPersist
	protected void logDbId() {
		if (DEBUG)
			LOG.debug("Neue WarenkorbPosition mit ID=" + id + " : " + this);
	}

	@PostUpdate
	protected void logNeueVersion() {
		if (TRACE)
			LOG.trace("WarenkorbPosition ID=" + id
					+ " geaendert: neue Version=" + version);
	}

	@Override
	public String toString() {
		return "id=" + id + " ,verion=" + version + " ,modellId"
				+ modellId.getId() + " ,warenkorbId" + warenkorbId.getId()
				+ " ,Anzahl=" + anzahl;
	}
	
	@Override
	public int hashCode() {
		int result = 37 + version; // Version als Offset
		result ^= id.intValue(); // Bit-weise XOR
		result ^= anzahl;
		result ^= modellId.hashCode();
		result ^= warenkorbId.hashCode();
		return result;
	}
}
