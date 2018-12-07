/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.Hibernate;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import model.DAO.ExercicioDao;
import model.Exercicio;




/**
 *
 * @author $Carlos Cordeiro <carloscordeiroconsultor@gmail.com>
 */

public class ExercicioHibernate implements ExercicioDao {

    
    public ExercicioHibernate() {}

    
    @Override
    public void adiciona(Exercicio exercicio) {
        EntityManager em = JPAManager.getInstance().getEntityManager();
        EntityTransaction t = em.getTransaction();

        try {
            t.begin();
            em.persist(exercicio);
            em.flush();
            t.commit();
        } catch (Exception e) {
            System.out.println("Erro ao adicionar o exercicio!!");
            t.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public Exercicio recuperar(int codigo) {
        EntityManager em = JPAManager.getInstance().getEntityManager();
        try {

            return (Exercicio) em.createQuery("From Exercicio Where codigo=" + codigo).getResultList().get(0);
        }catch(Exception e){
            System.out.println("Erro ao recuperar Exercicio pelo codigo!!");
            return null;
        } finally {
            //Fechamos a sessão
            em.close();
        }
    }
    
    
    @Override
    
    public void alterar(Exercicio exercicio) {
         EntityManager em = JPAManager.getInstance().getEntityManager();
        EntityTransaction t = em.getTransaction();

       try {
            t.begin();
            em.merge(exercicio);
            em.flush();
            t.commit();
        } catch (Exception e) {
            System.out.println("Erro ao alterar o exercicio!!");
            t.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    
     @Override
    public void deletar(Exercicio exercicio) {
        EntityManager em = JPAManager.getInstance().getEntityManager();
        EntityTransaction t = em.getTransaction();

        try {
            t.begin();
            em.remove(exercicio);
            t.commit();
        } catch (Exception e) {
            System.out.println("Erro ao deletar o exercicio");
            t.rollback();

        } finally {
            em.close();
        }
    }


    
     @Override
    public Exercicio recuperarNome(String nome) {
        EntityManager em = JPAManager.getInstance().getEntityManager();
        
        try {

            return (Exercicio) em.createQuery("From Exercicio Where nome='" + nome + "'").getResultList().get(0);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Nome do exercicio não encontrado!!");
            return null;

        } finally {
            //Fechamos a sessão
            em.close();
        }
    }

    @Override
    public List<Exercicio> recuperarTodos() {
        EntityManager em = JPAManager.getInstance().getEntityManager();
        List<Exercicio> exercicios = new ArrayList();
        try {

            exercicios = em.createQuery("FROM Exercicio").getResultList();
        } catch (Exception e) {
            System.out.println("Erro ao recuperar a lista de Exercicios");
        } finally {
            em.close();
        }

        return exercicios;

    }
    
}
