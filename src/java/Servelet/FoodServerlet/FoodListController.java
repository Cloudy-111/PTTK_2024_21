/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servelet.FoodServerlet;

import DAO.FoodDAO226;
import Model.Food226;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 *
 * @author Tuan Diep
 */
//@WebServlet("/food/list")
public class FoodListController extends HttpServlet {
    private FoodDAO226 foodDAO;

    public FoodListController() {
        foodDAO = new FoodDAO226();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Food226> foodList = foodDAO.getAllFood226();
            System.out.println("MatHang List in Controller: " + foodList);
            
            request.setAttribute("foodList", foodList);
            request.getRequestDispatcher("/Pages/gdSearchResult226.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error");
        }
    }
}

