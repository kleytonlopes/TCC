package br.com.stefanini.project.daos;

import java.util.List;





import org.hibernate.Query;
import org.hibernate.Session;

import br.com.stefanini.project.entities.Destinatario;
import br.com.stefanini.project.utils.HibernateUtil;

public class DestinatarioDao {
    
    public  List<Destinatario> filtrarPorNome(String nome){
    	List<Destinatario> destinatarios;
    	
    	Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		Query query = session.createQuery("from Destinatario as d where d.nome like :nome ");
		query.setParameter("nome","%"+nome+"%");
		destinatarios = query.list();
		//"br.com.stefanini.project.entities.Destinatario"
		session.getTransaction().commit();
		
		session.close();
        return destinatarios;
    }
    
    public  List<Destinatario> filtrarPorNomeeEmail(String nome,String email){
    	List<Destinatario> destinatarios;
    	
    	Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		Query query = session.createQuery("from Destinatario as d where d.nome like :nome and d.email like :email");
		query.setParameter("nome","%"+nome+"%");
		query.setParameter("email","%"+email+"%");
		destinatarios = query.list();
		//"br.com.stefanini.project.entities.Destinatario"
		session.getTransaction().commit();
		
		session.close();
        return destinatarios;
    }
    
    public  List<Destinatario> filtrarPorEmail(String email){
    	List<Destinatario> destinatarios;
    	
    	Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		Query query = session.createQuery("from Destinatario as d where d.email like :email ");
		query.setParameter("email","%"+email+"%");
		destinatarios = query.list();
		//"br.com.stefanini.project.entities.Destinatario"
		session.getTransaction().commit();
		
		session.close();
        return destinatarios;
    }
    
    
    public  List<Destinatario> buscarTodos(){
    	List<Destinatario> destinatarios;
    	
    	Session  session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		destinatarios = session.createCriteria(Destinatario.class).list();
		//"br.com.stefanini.project.entities.Destinatario"
		session.getTransaction().commit();
		
		session.close();
        return destinatarios;
    }
    public void inserir(Destinatario d){
    	if(d != null){
    		Session session = HibernateUtil.getSessionFactory().openSession();
    		session.beginTransaction();
    		
    		session.save(d);
    		
    		session.getTransaction().commit();
    		
    		session.close();
    	}
    }
    
    public void excluir(Destinatario d){
        if (d!=null){
        	Session session = HibernateUtil.getSessionFactory().openSession();
    		session.beginTransaction();
    		
    		session.delete(d);;
    		
    		session.getTransaction().commit();
    		
    		session.close();
        }
        
    }
    
    public Destinatario findById(int id) {
        for (Destinatario d : buscarTodos()) {
                if (d.getId()==(id)) {
                    return d;
                }
        }
        return null;
        
    }
    
	public void atualizar(Destinatario d) {
		
		if (d!=null){
			Session session = HibernateUtil.getSessionFactory().openSession();
    		session.beginTransaction();
    		
    		session.merge(d);
    		
    		session.getTransaction().commit();
    		
    		session.close();
			
        }
	}
  
  
}