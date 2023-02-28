/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.mycompany.projektmaterlapatryk;

import dao.TUzytkownikJpaController;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import to.UserTO;
import entity.TUzytkownik;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletContext;
import javax.transaction.UserTransaction;


/**
 *
 * @author Patryk
 */
@Named(value = "newJSFManagedBeanSession")
@SessionScoped
public class NewJSFManagedBeanSession implements Serializable {

	
	@Inject
	NewJSFManagedBeanApplication applicationControllerReference;
	
	@PersistenceContext(name="ProjektMaterlaPatrykPU")
	private EntityManager em;
	@Resource
	private UserTransaction utx;
	
	private List<UserTO> userToList = new ArrayList();

	public NewJSFManagedBeanSession() {
	}
	
	public String getApplicationControllerReference() {
		return " ** " + applicationControllerReference.getAppVariable() + " ** ";
	}

	public List<UserTO> getUserToList() {
		return userToList;
	}

	public void setUserToList(List<UserTO> userToList) {
		this.userToList = userToList;
	}
	
	@PostConstruct
	public void refreshData() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProjektMaterlaPatrykPU");
		TUzytkownikJpaController daneDao = new TUzytkownikJpaController(utx, emf);
		List<TUzytkownik> uzytkownikToListLokal = daneDao.findTUzytkownikEntities();
		if (uzytkownikToListLokal != null) {
			userToList.clear();
			for (TUzytkownik tUzytkownik:uzytkownikToListLokal) {
				userToList.add(new UserTO(tUzytkownik.getId(), tUzytkownik.getImie(), tUzytkownik.getNazwisko(), false));
			}
		}

	}
	
	public void visibleChange(UserTO userTO) {
		int indexObject = userToList.indexOf(userTO);
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProjektMaterlaPatrykPU");
		TUzytkownikJpaController daneDao = new TUzytkownikJpaController(utx, emf);
		try {
			daneDao.edit(new TUzytkownik(userTO.getId(), userTO.getImie(), userTO.getNazwisko()));
			userToList.set(indexObject, userTO);
		}
		catch (Exception ex) {
			sendJSFErrorMessage(ex);
		}
	}
	
	// usuniecie aktualnego wers wiersza z listy
	public void deleteRow(UserTO userTO) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProjektMaterlaPatrykPU");
		TUzytkownikJpaController daneDao = new TUzytkownikJpaController(utx, emf);
		try {
			daneDao.destroy(userTO.getId());
			refreshData();
		}
		catch(Exception ex) {
			sendJSFErrorMessage(ex);
		}
	}
	
	// dodanie
	public void addRow(UserTO userTO) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProjektMaterlaPatrykPU");
		TUzytkownikJpaController daneDao = new TUzytkownikJpaController(utx, emf);
		Long id = System.currentTimeMillis();
		TUzytkownik tUzytkownik = new TUzytkownik(id, "", "");
		try {
			daneDao.create(tUzytkownik);
			refreshData();
		}
		catch(Exception ex) {
			sendJSFErrorMessage(ex);
		}
	}
	
	public void sendJSFErrorMessage(Exception ex) {
		javax.faces.context.FacesContext facesContext = javax.faces.context.FacesContext.getCurrentInstance();
		ServletContext servletContext = (ServletContext)facesContext.getExternalContext().getContext();
		servletContext.log(servletContext.getContextPath() + ":" + ex.toString());
		facesContext.addMessage(null, new FacesMessage(ex.toString()));
	}
	
	public void sort(String dir) {
		Comparator<UserTO> comparator = (UserTO a, UserTO b) -> {
			int compareResult = 0;
			if (dir.equals("asc")) {
				compareResult = a.getNazwisko().compareTo(b.getNazwisko());
			}
			else {
				compareResult = b.getNazwisko().compareTo(a.getNazwisko());
			}
			return compareResult;
		};
		Collections.sort(userToList, comparator);
	}
	
}
