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
import com.geo.model.User;
import com.geo.services.CatalogService;
import com.geo.services.UserService;

@ManagedBean
@ViewScoped
public class UserController {

	private User user;
	private boolean showsUpdateBtn;
	private List<User> users;
	private List<Catalogue> catalogues;
	private Catalogue catalogue;
	private long catalogueSelected;

	@EJB
	private UserService userService; 
	@EJB
	private CatalogService catalogService;

	public UserController() {
		user = new User();
		catalogue= new Catalogue();
		showsUpdateBtn = false;
	}
	
	/**
	 * Método que se ejecuta después del constructor, se obtienen los registros
	 * de los usuarios y catálogos desde la base de datos.
	 */
	@PostConstruct
	public void init() {
		getAllUsers();
		getAllCatalogues();
	}

	
	/**
	 * Obtiene todos los registros de los usuarios
	 */
	public void getAllUsers(){
		users=userService.buscarTodos();
	}
	/**
	 * Obtiene todos los registros de parámetros
	 */
	public void getAllCatalogues(){
		catalogues=catalogService.buscarTodos();
	}
	/**
	 * Obtiene el parámetro de acuerdo a su ID
	 */
	public Catalogue getCatalogueById(Long id){
		return catalogue=catalogService.buscarPorIdLong(id);
	}
	
	/**
	 * Método para habilitar el botón actualizar y ocultar el botón guardar
	 */
	public void showUpdateButton() {
		showsUpdateBtn = true;
	}
	
	/**
	 * Insertar registro
	 */
	public void insertUser(){		
		try {
			user.setAuthenticationType(getCatalogueById(catalogueSelected).getName());
			userService.insertar(user);
			FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO,
					Messages.USER_INSERT_OK, null);
			FacesContext.getCurrentInstance().addMessage(null, mensaje);
			user = new User();
			getAllUsers();
		} catch (Exception e) {
			FacesMessage mensaje = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, e.getMessage(), null);
			FacesContext.getCurrentInstance().addMessage(null, mensaje);
		}
	}
	/**
	 * Actualizar registro
	 */
	public void updateUser(){
		try {
			userService.actualizar(user);
			FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO,
					Messages.USER_UPDATE_OK, null);
			FacesContext.getCurrentInstance().addMessage(null, mensaje);
			user = new User();
			getAllUsers();
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
	public void deleteUser(){
		try {
			userService.eliminar(user);
			FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO,
					Messages.USER_DELETE_OK, null);
			FacesContext.getCurrentInstance().addMessage(null, mensaje);
			user = new User();
			getAllUsers();
		} catch (Exception e) {
			FacesMessage mensaje = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, e.getMessage(), null);
			FacesContext.getCurrentInstance().addMessage(null, mensaje);
		}
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isShowsUpdateBtn() {
		return showsUpdateBtn;
	}

	public void setShowsUpdateBtn(boolean showsUpdateBtn) {
		this.showsUpdateBtn = showsUpdateBtn;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<Catalogue> getCatalogues() {
		return catalogues;
	}

	public void setCatalogues(List<Catalogue> catalogues) {
		this.catalogues = catalogues;
	}

	public Catalogue getCatalogue() {
		return catalogue;
	}

	public void setCatalogue(Catalogue catalogue) {
		this.catalogue = catalogue;
	}

	
	public long getCatalogueSelected() {
		return catalogueSelected;
	}

	public void setCatalogueSelected(long catalogueSelected) {
		this.catalogueSelected = catalogueSelected;
	}

	public void setCatalogueSelected(int catalogueSelected) {
		this.catalogueSelected = catalogueSelected;
	}


	
	
	
	
}
