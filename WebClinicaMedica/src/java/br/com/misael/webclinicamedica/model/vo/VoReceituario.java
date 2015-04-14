package br.com.misael.webclinicamedica.model.vo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Misael C. Homem
 */
@Entity(name = "RECEITUARIO")
public class VoReceituario implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @ManyToOne
    @JoinColumn(name = "IDCONSULTA")
    private VoRegistroConsulta voRegistroConsulta;
    
    @Id
    @ManyToOne
    @JoinColumn(name = "IDMEDICAMENTO")
    private VoMedicamento voMedicamento;
    
    @Column(length = 200, nullable = false)
    private String posologia;
    
    public VoRegistroConsulta getRegistroConsulta() {
        return voRegistroConsulta;
    }
    
    public void setRegistroConsulta(VoRegistroConsulta voRegistroConsulta) {
        this.voRegistroConsulta = voRegistroConsulta;
    }
    
    public VoMedicamento getMedicamento() {
        return voMedicamento;
    }
    
    public void setMedicamento(VoMedicamento voMedicamento) {
        this.voMedicamento = voMedicamento;
    }
    
    public String getPosologia() {
        return posologia;
    }
    
    public void setPosologia(String posologia) {
        this.posologia = posologia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (voRegistroConsulta != null ? voRegistroConsulta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the idConsulta fields are not set
        if (!(object instanceof VoReceituario)) {
            return false;
        }
        VoReceituario other = (VoReceituario) object;
        if ((this.voRegistroConsulta == null && other.voRegistroConsulta != null) || (this.voRegistroConsulta != null && !this.voRegistroConsulta.equals(other.voRegistroConsulta))) {
            return false;
        }
        return true;
    }
    
}
