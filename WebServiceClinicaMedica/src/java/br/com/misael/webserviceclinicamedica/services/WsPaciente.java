package br.com.misael.webserviceclinicamedica.services;

import br.com.misael.webserviceclinicamedica.model.dao.DaoPaciente;
import br.com.misael.webserviceclinicamedica.model.vo.VoPaciente;
import com.thoughtworks.xstream.XStream;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;

/**
 *
 * @author Misael C. Homem
 */
@WebService(serviceName = "WsPaciente")
public class WsPaciente {
    
    private List<VoPaciente>  voPacientes;

    @WebMethod(operationName = "consultarPacienteXml")
    public String consultarPacienteXml() throws Exception {
        
        DaoPaciente dao  = new DaoPaciente();
        this.voPacientes = dao.consultar();
        XStream xstream  = new XStream();
        xstream.alias("pacientes", List.class);
        String xml = xstream.toXML(this.voPacientes);
        
        return xml;
        
    }
    
    @WebMethod(operationName = "consultarPacienteLista")
    public List<VoPaciente> consultarPacienteLista() throws Exception {
        
        DaoPaciente dao = new DaoPaciente();
        this.voPacientes = dao.consultar();
        
        return this.voPacientes;
        
    }
}
