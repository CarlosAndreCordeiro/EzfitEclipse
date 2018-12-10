/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import model.Aluno;
import model.Exercicio;
import model.Treino;
import model.Hibernate.AlunoHibernate;
import model.Hibernate.ExercicioHibernate;
import model.Hibernate.ProfessorHibernate;
import model.Hibernate.TreinoHibernate;
import model.Professor;
import org.hibernate.type.LocalDateType;

/**
 *
 * @author $Carlos Cordeiro <carloscordeiroconsultor@gmail.com>
 */
public class Launch {
    
    public static void main (String args[]){
        
        Random gerador = new Random();
        List<Treino> treinos = new ArrayList<>();
        List<Treino> treinos2 = new ArrayList<>();
        List<Exercicio> exercicios = new ArrayList<>();
        List<Exercicio> exercicios2 = new ArrayList<>();
        ExercicioHibernate eh = new ExercicioHibernate();
        TreinoHibernate th = new TreinoHibernate();
        AlunoHibernate ah = new AlunoHibernate();
        ProfessorHibernate ph = new ProfessorHibernate();
        
        
        
        for(int i = 0 ; i<1; i++){
            Exercicio e = new Exercicio( "quebra peito nulo"+ gerador.nextInt(1000),null );
            Exercicio e2 = new Exercicio( "quebra custela"+ gerador.nextInt(1000), "descricao");
            eh.adiciona(e2);
            eh.adiciona(e);

            exercicios.add(e);
            exercicios.add(e2);
            
            exercicios2.add(e2);
            exercicios2.add(e);
        }
        
        
        Professor p = new Professor("cregf", "cpf111", "nome", "endereco", "sexo",LocalDate.of(1999,03,22), "email", "s");


        ph.adiciona(p);
        
        Professor p2 = new Professor("cregf2", "cpf111"+ gerador.nextInt(1000), "nome", "endereco", "sexo",LocalDate.of(1999,03,22), "email", "s");


        ph.adiciona(p2);
        
       
        p  =ph.recuperarCpf("cpf111");

 

        Aluno a = new Aluno("objetivo", 0, 0, null, "cpf22", "nome","endereco", "M", LocalDate.of(1982, 03, 22)," email", "senha");
        Aluno a2 = new Aluno("objetivo", 0, 0, null, "cpf333", "nome","endereco", "F", LocalDate.now()," email", "senha");
        
        ah.adiciona(a);
        ah.adiciona(a2);
        
        a  = ah.recuperarCpf("cpf22");
        a2 = ah.recuperarCpf("cpf333");
       
        Treino t = new Treino("treino primeiro"+ gerador.nextInt(1000), 
                    "descricao", 20, p, "sid", exercicios, a, false);
        
        Treino t2 = new Treino("treino Segundo"+ gerador.nextInt(1000), 
                "descricao", 10, p, "sid", exercicios2, a2, false);


        th.adiciona(t);
        th.adiciona(t2);
        
        treinos.add(t);
        treinos2.add(t2);
        
        
        a.setTreinos(treinos);
        a2.setTreinos(treinos2);
        
        ah.alterar(a);
        ah.alterar(a2);
     
        
     
        
           for (int i = 0; i<1; i++){
               
               Aluno aa = new Aluno("objetivo", 0, 0, null, 
                       "cpf222" + gerador.nextInt(1000), "nome","endereco", 
                       "sexo", LocalDate.of(1982, 03, 22)," email", "senha");
               ah.adiciona(aa);
               
       Professor pp = new Professor("Cref", 
                       "cpf111"+ gerador.nextInt(1000), null, "endereco", 
                       "sexo", LocalDate.of(1982, 03, 22), "email", "s");
               ph.adiciona(pp);
           }
           
        
     
        
    }
    
    

}
