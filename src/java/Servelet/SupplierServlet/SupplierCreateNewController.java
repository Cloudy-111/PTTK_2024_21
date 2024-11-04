/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Servelet.SupplierServlet;

import DAO.PurchaseBillDAO226;
import DAO.SupplierDAO226;
import Model.Member226;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author Tuan Diep
 */
//@WebServlet(name="SupplierCreateNewController", urlPatterns={"/create_new_supplier"})
public class SupplierCreateNewController extends HttpServlet {
    private SupplierDAO226 suppDAO;
    private PurchaseBillDAO226 purchaseBillDAO226;
    
    public SupplierCreateNewController(){
        suppDAO = new SupplierDAO226();
        purchaseBillDAO226 = new PurchaseBillDAO226();
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        int idWarehouser = Integer.parseInt(request.getParameter("idWarehouser"));
        String nameSupplier = request.getParameter("name_supplier");
        
        try {
            int idCreateNewSupplier = suppDAO.CreateNewSupplier(nameSupplier, idWarehouser);
            if (idCreateNewSupplier > 0) {
                response.getWriter().write("{\"status\":\"success\", \"message\":\"Supplier created successfully.\"}");
            } else {
                response.getWriter().write("{\"status\":\"error\", \"message\":\"Failed to create new Supplier.\"}");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("{\"status\":\"error\", \"message\":\"An error occurred while creating new Supplier.\"}");
        }
    }
}
