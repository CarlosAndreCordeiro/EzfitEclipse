package model.Hibernate;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import model.Aluno;
import model.DAO.AlunoDao;

public class AlunoHibernate implements AlunoDao {

    @Override
    public boolean logarAluno(String login, String senha) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

    public AlunoHibernate() { }

    @Override
    public void adiciona(Aluno aluno) {
        EntityManager em = JPAManager.getInstance().getEntityManager();
        EntityTransaction t = em.getTransaction();

        try {
            t.begin();
            em.persist(aluno);
            em.flush();
            t.commit();
        } catch (Exception e) {
            System.out.println("Erro ao adicionar o aluno!!");
            t.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public Aluno recuperar(int codigo) {
        EntityManager em = JPAManager.getInstance().getEntityManager();
        try {

            return (Aluno) em.createQuery("From Aluno Where codigo=" + codigo).getResultList().get(0);
        }catch(Exception e){
            System.out.println("Erro ao recuperar aluno pelo codigo!!");
            return null;
        } finally {
            //Fechamos a sessão
            em.close();
        }
    }

    @Override
    public void alterar(Aluno aluno) {

        EntityManager em = JPAManager.getInstance().getEntityManager();
        EntityTransaction t = em.getTransaction();

        try {
            t.begin();
            em.merge(aluno);
            em.flush();
            t.commit();
        } catch (Exception e) {
            System.out.println("Erro ao alterar o aluno!!");
            t.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public void deletar(Aluno aluno) {
        EntityManager em = JPAManager.getInstance().getEntityManager();
        EntityTransaction t = em.getTransaction();

        try {
            t.begin();
            em.remove(aluno);
            t.commit();
        } catch (Exception e) {
            System.out.println("Erro ao deletar o Aluno");
            t.rollback();

        } finally {
            em.close();
        }
    }

    @Override
    public List<Aluno> recuperarTodos() {
        EntityManager em = JPAManager.getInstance().getEntityManager();
        List<Aluno> alunos = new ArrayList();
        try {

            alunos = em.createQuery("FROM Aluno").getResultList();
        } catch (Exception e) {
            System.out.println("Erro ao recuperar a lista de alunos");
        } finally {
            em.close();
        }

        return alunos;

    }

    @Override
    public Aluno recuperarCpf(String cpf) {
        EntityManager em = JPAManager.getInstance().getEntityManager();
        
        try {

            return (Aluno) em.createQuery("From Aluno Where cpf='" + cpf + "'").getResultList().get(0);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("CPF do aluno não encontrado!!");
            return null;

        } finally {
            //Fechamos a sessão
            em.close();
        }
    }
}
