/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Servelet.PurchaseBillServlet;

import DAO.IngredientDAO226;
import DAO.PurchaseBillDAO226;
import DAO.PurchaseBill_IngredientDAO226;
import DAO.SupplierDAO226;
import Model.Ingredient226;
import Model.Member226;
import Model.PurchaseBill226;
import Model.PurchaseBill_Ingredient226;
import Model.Supplier226;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Tuan Diep
 */
//@WebServlet(name="PurchaseBillConfirmController", urlPatterns={"/purchasebill_confirm"})
public class PurchaseBillConfirmController extends HttpServlet {
    private PurchaseBillDAO226 pbDAO;
    private SupplierDAO226 spDAO;
    private PurchaseBill_IngredientDAO226 pb_iDAO;
    private IngredientDAO226 ingDAO;
    
    public PurchaseBillConfirmController(){
        pbDAO = new PurchaseBillDAO226();
        spDAO = new SupplierDAO226();
        pb_iDAO = new PurchaseBill_IngredientDAO226();
        ingDAO = new IngredientDAO226();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
            int idPurchaseBill = Integer.parseInt(request.getParameter("idPurchaseBill"));
            PurchaseBill226 pb = pbDAO.getPurchaseBillAfterChooseIngredientById(idPurchaseBill);
            Supplier226 sp = spDAO.getSupplierById(pb.getIdSupplier());
            
            List<PurchaseBill_Ingredient226> pbiList = pb_iDAO.getListPurchaseBill_IngredientByPurchaseBillId(idPurchaseBill);
            List<Ingredient226> ingList = ingDAO.getIngredientByPurchaseBillId(idPurchaseBill);
            Map<Integer, Ingredient226> ingredientMap = new HashMap<>();
            for (Ingredient226 ingredient : ingList) {
                ingredientMap.put(ingredient.getId(), ingredient);
            }
            Map<Integer, Double> totalMap = new HashMap<>();
            for (PurchaseBill_Ingredient226 pbi : pbiList) {
                double totalPrice = pbi.getAmountIngredient() * ingredientMap.get(pbi.getIdIngredient()).getPrice();
                totalMap.put(pbi.getIdIngredient(), totalPrice);
            }
            
            HttpSession session = request.getSession();
            Member226 mem = (Member226) session.getAttribute("mem");
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
            String formattedDate = dateFormat.format(pb.getCreateDate()).toString();
            request.setAttribute("formattedCreateDate", formattedDate);

            request.setAttribute("mem", mem);
            request.setAttribute("purchaseBill", pb);
            request.setAttribute("supplier", sp);
            request.setAttribute("list_ingredient", ingList);
            request.setAttribute("list_pbi", pbiList);
            request.setAttribute("Total", pb.getTotal());
            request.setAttribute("ingredientMap", ingredientMap);
            request.setAttribute("totalMap", totalMap);
            
            session.setAttribute("formattedCreateDate", formattedDate);
            session.setAttribute("mem", mem);
            session.setAttribute("purchaseBill", pb);
            session.setAttribute("supplier", sp);
            session.setAttribute("list_ingredient", ingList);
            session.setAttribute("list_pbi", pbiList);
            session.setAttribute("Total", pb.getTotal());
            session.setAttribute("ingredientMap", ingredientMap);
            session.setAttribute("totalMap", totalMap);
            
            request.getRequestDispatcher("/Pages/gdConfirmPurchaseBill226.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error");
        }
    } 
}
