package com.geo.services;

import javax.ejb.Stateless;

import com.geo.model.BranchOffice;

@Stateless
public class BranchOfficeService extends ServicioBase<BranchOffice>{

	private BranchOffice branchOffice;
	public BranchOfficeService() {
		super(BranchOffice.class, BranchOfficeService.class);
		branchOffice= new BranchOffice();
	}
	

}
