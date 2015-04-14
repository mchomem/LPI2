package br.com.misael.webserviceclinicamedica.model.vo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * A tabela Histórico é uma tabela exclusiva para o Web Service.
 * Nesta tabela é executado um INSERT com SELECT e após a tabela
 * é consultada gerando resultado de histórico de pacientes com
 * sua consulta finalizada.
 * 
 * @author Misael C. Homem
 */
@Entity(name = "HISTORICO")
public class VoHistorico  implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    private long idConsulta;
    
    @Id
    private long idPaciente;
    
    @Column(length = 100, nullable = false)
    private String nome;
    
    @Column(nullable = true)
    private int idade;
    
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataConsulta;
    
    @Column(length = 2000, nullable = false)
    private String prontuario;
    
    @Column(nullable = false)
    private boolean consultaFinalizada;
    
    public VoHistorico() {}
    
    public long getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(long idConsulta) {
        this.idConsulta = idConsulta;
    }
    
    public long getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(long idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public Date getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(Date dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public String getProntuario() {
        return prontuario;
    }

    public void setProntuario(String prontuario) {
        this.prontuario = prontuario;
    }

    public boolean isConsultaFinalizada() {
        return consultaFinalizada;
    }

    public void setConsultaFinalizada(boolean consultaFinalizada) {
        this.consultaFinalizada = consultaFinalizada;
    }
    
}
