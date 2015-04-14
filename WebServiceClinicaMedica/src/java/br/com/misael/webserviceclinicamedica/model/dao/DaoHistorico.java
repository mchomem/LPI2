package br.com.misael.webserviceclinicamedica.model.dao;

import br.com.misael.webserviceclinicamedica.model.vo.VoHistorico;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Misael C. Homem
 */
public class DaoHistorico {
    
    public DaoHistorico() {}
    
    private EntityManager getEntityManager() throws Exception {
        
        EntityManagerFactory factory = null;
        EntityManager entityManager  = null;
        factory = Persistence.createEntityManagerFactory("WebClinicaMedicaPU");
        entityManager = factory.createEntityManager();
        
        return entityManager;
        
    }
    
    public List<VoHistorico> consultarConsultasFinalizadas(String nomePaciente) throws Exception {
        
        EntityManager entityManager = getEntityManager();
        
        String sql = "SELECT"
            + " AGENDAMENTO.IDCONSULTA"
            + ", PACIENTE.IDPACIENTE"
            + ", PACIENTE.NOME"
            + ", PACIENTE.IDADE"
            + ", AGENDAMENTO.DATACONSULTA"
            + ", REGISTRO_CONSULTA.PRONTUARIO"
            + ", REGISTRO_CONSULTA.CONSULTAFINALIZADA"
            + " FROM"
            + " PACIENTE"
            + " JOIN AGENDAMENTO ON(AGENDAMENTO.IDPACIENTE = PACIENTE.IDPACIENTE)"
            + " JOIN REGISTRO_CONSULTA ON(REGISTRO_CONSULTA.IDCONSULTA = AGENDAMENTO.IDCONSULTA)"
            + " WHERE"
            + " REGISTRO_CONSULTA.CONSULTAFINALIZADA = 1";
        
        // Se o parâmetro não for informado, não adiciona a cláusula WHERE.
        if(nomePaciente.length() != 0) {
            
            sql += " AND PACIENTE.NOME LIKE '%" + nomePaciente + "%'";
                    
        }
            
        Query query = entityManager.createNativeQuery(sql, VoHistorico.class);
        
        try {
            
            // VoHistorico voHistorico = (VoHistorico) query.getSingleResult();
            List<VoHistorico> voHistoricos = query.getResultList();
            return voHistoricos;
            
        } catch (NoResultException e) {
            
            return null;
            
        }
        
    }
    
    public List<VoHistorico> consultarConsultasNaoFinalizadas(String nomePaciente) throws Exception {
        
        EntityManager entityManager = getEntityManager();
        
        String sql = "SELECT"
            + " AGENDAMENTO.IDCONSULTA"
            + ", PACIENTE.IDPACIENTE"
            + ", PACIENTE.NOME"
            + ", PACIENTE.IDADE"
            + ", AGENDAMENTO.DATACONSULTA"
            + ", REGISTRO_CONSULTA.PRONTUARIO"
            + ", REGISTRO_CONSULTA.CONSULTAFINALIZADA"
            + " FROM"
            + " PACIENTE"
            + " JOIN AGENDAMENTO ON(AGENDAMENTO.IDPACIENTE = PACIENTE.IDPACIENTE)"
            + " JOIN REGISTRO_CONSULTA ON(REGISTRO_CONSULTA.IDCONSULTA = AGENDAMENTO.IDCONSULTA)"
            + " WHERE"
            + " REGISTRO_CONSULTA.CONSULTAFINALIZADA = 0";
        
        // Se o parâmetro não for informado, não adiciona a cláusula WHERE.
        if(nomePaciente.length() != 0) {
            
            sql += " AND PACIENTE.NOME LIKE '%" + nomePaciente + "%'";
                    
        }
            
        Query query = entityManager.createNativeQuery(sql, VoHistorico.class);
        
        try {
            
            // VoHistorico voHistorico = (VoHistorico) query.getSingleResult();
            List<VoHistorico> voHistoricos = query.getResultList();
            return voHistoricos;
            
        } catch (NoResultException e) {
            
            return null;
            
        }
        
    }
    
    public List<VoHistorico> consultarHistorico() throws Exception {
        
        EntityManager entityManager = getEntityManager();
        
        String sql = "SELECT"
            + " AGENDAMENTO.IDCONSULTA"
            + ", PACIENTE.IDPACIENTE"
            + ", PACIENTE.NOME"
            + ", PACIENTE.IDADE"
            + ", AGENDAMENTO.DATACONSULTA"
            + ", REGISTRO_CONSULTA.PRONTUARIO"
            + ", REGISTRO_CONSULTA.CONSULTAFINALIZADA"
            + " FROM"
            + " PACIENTE"
            + " JOIN AGENDAMENTO ON(AGENDAMENTO.IDPACIENTE = PACIENTE.IDPACIENTE)"
            + " JOIN REGISTRO_CONSULTA ON(REGISTRO_CONSULTA.IDCONSULTA = AGENDAMENTO.IDCONSULTA)";
            
        Query query = entityManager.createNativeQuery(sql, VoHistorico.class);
        
        try {
            
            // VoHistorico voHistorico = (VoHistorico) query.getSingleResult();
            List<VoHistorico> voHistoricos = query.getResultList();
            return voHistoricos;
            
        } catch (NoResultException e) {
            
            return null;
            
        }
        
    }
    
    public List<VoHistorico> consultarHistoricoFiltro(String nomePaciente) throws Exception {
        
        EntityManager entityManager = getEntityManager();
        
        String sql = "SELECT"
            + " AGENDAMENTO.IDCONSULTA"
            + ", PACIENTE.IDPACIENTE"
            + ", PACIENTE.NOME"
            + ", PACIENTE.IDADE"
            + ", AGENDAMENTO.DATACONSULTA"
            + ", REGISTRO_CONSULTA.PRONTUARIO"
            + ", REGISTRO_CONSULTA.CONSULTAFINALIZADA"
            + " FROM"
            + " PACIENTE"
            + " JOIN AGENDAMENTO ON(AGENDAMENTO.IDPACIENTE = PACIENTE.IDPACIENTE)"
            + " JOIN REGISTRO_CONSULTA ON(REGISTRO_CONSULTA.IDCONSULTA = AGENDAMENTO.IDCONSULTA)";
            
        // Se o parâmetro não for informado, não adiciona a cláusula WHERE.
        if(nomePaciente.length() != 0) {
            
            sql += " WHERE PACIENTE.NOME LIKE '%" + nomePaciente + "%'";
                    
        }
            
        Query query = entityManager.createNativeQuery(sql, VoHistorico.class);
        
        try {
            
            // VoHistorico voHistorico = (VoHistorico) query.getSingleResult();
            List<VoHistorico> voHistoricos = query.getResultList();
            return voHistoricos;
            
        } catch (NoResultException e) {
            
            return null;
            
        }
        
    }
    
}
