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
import model.DAO.ProfessorDao;
import model.Professor;


/**
 *
 * @author $Carlos Cordeiro <carloscordeiroconsultor@gmail.com>
 */


public class ProfessorHibernate implements ProfessorDao {

 @Override
    public boolean logarProfessor(String login, String senha) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

    public ProfessorHibernate() { }

    @Override
    public void adiciona(Professor professor) {
        EntityManager em = JPAManager.getInstance().getEntityManager();
        EntityTransaction t = em.getTransaction();

        try {
            t.begin();
            em.persist(professor);
            em.flush();
            t.commit();
        } catch (Exception e) {
            System.out.println("Erro ao adicionar o professor!!");
            t.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public Professor recuperar(int codigo) {
        EntityManager em = JPAManager.getInstance().getEntityManager();
        try {

            return (Professor) em.createQuery("From Professor Where codigo=" + codigo).getResultList().get(0);
        }catch(Exception e){
            System.out.println("Erro ao recuperar professor pelo codigo!!");
            return null;
        } finally {
            //Fechamos a sessão
            em.close();
        }
    }

    @Override
    public void alterar(Professor professor) {

        EntityManager em = JPAManager.getInstance().getEntityManager();
        EntityTransaction t = em.getTransaction();

        try {
            t.begin();
            em.merge(professor);
            em.flush();
            t.commit();
        } catch (Exception e) {
            System.out.println("Erro ao alterar o professor!!");
            t.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public void deletar(Professor professor) {
        EntityManager em = JPAManager.getInstance().getEntityManager();
        EntityTransaction t = em.getTransaction();

        try {
            t.begin();
            em.remove(professor);
            t.commit();
        } catch (Exception e) {
            System.out.println("Erro ao deletar o Professor");
            t.rollback();

        } finally {
            em.close();
        }
    }

    @Override
    public List<Professor> recuperarTodos() {
        EntityManager em = JPAManager.getInstance().getEntityManager();
        List<Professor> professors = new ArrayList();
        try {

            professors = em.createQuery("FROM Professor").getResultList();
        } catch (Exception e) {
            System.out.println("Erro ao recuperar a lista de professors");
        } finally {
            em.close();
        }

        return professors;

    }

    @Override
    public Professor recuperarCpf(String cpf) {
        EntityManager em = JPAManager.getInstance().getEntityManager();
        
        try {

            return (Professor) em.createQuery("From Professor Where cpf='" + cpf + "'").getResultList().get(0);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("CPF do professor não encontrado!!");
            return null;

        } finally {
            //Fechamos a sessão
            em.close();
        }
    }
}
