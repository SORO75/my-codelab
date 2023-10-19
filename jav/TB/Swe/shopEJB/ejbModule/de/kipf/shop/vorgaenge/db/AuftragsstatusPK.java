package de.kipf.shop.vorgaenge.db;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class AuftragsstatusPK implements java.io.Serializable {

	private static final long serialVersionUID = 8765905318112992031L;

	@ManyToOne
	@JoinColumn(name = "a_id")
	public Auftrag auftrag;

	@ManyToOne
	@JoinColumn(name = "status_id")
	public Auftragsstatustyp status;
	
	public AuftragsstatusPK() {
		super();
	}
	
	public boolean equals(Object other) {
		if (this == other)
			return true;
		if (other instanceof AuftragsstatusPK == false)
			return false;
		final AuftragsstatusPK pk = (AuftragsstatusPK) other;
		return auftrag.equals(pk.auftrag) && status.equals(pk.status);
	}
	
	public Auftrag getAuftrag() {
		return auftrag;
	}

	public void setAuftrag(Auftrag auftrag) {
		this.auftrag = auftrag;
	}

	public Auftragsstatustyp getStatus() {
		return status;
	}

	public void setStatus(Auftragsstatustyp status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		return auftrag.hashCode() + status.hashCode();
	}
	
	
}
