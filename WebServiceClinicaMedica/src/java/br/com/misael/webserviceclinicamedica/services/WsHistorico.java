package br.com.misael.webserviceclinicamedica.services;

import br.com.misael.webserviceclinicamedica.model.dao.DaoHistorico;
import br.com.misael.webserviceclinicamedica.model.vo.VoHistorico;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import java.util.ArrayList;
import javax.jws.WebParam;

/**
 *
 * @author Misael C. Homem
 */
@WebService(serviceName = "WsHistorico")
public class WsHistorico {
    
    private List<VoHistorico> voHistoricos;    
    
    public WsHistorico() {}
    
    @WebMethod(operationName = "consultarConsultasFinalizadas")
    public List<VoHistorico> consultarConsultasFinalizadas(@WebParam(name = "nomePaciente") String nomePaciente) throws Exception {
        
        DaoHistorico dao = new DaoHistorico();
        
        this.voHistoricos = new ArrayList<>();
        this.voHistoricos = dao.consultarConsultasFinalizadas(nomePaciente);
        
        return this.voHistoricos;
        
    }
    
    @WebMethod(operationName = "consultarConsultasNaoFinalizadas")
    public List<VoHistorico> consultarConsultasNaoFinalizadas(@WebParam(name = "nomePaciente") String nomePaciente) throws Exception {
        
        DaoHistorico dao = new DaoHistorico();
        
        this.voHistoricos = new ArrayList<>();
        this.voHistoricos = dao.consultarConsultasNaoFinalizadas(nomePaciente);
        
        return this.voHistoricos;
        
    }
    
    @WebMethod(operationName = "consultarHistorico")
    public List<VoHistorico> consultarHistorico() throws Exception {
        
        DaoHistorico dao = new DaoHistorico();
        
        this.voHistoricos = new ArrayList<>();
        this.voHistoricos = dao.consultarHistorico();
        
        return this.voHistoricos;
        
    }
    
    @WebMethod(operationName = "consultarHistoricoFiltro")
    public List<VoHistorico> consultarHistoricoFiltro(@WebParam(name = "nomePaciente") String nomePaciente) throws Exception {
        
        DaoHistorico dao = new DaoHistorico();
        
        this.voHistoricos = new ArrayList<>();
        this.voHistoricos = dao.consultarHistoricoFiltro(nomePaciente);
        
        return this.voHistoricos;
        
    }
    
}
