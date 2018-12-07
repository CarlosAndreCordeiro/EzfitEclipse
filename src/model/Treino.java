/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author $Carlos Cordeiro <carloscordeiroconsultor@gmail.com>
 */


/**
 *
 * @author $Carlos Cordeiro <carloscordeiroconsultor@gmail.com>
 */


@Entity
public class Treino {
   
	
		   
	    @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private int codigo;
	    
	    @Column(length = 20, unique = true)
	    private String nome;
	    
	    @Column(length = 70)
	    private String descricao;
	    
	    @Column(length = 3)
	    private int duracao;

	    
	    @OneToOne 
	    @JoinColumn(name = "cod_professor" )
	    private Professor professor;   
	    
	    @Column(length = 6)
	    private String intensidade;    
	    

	    @OneToMany (fetch = FetchType.EAGER)
	    private List<Exercicio> exercicios;
	    
	    @ManyToOne
	    @JoinColumn(name = "cod_aluno" )
	    private Aluno aluno;
	    
	    @Column
	    private boolean realizado = false;



	    @Deprecated
	    public Treino() {
	    }

	       public Treino(String nome, String descricao, int duracao, Professor professor, String intensidade, List<Exercicio> exercicios, Aluno aluno, boolean realizado) {
	        this.nome = nome;
	        this.descricao = descricao;
	        this.duracao = duracao;
	        this.professor = professor;
	        this.intensidade = intensidade;
	        this.exercicios = exercicios;
	        this.aluno = aluno;
	        this.realizado = realizado;
	    }

	    public int getCodigo() {
	        return codigo;
	    }

	    public String getNome() {
	        return nome;
	    }

	    public void setNome(String nome) {
	        this.nome = nome;
	    }

	    public String getDescricao() {
	        return descricao;
	    }

	    public void setDescricao(String descricao) {
	        this.descricao = descricao;
	    }

	    public int getDuracao() {
	        return duracao;
	    }

	    public void setDuracao(int duracao) {
	        this.duracao = duracao;
	    }

	    public Professor getProfessor() {
	        return professor;
	    }

	    public void setProfessor(Professor professor) {
	        this.professor = professor;
	    }

	    public String getIntensidade() {
	        return intensidade;
	    }

	    public void setIntensidade(String intensidade) {
	        this.intensidade = intensidade;
	    }

	    public List<Exercicio> getExercicios() {
	        return exercicios;
	    }

	    public void setExercicios(List<Exercicio> exercicios) {
	        this.exercicios = exercicios;
	    }

	    public Aluno getAluno() {
	        return aluno;
	    }

	    public void setAluno(Aluno aluno) {
	        this.aluno = aluno;
	    }

	    public boolean isRealizado() {
	        return realizado;
	    }

	    public void setRealizado(boolean realizado) {
	        this.realizado = realizado;
	    }

	    @Override
	    public int hashCode() {
	        int hash = 5;
	        hash = 67 * hash + this.codigo;
	        hash = 67 * hash + Objects.hashCode(this.nome);
	        hash = 67 * hash + Objects.hashCode(this.descricao);
	        hash = 67 * hash + this.duracao;
	        hash = 67 * hash + Objects.hashCode(this.professor);
	        hash = 67 * hash + Objects.hashCode(this.intensidade);
	        hash = 67 * hash + Objects.hashCode(this.exercicios);
	        hash = 67 * hash + Objects.hashCode(this.aluno);
	        hash = 67 * hash + (this.realizado ? 1 : 0);
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
	        final Treino other = (Treino) obj;
	        if (this.codigo != other.codigo) {
	            return false;
	        }
	        if (this.duracao != other.duracao) {
	            return false;
	        }
	        if (this.realizado != other.realizado) {
	            return false;
	        }
	        if (!Objects.equals(this.nome, other.nome)) {
	            return false;
	        }
	        if (!Objects.equals(this.descricao, other.descricao)) {
	            return false;
	        }
	        if (!Objects.equals(this.intensidade, other.intensidade)) {
	            return false;
	        }
	        if (!Objects.equals(this.professor, other.professor)) {
	            return false;
	        }
	        if (!Objects.equals(this.exercicios, other.exercicios)) {
	            return false;
	        }
	        if (!Objects.equals(this.aluno, other.aluno)) {
	            return false;
	        }
	        return true;
	    }

	    @Override
	    public String toString() {
	        return "Treino{" + "codigo=" + codigo + ", nome=" + nome + ", descricao=" + descricao + ", duracao=" + duracao + ", professor=" + professor + ", intensidade=" + intensidade + ", exercicios=" + exercicios + ", aluno=" + aluno + ", realizado=" + realizado + '}';
	    }

	    
	    
	       
	 

}

