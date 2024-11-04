/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Servelet.IngredientServlet;

import DAO.IngredientDAO226;
import Model.Ingredient226;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author Tuan Diep
 */
//@WebServlet(name="IngredientSearchController", urlPatterns={"/search_ingredient"})
public class IngredientSearchController extends HttpServlet {
    private IngredientDAO226 ingreDAO226;
    
    public IngredientSearchController(){
        ingreDAO226 = new IngredientDAO226();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String keyword = request.getParameter(("keyword"));
        
        try {
            List<Ingredient226> ingList = ingreDAO226.getIngredientByKeyword(keyword);
            request.setAttribute("ingList", ingList);
            request.getRequestDispatcher("/Pages/gdListIngredient226.jsp").forward(request, response);
        } catch (Exception e) {
        }
    } 
}