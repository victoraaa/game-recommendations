/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import database.Database;
import java.util.*;

/**
 *
 * @author rodrigoruiz
 */
public class User {
    private int id;
    private List<Category> categories;
    private List<Game> games;
    
    private Database database;
    
    public User(Database database) {
        this.database = database;
        
        this.setCategories(new ArrayList<Category>());
        this.setGames(new ArrayList<Game>());
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public List<Category> getCategories() {
        return categories;
    }
    
    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
    
    public List<Game> getGames() {
        return games;
    }
    
    public void setGames(List<Game> games) {
        this.games = games;
    }
    
    private void sortRecommendedGames(List<Game> recommendedGames) {
        Collections.sort(recommendedGames, new Comparator(){
            @Override
            public int compare(Object o1, Object o2) {
                Game g1 = (Game) o1;
                Game g2 = (Game) o2;
                
                if(g1.getId() > g2.getId())
                    return 1;
                if(g1.getId() < g2.getId())
                    return -1;
                
                return 0;
            }
        });
    }
    
    public List<Game> recommendedGames() {
        List<Game> recommendedGames = new ArrayList<Game>();
        
        if(this.games.isEmpty() && this.categories.isEmpty())
            recommendedGames = this.database.allGames();
        
        if(!this.games.isEmpty())
            for(Game game : this.games)
                for(Game similarGame : game.getSimilarGames())
                    if(!recommendedGames.contains(similarGame))
                        recommendedGames.add(similarGame);
        
        if(!this.categories.isEmpty())
            for(Category category : this.categories)
                for(Game game : category.getGames())
                    if(!recommendedGames.contains(game))
                        recommendedGames.add(game);
        
        this.sortRecommendedGames(recommendedGames);
        
        return recommendedGames;
    }
}
