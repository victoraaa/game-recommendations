/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Category;
import entities.CategoryDAO;
import entities.CategoryDAOJPA;
import entities.User;
import entities.UserDAO;
import entities.UserDAOJPA;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Victor
 */
@WebServlet(name = "LoginController", urlPatterns = {"/Login"})
public class LoginController extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        
        HttpSession session = request.getSession(false);
        String destinationURL = "login.jsp";
       
        UserDAO userDAO = new UserDAOJPA();

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User userDB = userDAO.findByUsername(username);
    ///*
        //Caso o login esteja correto, loga e vai pra preferences.jsp; Se não, volta pra login.jsp
        if(userDB!=null){
            if(userDB.getPassword().equals(password)){
               // create a session
               session = request.getSession( true);
               // convert the boolean to a Boolean
               Boolean booleanIsAuthenticated = new Boolean(true);
               // store the boolean value to the session
               session.setAttribute("Logged?",booleanIsAuthenticated);

               session.setAttribute("user",userDB);
               CategoryDAO c=new CategoryDAOJPA();
               List<Category> categories =c.getAllCategories(); 
               request.getSession().setAttribute("categorias", categories);
               destinationURL = "inputOfCategories.jsp";  //pagina de pegar as coisas preferidas do usuario
            }
        }
 // */    
        /*
        //TIRAR ABAIXO
        //PARA TESTE:
            // create a session
               session = request.getSession( true);
               // convert the boolean to a Boolean
               Boolean booleanIsAuthenticated = new Boolean(false);
               // store the boolean value to the session
               session.setAttribute("Logged?",booleanIsAuthenticated);
               destinationURL="/Controller";
               
               //TIRAR ACIMA
         * 
         */
        try {
            request.getRequestDispatcher(destinationURL).forward(request, response);
            
        } finally {            
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
