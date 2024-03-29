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
public interface CategoryDAO {
    public void insert(Category c);
    public void merge(Category c);
    public List<Category> getAllCategories();
    public List<Game> getGames(int categoryId); //coloquem nomes mais explicativos para variáveis
    public Category getCategoryByName(String name);
    public Category getCategoryById(int categoryId);
}
