package org.primefaces.showcase.view.misc;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class FocusView implements Serializable {

	private String forFocus;

	private String username;
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void save() {

		if (!"admin".equals(username)) {
			forFocus = "username";

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid User Name", "Invalid User Name"));
		} else if (!"admin".equals(password)) {
			forFocus = "password";

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Password", "Invalid Password"));
		} else {

			username = null;
			password = null;
			forFocus = null;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Success"));

		}
	}

	public String getForFocus() {
		System.out.println("getForFocus " + forFocus);
		return forFocus;
	}

	public void setForFocus(String forFocus) {
		System.out.println("setForFocus " + forFocus);
		this.forFocus = forFocus;
	}

}