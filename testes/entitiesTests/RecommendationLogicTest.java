/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entitiesTests;

import database.Database;
import java.util.*;
import entities.*;
import org.junit.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author rodrigoruiz
 */
public class RecommendationLogicTest {
    User user;
    Database databaseMock;
    
    public RecommendationLogicTest() {
        
    }
    
    @BeforeClass
    public static void setUpClass() throws Exception {
        
    }
    
    @AfterClass
    public static void tearDownClass() throws Exception {
        
    }
    
    @Before
    public void setUp() {
        databaseMock = mock(Database.class);
        
        Category c1 = new Category(1, "rpg");
        Category c2 = new Category(2, "strategy");
        Category c3 = new Category(3, "first person shooter");
        
        Game g1 = new Game(1, "world of warcraft", c1);
        Game g2 = new Game(2, "diablo 2", c1);
        Game g3 = new Game(3, "warcraft 3", c2);
        Game g4 = new Game(4, "age of empires", c2);
        Game g5 = new Game(5, "uberstrike", c3);
        Game g6 = new Game(6, "counter strike", c3);
        Game g7 = new Game(7, "guild wars 2", c1, c3);
        Game g8 = new Game(8, "dota", c1, c2);
        
        c1.setGames(Arrays.asList(g2, g1, g8, g7));
        c2.setGames(Arrays.asList(g3, g4));
        c3.setGames(Arrays.asList(g5, g7, g6));
        
        g1.setSimilarGames(Arrays.asList(g3, g2, g7));
        g2.setSimilarGames(Arrays.asList(g1, g7));
        g3.setSimilarGames(Arrays.asList(g8, g1, g4));
        g4.setSimilarGames(Arrays.asList(g3));
        g5.setSimilarGames(Arrays.asList(g6));
        g6.setSimilarGames(Arrays.asList(g5));
        g7.setSimilarGames(Arrays.asList(g2, g1));
        g8.setSimilarGames(Arrays.asList(g3));
        
        when(databaseMock.gameByName("world of warcraft")).thenReturn(g1);
        when(databaseMock.gameByName("diablo 2")).thenReturn(g2);
        when(databaseMock.gameByName("warcraft 3")).thenReturn(g3);
        when(databaseMock.gameByName("age of empires")).thenReturn(g4);
        when(databaseMock.gameByName("uberstrike")).thenReturn(g5);
        when(databaseMock.gameByName("counter strike")).thenReturn(g6);
        when(databaseMock.gameByName("guild wars 2")).thenReturn(g7);
        when(databaseMock.gameByName("dota")).thenReturn(g8);
        
        when(databaseMock.categoryByName("rpg")).thenReturn(c1);
        when(databaseMock.categoryByName("strategy")).thenReturn(c2);
        when(databaseMock.categoryByName("first person shooter")).thenReturn(c3);
        
        when(databaseMock.allGames()).thenReturn(Arrays.asList(g1, g4, g7, g2, g5, g6, g3, g8));
        //when(databaseMock.allCategories()).thenReturn(Arrays.asList(c2, c1, c3));
        
        user = new User(databaseMock);
    }
    
    @After
    public void tearDown() {
        
    }
    
    @Test
    public void emptyUser() {
        assertEquals(databaseMock.allGames(), user.recommendedGames());
    }
    
    @Test
    public void recommendationByGames() {
        List<Game> userGames = new ArrayList<Game>();
        userGames.add(databaseMock.gameByName("world of warcraft"));
        user.setGames(userGames);
        
        // must be in the right order (compare id)
        List<Game> expectedRecommendation = new ArrayList<Game>();
        expectedRecommendation.add(databaseMock.gameByName("diablo 2"));
        expectedRecommendation.add(databaseMock.gameByName("warcraft 3"));
        expectedRecommendation.add(databaseMock.gameByName("guild wars 2"));
        
        assertEquals(expectedRecommendation, user.recommendedGames());
    }
    
    @Test
    public void recommendationByCategories() {
        List<Category> userCategories = new ArrayList<Category>();
        userCategories.add(databaseMock.categoryByName("rpg"));
        userCategories.add(databaseMock.categoryByName("strategy"));
        user.setCategories(userCategories);
        
        // must be in the right order (compare id)
        List<Game> expectedRecommendation = new ArrayList<Game>();
        expectedRecommendation.add(databaseMock.gameByName("world of warcraft"));
        expectedRecommendation.add(databaseMock.gameByName("diablo 2"));
        expectedRecommendation.add(databaseMock.gameByName("warcraft 3"));
        expectedRecommendation.add(databaseMock.gameByName("age of empires"));
        expectedRecommendation.add(databaseMock.gameByName("guild wars 2"));
        expectedRecommendation.add(databaseMock.gameByName("dota"));
        
        assertEquals(expectedRecommendation, user.recommendedGames());
    }
    
    @Test
    public void recommendationByGameAndCategories() {
        List<Game> userGames = new ArrayList<Game>();
        userGames.add(databaseMock.gameByName("world of warcraft"));
        user.setGames(userGames);
        
        List<Category> userCategories = new ArrayList<Category>();
        userCategories.add(databaseMock.categoryByName("rpg"));
        user.setCategories(userCategories);
        
        // must be in the right order (compare id)
        List<Game> expectedRecommendation = new ArrayList<Game>();
        expectedRecommendation.add(databaseMock.gameByName("world of warcraft"));
        expectedRecommendation.add(databaseMock.gameByName("diablo 2"));
        expectedRecommendation.add(databaseMock.gameByName("warcraft 3"));
        expectedRecommendation.add(databaseMock.gameByName("guild wars 2"));
        expectedRecommendation.add(databaseMock.gameByName("dota"));
        
        assertEquals(expectedRecommendation, user.recommendedGames());
    }
}