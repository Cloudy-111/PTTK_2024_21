/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Connection;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Tuan Diep
 */
public class ConnectionSQLServer {
    
    private static final String SERVER_NAME = "DESKTOP-EFGDU03\\SQL_DB";
    private static final String DATABASE_NAME = "PTTK_2024_21";
    private static final int PORT_NUMBER = 1433;
    private static final String USERNAME = "sa";
    private static final String PASSWORD = "123123123";

private static final SQLServerDataSource dataSource;

    static {
        dataSource = new SQLServerDataSource();
        dataSource.setServerName(SERVER_NAME);
        dataSource.setDatabaseName(DATABASE_NAME);
        dataSource.setPortNumber(PORT_NUMBER);
        dataSource.setUser(USERNAME);
        dataSource.setPassword(PASSWORD);
        dataSource.setTrustServerCertificate(true);
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
