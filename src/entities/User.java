/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 *
 * @author rodrigoruiz
 */
@Entity
public class User {
    @Id@GeneratedValue
    private int id;
    @ManyToMany
    private List<Category> categories;
    @ManyToMany
    private List<Game> games;
    private String username;
    private String password;
    
    public User() {
        this.setCategories(new ArrayList<Category>());
        this.setGames(new ArrayList<Game>());
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
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
    
   private void sortRecommendedGames(List<Game> recommendedGames, final Map<Game, Integer> linkCountForGame) {
       Collections.sort(recommendedGames, new Comparator(){
           @Override
           public int compare(Object o1, Object o2) {
               Game g1 = (Game) o1;
               Game g2 = (Game) o2;
               
               if(linkCountForGame.containsKey(g1) && linkCountForGame.containsKey(g2)) {
                   if(linkCountForGame.get(g1) < linkCountForGame.get(g2))
                       return 1;
                   if(linkCountForGame.get(g1) > linkCountForGame.get(g2))
                       return -1;
               }
               if(linkCountForGame.containsKey(g1) && !linkCountForGame.containsKey(g2))
                   return -1;
               if(linkCountForGame.containsKey(g2) && !linkCountForGame.containsKey(g1))
                   return 1;
               
               if(g1.getId() > g2.getId())
                   return 1;
               if(g1.getId() < g2.getId())
                   return -1;
               
               return 0;
           }
       });
   }
   
   private void fillWithSimilarGames(List<Game> recommendedGames, Map<Game, Integer> linkCountForGame, GameLinkDAO gameLinkDAO) {
       if(!this.games.isEmpty())
           for(Game game : this.games)
               for(GameLink gameLink : gameLinkDAO.getGameLinksWithGame(game)) {
                   Game similarGame;
                   
                   if(gameLink.getGame1().equals(game))
                       similarGame = gameLink.getGame2();
                   else
                       similarGame = gameLink.getGame1();
                   
                   if(linkCountForGame.containsKey(similarGame)) {
                       Integer linkCount = linkCountForGame.get(similarGame);
                       linkCountForGame.put(similarGame, linkCount + gameLink.getLinkCount());
                   }
                   else
                       linkCountForGame.put(similarGame, new Integer(gameLink.getLinkCount()));
                   
                   if(!recommendedGames.contains(similarGame))
                       recommendedGames.add(similarGame);
               }
   }
   
   private void fillCategoryGames(List<Game> recommendedGames) {
       if(!this.categories.isEmpty())
           for(Category category : this.categories)
               for(Game game : category.getGames())
                   if(!recommendedGames.contains(game))
                       recommendedGames.add(game);
   }
   
   public List<Game> getRecommendedGames(GameLinkDAO gameLinkDAO, GameDAO gameDAO, int numberOfGames) {
       List<Game> recommendedGames = new ArrayList<Game>();
       Map<Game, Integer> linkCountForGame = new HashMap<Game, Integer>();
       
       if(this.games.isEmpty() && this.categories.isEmpty())
           recommendedGames = gameDAO.getAllGames();
        
       this.fillWithSimilarGames(recommendedGames, linkCountForGame, gameLinkDAO);
       
       this.fillCategoryGames(recommendedGames);
       
       this.sortRecommendedGames(recommendedGames, linkCountForGame);
       
       if(recommendedGames.size() < numberOfGames)
           return recommendedGames;
       
       return recommendedGames.subList(0, numberOfGames);
   }
}
