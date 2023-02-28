/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.mycompany.projektmaterlapatryk;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author Patryk
 */
@Named(value = "newJSFManagedBeanApplication")
@ApplicationScoped
public class NewJSFManagedBeanApplication {
	
	Date date = new Date();
	String appVariable = date.toString();
	
	public NewJSFManagedBeanApplication() {
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getAppVariable() {
		return appVariable;
	}

	public void setAppVariable(String appVariable) {
		this.appVariable = appVariable;
	}
	
}
