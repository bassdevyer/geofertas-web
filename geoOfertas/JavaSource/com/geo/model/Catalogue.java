package com.geo.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the "CATALOGUE" database table.
 * 
 */
@Entity
@Table(name="\"CATALOGUE\"")
public class Catalogue implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private String description;

	private Boolean enabled;

	private String name;

	//bi-directional many-to-one association to CatalogueDetail
	@OneToMany(mappedBy="catalogue")
	private List<CatalogueDetail> catalogueDetails;

	public Catalogue() {
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<CatalogueDetail> getCatalogueDetails() {
		return this.catalogueDetails;
	}

	public void setCatalogueDetails(List<CatalogueDetail> catalogueDetails) {
		this.catalogueDetails = catalogueDetails;
	}

}