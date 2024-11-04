/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Servelet.PurchaseBillServlet;

import DAO.PurchaseBillDAO226;
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
//@WebServlet(name="PurchaseBillCreateController", urlPatterns={"/create_purchasebill"})
public class PurchaseBillCreateController extends HttpServlet {
    private PurchaseBillDAO226 purchaseBillDAO;
    
    public PurchaseBillCreateController(){
        purchaseBillDAO = new PurchaseBillDAO226();
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        int idSupplier = Integer.parseInt(request.getParameter("idSupplier"));
        
        try {
            HttpSession session = request.getSession();
            Member226 mem = (Member226) session.getAttribute("mem");
            int idWarehouser = mem.getId();
            int idNewPurchaseBill = purchaseBillDAO.createPurchaseBill(idSupplier, idWarehouser);
            session.setAttribute("purchaseBill", purchaseBillDAO.getPurchaseBillById(idNewPurchaseBill));
            if (idNewPurchaseBill > 0) {
                response.getWriter().write("{\"status\":\"success\", \"message\":\"Purchase bill created successfully.\"}");
            } else {
                response.getWriter().write("{\"status\":\"error\", \"message\":\"Failed to create the purchase bill.\"}");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("{\"status\":\"error\", \"message\":\"An error occurred while creating the purchase bill.\"}");
        }
        
    }
}
