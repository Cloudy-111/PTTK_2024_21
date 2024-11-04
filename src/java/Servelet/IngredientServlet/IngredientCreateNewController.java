/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Servelet.IngredientServlet;

import DAO.IngredientDAO226;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Tuan Diep
 */
//@WebServlet(name="IngredientCreateNewController", urlPatterns={"/create_new_ingredient"})
public class IngredientCreateNewController extends HttpServlet {
    private IngredientDAO226 ingreDAO;
    
    public IngredientCreateNewController(){
        ingreDAO = new IngredientDAO226();
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String nameIngredient = request.getParameter("name_ingredient");
        double price = Double.parseDouble(request.getParameter("price"));
        try {
            int idCreateNewIngredient = ingreDAO.CreateNewIngredient(nameIngredient, price);
            if(idCreateNewIngredient > 0){
                response.getWriter().write("{\"status\":\"success\", \"message\":\"Ingredient created successfully.\"}");
            } else {
                response.getWriter().write("{\"status\":\"error\", \"message\":\"Failed to create new Ingredient.\"}");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("{\"status\":\"error\", \"message\":\"An error occurred while creating new Ingredient.\"}");
        }
    }
}
