package br.com.stefanini.project.utils;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;

import br.com.stefanini.project.daos.DestinatarioDao;
import br.com.stefanini.project.entities.Destinatario;

public class Main {
	
	public static void main(String[] args) {
		
		DestinatarioDao dao = new DestinatarioDao();
		List<Destinatario> dests = dao.filtrarPorNome("k");
		
		
//		for (Iterator<Destinatario> iterator = dests.iterator(); iterator.hasNext();) {
//			Destinatario destinatario = (Destinatario) iterator.next();
//			if(!destinatario.getNome().contains("ab")){
//				iterator.remove();
//			}
//		}
		
		
		for (Destinatario destinatario : dests) {
			System.out.println(destinatario.getNome()+"---"+destinatario.getEmail());
		}
		
		
	}
	
}
