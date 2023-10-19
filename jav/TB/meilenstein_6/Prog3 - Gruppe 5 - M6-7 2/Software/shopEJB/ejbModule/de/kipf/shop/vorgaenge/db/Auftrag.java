package de.kipf.shop.vorgaenge.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;

import static javax.persistence.GenerationType.AUTO;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.kipf.shop.personen.db.Benutzer;
import static de.kipf.shop.util.EjbConstants.ERSTE_VERSION;
import static de.kipf.shop.util.EjbConstants.KEINE_ID;

@Entity
@Table(name = "auftrag")
public class Auftrag implements java.io.Serializable {

	private static final long serialVersionUID = 8794973281341712298L;

	protected static final Log LOG = LogFactory.getLog(Auftrag.class);

	protected static final boolean DEBUG = LOG.isDebugEnabled();

	protected static final boolean TRACE = LOG.isTraceEnabled();

	@Id
	@GeneratedValue(strategy = AUTO, generator = "auftrag_sequence_name")
	@SequenceGenerator(name = "auftrag_sequence_name", sequenceName = "auftrag_a_id_seq", allocationSize = 1)
	@Column(name = "a_id", nullable = false)
	protected Long id = KEINE_ID;

	@Version
	protected int version = ERSTE_VERSION;

	@OneToMany(mappedBy = "pk.auftrag")
	@OrderBy("zeitstempel ASC")
	protected List<Auftragsstatus> auftragsstati = new ArrayList<Auftragsstatus>();

	@ManyToOne
	@JoinColumn(name = "benutzername_fk")
	protected Benutzer benutzer;

	public Auftrag() {
		super();
	}

	@PrePersist
	protected void prePersist() {
	}

	@PostPersist
	protected void logDbId() {
		if (DEBUG)
			LOG.debug("Neuer Auftrag mit id=" + id + " : " + this);
	}

	@PreUpdate
	protected void preUpdate() {
	}

	@PostUpdate
	protected void logNeueVersion() {
		if (TRACE)
			LOG.trace("Auftrag id=" + id + " geaendert: neue Version="
					+ version);
	}

	public List<Auftragsstatus> getAuftragsstati() {
		return auftragsstati;
	}

	public void setAuftragsstati(List<Auftragsstatus> auftragsstati) {
		this.auftragsstati = auftragsstati;
	}

	public Benutzer getBenutzer() {
		return benutzer;
	}

	public void setBenutzer(Benutzer benutzer) {
		this.benutzer = benutzer;
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
		return "{id=" + id + ", version=" + version + ", benutzer=" + benutzer
				+ "}";
	}

	@Override
	public boolean equals(Object other) {
		if (this == other)
			return true;
		if (other instanceof Auftrag == false)
			return false;

		final Auftrag a = (Auftrag) other;
		return id.equals(a.id) && version == a.version
				&& auftragsstati.equals(a.auftragsstati)
				&& benutzer.equals(a.benutzer);
	}

	@Override
	public int hashCode() {
		int result = 37 + version;
		result ^= id.hashCode();
		result ^= benutzer.hashCode();
		return result;
	}

}
