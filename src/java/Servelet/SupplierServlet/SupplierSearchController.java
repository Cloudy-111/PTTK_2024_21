/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Servelet.SupplierServlet;

import DAO.SupplierDAO226;
import Model.Supplier226;
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
public class SupplierSearchController extends HttpServlet {
    private SupplierDAO226 suppDAO;
    
    public SupplierSearchController(){
        suppDAO = new SupplierDAO226();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        
        try {
            List<Supplier226> suppList = suppDAO.getSupplierByKeyword(keyword);
            System.out.println("Supplier Search List Result: " + suppList);
            
            request.setAttribute("suppList", suppList);
            request.getRequestDispatcher("/Pages/gdListSupplier226.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error");
        }
    }
}
