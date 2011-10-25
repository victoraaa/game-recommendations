/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import entities.Game;
import entities.GameLink;
import entities.GameLinkDAO;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import util.JPAUtil;

/**
 *
 * @author Luisa
 */
public class GameLinkDAOJPA implements GameLinkDAO{

    @Override
    public List<GameLink> getGameLinksWithGame(Game game) {
        List<GameLink> gameLinks = new ArrayList<GameLink>();
        EntityManager em = JPAUtil.getEntityManager();
        Query query = em.createQuery("SELECT g FROM GameLink g WHERE g.game1=:game OR g.game2=game"); 
        query.setParameter("game", game);
        gameLinks = query.getResultList();
        em.close();
        return gameLinks;
    }

    @Override
    public void insertGameLink(GameLink gl) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void addLink() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
