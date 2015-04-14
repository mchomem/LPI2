/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.misael.webclinicamedica.managedbean;

import br.com.misael.webclinicamedica.model.dao.DaoAgendamento;
import br.com.misael.webclinicamedica.model.dao.DaoPaciente;
import br.com.misael.webclinicamedica.model.vo.VoAgendamento;
import br.com.misael.webclinicamedica.model.vo.VoPaciente;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Misael C. Homem
 */
@ManagedBean
@SessionScoped
public class MbAgendamento {

    private VoAgendamento voAgendamento;
    private List<VoAgendamento> voAgendamentos;
    private List<VoPaciente> voPacientes;
    private DaoAgendamento daoAgendamento;
    private DaoPaciente daoPaciente;

    /**
     * Creates a new instance of MbAgendamento
     */
    public MbAgendamento() {
        
        this.voAgendamento  = new VoAgendamento();
        this.voAgendamentos = new ArrayList<>();
        this.daoAgendamento = new DaoAgendamento();
        this.daoPaciente    = new DaoPaciente();
        
    }
    
    public String inicializar() {
        
        this.voAgendamento = new VoAgendamento();
        return "frmagendamento";
        
    }
    
    public void gravar() {
                
        String   mensagem = null;
        Severity iconeDialogo = null;
        
        try {
            
            System.err.println("Passei por aqui");
            daoAgendamento.gravar(voAgendamento);
            
            mensagem     = "Registro gravado com sucesso.";
            iconeDialogo = FacesMessage.SEVERITY_INFO;
           
        } catch (Exception e) {
            
            System.out.println(e.getMessage());
            Logger.getLogger(MbPaciente.class.getName()).log(Level.SEVERE, null, e);
            
            mensagem     = "Falha ao gravar.";
            iconeDialogo = FacesMessage.SEVERITY_ERROR;
            
        } finally {
            
            FacesMessage dialogo = new FacesMessage(iconeDialogo, "Web Clínica médica", mensagem);
            RequestContext.getCurrentInstance().showMessageInDialog(dialogo);
            
        }
        
    }
    
    public String excluir() {
        
        try {
            
            long id = this.voAgendamento.getIdConsulta();
            this.daoAgendamento.excluir(id);
            this.consultar();
            
        } catch(Exception e) {
            
            System.err.println(e.getMessage());
            
        }
        
        return "tblagendamento?faces-redirect=true";
        
    }
    
    public String consultar() {
        
        try {
            
            this.voAgendamentos = daoAgendamento.consultar();
            
        } catch(Exception e) {
            
            System.out.println(e.getMessage());
            Logger.getLogger(MbPaciente.class.getName()).log(Level.SEVERE, null, e);
            
            FacesMessage dialogo = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Web Clínica médica", "Falha ao consultar.");
            RequestContext.getCurrentInstance().showMessageInDialog(dialogo);
            
        }
        
        return "tblagendamento";
        
    }
    
    public List<VoPaciente> popularPacientes() {
        
        try {
            
            this.voPacientes = this.daoPaciente.consultar();
            
        } catch(Exception e) {
            
            System.out.println(e.getMessage());
            Logger.getLogger(MbPaciente.class.getName()).log(Level.SEVERE, null, e);
            
        }
        
        return this.voPacientes;
        
    }
    
    public VoAgendamento getVoAgendamento() {
        return voAgendamento;
    }

    public void setVoAgendamento(VoAgendamento voAgendamento) {
        this.voAgendamento = voAgendamento;
    }

    public List<VoAgendamento> getVoAgendamentos() {
        return voAgendamentos;
    }

    public void setVoAgendamentos(List<VoAgendamento> voAgendamentos) {
        this.voAgendamentos = voAgendamentos;
    }
    
    public List<VoPaciente> getVoPacientes() {
        return voPacientes;
    }

    public void setVoPacientes(List<VoPaciente> voPacientes) {
        this.voPacientes = voPacientes;
    }
    
}
