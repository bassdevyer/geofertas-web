package com.geo.services;

import javax.ejb.Stateless;

import com.geo.model.User;

/**
 * Servicio utilizado para interactuar con la BDD, insertar, actualizar, borrar y buscar para la entidad Parameter
 * @author 
 *
 */
@Stateless
public class UserService extends ServicioBase<User>{

	private User user;
	
	public UserService() {
		super(User.class, UserService.class);
		user= new User();
	}
	

	
}
