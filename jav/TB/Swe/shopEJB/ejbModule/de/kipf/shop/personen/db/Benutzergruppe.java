package de.kipf.shop.personen.db;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import static de.kipf.shop.util.EjbConstants.KEINE_ID;
import static de.kipf.shop.util.EjbConstants.ERSTE_VERSION;

@Entity
@Table(name = "benutzergruppe")
@NamedQueries( {
		@NamedQuery(name = Benutzergruppe.BENUTZERGRUPPEN_ALL, query = "FROM Benutzergruppe g", hints = {
				@QueryHint(name = "org.hibernate.cacheable", value = "true"),
				@QueryHint(name = "org.hibernate.cacheRegion", value = "org.hibernate.cache.StandardQueryCache") }),
		@NamedQuery(name = Benutzergruppe.BENUTZERGRUPPE_ALL_FETCH_BENUTZER, query = "FROM Benutzergruppe g LEFT JOIN FETCH g.benutzer", hints = {
						@QueryHint(name = "org.hibernate.cacheable", value = "true"),
						@QueryHint(name = "org.hibernate.cacheRegion", value = "org.hibernate.cache.StandardQueryCache") }),
		@NamedQuery(name = Benutzergruppe.BENUTZERGRUPPE_BY_NAME, query = "FROM Benutzergruppe g WHERE g.name = :"
				+ Benutzergruppe.ATTR_NAME),
		@NamedQuery(name = Benutzergruppe.BENUTZERGRUPPE_BY_NAME_FETCH_BENUTZER, query = "SELECT DISTINCT g FROM Benutzergruppe g LEFT JOIN FETCH g.benutzer b WHERE g.name = :"
				+ Benutzergruppe.ATTR_NAME) })
public class Benutzergruppe implements java.io.Serializable {

	private static final long serialVersionUID = 2085309688164785447L;

	protected static final Log LOG = LogFactory.getLog(Benutzergruppe.class);

	protected static final boolean DEBUG = LOG.isDebugEnabled();

	protected static final boolean TRACE = LOG.isTraceEnabled();

	public static final String ATTR_NAME = "Name";

	public static final String BENUTZERGRUPPEN_ALL = "benutzergruppen.all";
	
	public static final String BENUTZERGRUPPE_ALL_FETCH_BENUTZER = "benutzergruppen.all.fetchBenutzer";

	public static final String BENUTZERGRUPPE_BY_NAME = "benutzergruppe.byName";

	public static final String BENUTZERGRUPPE_BY_NAME_FETCH_BENUTZER = "benutzergruppe.byName.fetchBenutzer";

	@Id
	@GeneratedValue(strategy = AUTO, generator = "benutzergruppe_sequence_name")
	@SequenceGenerator(name = "benutzergruppe_sequence_name", sequenceName = "benutzergruppe_bg_id_seq", allocationSize = 1)
	@Column(name = "bg_id", nullable = false)
	protected Long id = KEINE_ID;

	@Version
	protected int version = ERSTE_VERSION;

	@ManyToMany
	@JoinTable(name = "benutzer_gruppe", joinColumns = { @JoinColumn(name = "bg_id") }, inverseJoinColumns = { @JoinColumn(name = "benutzername") })
	// @OrderBy("benutzername ASC") // Für Set wohl nicht möglich/nötig
	protected Set<Benutzer> benutzer;

	@Column(length = 50, nullable = false, unique = true)
	protected String name;

	public Benutzergruppe() {
		super();
	}

	public Benutzergruppe(String name) {
		super();
		this.name = name;
	}

	@PrePersist
	protected void prePersist() {
	}

	@PostPersist
	protected void logDbId() {
		if (DEBUG)
			LOG.debug("Neue Benutzergruppe mit ID=" + id + " : " + this);
	}

	@PreUpdate
	protected void preUpdate() {
	}

	@PostUpdate
	protected void logNeueVersion() {
		if (TRACE)
			LOG.trace("Benutzergruppe ID=" + id + " geaendert: neue Version="
					+ version);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Set<Benutzer> getBenutzer() {
		return benutzer;
	}

	public void setBenutzer(Set<Benutzer> benutzer) {
		this.benutzer = benutzer;
	}

	@Override
	public String toString() {
		return "{id=" + id + ", version=" + version + ", name=" + name + "}";
	}

	@Override
	public boolean equals(Object other) {
		if (this == other)
			return true;
		if (other instanceof Benutzergruppe == false)
			return false;

		final Benutzergruppe bg = (Benutzergruppe) other;
		return id.equals(bg.id) && version == bg.version
				&& name.equals(bg.name);
	}

}
