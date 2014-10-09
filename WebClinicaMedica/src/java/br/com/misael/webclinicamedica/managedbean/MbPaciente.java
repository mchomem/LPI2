/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.misael.webclinicamedica.managedbean;

import br.com.misael.webclinicamedica.model.dao.DaoPaciente;
import br.com.misael.webclinicamedica.model.vo.VoPaciente;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Misael
 */
@ManagedBean
@SessionScoped
public class MbPaciente {

    private VoPaciente voPaciente;
    private VoPaciente voPacienteSelecionado;
    private List<VoPaciente> voPacientes;
    private DaoPaciente daoPaciente;
    
    /**
     * Creates a new instance of MbPaciente
     */
    public MbPaciente() {
    
        voPaciente = new VoPaciente();
        voPacienteSelecionado = new VoPaciente();
        voPacientes = new ArrayList<>();
        daoPaciente = new DaoPaciente();
    
    }
    
    public void gravar() {
        
        System.out.println("Gravado...");
        
        try {
            daoPaciente.gravar(voPaciente);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(MbPaciente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void excluir() {
        
    }
    
    public String consultar() {
        return "tblpaciente";
    }
    
    public VoPaciente getVoPaciente() {
        return voPaciente;
    }

    public void setVoPaciente(VoPaciente voPaciente) {
        this.voPaciente = voPaciente;
    }

    public VoPaciente getVoPacienteSelecionado() {
        return voPacienteSelecionado;
    }

    public void setVoPacienteSelecionado(VoPaciente voPacienteSelecionado) {
        this.voPacienteSelecionado = voPacienteSelecionado;
    }

    public List<VoPaciente> getVoPacientes() {
        return voPacientes;
    }

    public void setVoPacientes(List<VoPaciente> voPacientes) {
        this.voPacientes = voPacientes;
    }
    
}
