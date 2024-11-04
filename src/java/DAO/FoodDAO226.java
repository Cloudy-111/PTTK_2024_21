/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Food226;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import Connection.ConnectionSQLServer;
import java.util.List;
import org.apache.tomcat.jdbc.pool.DataSource;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Tuan Diep
 */
public class FoodDAO226 extends DAO{
    
    public List<Food226> getAllFood226() throws SQLException {
        List<Food226> foodList = new ArrayList<>();
        String sql = "SELECT * FROM Food226";

        try (Connection conn = ConnectionSQLServer.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
             System.out.println("Kết nối đến cơ sở dữ liệu thành công.");
            while (rs.next()) {
                Food226 food = new Food226();
                food.setId(rs.getInt("id"));
                food.setName(rs.getString("name"));
                food.setDescription(rs.getString("description"));
                food.setPrice(rs.getFloat("price"));
                foodList.add(food);
            }
        }catch(Exception e){
            Food226 food = new Food226();
            
            food.setId(100);
            food.setName("ERROR IN TRY");
            food.setDescription("ERROR");
            food.setPrice(100);
            
            foodList.add(food);
        }
        return foodList;
    }
    
    public List<Food226> getFoodByKeyword(String keyword){
        List<Food226> foodList = new ArrayList<>();
        String sql = "SELECT * FROM Food226 WHERE name LIKE ?";
        
        try (Connection conn = ConnectionSQLServer.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)){
                
             ps.setString(1, "%" + keyword + "%");
             ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Food226 food = new Food226();
                food.setId(rs.getInt("id"));
                food.setName(rs.getString("name"));
                food.setDescription(rs.getString("description"));
                food.setPrice(rs.getFloat("price"));
                foodList.add(food);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return foodList;
    }
    
    public Food226 getFoodById(int id){
        String sql = "Select * From Food226 Where id = ?";
        
        Food226 result = new Food226();
        
        try (Connection conn = ConnectionSQLServer.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)){
                
             ps.setInt(1, id);
             ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Food226 food = new Food226();
                food.setId(rs.getInt("id"));
                food.setName(rs.getString("name"));
                food.setDescription(rs.getString("description"));
                food.setPrice(rs.getFloat("price"));
                result = food;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
