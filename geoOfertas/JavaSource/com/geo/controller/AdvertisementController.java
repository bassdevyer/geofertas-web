package com.geo.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.geo.constants.Messages;
import com.geo.model.Advertisement;
import com.geo.model.Company;
import com.geo.services.AdvertisementService;

@ManagedBean
@ViewScoped
public class AdvertisementController {

	private Advertisement advertisement;
	private List<Advertisement> advertisements;
	private boolean showsUpdateBtn;

	@EJB
	private AdvertisementService advertisementService;

	public AdvertisementController() {
		advertisement = new Advertisement();
		showsUpdateBtn = false;
	}

	/**
	 * Método que se ejecuta después del constructor, se obtienen los registros
	 * de las odertas desde la base de datos.
	 */
	@PostConstruct
	public void init() {
		getAllAdvertisements();
	}

	/**
	 * Obtiene todos los registros de las ofertas
	 */
	public void getAllAdvertisements() {
		advertisements = advertisementService.buscarTodos();
	}

	/**
	 * Método para habilitar el botón actualizar y ocultar el botón guardar
	 */
	public void showUpdateButton() {
		showsUpdateBtn = true;
	}

	/**
	 * Insertar Registro
	 */
	public void insertAdvertisement() {
		try {
			advertisementService.insertar(advertisement);
			FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO,
					Messages.ADVERTISEMENT_INSERT_OK, null);
			FacesContext.getCurrentInstance().addMessage(null, mensaje);
			advertisement = new Advertisement();
			getAllAdvertisements();
		} catch (Exception e) {
			FacesMessage mensaje = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, e.getMessage(), null);
			FacesContext.getCurrentInstance().addMessage(null, mensaje);
		}
	}

	/**
	 * Actualizar
	 */
	public void updateAdvertisement() {
		try {
			advertisementService.actualizar(advertisement);
			FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO,
					Messages.ADVERTISEMENT_UPDATE_OK, null);
			FacesContext.getCurrentInstance().addMessage(null, mensaje);
			advertisement = new Advertisement();
			getAllAdvertisements();
			showsUpdateBtn=false;
		} catch (Exception e) {
			FacesMessage mensaje = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, e.getMessage(), null);
			FacesContext.getCurrentInstance().addMessage(null, mensaje);
		}
	}

	/**
	 * Eliminar
	 */
	public void deleteAdvertisement() {
		try {
			advertisementService.eliminar(advertisement);
			FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO,
					Messages.ADVERTISEMENT_DELETE_OK, null);
			FacesContext.getCurrentInstance().addMessage(null, mensaje);
			advertisement = new Advertisement();
			getAllAdvertisements();
		} catch (Exception e) {
			FacesMessage mensaje = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, e.getMessage(), null);
			FacesContext.getCurrentInstance().addMessage(null, mensaje);
		}
	}

	public Advertisement getAdvertisement() {
		return advertisement;
	}

	public void setAdvertisement(Advertisement advertisement) {
		this.advertisement = advertisement;
	}

	public List<Advertisement> getAdvertisements() {
		return advertisements;
	}

	public void setAdvertisements(List<Advertisement> advertisements) {
		this.advertisements = advertisements;
	}

	public boolean isShowsUpdateBtn() {
		return showsUpdateBtn;
	}

	public void setShowsUpdateBtn(boolean showsUpdateBtn) {
		this.showsUpdateBtn = showsUpdateBtn;
	}
	
	

}
