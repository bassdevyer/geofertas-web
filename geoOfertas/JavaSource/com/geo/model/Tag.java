package com.geo.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the "TAG" database table.
 * 
 */
@Entity
@Table(name="\"TAG\"")
public class Tag implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private Boolean enabled;

	private Long name;

	//bi-directional many-to-one association to AdvertisementTag
	@OneToMany(mappedBy="tag")
	private List<AdvertisementTag> advertisementTags;

	//bi-directional many-to-one association to UserTag
	@OneToMany(mappedBy="tag")
	private List<UserTag> userTags;

	public Tag() {
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

	public Long getName() {
		return this.name;
	}

	public void setName(Long name) {
		this.name = name;
	}

	public List<AdvertisementTag> getAdvertisementTags() {
		return this.advertisementTags;
	}

	public void setAdvertisementTags(List<AdvertisementTag> advertisementTags) {
		this.advertisementTags = advertisementTags;
	}

	public List<UserTag> getUserTags() {
		return this.userTags;
	}

	public void setUserTags(List<UserTag> userTags) {
		this.userTags = userTags;
	}

}