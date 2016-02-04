package com.geo.services;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.geo.constants.Messages;
import com.geo.model.BranchOffice;
import com.geo.model.Company;

@ManagedBean
@ViewScoped
public class BranchOfficeController {
	
	private BranchOffice branchOffice;
	private List<BranchOffice> branchOffices;
	private boolean showsUpdateBtn;
	private Company company;
	private List<Company> companies;
	private Long companySelected;
	
	
	@EJB
	private CompanyService companyService;	
	@EJB
	private BranchOfficeService branchOfficeService;
	
	public BranchOfficeController() {
	branchOffice= new BranchOffice();
	showsUpdateBtn=false;
	}
	
	/**
	 * M�todo que se ejecuta despu�s del constructor, se obtienen los registros
	 * de las sucursales desde la base de datos.
	 */
	@PostConstruct
	public void init() {
		getAllBranchOffices();
		getAllCompanies();
	}
	/**
	 * Obtiene todos los registros de sucursales
	 */
	public void getAllBranchOffices(){
		branchOffices=branchOfficeService.buscarTodos();
	}
	/**
	 * Obtiene todos los registros de las companias
	 */
	public void getAllCompanies(){
		companies=companyService.buscarTodos();
	}
	/**
	 * M�todo para habilitar el bot�n actualizar y ocultar el bot�n guardar
	 */
	public void showUpdateButton() {
		showsUpdateBtn = true;
	}
	/**
	 * Busca la com��ia por Id
	 */
	public Company getCompanyById(Long id){
		return companyService.buscarPorIdLong(id);
	}
	
	/**
	 * Insertar registro
	 */
	public void insertBranchOffice(){
		try {
			branchOffice.setCompany(getCompanyById(companySelected));
			branchOfficeService.insertar(branchOffice);
			FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO,
					Messages.BRANCHOFFICE_INSERT_OK, null);
			FacesContext.getCurrentInstance().addMessage(null, mensaje);
			branchOffice= new BranchOffice();
			getAllBranchOffices();
		} catch (Exception e) {
			FacesMessage mensaje = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, e.getMessage(), null);
			FacesContext.getCurrentInstance().addMessage(null, mensaje);
		}
	}
	
	/**
	 * Actualizar registro
	 */
	public void updateBranchOffice(){
		try {
			branchOfficeService.actualizar(branchOffice);
			FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO,
					Messages.BRANCHOFFICE_UPDATE_OK, null);
			FacesContext.getCurrentInstance().addMessage(null, mensaje);
			branchOffice= new BranchOffice();
			getAllBranchOffices();
			showsUpdateBtn=false;
		} catch (Exception e) {
			FacesMessage mensaje = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, e.getMessage(), null);
			FacesContext.getCurrentInstance().addMessage(null, mensaje);
		}
	}
	
	/**
	 * Borrar registro
	 */
	public void deleteBranchOffice(){
		try {
			branchOfficeService.eliminar(branchOffice);
			FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO,
					Messages.BRANCHOFFICE_DELETE_OK, null);
			FacesContext.getCurrentInstance().addMessage(null, mensaje);
			branchOffice= new BranchOffice();
			getAllBranchOffices();
		} catch (Exception e) {
			FacesMessage mensaje = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, e.getMessage(), null);
			FacesContext.getCurrentInstance().addMessage(null, mensaje);
		}
	}

	public BranchOffice getBranchOffice() {
		return branchOffice;
	}

	public void setBranchOffice(BranchOffice branchOffice) {
		this.branchOffice = branchOffice;
	}

	public List<BranchOffice> getBranchOffices() {
		return branchOffices;
	}

	public void setBranchOffices(List<BranchOffice> branchOffices) {
		this.branchOffices = branchOffices;
	}

	public boolean isShowsUpdateBtn() {
		return showsUpdateBtn;
	}

	public void setShowsUpdateBtn(boolean showsUpdateBtn) {
		this.showsUpdateBtn = showsUpdateBtn;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public List<Company> getCompanies() {
		return companies;
	}

	public void setCompanies(List<Company> companies) {
		this.companies = companies;
	}

	public Long getCompanySelected() {
		return companySelected;
	}

	public void setCompanySelected(Long companySelected) {
		this.companySelected = companySelected;
	}

	
	
	
}
