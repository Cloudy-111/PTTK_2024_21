/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import Connection.ConnectionSQLServer;
import Model.Member226;
import java.util.List;
import org.apache.tomcat.jdbc.pool.DataSource;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Tuan Diep
 */
public class StaffDAO extends DAO{
    
    public boolean isValidUser(String username, String password) throws SQLException{
        String query = "Select * from Staff226 Where id = (Select id from Member226 Where username = ? and password = ?)";
        
        try(Connection conn = ConnectionSQLServer.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)){
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        }
    }
    
    public int isValidWarehouser(String username, String password) throws SQLException{
        String query = "SELECT Warehouser226.id \n" +
                        "FROM Warehouser226 \n" +
                        "JOIN Staff226 ON Warehouser226.id = Staff226.id\n" +
                        "WHERE Staff226.id = (\n" +
                        "    SELECT TOP 1 id \n" +
                        "    FROM Member226 \n" +
                        "    WHERE username = ? and password = ?\n" +
                        ");";
        
        try(Connection conn = ConnectionSQLServer.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)){
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return rs.getInt("id");
            }
        }
        return 0;
    }
    
    public Member226 getMemberById(int id) throws SQLException{
        String query = "SELECT * FROM Member226 WHERE id = ?";
        
        try(Connection conn = ConnectionSQLServer.getConnection();
                PreparedStatement ps = conn.prepareStatement(query)){
            ps.setInt(1, id);
            ResultSet rs= ps.executeQuery();
            if(rs.next()){
                Member226 mem = new Member226();
                mem.setId(rs.getInt("id"));
                mem.setEmail(rs.getString("email"));
                mem.setFullname(rs.getString("fullname"));
                mem.setNote(rs.getString("note"));
                mem.setPhone(rs.getString("phone"));
                return mem;
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
