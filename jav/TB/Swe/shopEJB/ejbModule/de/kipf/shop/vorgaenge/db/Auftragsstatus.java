package de.kipf.shop.vorgaenge.db;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.Version;
import javax.persistence.Table;
import static javax.persistence.TemporalType.TIMESTAMP;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Date;

import static de.kipf.shop.util.EjbConstants.ERSTE_VERSION;

@Entity
@Table(name = "auftragsstatus")
public class Auftragsstatus implements java.io.Serializable {

	private static final long serialVersionUID = 7537582791671414219L;

	protected static final Log LOG = LogFactory.getLog(Auftragsstatus.class);

	protected static final boolean DEBUG = LOG.isDebugEnabled();

	protected static final boolean TRACE = LOG.isTraceEnabled();

	@Version
	protected int version = ERSTE_VERSION;

	@EmbeddedId
	protected AuftragsstatusPK pk = null;

	@Column(name = "datum", nullable = false)
	@Temporal(TIMESTAMP)
	protected Date zeitstempel;

	public Auftragsstatus(Auftrag auftrag, Auftragsstatustyp statusTyp, Date zeitstempel) {
		super();
		AuftragsstatusPK pk = new AuftragsstatusPK();
		pk.auftrag = auftrag;
		pk.status = statusTyp;
		this.pk = pk;
		this.zeitstempel = zeitstempel;
	}
	
	public Auftragsstatus() {
		super();
	}

	@PrePersist
	protected void prePersist() {
	}

	@PostPersist
	protected void logDbId() {
		if (DEBUG)
			LOG.debug("Neuer Auftragsstatus mit auftrag=" + pk.auftrag
					+ ", status=" + pk.status + " : " + this);
	}

	@PreUpdate
	protected void preUpdate() {
	}

	@PostUpdate
	protected void logNeueVersion() {
		if (TRACE)
			LOG.trace("Auftragsstatus auftrag=" + pk.auftrag + ", status="
					+ pk.status + " geaendert: neue Version=" + version);
	}

	public AuftragsstatusPK getPk() {
		return pk;
	}

	public void setPk(AuftragsstatusPK pk) {
		this.pk = pk;
	}

	public Date getZeitstempel() {
		return zeitstempel;
	}

	public void setZeitstempel(Date zeitstempel) {
		this.zeitstempel = zeitstempel;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "{auftrag=" + pk.auftrag + ", status=" + pk.status
				+ ", version=" + version + ", zeitstempel=" + zeitstempel + "}";
	}

	@Override
	public boolean equals(Object other) {
		if (this == other)
			return true;
		if (other instanceof Auftragsstatus == false)
			return false;

		final Auftragsstatus as = (Auftragsstatus) other;
		return version == as.version && pk.equals(as.pk)
				&& zeitstempel.equals(as.zeitstempel);
	}

	@Override
	public int hashCode() {
		int result = 37 + version;
		result ^= pk.hashCode();
		result ^= zeitstempel.hashCode();
		return result;
	}

}
