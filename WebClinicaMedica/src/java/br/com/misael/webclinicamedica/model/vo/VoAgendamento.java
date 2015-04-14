/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.misael.webclinicamedica.model.vo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Misael C. Homem
 */
@Entity(name = "AGENDAMENTO")
public class VoAgendamento implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idConsulta;
    
    @ManyToOne
    @JoinColumn(name="IDPACIENTE", nullable = false)
    private VoPaciente paciente;
    
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataConsulta;

    public Long getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(Long idConsulta) {
        this.idConsulta = idConsulta;
    }
    
    public VoPaciente getPaciente() {
        return paciente;
    }

    public void setPaciente(VoPaciente paciente) {
        this.paciente = paciente;
    }

    public Date getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(Date dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idConsulta != null ? idConsulta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VoAgendamento)) {
            return false;
        }
        VoAgendamento other = (VoAgendamento) object;
        if ((this.idConsulta == null && other.idConsulta != null) || (this.idConsulta != null && !this.idConsulta.equals(other.idConsulta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Data: " + new SimpleDateFormat("dd/MM/yyyy").format(dataConsulta)
    			+ " - Cód: " + idConsulta
    			+ " - " + paciente.getNome();
    }

}
