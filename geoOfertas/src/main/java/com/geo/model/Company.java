package com.geo.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the "COMPANY" database table.
 * 
 */
@Entity
@Table(name="\"COMPANY\"")
public class Company implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private String description;

	private Boolean enabled;

	private byte[] logo;

	private String name;

	//bi-directional many-to-one association to BranchOffice
	@OneToMany(mappedBy="company")
	private List<BranchOffice> branchOffices;

	//bi-directional many-to-one association to UserCompany
	@OneToMany(mappedBy="company")
	private List<UserCompany> userCompanies;

	public Company() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getEnabled() {
		return this.enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public byte[] getLogo() {
		return this.logo;
	}

	public void setLogo(byte[] logo) {
		this.logo = logo;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<BranchOffice> getBranchOffices() {
		return this.branchOffices;
	}

	public void setBranchOffices(List<BranchOffice> branchOffices) {
		this.branchOffices = branchOffices;
	}

	public List<UserCompany> getUserCompanies() {
		return this.userCompanies;
	}

	public void setUserCompanies(List<UserCompany> userCompanies) {
		this.userCompanies = userCompanies;
	}

}