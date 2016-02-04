package com.geo.services;

import javax.ejb.Stateless;

import com.geo.model.Company;

@Stateless
public class CompanyService extends ServicioBase<Company>{
	
private Company company;
	
	public CompanyService() {
		super(Company.class, CompanyService.class);
		company= new Company();
	}

}
