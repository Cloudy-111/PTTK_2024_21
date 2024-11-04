/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servelet.FoodServerlet;

import DAO.FoodDAO226;
import Model.Food226;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author Tuan Diep
 */
//@WebServlet("/food/details/*")
public class FoodDetailController extends HttpServlet{
    private FoodDAO226 foodDAO;

    public FoodDetailController() {
        foodDAO = new FoodDAO226();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        String pathInfor = request.getPathInfo();
        
        if(pathInfor != null && pathInfor.length() > 1){
            try {
                int foodId = Integer.parseInt(pathInfor.substring(1));
                Food226 food = foodDAO.getFoodById(foodId);
                
                if (food != null) {
                    request.setAttribute("food", food);
                    request.getRequestDispatcher("/Pages/gdDetailFood226.jsp").forward(request, response); 
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Food not found");
                }
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid food ID");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Food ID is required");
        }
    }
}
