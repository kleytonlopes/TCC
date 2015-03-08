package br.com.stefanini.project.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.com.stefanini.project.daos.DestinatarioDao;
import br.com.stefanini.project.entities.Destinatario;

@FacesConverter("converssorDest")
public class ConverssorDestinatario implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		try{
	        if(value != null && !value.isEmpty()){
	                DestinatarioDao dao= new DestinatarioDao();
	                Destinatario d = dao.findById(new Integer(value));
	                return d;
	        }
	        }
	        catch(Exception e){
	            e.printStackTrace();
	            throw new ConverterException(new FacesMessage(" > Pelo menos um campo é obrigatório!"));
	        }
	        return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value != null && value instanceof Destinatario){
			Destinatario d = (Destinatario)value;
            return Integer.toString(d.getId());
        }
        return null;
	}

}
