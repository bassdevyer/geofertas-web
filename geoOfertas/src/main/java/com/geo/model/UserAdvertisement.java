package com.geo.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "USER_ADVERTISEMENT" database table.
 * 
 */
@Entity
@Table(name="\"USER_ADVERTISEMENT\"")
public class UserAdvertisement implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private Boolean enabled;

	//bi-directional many-to-one association to Advertisement
	@ManyToOne
	private Advertisement advertisement;

	//bi-directional many-to-one association to User
	@ManyToOne
	private User user;

	public UserAdvertisement() {
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

	public Advertisement getAdvertisement() {
		return this.advertisement;
	}

	public void setAdvertisement(Advertisement advertisement) {
		this.advertisement = advertisement;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}