package com.geo.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.geo.constants.Messages;
import com.geo.model.Catalogue;
import com.geo.services.CatalogService;

@ManagedBean
@ViewScoped
public class CatalogueController {
	
	private Catalogue catalogue;
	private List<Catalogue> catalogues;
	private boolean showsUpdateBtn;
	
	@EJB
	private CatalogService catalogService;
	
	public CatalogueController() {
	catalogue= new Catalogue();
	showsUpdateBtn=false;
	}
	
	/**
	 * M�todo que se ejecuta despu�s del constructor, se obtienen los registros
	 * de los cat�logos desde la base de datos.
	 */
	@PostConstruct
	public void init() {
		getAllCatalogues();
	}
	/**
	 * Obtiene todos los registros de par�metros
	 */
	public void getAllCatalogues(){
		catalogues=catalogService.buscarTodos();
	}
	/**
	 * M�todo para habilitar el bot�n actualizar y ocultar el bot�n guardar
	 */
	public void showUpdateButton() {
		showsUpdateBtn = true;
	}
	/**
	 * Insertar registro
	 */
	public void insertCatalogue(){
		try {
			catalogService.insertar(catalogue);
			FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO,
					Messages.CATALOGUE_INSERT_OK, null);
			FacesContext.getCurrentInstance().addMessage(null, mensaje);
			catalogue= new Catalogue();
			getAllCatalogues();
		} catch (Exception e) {
			FacesMessage mensaje = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, e.getMessage(), null);
			FacesContext.getCurrentInstance().addMessage(null, mensaje);
		}
	}
	/**
	 * Actualizar registro
	 */
	public void updateCatalogue(){
		try {
			catalogService.actualizar(catalogue);
			FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO,
					Messages.CATALOGUE_UPDATE_OK, null);
			FacesContext.getCurrentInstance().addMessage(null, mensaje);
			catalogue= new Catalogue();
			getAllCatalogues();
			showsUpdateBtn=false;
		} catch (Exception e) {
			FacesMessage mensaje = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, e.getMessage(), null);
			FacesContext.getCurrentInstance().addMessage(null, mensaje);
		}
	}
	/**
	 * Borrar registro
	 */	
	public void deleteCatalogue(){
		try {
			catalogService.eliminar(catalogue);
			FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO,
					Messages.CATALOGUE_DELETE_OK, null);
			FacesContext.getCurrentInstance().addMessage(null, mensaje);
			catalogue= new Catalogue();
			getAllCatalogues();
		} catch (Exception e) {
			FacesMessage mensaje = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, e.getMessage(), null);
			FacesContext.getCurrentInstance().addMessage(null, mensaje);
		}
	}

	public Catalogue getCatalogue() {
		return catalogue;
	}

	public void setCatalogue(Catalogue catalogue) {
		this.catalogue = catalogue;
	}

	public List<Catalogue> getCatalogues() {
		return catalogues;
	}

	public void setCatalogues(List<Catalogue> catalogues) {
		this.catalogues = catalogues;
	}

	public boolean isShowsUpdateBtn() {
		return showsUpdateBtn;
	}

	public void setShowsUpdateBtn(boolean showsUpdateBtn) {
		this.showsUpdateBtn = showsUpdateBtn;
	}
	
	

}
