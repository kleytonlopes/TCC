package br.com.stefanini.project.mb;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.richfaces.component.SortOrder;


@ManagedBean(name="Sorting")
@ViewScoped
public class DestinatariosSortingBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private SortOrder namesOrder = SortOrder.ascending;
	private SortOrder emailsOrder = SortOrder.unsorted;
	
	
	public void sortByNames(){
		emailsOrder = SortOrder.unsorted;
		
		if(namesOrder.equals(SortOrder.ascending)){
			setNamesOrder(SortOrder.descending);
		}
		else {
			setNamesOrder(SortOrder.ascending);
		}
	}
	
	public void sortByEmails(){
		namesOrder = SortOrder.unsorted;
		
		if(emailsOrder.equals(SortOrder.ascending)){
			setEmailsOrder(SortOrder.descending);
		}
		else {
			setEmailsOrder(SortOrder.ascending);
		}
	}

	public SortOrder getNamesOrder() {
		return namesOrder;
	}


	public void setNamesOrder(SortOrder namesOrder) {
		this.namesOrder = namesOrder;
	}


	public SortOrder getEmailsOrder() {
		return emailsOrder;
	}


	public void setEmailsOrder(SortOrder emailsOrder) {
		this.emailsOrder = emailsOrder;
	}
	
	
}
