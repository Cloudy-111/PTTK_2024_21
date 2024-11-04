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
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Tuan Diep
 */
//@WebServlet(name="ExportPurchaseBillPDF", urlPatterns={"/ExportPurchaseBillPDF"})
public class ExportPurchaseBillPDF extends HttpServlet {
    private PurchaseBillDAO226 pbDAO;
    private SupplierDAO226 spDAO;
    private PurchaseBill_IngredientDAO226 pb_iDAO;
    private IngredientDAO226 ingDAO;
    
    public ExportPurchaseBillPDF(){
        pbDAO = new PurchaseBillDAO226();
        spDAO = new SupplierDAO226();
        pb_iDAO = new PurchaseBill_IngredientDAO226();
        ingDAO = new IngredientDAO226();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        PurchaseBill226 pb = (PurchaseBill226) session.getAttribute("purchaseBill");
        Supplier226 sp = (Supplier226) session.getAttribute("supplier");
        List<PurchaseBill_Ingredient226> pbiList = (List<PurchaseBill_Ingredient226>) session.getAttribute("list_pbi");
        List<Ingredient226> ingList = (List<Ingredient226>) session.getAttribute("list_ingredient");
        Double Total = (Double) session.getAttribute("Total");
        String createDate = (String) session.getAttribute("formattedCreateDate");
        Member226 mem = (Member226) session.getAttribute("mem");
        Map<Integer, Ingredient226> ingredientMap = (Map<Integer, Ingredient226>) session.getAttribute("ingredientMap");
        Map<Integer, Double> totalMap = (Map<Integer, Double>) session.getAttribute("totalMap");
        
        session.setAttribute("mem", mem);
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=purchase_bill_" + pb.getId() + ".pdf");
        
        try (OutputStream out = response.getOutputStream()) {
            createPdf(out, pb, sp, ingList, pbiList, Total, createDate, mem, ingredientMap, totalMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    } 

    private void createPdf(OutputStream out, PurchaseBill226 pb, Supplier226 sp, List<Ingredient226> ingList, List<PurchaseBill_Ingredient226> pbiList, Double Total, String createDate, Member226 mem, Map<Integer, Ingredient226> ingredientMap, Map<Integer, Double> totalMap) throws DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, out);
        try {
            document.open();

            // Thêm tiêu đề hóa đơn
            document.add(new Paragraph("Confirm Purchase Bill"));
            document.add(new Paragraph("Ma Hoa Don: " + pb.getId()));
            document.add(new Paragraph("Ngay tao: " + createDate));
            document.add(new Paragraph("Nhan vien thanh toan: " + mem.getFullname()));
            document.add(new Paragraph("Nha cung cap: " + sp.getName()));
            document.add(new Paragraph("Total: " + Total));

            PdfPTable table = new PdfPTable(6); 
            table.addCell("ID");
            table.addCell("Name");
            table.addCell("Description");
            table.addCell("Price");
            table.addCell("Quantity");
            table.addCell("Total");

            for (PurchaseBill_Ingredient226 pbi : pbiList) {
                Ingredient226 ing = ingredientMap.get(pbi.getIdIngredient());
                table.addCell(String.valueOf(ing.getId()));
                table.addCell(ing.getName());
                table.addCell(ing.getDescription());
                table.addCell(String.valueOf(ing.getPrice()));
                table.addCell(String.valueOf(pbi.getAmountIngredient()));
                table.addCell(String.valueOf(totalMap.get(pbi.getIdIngredient())));
            }

            document.add(table);

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

