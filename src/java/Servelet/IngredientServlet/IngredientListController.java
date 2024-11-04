/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Servelet.IngredientServlet;

import DAO.IngredientDAO226;
import Model.Ingredient226;
import Model.PurchaseBill226;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author Tuan Diep
 */
//@WebServlet(name="IngredientListController", urlPatterns={"/list_ingredient"})
public class IngredientListController extends HttpServlet {
    private IngredientDAO226 ingreDAO;
    
    public IngredientListController(){
        ingreDAO = new IngredientDAO226();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
            List<Ingredient226> ingList = ingreDAO.getAllIngredient226();
            System.out.println("Ingredient List in Controller: " + ingList);
            
            HttpSession session = request.getSession();
            PurchaseBill226 pb = (PurchaseBill226) session.getAttribute("purchaseBill");
            
            request.setAttribute("purchaseBill", pb);
            request.setAttribute("ingList", ingList);
            request.getRequestDispatcher("/Pages/gdListIngredient226.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error");
        }
    } 
}
