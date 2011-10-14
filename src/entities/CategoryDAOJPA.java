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
public class CategoryDAOJPA implements CategoryDAO {

    @Override
    public void insert(Category c) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(c);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void merge(Category c) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        em.merge(c);
        em.getTransaction().commit();
        em.close();
    }


    @Override
    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<Category>();
        EntityManager em = JPAUtil.getEntityManager();
        Query query = em.createQuery("SELECT c FROM Category c");
        categories = query.getResultList();
        em.close();
        return categories;
    }

    @Override
    public List<Game> getGames(int id) {
        List<Game> games = new ArrayList<Game>();
        EntityManager em = JPAUtil.getEntityManager();
        Query query = em.createQuery("SELECT c.games FROM Category c WHERE c.id=:id"); 
        query.setParameter("id", id);
        games = query.getResultList();
        em.close();
        return games;
    }

    @Override
    public Category getCategoryByName(String name) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

   

  

    
}
