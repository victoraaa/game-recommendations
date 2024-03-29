/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Category;
import entities.CategoryDAO;
import entities.CategoryDAOJPA;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Luisa
 */
//Atenção! Esse servlet é o usado para a página inicial, pra saber pra onde manda o cara
public class IsLoggedController extends HttpServlet {

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
        // check to see if a session has already been created for this user
      //   don't create a new session yet.
      HttpSession session = request.getSession(false);
      String requestedPage = request.getServletPath();
      String destinationURL = requestedPage;
      if ( session != null) {
         // retrieve authentication parameter from the session
         Boolean isLogged = (Boolean) session.getAttribute("Logged?"); //vê se o usuário está logado
         // if the user is not authenticated
         if (isLogged==null || !isLogged.booleanValue() ) {
            // process the unauthenticated request
            destinationURL = "login.jsp";
         }
         else{
             CategoryDAO c=new CategoryDAOJPA();
             List<Category> categories =c.getAllCategories(); 
             request.getSession().setAttribute("categorias", categories);
             destinationURL = "inputOfCategories.jsp"; //Fazer ele verificar se o usuário já tem coisas preferidas ou não e decidir pra qual página mandar
         }
      }
      else // the session does not exist
      {
         // therefore the user is not authenticated
         // process the unauthenticated request
         destinationURL= "login.jsp";
      }
   
    
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
