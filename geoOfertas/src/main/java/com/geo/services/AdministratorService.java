package com.geo.services;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.geo.model.Administrator;

/**
 * Servicio utilizado para interactuar con la BDD, insertar, actualizar, borrar y buscar para la entidad Administrator
 * @author 
 *
 */
@Stateless
public class AdministratorService extends ServicioBase<Administrator>{

	private Administrator administrator;
	
	public AdministratorService() {
		super(Administrator.class, AdministratorService.class);
		administrator= new Administrator();
	}
	
/**
 * Valida las credenciales del usuario administrador. 
 * @param name
 * @param pass
 * @return
 */
	public boolean adminValidate(String name, String pass){
		Query q = em.createQuery("select a from Administrator a where "
				+ "a.username= :paramName and a.password= :paramPassword");
		q.setParameter("paramName", name);
		q.setParameter("paramPassword", pass);
		administrator=(Administrator)q.getSingleResult();
		if(administrator!=null){
			return true;
		}else{
			return false;
		}
	}
	
}
