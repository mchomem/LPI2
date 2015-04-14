/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.misael.webclinicamedica.converter;

import br.com.misael.webclinicamedica.model.vo.VoAgendamento;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * Classe necessário para conversão utilizado pelos componentes do PrimeFaces (p:selectOneMenu).
 * @author Misael C. Homem
 */
@FacesConverter(forClass = VoAgendamento.class)
public class CoAgendamento implements Converter {
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        
        if(value != null && !value.isEmpty()) {
            
            return (VoAgendamento) component.getAttributes().get(value);
            
        }
        
        return null;
        
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        
        if(value instanceof VoAgendamento) {
        
            VoAgendamento entity = (VoAgendamento) value;
            
            if(entity != null && entity instanceof VoAgendamento && entity.getIdConsulta()!= null) {
                
                component.getAttributes().put(entity.getIdConsulta().toString(), entity);
                return entity.getIdConsulta().toString();
                
            }
            
        }
        
        return "";
        
    }
    
}
