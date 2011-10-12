/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import util.JPAUtil;

/**
 *
 * @author Luisa
 */
public class UserDAOJPA implements UserDAO{

    @Override
    public void insert(User u) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(u);
        em.getTransaction().commit();
        em.close();       
    }

    @Override
    public void merge(User u) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        em.merge(u);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public User findByUsername(String name) {
        EntityManager em = JPAUtil.getEntityManager();
        Query query = em.createQuery("select u from User u where u.username= :name");
        query.setParameter("name", name);
        User user=null;
        try {
            user = (User) query.getSingleResult();
        } catch (Exception e) {
        }
        return user;
    }

    @Override
    public List<Game> getGames(int id) {
        List<Game> games = new ArrayList<Game>();
        EntityManager em = JPAUtil.getEntityManager();
        Query query = em.createQuery("SELECT u.games FROM User u WHERE u.id=:id"); 
        query.setParameter("id", id);
        games = query.getResultList();
        em.close();
        return games;
    }

    @Override
    public List<Category> getCategories(int id) {
        List<Category> categories = new ArrayList<Category>();
        EntityManager em = JPAUtil.getEntityManager();
        Query query = em.createQuery("SELECT u.categories FROM User u WHERE u.id=:id"); 
        query.setParameter("id", id);
        categories = query.getResultList();
        em.close();
        return categories;
    }
    
}
