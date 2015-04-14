package br.com.misael.webclinicamedica.model.vo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

/**
 *
 * @author Misael C. Homem
 */
@Entity(name = "USUARIO")
public class VoUsuario implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idUsuario;
    
    @Column(nullable = false, length = 20, unique = true)
    private String nome;
    
    @Column(nullable = false, length = 20)
    private String senha;
    
    @Transient
    private String confSenha;
    
    public Long getIdUsuairo() {
        return idUsuario;
    }
    
    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public String getConfSenha() {
        return confSenha;
    }
    
    public void setConfSenha(String confSenha) {
        this.confSenha = confSenha;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nome != null ? nome.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the idUsuario fields are not set
        if (!(object instanceof VoUsuario)) {
            return false;
        }
        VoUsuario other = (VoUsuario) object;
        if ((this.nome == null && other.nome != null) || (this.nome != null && !this.nome.equals(other.nome))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nome;
    }
    
}
