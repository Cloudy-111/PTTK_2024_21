/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Connection.ConnectionSQLServer;
import Model.Food226;
import Model.Supplier226;
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
public class SupplierDAO226 extends DAO{
    public List<Supplier226> getAllSupplier226() throws SQLException {
        List<Supplier226> suppList = new ArrayList<>();
        String sql = "SELECT * FROM Supplier226";

        try (Connection conn = ConnectionSQLServer.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
             System.out.println("Kết nối đến cơ sở dữ liệu thành công.");
            while (rs.next()) {
                Supplier226 supp = new Supplier226();
                supp.setId(rs.getInt("id"));
                supp.setName(rs.getString("name"));
                supp.setEmail(rs.getString("phone"));
                supp.setPhone(rs.getString("email"));
                supp.setIdWarehouser(rs.getInt("idWarehouser"));
                suppList.add(supp);
            }
        }catch(Exception e){
            Supplier226 supp = new Supplier226();
            
            supp.setId(100);
            supp.setName("ERROR IN TRY");
            supp.setEmail("ERROR");
            supp.setPhone("ERROR");
            supp.setIdWarehouser(100);
            
            suppList.add(supp);
        }
        return suppList;
    }
    
    public List<Supplier226> getSupplierByKeyword(String keyword){
        List<Supplier226> suppList = new ArrayList<>();
        String sql = "SELECT * FROM Supplier226 Where name LIKE ?";
        
        try(Connection conn = ConnectionSQLServer.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Supplier226 supp = new Supplier226();
                supp.setId(rs.getInt("id"));
                supp.setName(rs.getString("name"));
                supp.setPhone(rs.getString("phone"));
                supp.setEmail(rs.getString("email"));
                supp.setIdWarehouser(rs.getInt("idWarehouser"));
                suppList.add(supp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return suppList;
    }
    
    public int CreateNewSupplier(String name, int idWarehouser) throws SQLException{
        String sql = "INSERT INTO Supplier226 (name, idWarehouser) Values (?, ?)";
        
        try (Connection conn = ConnectionSQLServer.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
        
            ps.setString(1, name);
            ps.setInt(2, idWarehouser);

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating supplier failed, no rows affected.");
            }

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int newId = generatedKeys.getInt(1);
                    System.out.println("New Supplier ID: " + newId);
                    return newId;
                } else {
                    throw new SQLException("Creating supplier failed, no ID obtained.");
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
    
    public Supplier226 getSupplierById(int id){
        String query = "SELECT * FROM Supplier226 WHERE id = ?";
        
        try(Connection conn = ConnectionSQLServer.getConnection();
                PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                Supplier226 sp = new Supplier226();
                sp.setId(rs.getInt("id"));
                sp.setName(rs.getString("name"));
                sp.setIdWarehouser(rs.getInt("idWarehouser"));
                return sp;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
