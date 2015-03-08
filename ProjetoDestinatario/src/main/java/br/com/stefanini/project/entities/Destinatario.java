package br.com.stefanini.project.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="table_destinatario")
public class Destinatario implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue()
	@Column(name="id_destinatario")
	private int id;
	
	@Column(name="nome_destinatario")
	private String nome;
	
	@Column(name="email_destinatario")
	private String email;
	
	public Destinatario(String nome, String email) {
		this.nome = nome;
		this.email = email;
	}
	public Destinatario() {
	}
	
	
	@Override
	public boolean equals(Object o) {
		Destinatario d = (Destinatario) o;
		if(d!=null){
			if(d.getId() == getId() || d.getEmail().equals(getEmail()))
				return true;
		}
		return false;
	}
	@Override
	public int hashCode() {
		return getId()*getEmail().hashCode();
	}
	//getters and setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
}
