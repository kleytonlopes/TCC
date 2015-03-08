package br.com.stefanini.project.mb;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;


import javax.faces.bean.SessionScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

import br.com.stefanini.project.daos.DestinatarioDao;
import br.com.stefanini.project.entities.Destinatario;

@ManagedBean(name="Destinatario")
@SessionScoped
public class DestinatarioBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Destinatario destinatarioTemp;
	private List<Destinatario> destinatarios;
	private boolean mostrandoSalvar;
	private boolean ambosCamposObrigatórios;
	
	public DestinatarioBean() {
		destinatarioTemp = new Destinatario();
		DestinatarioDao dao = new DestinatarioDao();
		destinatarios = dao.buscarTodos();
		setMostrandoSalvar(false);
		setAmbosCamposObrigatórios(true);
	}
	
	public String incluirDestinatario(){
		
		if(destinatarioTemp != null){
			DestinatarioDao dao = new DestinatarioDao();
			dao.inserir(getDestinatarioTemp());
			atualizarListDesinatarios();
			
			imprimirMensagem(getMessage("MA001"));
			
	       
		}
		 return "manter";
	}
	
	protected String getMessage(String key) {
		FacesContext context = FacesContext.getCurrentInstance();
		ResourceBundle bundle = ResourceBundle.getBundle("resource",context.getViewRoot().getLocale());
		return bundle.getString(key);
	}
	
	public boolean verificarAmbosCamposVazios(){
		return (destinatarioTemp.getNome().equals(""))&& 
				( destinatarioTemp.getEmail().equals(""));
	}
	
	public String editarDestinatario(){
		DestinatarioDao dao = new DestinatarioDao();
		dao.atualizar(getDestinatarioTemp());
		atualizarListDesinatarios();
		setMostrandoSalvar(false);
		
		imprimirMensagem(getMessage("MA001"));
		
		return "";
		
	}
	public void imprimirMensagem(String msg){
		FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,msg,null));
		
	}
	
	public String mostrarBotaoSalvar(){
		setMostrandoSalvar(true);
		return "manter";
	}
	public String pesquisarDestinatários(){
		setAmbosCamposObrigatórios(false);
		
		if(verificarAmbosCamposVazios()){
			imprimirMensagem(getMessage("MN001"));
		}
		else {
			DestinatarioDao dao = new DestinatarioDao();
			
			if(!destinatarioTemp.getNome().equals("") && !destinatarioTemp.getEmail().equals("")){
				setDestinatarios(dao.filtrarPorNomeeEmail(destinatarioTemp.getNome(), destinatarioTemp.getEmail()));
			}
			else if(!destinatarioTemp.getEmail().equals("")){
				setDestinatarios(dao.filtrarPorEmail(destinatarioTemp.getEmail()));
			}
			else{
				setDestinatarios(dao.filtrarPorNome(destinatarioTemp.getNome()));
			}
			
			if(destinatarios.isEmpty()||destinatarios==null){
				imprimirMensagem(getMessage("MA003"));
			}
			
		}
		
		return "manter";
	}
	
	public void filtrarCandidatosPorNome(String nome){
		for (Iterator<Destinatario> iterator = destinatarios.iterator(); iterator.hasNext();) {
			Destinatario destinatario = (Destinatario) iterator.next();
			if(!destinatario.getNome().contains(nome)){
				iterator.remove();
			}
		}
	}
	
	public void filtrarCandidatosPorEmail(String email){
		for (Iterator<Destinatario> iterator = destinatarios.iterator(); iterator.hasNext();) {
			Destinatario destinatario = (Destinatario) iterator.next();
			if(!destinatario.getEmail().contains(email)){
				iterator.remove();
			}
		}
	}
	
	public String excluirDestinatario(){
		if(getDestinatarios().size()<=1){
			imprimirMensagem(getMessage("MN005"));
		}
		else{
			DestinatarioDao dao = new DestinatarioDao();
			dao.excluir(destinatarioTemp);
			
			
			imprimirMensagem(getMessage("MA001"));
			
		}
		atualizarListDesinatarios();
		return "manter";
	}
	
	public void atualizarListDesinatarios(){
		DestinatarioDao dao = new DestinatarioDao();
		setDestinatarios(dao.buscarTodos());
		destinatarioTemp = new Destinatario();
	}
	
	//getters and setters
	public List<Destinatario> getDestinatarios() {
		return destinatarios;
	}

	public void setDestinatarios(List<Destinatario> destinatarios) {
		this.destinatarios = destinatarios;
	}

	public Destinatario getDestinatarioTemp() {
		return destinatarioTemp;
	}

	public void setDestinatarioTemp(Destinatario destinatarioTemp) {
		this.destinatarioTemp = destinatarioTemp;
	}

	public boolean isMostrandoSalvar() {
		return mostrandoSalvar;
	}

	public void setMostrandoSalvar(boolean mostrandoSalvar) {
		this.mostrandoSalvar = mostrandoSalvar;
	}

	public boolean isAmbosCamposObrigatórios() {
		return ambosCamposObrigatórios;
	}

	public void setAmbosCamposObrigatórios(boolean ambosCamposObrigatórios) {
		this.ambosCamposObrigatórios = ambosCamposObrigatórios;
	}

	
	

	
}
