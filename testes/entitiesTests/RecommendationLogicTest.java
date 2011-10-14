/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entitiesTests;

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
    CategoryDAO categoryDAOMock;
    GameDAO gameDAOMock;
    GameLinkDAO gameLinkDAOMock;
    
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
        categoryDAOMock = mock(CategoryDAO.class);
        gameDAOMock = mock(GameDAO.class);
        gameLinkDAOMock = mock(GameLinkDAO.class);
        user = new User();
        
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
        
        GameLink l1 = new GameLink(g1, g2, 10);
        GameLink l2 = new GameLink(g1, g3, 5);
        GameLink l3 = new GameLink(g1, g7, 8);
        GameLink l4 = new GameLink(g2, g3, 3);
        GameLink l5 = new GameLink(g2, g6, 2);
        GameLink l6 = new GameLink(g2, g7, 12);
        GameLink l7 = new GameLink(g3, g4, 11);
        GameLink l8 = new GameLink(g3, g8, 6);
        GameLink l9 = new GameLink(g4, g7, 2);
        GameLink l10 = new GameLink(g5, g6, 20);
        
        when(gameLinkDAOMock.getGameLinksWithGame(g1)).thenReturn(Arrays.asList(l1, l2, l3));
        when(gameLinkDAOMock.getGameLinksWithGame(g2)).thenReturn(Arrays.asList(l1, l4, l5, l6));
        when(gameLinkDAOMock.getGameLinksWithGame(g3)).thenReturn(Arrays.asList(l2, l4, l7, l8));
        when(gameLinkDAOMock.getGameLinksWithGame(g4)).thenReturn(Arrays.asList(l7));
        when(gameLinkDAOMock.getGameLinksWithGame(g5)).thenReturn(Arrays.asList(l10));
        when(gameLinkDAOMock.getGameLinksWithGame(g6)).thenReturn(Arrays.asList(l5, l10));
        when(gameLinkDAOMock.getGameLinksWithGame(g7)).thenReturn(Arrays.asList(l3, l6, l9));
        when(gameLinkDAOMock.getGameLinksWithGame(g8)).thenReturn(Arrays.asList(l8));
        
        
        when(gameDAOMock.getGameByName("world of warcraft")).thenReturn(g1);
        when(gameDAOMock.getGameByName("diablo 2")).thenReturn(g2);
        when(gameDAOMock.getGameByName("warcraft 3")).thenReturn(g3);
        when(gameDAOMock.getGameByName("age of empires")).thenReturn(g4);
        when(gameDAOMock.getGameByName("uberstrike")).thenReturn(g5);
        when(gameDAOMock.getGameByName("counter strike")).thenReturn(g6);
        when(gameDAOMock.getGameByName("guild wars 2")).thenReturn(g7);
        when(gameDAOMock.getGameByName("dota")).thenReturn(g8);
        
        when(categoryDAOMock.getCategoryByName("rpg")).thenReturn(c1);
        when(categoryDAOMock.getCategoryByName("strategy")).thenReturn(c2);
        when(categoryDAOMock.getCategoryByName("first person shooter")).thenReturn(c3);
        
        when(gameDAOMock.getAllGames()).thenReturn(Arrays.asList(g1, g4, g7, g2, g5, g6, g3, g8));
        //when(categoryDAOMock.getAllCategories()).thenReturn(Arrays.asList(c2, c1, c3));
    }
    
    @After
    public void tearDown() {
        
    }
    
    @Test
    public void emptyUser() {
        assertEquals(gameDAOMock.getAllGames(), user.getRecommendedGames(gameLinkDAOMock, gameDAOMock, 100));
    }
    
    @Test
    public void emptyUserGettingLessGames() {
        assertEquals(gameDAOMock.getAllGames().subList(0, 5), user.getRecommendedGames(gameLinkDAOMock, gameDAOMock, 5));
    }
    
    @Test
    public void emptyUserGettingNoGames() {
        assertEquals(new ArrayList<Game>(), user.getRecommendedGames(gameLinkDAOMock, gameDAOMock, 0));
    }
    
    @Test
    public void recommendationByGamesWithOneGame() {
        List<Game> userGames = new ArrayList<Game>();
        userGames.add(gameDAOMock.getGameByName("world of warcraft"));
        user.setGames(userGames);
        
        // must be in the right order (compare linkCount)
        List<Game> expectedRecommendation = new ArrayList<Game>();
        expectedRecommendation.add(gameDAOMock.getGameByName("diablo 2"));
        expectedRecommendation.add(gameDAOMock.getGameByName("guild wars 2"));
        expectedRecommendation.add(gameDAOMock.getGameByName("warcraft 3"));
        
        assertEquals(expectedRecommendation, user.getRecommendedGames(gameLinkDAOMock, gameDAOMock, 100));
    }
    
    @Test
    public void recommendationByGamesWithOneGameGettingLessGames() {
        List<Game> userGames = new ArrayList<Game>();
        userGames.add(gameDAOMock.getGameByName("world of warcraft"));
        user.setGames(userGames);
        
        // must be in the right order (compare linkCount)
        List<Game> expectedRecommendation = new ArrayList<Game>();
        expectedRecommendation.add(gameDAOMock.getGameByName("diablo 2"));
        expectedRecommendation.add(gameDAOMock.getGameByName("guild wars 2"));
        
        assertEquals(expectedRecommendation, user.getRecommendedGames(gameLinkDAOMock, gameDAOMock, 2));
    }
    
    @Test
    public void recommendationByGamesWithTwoGames() {
        List<Game> userGames = new ArrayList<Game>();
        userGames.add(gameDAOMock.getGameByName("world of warcraft"));
        userGames.add(gameDAOMock.getGameByName("guild wars 2"));
        userGames.add(gameDAOMock.getGameByName("counter strike"));
        user.setGames(userGames);
        
        // must be in the right order (compare linkCount sum of all user games)
        List<Game> expectedRecommendation = new ArrayList<Game>();
        expectedRecommendation.add(gameDAOMock.getGameByName("diablo 2"));
        expectedRecommendation.add(gameDAOMock.getGameByName("uberstrike"));
        expectedRecommendation.add(gameDAOMock.getGameByName("world of warcraft"));
        expectedRecommendation.add(gameDAOMock.getGameByName("guild wars 2"));
        expectedRecommendation.add(gameDAOMock.getGameByName("warcraft 3"));
        expectedRecommendation.add(gameDAOMock.getGameByName("age of empires"));
        
        assertEquals(expectedRecommendation, user.getRecommendedGames(gameLinkDAOMock, gameDAOMock, 100));
    }
    
    @Test
    public void recommendationByGamesWithTwoGamesGettingLessGames() {
        List<Game> userGames = new ArrayList<Game>();
        userGames.add(gameDAOMock.getGameByName("world of warcraft"));
        userGames.add(gameDAOMock.getGameByName("guild wars 2"));
        userGames.add(gameDAOMock.getGameByName("counter strike"));
        user.setGames(userGames);
        
        // must be in the right order (compare linkCount sum of all user games)
        List<Game> expectedRecommendation = new ArrayList<Game>();
        expectedRecommendation.add(gameDAOMock.getGameByName("diablo 2"));
        expectedRecommendation.add(gameDAOMock.getGameByName("uberstrike"));
        expectedRecommendation.add(gameDAOMock.getGameByName("world of warcraft"));
        
        assertEquals(expectedRecommendation, user.getRecommendedGames(gameLinkDAOMock, gameDAOMock, 3));
    }
    
    @Test
    public void recommendationByCategories() {
        List<Category> userCategories = new ArrayList<Category>();
        userCategories.add(categoryDAOMock.getCategoryByName("rpg"));
        userCategories.add(categoryDAOMock.getCategoryByName("strategy"));
        user.setCategories(userCategories);
        
        // must be in the right order (compare id)
        List<Game> expectedRecommendation = new ArrayList<Game>();
        expectedRecommendation.add(gameDAOMock.getGameByName("world of warcraft"));
        expectedRecommendation.add(gameDAOMock.getGameByName("diablo 2"));
        expectedRecommendation.add(gameDAOMock.getGameByName("warcraft 3"));
        expectedRecommendation.add(gameDAOMock.getGameByName("age of empires"));
        expectedRecommendation.add(gameDAOMock.getGameByName("guild wars 2"));
        expectedRecommendation.add(gameDAOMock.getGameByName("dota"));
        
        assertEquals(expectedRecommendation, user.getRecommendedGames(gameLinkDAOMock, gameDAOMock, 10));
    }
    
    @Test
    public void recommendationByCategoriesGettingLessGames() {
        List<Category> userCategories = new ArrayList<Category>();
        userCategories.add(categoryDAOMock.getCategoryByName("rpg"));
        userCategories.add(categoryDAOMock.getCategoryByName("strategy"));
        user.setCategories(userCategories);
        
        // must be in the right order (compare id)
        List<Game> expectedRecommendation = new ArrayList<Game>();
        expectedRecommendation.add(gameDAOMock.getGameByName("world of warcraft"));
        expectedRecommendation.add(gameDAOMock.getGameByName("diablo 2"));
        expectedRecommendation.add(gameDAOMock.getGameByName("warcraft 3"));
        expectedRecommendation.add(gameDAOMock.getGameByName("age of empires"));
        expectedRecommendation.add(gameDAOMock.getGameByName("guild wars 2"));
        
        assertEquals(expectedRecommendation, user.getRecommendedGames(gameLinkDAOMock, gameDAOMock, 5));
    }
    
    @Test
    public void recommendationByGameAndCategories() {
        List<Game> userGames = new ArrayList<Game>();
        userGames.add(gameDAOMock.getGameByName("world of warcraft"));
        user.setGames(userGames);
        
        List<Category> userCategories = new ArrayList<Category>();
        userCategories.add(categoryDAOMock.getCategoryByName("rpg"));
        user.setCategories(userCategories);
        
        // must be in the right order (compare linkCount and then id)
        List<Game> expectedRecommendation = new ArrayList<Game>();
        expectedRecommendation.add(gameDAOMock.getGameByName("diablo 2"));
        expectedRecommendation.add(gameDAOMock.getGameByName("guild wars 2"));
        expectedRecommendation.add(gameDAOMock.getGameByName("warcraft 3"));
        expectedRecommendation.add(gameDAOMock.getGameByName("world of warcraft"));
        expectedRecommendation.add(gameDAOMock.getGameByName("dota"));
        
        assertEquals(expectedRecommendation, user.getRecommendedGames(gameLinkDAOMock, gameDAOMock, 10));
    }
    
    @Test
    public void recommendationByGameAndCategoriesGettingLessGames() {
        List<Game> userGames = new ArrayList<Game>();
        userGames.add(gameDAOMock.getGameByName("world of warcraft"));
        user.setGames(userGames);
        
        List<Category> userCategories = new ArrayList<Category>();
        userCategories.add(categoryDAOMock.getCategoryByName("rpg"));
        user.setCategories(userCategories);
        
        // must be in the right order (compare linkCount and then id)
        List<Game> expectedRecommendation = new ArrayList<Game>();
        expectedRecommendation.add(gameDAOMock.getGameByName("diablo 2"));
        
        assertEquals(expectedRecommendation, user.getRecommendedGames(gameLinkDAOMock, gameDAOMock, 1));
    }
}
