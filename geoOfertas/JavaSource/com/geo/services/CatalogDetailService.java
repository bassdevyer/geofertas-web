package com.geo.services;

import javax.ejb.Stateless;

import com.geo.model.CatalogueDetail;
@Stateless
public class CatalogDetailService extends ServicioBase<CatalogueDetail>{
	

private CatalogueDetail catalogDetail;
	
	public CatalogDetailService() {
		super(CatalogueDetail.class, CatalogDetailService.class);
		catalogDetail= new CatalogueDetail();
	}
}
