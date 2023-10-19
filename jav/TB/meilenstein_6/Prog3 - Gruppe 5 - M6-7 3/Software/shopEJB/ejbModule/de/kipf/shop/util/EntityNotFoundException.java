package de.kipf.shop.util;

import java.io.Serializable;

import javax.ejb.ApplicationException;

import de.kipf.shop.teile.db.Einzelteil;

@ApplicationException(rollback = true)
public class EntityNotFoundException extends
		javax.persistence.EntityNotFoundException {

	private static final long serialVersionUID = -6831375297241021715L;

	private Serializable id;

	private String classname;

	public EntityNotFoundException(Serializable id, String classname) {
		super("Entity mit ID=" + id + ", Klasse=" + classname
				+ " nicht gefunden");
		this.id = id;
		this.classname = classname;
	}

	public EntityNotFoundException(Long id2, Class<? extends Einzelteil> class1, String simpleName) {
		// TODO Auto-generated constructor stub
	}

	public Serializable getId() {
		return id;
	}

	public String getClassname() {
		return classname;
	}
}
