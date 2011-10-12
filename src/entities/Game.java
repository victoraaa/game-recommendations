/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 *
 * @author rodrigoruiz
 */
@Entity
public class Game {
    @Id@GeneratedValue
    private int id;
    private String name;
    @ManyToMany
    private List<Category> categories;
    @ManyToMany
    private List<Game> similarGames;
    
    public Game() {
        
    }
    
    public Game(int id, String name, Category... categories) {
        this.id = id;
        this.name = name;
        this.categories = Arrays.asList(categories);
        this.similarGames = new ArrayList<Game>();
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public List<Category> getCategories() {
        return categories;
    }
    
    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public List<Game> getSimilarGames() {
        return similarGames;
    }
    
    public void setSimilarGames(List<Game> similarGames) {
        this.similarGames = similarGames;
    }
}
