/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.List;

/**
 *
 * @author Luisa
 */
public interface UserDAO {
    public void insert(User u);
    public void merge(User u);
    public User findByUsername(String username);
    public List<Game> getGames(int userId); //coloquem nomes mais explicativos para variáveis
    public List<Category> getCategories(int userId);
}
