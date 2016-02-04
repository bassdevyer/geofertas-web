package com.geo.services;

import javax.ejb.Stateless;

import com.geo.model.Advertisement;

@Stateless
public class AdvertisementService extends ServicioBase<Advertisement>{

	private Advertisement advertisement;
	
	public AdvertisementService() {
		super(Advertisement.class, AdvertisementService.class);
		advertisement= new Advertisement();
	}
	
}
