package com.geo.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "USER_TAG" database table.
 * 
 */
@Entity
@Table(name="\"USER_TAG\"")
public class UserTag implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private Boolean enabled;

	//bi-directional many-to-one association to Tag
	@ManyToOne
	private Tag tag;

	//bi-directional many-to-one association to User
	@ManyToOne
	private User user;

	public UserTag() {
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

	public Tag getTag() {
		return this.tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}