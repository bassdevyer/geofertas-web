package com.geo.services;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.geo.model.Parameter;

/**
 * Servicio utilizado para interactuar con la BDD, insertar, actualizar, borrar y buscar para la entidad Parameter
 * @author 
 *
 */
@Stateless
public class ParameterService extends ServicioBase<Parameter>{

	private Parameter parameter;
	
	public ParameterService() {
		super(Parameter.class, ParameterService.class);
		parameter= new Parameter();
	}
	

	
}
