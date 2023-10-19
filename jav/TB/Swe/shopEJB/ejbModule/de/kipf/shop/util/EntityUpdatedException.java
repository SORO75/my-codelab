package de.kipf.shop.util;

import java.io.Serializable;

import javax.ejb.ApplicationException;

import de.kipf.shop.teile.db.Einzelteil;

@ApplicationException(rollback = true)
public class EntityUpdatedException extends RuntimeException {

	private static final long serialVersionUID = -2948160566808917395L;

	private Serializable id;

	private String classname;

	public EntityUpdatedException(Serializable id, String classname) {
		super("Entity mit ID=" + id + ", Klasse=" + classname
				+ " wurde konkurrierend aktualisiert");
		this.id = id;
		this.classname = classname;
	}

	public EntityUpdatedException(Long id2, Class<? extends Einzelteil> class1, String simpleName) {

	}

	public Serializable getId() {
		return id;
	}

	public String getClassname() {
		return classname;
	}
}
