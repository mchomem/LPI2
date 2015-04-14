package br.com.misael.webclinicamedica.model.dao;

import br.com.misael.webclinicamedica.model.vo.VoPaciente;
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

    private EntityManager getEntityManager() {
        
        EntityManagerFactory factory = null;
        EntityManager entityManager  = null;

        try {
            factory = Persistence.createEntityManagerFactory("WebClinicaMedicaPU");
            entityManager = factory.createEntityManager();
        } catch(Exception ex) {
            System.err.println(ex.getMessage());
        }

        return entityManager;

    }

    public VoPaciente gravar(VoPaciente voPaciente) throws Exception {

        EntityManager entityManager = getEntityManager();

        try {
            
            entityManager.getTransaction().begin();
            if (voPaciente.getIdPaciente() == null) {
                // Grava novo registro.
                entityManager.persist(voPaciente);
            } else {
                // Edita um registro já existente.
                voPaciente = entityManager.merge(voPaciente);
            }
            entityManager.getTransaction().commit();
            
        } finally {
            
            entityManager.close();
            
        }
        
        return voPaciente;
        
    }

    public void excluir(Long idPaciente) {

        EntityManager entityManager = getEntityManager();
        
        try {
            
            entityManager.getTransaction().begin();
            VoPaciente voPaciente = entityManager.find(VoPaciente.class, idPaciente);
            entityManager.remove(voPaciente);
            entityManager.getTransaction().commit();
            
        } finally {
            
            entityManager.close();
            
        }
        
    }

    // Ideia da implementação obtida em http://www.devmedia.com.br/definindo-entity-manager-na-java-persistence-api/28271
    public List<VoPaciente> consultar() throws Exception {
        
        EntityManager entityManager = getEntityManager();
        String jpql = "SELECT P FROM PACIENTE P";
        TypedQuery<VoPaciente> consulta = entityManager.createQuery(jpql, VoPaciente.class);
        List<VoPaciente> voPacientes = consulta.getResultList();
        
        return voPacientes;
        
    }

    public VoPaciente consultar(Long idPaciente) {
        
        EntityManager entityManager = getEntityManager();
        VoPaciente voPaciente = null;
        
        try {
            voPaciente = entityManager.find(VoPaciente.class, idPaciente);
        } finally {
            entityManager.close();
        }
        
        return voPaciente;
        
    }

}
