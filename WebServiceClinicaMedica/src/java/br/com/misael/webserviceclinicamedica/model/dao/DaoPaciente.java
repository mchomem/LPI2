package br.com.misael.webserviceclinicamedica.model.dao;

import br.com.misael.webserviceclinicamedica.model.vo.VoPaciente;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author Misael C. Homem
 */
public class DaoPaciente {

    public DaoPaciente() {}
    
    private EntityManager getEntityManager() throws Exception {
        
        EntityManagerFactory factory = null;
        EntityManager entityManager  = null;
        factory = Persistence.createEntityManagerFactory("WebClinicaMedicaPU");
        entityManager = factory.createEntityManager();

        return entityManager;

    }

     // Ideia da implementação obtida em http://www.devmedia.com.br/definindo-entity-manager-na-java-persistence-api/28271
    public List<VoPaciente> consultar() throws Exception {
        
        EntityManager entityManager = getEntityManager();
        String jpql = "SELECT P FROM PACIENTE P";
        TypedQuery<VoPaciente> consulta = entityManager.createQuery(jpql, VoPaciente.class);
        List<VoPaciente> voPacientes    = consulta.getResultList();
        
        return voPacientes;
        
    }

    public VoPaciente consultar(Long idPaciente) throws Exception {
        
        EntityManager entityManager = getEntityManager();
        VoPaciente voPaciente       = entityManager.find(VoPaciente.class, idPaciente);
        entityManager.close();
        
        return voPaciente;
        
    }
    
}
