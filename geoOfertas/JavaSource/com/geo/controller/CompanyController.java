package com.geo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.UploadedFile;

import sun.misc.IOUtils;

import com.geo.constants.Messages;
import com.geo.model.Company;
import com.geo.services.CompanyService;


@ManagedBean
@ViewScoped
public class CompanyController {
	
	private Company company;
	private List<Company> companies;
	private boolean showsUpdateBtn;
	private File file;
	 private UploadedFile uploadFile;
	
	@EJB
	private CompanyService companyService;
	
	
	public CompanyController() {
		company= new Company();
		showsUpdateBtn = false;
		
	}

	/**
	 * Método que se ejecuta después del constructor, se obtienen los registros
	 * de las companias desde la base de datos.
	 */
	@PostConstruct
	public void init() {
		getAllCompanies();
	}

	
	/**
	 * Obtiene todos los registros de las companias
	 */
	public void getAllCompanies(){
		companies=companyService.buscarTodos();
	}
	
	/**
	 * Método para habilitar el botón actualizar y ocultar el botón guardar
	 */
	public void showUpdateButton() {
		showsUpdateBtn = true;
	}
	
	/**
	 * Insertar Registro
	 */
	public void insertCompany(){
		try {
			saveLogo(file.getAbsolutePath(), file);
			companyService.insertar(company);			
			FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO,
					Messages.COMPANY_INSERT_OK, null);
			FacesContext.getCurrentInstance().addMessage(null, mensaje);
			company= new Company();
			getAllCompanies();
		} catch (Exception e) {
			FacesMessage mensaje = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, e.getMessage(), null);
			FacesContext.getCurrentInstance().addMessage(null, mensaje);
		}
	}
	
	/**
	 * Actualizar registro
	 */
	public void updateCompany() {
		try {
			saveLogo(file.getAbsolutePath(), file);
			companyService.actualizar(company);
			FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO,
					Messages.COMPANY_UPDATE_OK, null);
			FacesContext.getCurrentInstance().addMessage(null, mensaje);
			company= new Company();
			getAllCompanies();
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
	public void deleteCompany() {
		try {
			companyService.eliminar(company);
			FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO,
					Messages.COMPANY_DELETE_OK, null);
			FacesContext.getCurrentInstance().addMessage(null, mensaje);
			company= new Company();
			getAllCompanies();
		} catch (Exception e) {
			FacesMessage mensaje = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, e.getMessage(), null);
			FacesContext.getCurrentInstance().addMessage(null, mensaje);
		}
	
	}
	
	public void uploadLogo(){
		uploadFile.getFileName();
		System.out.println("FILE :::"+uploadFile.getFileName()); 
		file=(File)uploadFile;
		file.getAbsolutePath();
		System.out.println("FILE dfd :::"+file.getAbsolutePath()); 
	}
	
	/* metodo que guarda una imagen JPG en la base de datos
	 * input: ID : identificador unico para el registro, osea primary key
	 * name: nombre de la imagen para reconocerlo mas adelante
	 * ruta: direccion absoluta de la imagen JPG
	 */
	    public boolean saveLogo(String pathS, File file) {
	    	System.out.println("FILE dfd :::"+pathS);  
	        FileInputStream fis = null;
	        Path path = Paths.get(pathS);	        
	        try {
	             file = new File(pathS);
	             fis = new FileInputStream(file);
	             company.setLogo(Files.readAllBytes(path));	           
	             return true;
	        } catch (Exception e) {
	             System.out.println(e.getMessage());
	        } 
	        return false;
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

	public boolean isShowsUpdateBtn() {
		return showsUpdateBtn;
	}

	public void setShowsUpdateBtn(boolean showsUpdateBtn) {
		this.showsUpdateBtn = showsUpdateBtn;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public UploadedFile getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(UploadedFile uploadFile) {
		this.uploadFile = uploadFile;
	}
	
	
	
	
}
