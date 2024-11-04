/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import Connection.ConnectionSQLServer;
import Model.Ingredient226;
import java.util.List;
import org.apache.tomcat.jdbc.pool.DataSource;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Tuan Diep
 */
public class IngredientDAO226 extends DAO{
    public List<Ingredient226> getAllIngredient226(){
        List<Ingredient226> ingList = new ArrayList<>();
        String sql = "SELECT * FROM Ingredient226";

        try(Connection conn = ConnectionSQLServer.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {
            System.out.println("Kết nối đến cơ sở dữ liệu thành công.");
            while (rs.next()) {
                Ingredient226 ing = new Ingredient226();
                ing.setId(rs.getInt("id"));
                ing.setName(rs.getString("name"));
                ing.setDescription(rs.getString("description"));
                ing.setPrice(rs.getDouble("price"));
                ing.setRemain(rs.getInt("remain"));
                ingList.add(ing);
            }
            
        } catch (Exception e) {
            Ingredient226 ing = new Ingredient226();
            
            ing.setId(100);
            ing.setName("ERROR IN TRY");
            ing.setDescription("ERROR");
            ing.setPrice(100);
            ing.setPrice(100);
            
            ingList.add(ing);
        }
        return ingList;
    }
    
    public List<Ingredient226> getIngredientByKeyword(String keyword){
        List<Ingredient226> ingList = new ArrayList<>();
        String sql = "SELECT * FROM Ingredient226 WHERE name LIKE ?";
        
        try (Connection conn = ConnectionSQLServer.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)){
                
             ps.setString(1, "%" + keyword + "%");
             ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Ingredient226 ing = new Ingredient226();
                ing.setId(rs.getInt("id"));
                ing.setName(rs.getString("name"));
                ing.setDescription(rs.getString("description"));
                ing.setPrice(rs.getDouble("price"));
                ing.setRemain(rs.getInt("remain"));
                ingList.add(ing);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return ingList;
    }
    
    public int CreateNewIngredient(String name, double price) throws SQLException{
        String sql = "INSERT INTO Ingredient226 (name, price, remain) Values (?, ?, ?)";
        
        try (Connection conn = ConnectionSQLServer.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
        
            ps.setString(1, name);
            ps.setFloat(2, (float) price);
            ps.setInt(3, 0);

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating ingredient failed, no rows affected.");
            }

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int newId = generatedKeys.getInt(1);
                    System.out.println("New Ingredient ID: " + newId);
                    return newId;
                } else {
                    throw new SQLException("Creating ingredient failed, no ID obtained.");
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
    
    public List<Ingredient226> getIngredientByPurchaseBillId(int idPurchaseBill){
        String query = "SELECT * \n" +
                        "FROM Ingredient226\n" +
                        "WHERE id IN (SELECT idIngredient FROM PurchaseBill_Ingredient226 WHERE idPurchaseBill = ?)";
        List<Ingredient226> ingredients = new ArrayList<>();
        try (Connection conn = ConnectionSQLServer.getConnection();
            PreparedStatement ps = conn.prepareStatement(query)) {

           ps.setInt(1, idPurchaseBill);
           ResultSet rs = ps.executeQuery();

           while (rs.next()) {
               Ingredient226 ingredient = new Ingredient226();
               ingredient.setId(rs.getInt("id"));
               ingredient.setName(rs.getString("name"));
               ingredient.setDescription(rs.getString("description"));
               ingredient.setPrice(rs.getDouble("price"));
               ingredient.setRemain(rs.getInt("remain"));

               ingredients.add(ingredient);
           }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return ingredients;
    }
}
