package entitiesTests;


import entities.User;
import entities.User;
import entities.UserDAO;
import entities.UserDAO;
import entities.UserDAOJPA;
import entities.UserDAOJPA;
import org.junit.Test;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Luisa
 */
public class TesteBanco {
    @Test
    public void teste(){
        User luisa=new User();
        //luisa.setId(1);
        luisa.setPassword("luisa");
        luisa.setUsername("lu");
        UserDAO userDAO=new UserDAOJPA();
        userDAO.insert(luisa);
    }
}
