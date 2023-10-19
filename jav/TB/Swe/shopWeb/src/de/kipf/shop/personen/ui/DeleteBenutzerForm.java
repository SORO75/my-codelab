package de.kipf.shop.personen.ui;

import org.apache.struts.action.ActionForm;

public class DeleteBenutzerForm extends ActionForm {
	private static final long serialVersionUID = -9049374172823649366L;
	private String id;

    public DeleteBenutzerForm() {
        super();
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    
	@Override
	public String toString() {
		return "{id=" + id + "}";
	}
}
