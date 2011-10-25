/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.List;

/**
 *
 * @author rodrigoruiz
 */
public interface GameLinkDAO {
    public List<GameLink> getGameLinksWithGame(Game game);
    public void insertGameLink(GameLink gl);
    public void addLink ();
}
