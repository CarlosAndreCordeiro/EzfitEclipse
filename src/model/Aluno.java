package model;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
public class Aluno extends Pessoa {
   
    @Column(length = 50)
    private String objetivo;
    @Column 
    private double altura;
    @Column()
    private double peso;  
    @OneToMany (mappedBy = "aluno", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private List<Treino> treinos;
   
    
    @Deprecated
    public Aluno(){
    }

    
    


    public Aluno(String objetivo, double altura, double peso, List<Treino> treinos, String cpf, String nome, String endereco, String sexo, LocalDate dataNascimento, String email, String senha) {
        super(cpf, nome, endereco, sexo, dataNascimento, email, senha);
        this.objetivo = objetivo;
        this.altura = altura;
        this.peso = peso;
        this.treinos = treinos;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public List<Treino> getTreinos() {
        return treinos;
    }

    public void setTreinos(List<Treino> treinos) {
        this.treinos = treinos;
    }

    @Override
    public String toString() {
        return "Aluno{" + "objetivo=" + objetivo + ", altura=" + altura + ", peso=" + peso + ", treinos=" + treinos + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.objetivo);
        hash = 71 * hash + (int) (Double.doubleToLongBits(this.altura) ^ (Double.doubleToLongBits(this.altura) >>> 32));
        hash = 71 * hash + (int) (Double.doubleToLongBits(this.peso) ^ (Double.doubleToLongBits(this.peso) >>> 32));
        hash = 71 * hash + Objects.hashCode(this.treinos);
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
        final Aluno other = (Aluno) obj;
        if (Double.doubleToLongBits(this.altura) != Double.doubleToLongBits(other.altura)) {
            return false;
        }
        if (Double.doubleToLongBits(this.peso) != Double.doubleToLongBits(other.peso)) {
            return false;
        }
        if (!Objects.equals(this.objetivo, other.objetivo)) {
            return false;
        }
        if (!Objects.equals(this.treinos, other.treinos)) {
            return false;
        }
        return true;
    }

    
    
    
}
