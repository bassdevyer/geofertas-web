package com.geo.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the "ADVERTISEMENT" database table.
 * 
 */
@Entity
@Table(name="\"ADVERTISEMENT\"")
public class Advertisement implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private Boolean active;

	private String description;

	@Column(name="end_date")
	private String endDate;

	private byte[] image;

	@Column(name="start_date")
	private String startDate;

	private String title;

	//bi-directional many-to-one association to AdvertisementTag
	@OneToMany(mappedBy="advertisement")
	private List<AdvertisementTag> advertisementTags;

	//bi-directional many-to-one association to UserAdvertisement
	@OneToMany(mappedBy="advertisement")
	private List<UserAdvertisement> userAdvertisements;

	public Advertisement() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getActive() {
		return this.active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEndDate() {
		return this.endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public byte[] getImage() {
		return this.image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getStartDate() {
		return this.startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<AdvertisementTag> getAdvertisementTags() {
		return this.advertisementTags;
	}

	public void setAdvertisementTags(List<AdvertisementTag> advertisementTags) {
		this.advertisementTags = advertisementTags;
	}

	public List<UserAdvertisement> getUserAdvertisements() {
		return this.userAdvertisements;
	}

	public void setUserAdvertisements(List<UserAdvertisement> userAdvertisements) {
		this.userAdvertisements = userAdvertisements;
	}

}