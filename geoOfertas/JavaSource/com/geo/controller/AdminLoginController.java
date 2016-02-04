package com.geo.controller;

import java.io.IOException;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.geo.constants.Messages;
import com.geo.model.Administrator;
import com.geo.services.AdministratorService;

@ManagedBean
@ViewScoped
public class AdminLoginController {

	private Administrator administrator;
	@EJB
	private AdministratorService administratorService;
	
	public AdminLoginController() {
	administrator = new Administrator();
	}
	
	/**
	 * Valida las credenciales del usuario administrador para poder ingresar al sistema.
	 */
	public void adminValidate(){
		if(administrator!=null){
			 if(administratorService.adminValidate(administrator.getUsername(), administrator.getPassword())){
				 try {
					FacesContext.getCurrentInstance().getExternalContext().redirect("/AdminEpn/Lugar.jsf");
				} catch (IOException e) {
					System.out.println(e.getMessage()); 
				}
			 }else{
				 FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO,
							Messages.VALIDATION_ADMIN_LOGIN_ERROR, null);
					FacesContext.getCurrentInstance().addMessage(null, mensaje);
					
			 }
		 }
	}

	public Administrator getAdministrator() {
		return administrator;
	}

	public void setAdministrator(Administrator administrator) {
		this.administrator = administrator;
	}
	
	
}
