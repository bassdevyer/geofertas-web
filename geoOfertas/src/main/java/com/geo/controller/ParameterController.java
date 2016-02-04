package com.geo.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.geo.constants.Messages;
import com.geo.model.Parameter;
import com.geo.services.ParameterService;
import com.geo.services.UserService;

/**
 * Controlador para la p�gina de administraci�n de par�metros del sistema.
 * 
 * @author �
 * 
 */
@ManagedBean
@ViewScoped
public class ParameterController {

	private Parameter parameter;
	private List<Parameter> parameters;
	private boolean showsUpdateBtn;

	@EJB
	private ParameterService parameterService;
	@EJB
	private UserService userService;

	public ParameterController() {
		parameter = new Parameter();
		showsUpdateBtn = false;
	}

	/**
	 * M�todo que se ejecuta despu�s del constructor, se obtienen los registros
	 * de los par�metros y usuarios desde la base de datos.
	 */
	@PostConstruct
	public void init() {
		getAllParameters();
	}

	public void insertParameter() {
		try {
			parameterService.insertar(parameter);

			FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO,
					Messages.PARAMETER_INSERT_OK, null);
			FacesContext.getCurrentInstance().addMessage(null, mensaje);
			parameter = new Parameter();
			getAllParameters();
		} catch (Exception e) {
			FacesMessage mensaje = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, e.getMessage(), null);
			FacesContext.getCurrentInstance().addMessage(null, mensaje);
		}
	}

	public void updateParameter() {
		try {
			parameterService.actualizar(parameter);
			FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO,
					Messages.PARAMETER_UPDATE_OK, null);
			FacesContext.getCurrentInstance().addMessage(null, mensaje);
			parameter = new Parameter();
			getAllParameters();
			showsUpdateBtn=false;
		} catch (Exception e) {
			FacesMessage mensaje = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, e.getMessage(), null);
			FacesContext.getCurrentInstance().addMessage(null, mensaje);
		}

	}

	public void deleteParameter() {
		try {
			parameterService.eliminar(parameter);
			FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO,
					Messages.PARAMETER_DELETE_OK, null);
			FacesContext.getCurrentInstance().addMessage(null, mensaje);
			parameter = new Parameter();
			getAllParameters();
		} catch (Exception e) {
			FacesMessage mensaje = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, e.getMessage(), null);
			FacesContext.getCurrentInstance().addMessage(null, mensaje);
		}
	}

	public void showUpdateButton() {
		showsUpdateBtn = true;
	}

	/**
	 * Se obtienen los registros de los par�metros desde la base de datos.
	 */
	public void getAllParameters() {
		parameters = parameterService.buscarTodos();
	}

	
	public Parameter getParameter() {
		return parameter;
	}

	public void setParameter(Parameter parameter) {
		this.parameter = parameter;
	}

	public List<Parameter> getParameters() {
		return parameters;
	}

	public void setParameters(List<Parameter> parameters) {
		this.parameters = parameters;
	}

	public boolean isShowsUpdateBtn() {
		return showsUpdateBtn;
	}

	public void setShowsUpdateBtn(boolean showsUpdateBtn) {
		this.showsUpdateBtn = showsUpdateBtn;
	}


}
