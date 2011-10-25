/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import entities.Game;
import entities.GameDAO;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import util.JPAUtil;

/**
 *
 * @author Luisa
 */
public class GameDAOJPA implements GameDAO{

    @Override
    public Game getGameByName(String name) {
        Game game=null;
        EntityManager em = JPAUtil.getEntityManager();
        Query query = em.createQuery("SELECT g FROM Game g WHERE g.name=:name"); 
        query.setParameter("name", name);
        try {
            game= (Game) query.getSingleResult();
        } catch (Exception e) {
        }
        return game;
    }

    @Override
    public List<Game> getAllGames() {
        List<Game> games = new ArrayList<Game>();
        EntityManager em = JPAUtil.getEntityManager();
        Query query = em.createQuery("SELECT g FROM Game g");
        games = query.getResultList();
        em.close();
        return games;
    }

  
    
}
