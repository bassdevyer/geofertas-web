package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "CATALOGUE_DETAIL" database table.
 * 
 */
@Entity
@Table(name="\"CATALOGUE_DETAIL\"")
public class CatalogueDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private Boolean active;

	private String description;

	private String value;

	//bi-directional many-to-one association to Catalogue
	@ManyToOne
	private Catalogue catalogue;

	public CatalogueDetail() {
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

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Catalogue getCatalogue() {
		return this.catalogue;
	}

	public void setCatalogue(Catalogue catalogue) {
		this.catalogue = catalogue;
	}

}