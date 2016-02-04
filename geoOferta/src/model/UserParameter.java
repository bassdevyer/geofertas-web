package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "USER_PARAMETER" database table.
 * 
 */
@Entity
@Table(name="\"USER_PARAMETER\"")
public class UserParameter implements Serializable {
	private static final long serialVersionUID = 1L;

	private Boolean enabled;

	private Long id;

	//bi-directional many-to-one association to Parameter
	@ManyToOne
	private Parameter parameter;

	//bi-directional many-to-one association to User
	@ManyToOne
	private User user;

	public UserParameter() {
	}

	public Boolean getEnabled() {
		return this.enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Parameter getParameter() {
		return this.parameter;
	}

	public void setParameter(Parameter parameter) {
		this.parameter = parameter;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}