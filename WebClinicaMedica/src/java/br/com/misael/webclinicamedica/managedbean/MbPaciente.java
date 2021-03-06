package br.com.misael.webclinicamedica.managedbean;

import br.com.misael.webclinicamedica.model.dao.DaoPaciente;
import br.com.misael.webclinicamedica.model.vo.VoPaciente;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import org.primefaces.context.RequestContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Misael C. Homem
 */
@ManagedBean
@SessionScoped
public class MbPaciente {

    private VoPaciente       voPaciente;
    private List<VoPaciente> voPacientes;
    private DaoPaciente      daoPaciente;
    
    /**
     * Creates a new instance of MbPaciente
     */
    public MbPaciente() {
    
        this.voPaciente  = new VoPaciente();
        this.voPacientes = new ArrayList<>();
        this.daoPaciente = new DaoPaciente();
    
    }
    
    public String inicializar() {
        
        this.voPaciente = new VoPaciente();
        return "frmpaciente";
        
    }
    
    public void gravar() {
        
        String   mensagem;
        Severity iconeDialogo;
        
        try {
            
            daoPaciente.gravar(voPaciente);
            
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
            
            long id = this.voPaciente.getIdPaciente();
            this.daoPaciente.excluir(id);
            this.consultar();
            
        } catch(Exception e) {
            
            System.err.println(e.getMessage());
            
        }
        
        // Para atualizar a dataTable da página jsf logo após a operação de exclusão
        // deve ser usado no retorno do método da classe ManagedBean uma query-string: <página>?faces-redirect=true"
        // no return.
        // http://www.guj.com.br/java/283626-autualizar-pagina-apos-pconfirmdialog
        return "tblpaciente?faces-redirect=true";
        
    }
    
    public String consultar() {
        
        try {
            
            this.voPacientes = daoPaciente.consultar();
            
        } catch(Exception e) {
            
            System.out.println(e.getMessage());
            Logger.getLogger(MbPaciente.class.getName()).log(Level.SEVERE, null, e);
            
            FacesMessage dialogo = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Web Clínica médica", "Falha ao consultar.");
            RequestContext.getCurrentInstance().showMessageInDialog(dialogo);
            
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
