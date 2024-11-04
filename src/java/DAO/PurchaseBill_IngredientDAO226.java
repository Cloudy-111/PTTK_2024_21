/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Connection.ConnectionSQLServer;
import Model.PurchaseBill_Ingredient226;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tuan Diep
 */
public class PurchaseBill_IngredientDAO226 extends DAO{
    public int CreateNewPurchaseBill_Ingredient(int idPurchaseBill, int idIngredient, int amountIngredient){
        String sql = "INSERT INTO PurchaseBill_Ingredient226 (amountIngredient, idPurchaseBill, idIngredient) Values (?, ?, ?)";
        
        try(Connection conn = ConnectionSQLServer.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, amountIngredient);
            ps.setInt(2, idPurchaseBill);
            ps.setInt(3, idIngredient);
            
            int affectedRows = ps.executeUpdate();
            
            if(affectedRows == 0){
                throw new SQLException("Creating PurchaseBill_Ingredient failed, no rows affected.");
            }
            
            try(ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if(generatedKeys.next()){
                    int newId = generatedKeys.getInt(1);
                    System.out.println("New PurchaseBill_Ingredient: " + newId);
                    return newId;
                } else {
                    throw new SQLException("Creating PurchaseBill_Ingredient failed, no ID obtained.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
    
    public List<PurchaseBill_Ingredient226> getListPurchaseBill_IngredientByPurchaseBillId(int idPurchaseBill){
        String sql = "SELECT * FROM PurchaseBill_Ingredient226 WHERE idPurchaseBill = ?";
        List<PurchaseBill_Ingredient226> result = new ArrayList<>();
        try(Connection conn = ConnectionSQLServer.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idPurchaseBill);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                PurchaseBill_Ingredient226 pb_i = new PurchaseBill_Ingredient226();
                pb_i.setId(rs.getInt("id"));
                pb_i.setAmountIngredient(rs.getInt("amountIngredient"));
                pb_i.setIdIngredient(rs.getInt("idIngredient"));
                pb_i.setIdPurchaseBill(rs.getInt("idPurchaseBill"));
                result.add(pb_i);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
