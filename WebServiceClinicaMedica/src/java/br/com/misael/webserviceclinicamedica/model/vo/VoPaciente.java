package br.com.misael.webserviceclinicamedica.model.vo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Misael C. Homem
 */
@Entity(name = "PACIENTE")
public class VoPaciente implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPaciente;
    
    @Column(length = 100, nullable = false)
    private String nome;
    
    @Column(nullable = true)
    private int idade; 
    
    @Column(length = 1, nullable = false)
    private String sexo;
    
    @Column(length = 100, nullable = false)
    private String endereco;
    
    @Column(length = 14, nullable = false)
    private String telefone;
    
    @Column(nullable = false)
    private boolean ativo;

    public VoPaciente() {}

    public Long getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Long idPaciente) {
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

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
    
    public String getExibicaoAtivo() {
        return this.ativo ? "Sim" : "Não";        
    }
    
    public void setExibicaoAtivo(String ativo) {
        this.ativo = (ativo.equals("Sim") ? true : false);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPaciente != null ? idPaciente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the idPaciente fields are not set
        if (!(object instanceof VoPaciente)) {
            return false;
        }
        
        VoPaciente other = (VoPaciente) object;
        if ((this.idPaciente == null && other.idPaciente != null) || (this.idPaciente != null && !this.idPaciente.equals(other.idPaciente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nome;
    }
    
}
