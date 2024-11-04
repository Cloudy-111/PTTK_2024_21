/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import Connection.ConnectionSQLServer;
import Model.PurchaseBill226;
import java.util.List;
import org.apache.tomcat.jdbc.pool.DataSource;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author Tuan Diep
 */
public class PurchaseBillDAO226 extends DAO{
    public int createPurchaseBill(int supplierId, int idWarehouser){
        String insertBillQuery = "INSERT INTO Bill226 (createTime) Values (CURRENT_TIMESTAMP)";
        String insertPurchaseBillQuery = "INSERT INTO PurchaseBill226 (id, idSupplier, idWarehouser) Values (?, ?, ?)";
        
        try(Connection conn = ConnectionSQLServer.getConnection()) {
            PreparedStatement psBill = conn.prepareStatement(insertBillQuery, Statement.RETURN_GENERATED_KEYS);
            int affectedRows = psBill.executeUpdate();
            
            if(affectedRows == 0){
                throw new SQLException("Creating Bill failed, no rows affected.");
            }
            
            ResultSet generatedKeys = psBill.getGeneratedKeys();
            
            if(generatedKeys.next()){
                int billId = generatedKeys.getInt(1);
                PreparedStatement psPurchaseBill = conn.prepareStatement(insertPurchaseBillQuery);
                psPurchaseBill.setInt(1, billId);
                psPurchaseBill.setInt(2, supplierId);
                psPurchaseBill.setInt(3, idWarehouser);
                psPurchaseBill.executeUpdate();

                return billId;
            } else {
                throw new SQLException("Creating Bill failed, no ID obtained.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
    
    public PurchaseBill226 getPurchaseBillById(int id){
        String query = "SELECT pb.id, pb.idSupplier, pb.idWarehouser, b.createTime, b.Total " +
                        "FROM PurchaseBill226 pb " +
                        "JOIN Bill226 b ON pb.id = b.id " +
                        "WHERE pb.id = ?";
        
        try(Connection conn = ConnectionSQLServer.getConnection();
            PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                PurchaseBill226 pb = new PurchaseBill226();
                pb.setId(rs.getInt("id"));
                pb.setIdSupplier(rs.getInt("idSupplier"));
                
                Timestamp createDateTimestamp = rs.getTimestamp("createTime");
                if (createDateTimestamp != null) {
                    pb.setCreateDate(new Date(createDateTimestamp.getTime())); 
                }
                
//                pb.setTotal(rs.getFloat("Total"));
                
//                System.out.println(pb);
                return pb;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public PurchaseBill226 getPurchaseBillAfterChooseIngredientById(int id){
        String query = "SELECT pb.id, pb.idSupplier, b.createTime, " +
                        "SUM(pi.amountIngredient * i.price) AS Total " +
                        "FROM PurchaseBill226 pb " +
                        "JOIN Bill226 b ON pb.id = b.id " +
                        "LEFT JOIN PurchaseBill_Ingredient226 pi ON pb.id = pi.idPurchaseBill " +
                        "LEFT JOIN Ingredient226 i ON pi.idIngredient = i.id " +
                        "WHERE pb.id = ? " +
                        "GROUP BY pb.id, pb.idSupplier, b.createTime";
        try(Connection conn = ConnectionSQLServer.getConnection();
            PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                PurchaseBill226 pb = new PurchaseBill226();
                pb.setId(rs.getInt("id"));
                pb.setIdSupplier(rs.getInt("idSupplier"));
                
                Timestamp createDateTimestamp = rs.getTimestamp("createTime");
                if (createDateTimestamp != null) {
                    pb.setCreateDate(new Date(createDateTimestamp.getTime())); 
                }
                
                float total = rs.getFloat("Total");
                pb.setTotal(total);
                updateBillTotal(id, total);
                
//                System.out.println(pb);
                return pb;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    private void updateBillTotal(int id, float total) {
    String updateQuery = "UPDATE Bill226 SET Total = ? WHERE id = ?";

    try (Connection conn = ConnectionSQLServer.getConnection();
         PreparedStatement ps = conn.prepareStatement(updateQuery)) {
        ps.setFloat(1, total);
        ps.setInt(2, id);
        ps.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
}
