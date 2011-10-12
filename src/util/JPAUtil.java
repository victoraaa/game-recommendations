/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


/**
 *
 * @author aluno
 */
public class JPAUtil {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("ExamePU");
    
    public static EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
    
}
