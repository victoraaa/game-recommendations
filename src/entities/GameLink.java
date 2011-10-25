/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author rodrigoruiz
 */
@Entity
public class GameLink {
    @Id
    @OneToOne
    private Game game1;
    @Id
    @OneToOne
    private Game game2;
    private int linkCount; // number of users that have both games
    
    public GameLink() {
        
    }
    
    public GameLink(Game game1, Game game2, int linkCount) {
        this.game1 = game1;
        this.game2 = game2;
        this.linkCount = linkCount;
    }
    
    public Game getGame1() {
        return game1;
    }
    
    public void setGame1(Game game1) {
        this.game1 = game1;
    }
    
    public Game getGame2() {
        return game2;
    }
    
    public void setGame2(Game game2) {
        this.game2 = game2;
    }
    
    public int getLinkCount() {
        return linkCount;
    }
    
    public void setLinkCount(int linkCount) {
        this.linkCount = linkCount;
    }
}
