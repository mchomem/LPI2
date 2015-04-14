/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.misael.webclinicamedica.model.dao;

import br.com.misael.webclinicamedica.model.vo.VoMedicamento;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author Misael
 */
public class DaoMedicamento {
    
    public DaoMedicamento() {}

    private EntityManager getEntityManager() throws Exception {
        
        EntityManagerFactory factory = null;
        EntityManager entityManager  = null;
        factory = Persistence.createEntityManagerFactory("WebClinicaMedicaPU");
        entityManager = factory.createEntityManager();

        return entityManager;

    }

    public VoMedicamento gravar(VoMedicamento voMedicamento) throws Exception {

        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        
        if (voMedicamento.getIdMedicamento() == null) {
            // Grava novo registro.
            entityManager.persist(voMedicamento);
        } else {
            // Edita um registro já existente.
            voMedicamento = entityManager.merge(voMedicamento);
        }
        
        entityManager.getTransaction().commit();
        entityManager.close();
       
        return voMedicamento;
        
    }

    public void excluir(long idMedicamento) throws Exception {

        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        VoMedicamento voMedicamento = entityManager.find(VoMedicamento.class, idMedicamento);
        entityManager.remove(voMedicamento);
        entityManager.getTransaction().commit();
        entityManager.close();
        
    }

    // Ideia da implementação obtida em http://www.devmedia.com.br/definindo-entity-manager-na-java-persistence-api/28271
    public List<VoMedicamento> consultar() throws Exception {
        
        EntityManager entityManager    = getEntityManager();
        String jpql = "SELECT M FROM MEDICAMENTO M";
        TypedQuery<VoMedicamento> consulta = entityManager.createQuery(jpql, VoMedicamento.class);
        List<VoMedicamento> voMedicamentos     = consulta.getResultList();
        
        return voMedicamentos;
        
    }
    
}
