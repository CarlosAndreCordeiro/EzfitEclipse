/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author BobaNote
 */
public class JPAManager {
    
    private static JPAManager instance;
    private EntityManagerFactory emf;
    
    private JPAManager(){
        this.emf = Persistence.createEntityManagerFactory("ProjetoBD2PU");
    }
    
    public static JPAManager getInstance(){
        if(instance == null){
            instance = new JPAManager();
        }
        return instance;
    }
    
    public EntityManager getEntityManager(){
        return this.emf.createEntityManager();
    }
    
}
