package br.com.misael.webclinicamedica.converter;

import br.com.misael.webclinicamedica.model.vo.VoPaciente;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


/**
 * Classe necessário para conversão utilizado pelos componentes do PrimeFaces (p:selectOneMenu).
 * @author Misael C. Homem
 */
@FacesConverter(forClass = VoPaciente.class)
public class CoPacinte implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        
        if(value != null && !value.isEmpty()) {
            
            return (VoPaciente) component.getAttributes().get(value);
            
        }
        
        return null;
        
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        
        if(value instanceof VoPaciente) {
        
            VoPaciente entity = (VoPaciente) value;
            
            if(entity != null && entity instanceof VoPaciente && entity.getIdPaciente() != null) {
                
                component.getAttributes().put(entity.getIdPaciente().toString(), entity);
                return entity.getIdPaciente().toString();
                
            }
            
        }
        
        return "";
        
    }
    
}
