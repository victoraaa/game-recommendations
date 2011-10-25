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
public interface GameDAO {
    public Game getGameByName(String name);
    public List<Game> getAllGames();
    
}
