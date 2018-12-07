/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.Hibernate;

import java.util.ArrayList;
import java.util.List;
import model.DAO.TreinoDao;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import model.Treino;

/**
 *
 * @author $Carlos Cordeiro <carloscordeiroconsultor@gmail.com>
 */

public class TreinoHibernate implements TreinoDao {

   
    
    public TreinoHibernate() {}

    
    @Override
    public void adiciona(Treino treino) {
        EntityManager em = JPAManager.getInstance().getEntityManager();
        EntityTransaction t = em.getTransaction();

        try {
            t.begin();
            em.persist(treino);
            em.flush();
            t.commit();
        } catch (Exception e) {
            System.out.println("Erro ao adicionar o treino!!");
            t.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public Treino recuperar(int codigo) {
        EntityManager em = JPAManager.getInstance().getEntityManager();
        try {

            return (Treino) em.createQuery("From Treino Where codigo=" + codigo).getResultList().get(0);
        }catch(Exception e){
            System.out.println("Erro ao recuperar Treino pelo codigo!!");
            return null;
        } finally {
            //Fechamos a sessão
            em.close();
        }
    }
    
    
    @Override
    
    public void alterar(Treino treino) {
         EntityManager em = JPAManager.getInstance().getEntityManager();
        EntityTransaction t = em.getTransaction();

       try {
            t.begin();
            em.merge(treino);
            em.flush();
            t.commit();
        } catch (Exception e) {
            System.out.println("Erro ao alterar o treino!!");
            t.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    
     @Override
    public void deletar(Treino treino) {
        EntityManager em = JPAManager.getInstance().getEntityManager();
        EntityTransaction t = em.getTransaction();

        try {
            t.begin();
            em.remove(treino);
            t.commit();
        } catch (Exception e) {
            System.out.println("Erro ao deletar o treino");
            t.rollback();

        } finally {
            em.close();
        }
    }


    
     @Override
    public Treino recuperarNome(String nome) {
        EntityManager em = JPAManager.getInstance().getEntityManager();
        
        try {

            return (Treino) em.createQuery("From Treino Where nome='" + nome + "'").getResultList().get(0);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Nome do treino não encontrado!!");
            return null;

        } finally {
            //Fechamos a sessão
            em.close();
        }
    }

    @Override
    public List<Treino> recuperarTodos() {
        EntityManager em = JPAManager.getInstance().getEntityManager();
        List<Treino> treinos = new ArrayList();
        try {

            treinos = em.createQuery("FROM Treino").getResultList();
        } catch (Exception e) {
            System.out.println("Erro ao recuperar a lista de Treinos");
        } finally {
            em.close();
        }

        return treinos;

    }
    
}
