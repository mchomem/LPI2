/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.misael.webclinicamedica.model.vo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 *
 * @author Misael C. Homem
 */
@Entity(name = "REGISTRO_CONSULTA")
public class VoRegistroConsulta implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @OneToOne
    @JoinColumn(name="IDCONSULTA")
    private VoAgendamento voAgendamento;
    
    @Column(length = 2000, nullable = false)
    private String prontuario;
    
    @Column(nullable = false)
    private boolean consultaFinalizada;
    
    public VoAgendamento getVoAgendamento() {
        return voAgendamento;
    }

    public void setVoAgendamento(VoAgendamento voAgendamento) {
        this.voAgendamento = voAgendamento;
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
    
    public String getExibicaoconsultaFinalizada() {
        return this.consultaFinalizada ? "Sim" : "Não";        
    }
    
    public void SetExibicaoconsultaFinalizada(String consultaFinalizada) {
        this.consultaFinalizada = (consultaFinalizada.equals("Sim") ? true : false);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (voAgendamento != null ? voAgendamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the idConsulta fields are not set
        if (!(object instanceof VoRegistroConsulta)) {
            return false;
        }
        VoRegistroConsulta other = (VoRegistroConsulta) object;
        if ((this.voAgendamento == null && other.voAgendamento != null) || (this.voAgendamento != null && !this.voAgendamento.equals(other.voAgendamento))) {
            return false;
        }
        return true;
    }

}
