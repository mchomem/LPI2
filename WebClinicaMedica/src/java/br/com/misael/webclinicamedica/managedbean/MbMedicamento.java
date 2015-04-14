/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.misael.webclinicamedica.managedbean;

import br.com.misael.webclinicamedica.model.dao.DaoMedicamento;
import br.com.misael.webclinicamedica.model.vo.VoMedicamento;
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
public class MbMedicamento {

    private VoMedicamento       voMedicamento;
    private List<VoMedicamento> voMedicamentos;
    private DaoMedicamento      daoMedicamento;
    
    /**
     * Creates a new instance of mbMedicamento
     */
    public MbMedicamento() {
        
        this.voMedicamento  = new VoMedicamento();
        this.voMedicamentos = new ArrayList<>();
        this.daoMedicamento = new DaoMedicamento();
        
    }
    
    public String inicializar() {
        
        this.voMedicamento = new VoMedicamento();
        return "frmmedicamento";
        
    }
    
    public void gravar() {
        
        String   mensagem;
        Severity iconeDialogo;
        
        try {
            
            daoMedicamento.gravar(voMedicamento);
            
            mensagem     = "Registro gravado com sucesso.";
            iconeDialogo = FacesMessage.SEVERITY_INFO;
            
        } catch(Exception e) {
            
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
            
            long id = this.voMedicamento.getIdMedicamento();
            this.daoMedicamento.excluir(id);
            this.consultar();
            
        } catch(Exception e) {
            
            System.err.println(e.getMessage());
            
        }
        
        return "tblmedicamento?faces-redirect=true";
        
    }
    
    public String consultar() {
        
        try {
         
            this.voMedicamentos = daoMedicamento.consultar();
            
        } catch(Exception e) {
            
            System.out.println(e.getMessage());
            Logger.getLogger(MbPaciente.class.getName()).log(Level.SEVERE, null, e);
            
            FacesMessage dialogo = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Web Clínica médica", "Falha ao consultar.");
            RequestContext.getCurrentInstance().showMessageInDialog(dialogo);
            
        }
        
        return "tblmedicamento";
        
    }
    
    public VoMedicamento getVoMedicamento() {
        return voMedicamento;
    }

    public void setVoMedicamento(VoMedicamento voMedicamento) {
        this.voMedicamento = voMedicamento;
    }

    public List<VoMedicamento> getVoMedicamentos() {
        return voMedicamentos;
    }

    public void setVoMedicamentos(List<VoMedicamento> voMedicamentos) {
        this.voMedicamentos = voMedicamentos;
    }
    
}
