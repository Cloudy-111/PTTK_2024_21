/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

/**
 *
 * @author Tuan Diep
 */
public class DAO {
    private SQLServerDataSource ds;
    
    public DAO(){
        ds = new SQLServerDataSource();
        ds.setUser("sa");
        ds.setPassword("123123123");
        ds.setDatabaseName("PTTK_2024_21");
        ds.setServerName("DESKTOP-EFGDU03\\SQL_DB");
        ds.setPortNumber(1433);
        ds.setTrustServerCertificate(true);
    }
}
