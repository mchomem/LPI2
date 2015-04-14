/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.misael.webclinicamedica.managedbean;

import br.com.misael.webclinicamedica.model.dao.DaoAgendamento;
import br.com.misael.webclinicamedica.model.dao.DaoRegistroConsulta;
import br.com.misael.webclinicamedica.model.vo.VoAgendamento;
import br.com.misael.webclinicamedica.model.vo.VoRegistroConsulta;
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
public class MbRegistroConsulta {

    private VoRegistroConsulta       voRegistroConsulta;
    private List<VoRegistroConsulta> voRegistroConsultas;
    private List<VoAgendamento>      voAgendamentos;
    private DaoRegistroConsulta      daoRegistroConsulta;
    private DaoAgendamento           daoAgendamento;
    
    /**
     * Creates a new instance of MbRegistroConsulta
     */
    public MbRegistroConsulta() {
        
        this.voRegistroConsulta  = new VoRegistroConsulta();
        this.voRegistroConsultas = new ArrayList<>();
        this.voAgendamentos      = new ArrayList<>();
        this.daoRegistroConsulta = new DaoRegistroConsulta();
        this.daoAgendamento      = new DaoAgendamento();
        
    }
    
    public String inicializar() {
        
        this.voRegistroConsulta = new VoRegistroConsulta();
        return "frmregistroconsulta";
        
    }
    
    public void gravar() {
        
        String   mensagem;
        Severity iconeDialogo;
        
        try {
            
            daoRegistroConsulta.gravar(voRegistroConsulta);
            
            mensagem     = "Registro gravado com sucesso.";
            iconeDialogo = FacesMessage.SEVERITY_INFO;
           
        } catch (Exception e) {
            
            System.out.println(e.getMessage());
            Logger.getLogger(MbPaciente.class.getName()).log(Level.SEVERE, null, e);
            
            mensagem     = "Falha ao gravar.";
            iconeDialogo = FacesMessage.SEVERITY_ERROR;
            
        }
        
        FacesMessage dialogo = new FacesMessage(iconeDialogo, "Web Clínica médica", mensagem);
        RequestContext.getCurrentInstance().showMessageInDialog(dialogo);
        
    }
    
    public String excluir() {
        
        try {
            
            long id = this.voRegistroConsulta.getVoAgendamento().getIdConsulta();
            this.daoRegistroConsulta.excluir(id);
            this.consultar();
            
        } catch(Exception e) {
            
            System.err.println(e.getMessage());
            
        }
        
        return "tblregistroconsulta?faces-redirect=true";
        
    }
    
    public String consultar() {
        
        try {
            
            this.voRegistroConsultas = daoRegistroConsulta.consultar();
            
        } catch(Exception e) {
            
            System.out.println(e.getMessage());
            Logger.getLogger(MbPaciente.class.getName()).log(Level.SEVERE, null, e);
            
            FacesMessage dialogo = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Web Clínica médica", "Falha ao consultar.");
            RequestContext.getCurrentInstance().showMessageInDialog(dialogo);
            
        }
        
        return "tblregistroconsulta";
        
    }
    
    public List<VoAgendamento> popularAgendamentos() {
        
        try {
            
            this.voAgendamentos = this.daoAgendamento.consultar();
            
        } catch(Exception e) {
            
            System.out.println(e.getMessage());
            Logger.getLogger(MbPaciente.class.getName()).log(Level.SEVERE, null, e);
            
        }
        
        return this.voAgendamentos;
        
    }
    
    public VoRegistroConsulta getVoRegistroConsulta() {
        return voRegistroConsulta;
    }

    public void setVoRegistroConsulta(VoRegistroConsulta voRegistroConsulta) {
        this.voRegistroConsulta = voRegistroConsulta;
    }

    public List<VoRegistroConsulta> getVoRegistroConsultas() {
        return voRegistroConsultas;
    }

    public void setVoRegistroConsultas(List<VoRegistroConsulta> voRegistroConsultas) {
        this.voRegistroConsultas = voRegistroConsultas;
    }
    
}
