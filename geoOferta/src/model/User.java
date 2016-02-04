package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the "USER" database table.
 * 
 */
@Entity
@Table(name="\"USER\"")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(name="authentication_type")
	private String authenticationType;

	private String email;

	private Boolean enabled;

	@Column(name="first_name")
	private String firstName;

	@Column(name="last_name")
	private String lastName;

	private String password;

	private String username;

	//bi-directional many-to-one association to UserAdvertisement
	@OneToMany(mappedBy="user")
	private List<UserAdvertisement> userAdvertisements;

	//bi-directional many-to-one association to UserCompany
	@OneToMany(mappedBy="user")
	private List<UserCompany> userCompanies;

	//bi-directional many-to-one association to UserParameter
	@OneToMany(mappedBy="user")
	private List<UserParameter> userParameters;

	//bi-directional many-to-one association to UserTag
	@OneToMany(mappedBy="user")
	private List<UserTag> userTags;

	public User() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAuthenticationType() {
		return this.authenticationType;
	}

	public void setAuthenticationType(String authenticationType) {
		this.authenticationType = authenticationType;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getEnabled() {
		return this.enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<UserAdvertisement> getUserAdvertisements() {
		return this.userAdvertisements;
	}

	public void setUserAdvertisements(List<UserAdvertisement> userAdvertisements) {
		this.userAdvertisements = userAdvertisements;
	}

	public List<UserCompany> getUserCompanies() {
		return this.userCompanies;
	}

	public void setUserCompanies(List<UserCompany> userCompanies) {
		this.userCompanies = userCompanies;
	}

	public List<UserParameter> getUserParameters() {
		return this.userParameters;
	}

	public void setUserParameters(List<UserParameter> userParameters) {
		this.userParameters = userParameters;
	}

	public List<UserTag> getUserTags() {
		return this.userTags;
	}

	public void setUserTags(List<UserTag> userTags) {
		this.userTags = userTags;
	}

}