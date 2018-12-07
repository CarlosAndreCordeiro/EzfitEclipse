/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;

/**
 *
 * @author $Carlos Cordeiro <carloscordeiroconsultor@gmail.com>
 */

@Entity
public class Professor extends Pessoa{
    
    @Column(length = 20)
    private String cref;

    
    @Deprecated
    public Professor() {
    }

    

    public Professor(String cref, String cpf, String nome, String endereco, String sexo, LocalDate dataNascimento, String email, String senha) {
        super(cpf, nome, endereco, sexo, dataNascimento, email, senha);
        this.cref = cref;
    }

    

    public String getCref() {
        return cref;
    }

    public void setCref(String cref) {
        this.cref = cref;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.cref);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Professor other = (Professor) obj;
        if (!Objects.equals(this.cref, other.cref)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Professor{" + "cref=" + cref + '}';
    }

    
    
    
    
    
}
