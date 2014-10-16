package br.com.misael.webclinicamedica.managedbean;

import br.com.misael.webclinicamedica.model.dao.DaoPaciente;
import br.com.misael.webclinicamedica.model.vo.VoPaciente;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Misael C. Homem
 */
@ManagedBean
@RequestScoped
public class MbPaciente {

    private VoPaciente voPaciente;
    private List<VoPaciente> voPacientes;
    private DaoPaciente daoPaciente;
    
    /**
     * Creates a new instance of MbPaciente
     */
    public MbPaciente() {
    
        voPaciente = new VoPaciente();
        voPacientes = new ArrayList<>();
        daoPaciente = new DaoPaciente();
    
    }
    
    public void gravar() {
        
        try {
            daoPaciente.gravar(voPaciente);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            Logger.getLogger(MbPaciente.class.getName()).log(Level.SEVERE, null, e);
        }
        
    }
    
    public void excluir() {
        
        long id = this.voPaciente.getIdPaciente();
        System.err.println("**************************");
        System.err.println("*** ID PACIENTE ***: " + id);
        System.err.println("**************************");
        this.daoPaciente.excluir(id);
        
    }
    
    public String consultar() {
        
        try {
            
            this.voPacientes = daoPaciente.consultar();
            
        } catch(Exception e) {
            
            System.out.println(e.getMessage());
            Logger.getLogger(MbPaciente.class.getName()).log(Level.SEVERE, null, e);
            
        }
        
        return "tblpaciente";
        
    }
    
    public VoPaciente getVoPaciente() {
        return voPaciente;
    }

    public void setVoPaciente(VoPaciente voPaciente) {
        this.voPaciente = voPaciente;
    }

    public List<VoPaciente> getVoPacientes() {
        return voPacientes;
    }

    public void setVoPacientes(List<VoPaciente> voPacientes) {
        this.voPacientes = voPacientes;
    }
    
}
