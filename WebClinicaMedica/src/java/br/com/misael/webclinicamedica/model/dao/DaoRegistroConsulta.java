/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.misael.webclinicamedica.model.dao;

import br.com.misael.webclinicamedica.model.vo.VoRegistroConsulta;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author Misael C. Homem
 */
public class DaoRegistroConsulta {
    
    public DaoRegistroConsulta() {}
    
    private EntityManager getEntityManager() throws Exception {

        EntityManagerFactory factory = null;
        EntityManager entityManager  = null;
        factory = Persistence.createEntityManagerFactory("WebClinicaMedicaPU");
        entityManager = factory.createEntityManager();

        return entityManager;

    }
    
    public VoRegistroConsulta gravar(VoRegistroConsulta voRegistroConsulta) throws Exception {

        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        
        if (voRegistroConsulta.getVoAgendamento().getIdConsulta() == null) {
            // Grava novo registro.
            entityManager.persist(voRegistroConsulta);
        } else {
            // Edita um registro já existente.
            voRegistroConsulta = entityManager.merge(voRegistroConsulta);
        }
        
        entityManager.getTransaction().commit();
        entityManager.close();
       
        return voRegistroConsulta;
        
    }

    public void excluir(Long idConsulta) throws Exception {

        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        VoRegistroConsulta voRegistroConsulta = entityManager.find(VoRegistroConsulta.class, idConsulta);
        entityManager.remove(voRegistroConsulta);
        entityManager.getTransaction().commit();
        entityManager.close();
        
    }

    // Ideia da implementação obtida em http://www.devmedia.com.br/definindo-entity-manager-na-java-persistence-api/28271
    public List<VoRegistroConsulta> consultar() throws Exception {
        
        EntityManager entityManager = getEntityManager();
        String jpql = "SELECT RC FROM REGISTRO_CONSULTA RC";
        TypedQuery<VoRegistroConsulta> consulta = entityManager.createQuery(jpql, VoRegistroConsulta.class);
        List<VoRegistroConsulta> voRegistroConsultas    = consulta.getResultList();
        
        return voRegistroConsultas;
        
    }

    public VoRegistroConsulta consultar(Long idPaciente) throws Exception {
        
        EntityManager entityManager = getEntityManager();
        VoRegistroConsulta voRegistroConsulta       = entityManager.find(VoRegistroConsulta.class, idPaciente);
        entityManager.close();
        
        return voRegistroConsulta;
        
    }
    
}
