package br.com.misael.webclinicamedica.model.dao;

import br.com.misael.webclinicamedica.model.vo.VoAgendamento;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author Misael C. Homem
 */
public class DaoAgendamento {
    
    public DaoAgendamento() {}
    
    private EntityManager getEntityManager() throws Exception {
        
        EntityManagerFactory factory = null;
        EntityManager entityManager  = null;
        factory = Persistence.createEntityManagerFactory("WebClinicaMedicaPU");
        entityManager = factory.createEntityManager();
        
        return entityManager;

    }
    
    public VoAgendamento gravar(VoAgendamento voAgendamento) throws Exception {
        
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();

        if (voAgendamento.getIdConsulta()== null) {
            // Grava novo registro.
            entityManager.persist(voAgendamento);
        } else {
            // Edita um registro já existente.
            voAgendamento = entityManager.merge(voAgendamento);
        }

        entityManager.getTransaction().commit();
        entityManager.close();
        
        return voAgendamento;
    }
    
    public void excluir(Long idConsulta) throws Exception {

        EntityManager entityManager = getEntityManager();    
        entityManager.getTransaction().begin();
        VoAgendamento voAgendamento = entityManager.find(VoAgendamento.class, idConsulta);
        entityManager.remove(voAgendamento);
        entityManager.getTransaction().commit();
        entityManager.close();
        
    }
    
    public List<VoAgendamento> consultar() throws Exception {
        
        EntityManager entityManager = getEntityManager();
        String jpql = "SELECT A FROM AGENDAMENTO A";
        TypedQuery<VoAgendamento> consulta = entityManager.createQuery(jpql, VoAgendamento.class);
        List<VoAgendamento> voAgendamentos = consulta.getResultList();
        
        return voAgendamentos;
        
    }
    
    public VoAgendamento consultar(Long idConsulta) throws Exception {
        
        EntityManager entityManager = getEntityManager();
        VoAgendamento voAgendamento = entityManager.find(VoAgendamento.class, idConsulta);
        entityManager.close();
        
        return voAgendamento;
        
    }
    
}
