/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.ArrayList;
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
public class Category {
    @Id@GeneratedValue
    private int idCategory;
    private String name;
    @ManyToMany
    private List<Game> games;
    
    public Category() {
        games=new ArrayList<Game>();
    }
    
    public Category(int id, String name) {
        games=new ArrayList<Game>();
        this.idCategory = id;
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
        return idCategory;
    }
    
    public void setId(int id) {
        this.idCategory = id;
    }
}
