package br.com.misael.webclinicamedica.model.dao;

import br.com.misael.webclinicamedica.model.vo.VoUsuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Misael C. Homem
 */
public class DaoUsuairo {
    
    public DaoUsuairo() {}

    private EntityManager getEntityManager() throws Exception {
        
        EntityManagerFactory factory = null;
        EntityManager entityManager  = null;
        factory = Persistence.createEntityManagerFactory("WebClinicaMedicaPU");
        entityManager = factory.createEntityManager();

        return entityManager;

    }

    public VoUsuario gravar(VoUsuario voUsuario) throws Exception {

        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        
        if (voUsuario.getIdUsuairo() == null) {
            // Grava novo registro.
            entityManager.persist(voUsuario);
        } else {
            // Edita um registro já existente.
            voUsuario = entityManager.merge(voUsuario);
        }
        
        entityManager.getTransaction().commit();
        entityManager.close();
       
        return voUsuario;
        
    }

    public void excluir(long idUsuario) throws Exception {

        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        VoUsuario voUsuario = entityManager.find(VoUsuario.class, idUsuario);
        entityManager.remove(voUsuario);
        entityManager.getTransaction().commit();
        entityManager.close();
        
    }

    // Ideia da implementação obtida em http://www.devmedia.com.br/definindo-entity-manager-na-java-persistence-api/28271
    public List<VoUsuario> consultar() throws Exception {
        
        EntityManager entityManager    = getEntityManager();
        String jpql = "SELECT U FROM USUARIO U";
        TypedQuery<VoUsuario> consulta = entityManager.createQuery(jpql, VoUsuario.class);
        List<VoUsuario> voUsuarios     = consulta.getResultList();
        
        return voUsuarios;
        
    }

    public VoUsuario consultar(String nomeUsuario) throws Exception {
        
        EntityManager entityManager = getEntityManager();
        VoUsuario voUsuario         = entityManager.find(VoUsuario.class, nomeUsuario);
        entityManager.close();
        
        return voUsuario;
        
    }
    
    public VoUsuario consultar(String nomeUsuario, String senhaUsuario) throws Exception {
        
        EntityManager entityManager = getEntityManager();
        /*
        String jpql = "SELECT U FROM USUARIO U WHERE U.NOME = :n AND U.SENHA = :s";
        Query consulta = entityManager.createQuery(jpql, VoUsuario.class);
        consulta.setParameter("n", nomeUsuario);
        consulta.setParameter("s", senhaUsuario);
        */
        Query nq = entityManager.createNativeQuery("SELECT * FROM USUARIO WHERE NOME='" + nomeUsuario + "' and senha='" + senhaUsuario + "'", VoUsuario.class);

        try {
            
            VoUsuario voUsuario = (VoUsuario) nq.getSingleResult();
            
            return voUsuario;
            
        } catch (NoResultException e) {
            
            return null;
            
        }
        
    }
    
}
