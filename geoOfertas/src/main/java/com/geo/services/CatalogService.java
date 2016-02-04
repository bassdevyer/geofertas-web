package com.geo.services;

import javax.ejb.Stateless;

import com.geo.model.Catalogue;
@Stateless
public class CatalogService extends ServicioBase<Catalogue>{
	
private Catalogue catalog;
	
	public CatalogService() {
		super(Catalogue.class, CatalogService.class);
		catalog= new Catalogue();
	}

}
