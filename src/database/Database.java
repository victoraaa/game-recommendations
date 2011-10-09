/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import entities.*;
import java.util.List;

/**
 *
 * @author rodrigoruiz
 */
public interface Database {
    public List<Game> allGames();
    public List<Category> allCategories();
    public Game gameByName(String name);
    public Category categoryByName(String name);
}
