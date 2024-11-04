/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Servelet.PurchaseBill_IngredientServlet;

import DAO.PurchaseBill_IngredientDAO226;
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
//@WebServlet(name="PurchaseBill_IngredientCreateNewController", urlPatterns={"/create_new_purchaseBill_ingredient"})
public class PurchaseBill_IngredientCreateNewController extends HttpServlet {
    private PurchaseBill_IngredientDAO226 pb_iDAO;
    
    public PurchaseBill_IngredientCreateNewController(){
        pb_iDAO = new PurchaseBill_IngredientDAO226();
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        int amountIngredient = Integer.parseInt(request.getParameter("amountIngredient"));
        int idPurchaseBill = Integer.parseInt(request.getParameter("idPurchaseBill"));
        int idIngredient = Integer.parseInt(request.getParameter("idIngredient"));
        
        try {
            int idNewPurchaseBill_Ingredient = pb_iDAO.CreateNewPurchaseBill_Ingredient(idPurchaseBill, idIngredient, amountIngredient);
            if(idNewPurchaseBill_Ingredient > 0){
                response.getWriter().write("{\"status\":\"success\", \"message\":\"PurchaseBill_Ingredient created successfully.\"}");
            } else {
                response.getWriter().write("{\"status\":\"error\", \"message\":\"Failed to create the PurchaseBill_Ingredient.\"}");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("{\"status\":\"error\", \"message\":\"An error occurred while creating the PurchaseBill_Ingredient.\"}");
        }
    }
}
