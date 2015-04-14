/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.misael.webclinicamedica.managedbean;

import br.com.misael.webclinicamedica.model.dao.DaoUsuairo;
import br.com.misael.webclinicamedica.model.vo.VoUsuario;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Misael C. Homem
 */
@ManagedBean
@SessionScoped
public class MbUsuario {
    
    private VoUsuario       voUsuario;
    private VoUsuario       voUsuarioLogin;
    private List<VoUsuario> voUsuarios;
    private DaoUsuairo      daoUsuario;
    
    /**
     * Creates a new instance of MbUsuario
     */
    public MbUsuario() {
        
        this.voUsuario      = new VoUsuario();
        this.voUsuarioLogin = new VoUsuario();
        this.voUsuarios     = new ArrayList<>();
        this.daoUsuario     = new DaoUsuairo();
        
    }
    
    public String inicializar() {
        
        this.voUsuario = new VoUsuario();
        return "frmusuario";
        
    }
    
    public void gravar() {
        
        String   mensagem;
        Severity iconeDialogo;
        
        try {
            
            if(!this.senhasSaoIguais()) {
                FacesMessage dialogo = new FacesMessage(FacesMessage.SEVERITY_WARN, "Web Clínica médica", "Senhas não conferem.");
                RequestContext.getCurrentInstance().showMessageInDialog(dialogo);
                return;
            }
            
            daoUsuario.gravar(voUsuario);
            
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
            
            long id = this.voUsuario.getIdUsuairo();
            this.daoUsuario.excluir(id);
            this.consultar();
            
        } catch(Exception e) {
            
            System.err.println(e.getMessage());
            
        }
        
        return "tblusuario?faces-redirect=true";
        
    }
    
    private boolean senhasSaoIguais() {
        
        boolean saoIguais = false;
        String senha      = voUsuario.getSenha();
        String confSenha  = voUsuario.getConfSenha();
        
        if(senha.equals(confSenha)) {
            saoIguais = true;
        }
        
        return saoIguais;
        
    }
    
    public String consultar() {
    
        try {
            
            this.voUsuarios = daoUsuario.consultar();
            
        } catch(Exception e) {
            
            System.out.println(e.getMessage());
            Logger.getLogger(MbPaciente.class.getName()).log(Level.SEVERE, null, e);
            
            FacesMessage dialogo = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Web Clínica médica", "Falha ao consultar.");
            RequestContext.getCurrentInstance().showMessageInDialog(dialogo);
            
        }
        
        return "tblusuario";
        
    }
    
    public String consultarLogin() {
        
        try {
            
            VoUsuario voUsuarioLogado = this.daoUsuario.consultar(voUsuarioLogin.getNome(), voUsuarioLogin.getSenha());

            if(voUsuarioLogado == null || voUsuarioLogado.getNome() == null || voUsuarioLogado.getSenha() == null) {
                
                FacesMessage dialogo = new FacesMessage(FacesMessage.SEVERITY_WARN, "Web Clínica médica", "Não é possível logar na aplicação. Verifique os dados de usuário e senha.");
                RequestContext.getCurrentInstance().showMessageInDialog(dialogo);
                                
                return null;
                
            } else {
                
                this.voUsuarioLogin = voUsuarioLogado;
                
                // invalidar sessão para fazer logoff
                //FacesContext.getCurrentInstance().getExternalContext().getSession(false).in
                
            }
            
        } catch(Exception e) {
            
            System.out.println(e.getMessage());
            Logger.getLogger(MbPaciente.class.getName()).log(Level.SEVERE, null, e);
            
            FacesMessage dialogo = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Web Clínica médica", "Falha ao consultar.");
            RequestContext.getCurrentInstance().showMessageInDialog(dialogo);
            
        }
        
        return "paginamestre";
        
    }
    
    public String saudarUsuario() {
        
	Calendar calendar = Calendar.getInstance();
        int hora = calendar.get(Calendar.HOUR_OF_DAY);
        String saudacao;
		
        if(hora >= 6 && hora < 12) {
            saudacao = "Bom dia";
        }else if(hora >= 12 && hora <= 18){
            saudacao = "Boa tarde";
        }else{
            saudacao = "Boa noite";
        }
        
        return  " " + saudacao + ", ";

    }
    
    public String ecerrarSessao() {
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        session.invalidate();
        
        return "frmlogin";
        
    }
    
    public VoUsuario getVoUsuario() {
        return voUsuario;
    }

    public void setVoUsuario(VoUsuario voUsuario) {
        this.voUsuario = voUsuario;
    }
    
    public VoUsuario getVoUsuarioLogin() {
        return voUsuarioLogin;
    }

    public void setVoUsuarioLogin(VoUsuario voUsuarioLogin) {
        this.voUsuarioLogin = voUsuarioLogin;
    }

    public List<VoUsuario> getVoUsuarios() {
        return voUsuarios;
    }

    public void setVoUsuarios(List<VoUsuario> voUsuarios) {
        this.voUsuarios = voUsuarios;
    }
    
}
