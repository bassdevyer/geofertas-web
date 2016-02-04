package com.geo.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the "PARAMETER" database table.
 * 
 */
@Entity
@Table(name="\"PARAMETER\"")
public class Parameter implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private Boolean enabled;

	private String name;
	
	private String value;

	//bi-directional many-to-one association to UserParameter
	@OneToMany(mappedBy="parameter")
	private List<UserParameter> userParameters;

	public Parameter() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getEnabled() {
		return this.enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public List<UserParameter> getUserParameters() {
		return this.userParameters;
	}

	public void setUserParameters(List<UserParameter> userParameters) {
		this.userParameters = userParameters;
	}

}