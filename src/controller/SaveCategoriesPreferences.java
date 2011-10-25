/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Category;
import entities.CategoryDAO;
import entities.CategoryDAOJPA;
import entities.Game;
import entities.User;
import entities.UserDAO;
import entities.UserDAOJPA;
import java.io.IOException;
import java.util.ArrayList;
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
public class SaveCategoriesPreferences extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserDAO userDAO=new UserDAOJPA();
        CategoryDAO categoryDAO=new CategoryDAOJPA();
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        User user=(User) session.getAttribute("user");
        String[] categoriesId=request.getParameterValues("categories");
        List <Category> categories=new ArrayList<Category>();
        for(int i=0; i<categoriesId.length; i++){
            categories.add(categoryDAO.getCategoryById(Integer.parseInt(categoriesId[i])));
        }
        user.setCategories(categories);
        userDAO.merge(user);
        //pegar o list de games pelo método de recomendação
        //este é apenas um teste
        List<Game> games=new ArrayList<Game>();
        Game game1=new Game();
        game1.setName("game1");
        Game game2=new Game();
        game2.setName("game2");
        games.add(game1);
        games.add(game2);
        request.getSession().setAttribute("games", games);
        String destinationURL ="outputOfRecommendations.jsp";
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

