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
public class Category {
    private int id;
    private String name;
    private List<Game> games;
    
    public Category() {
        
    }
    
    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public List<Game> getGames() {
        return games;
    }
    
    public void setGames(List<Game> games) {
        this.games = games;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
}
