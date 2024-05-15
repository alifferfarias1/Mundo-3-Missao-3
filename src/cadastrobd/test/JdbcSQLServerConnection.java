package cadastrobd.test;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;
import java.util.logging.Level;

import cadastrobd.model.util.DatabaseCredentials;

/**
 * This program demonstrates how to establish a database connection to a SQL Server database.
 * @author Aliffer
 */
public class SQLServerConnectionExample {
    
    private static final Logger LOGGER = Logger.getLogger(SQLServerConnectionExample.class.getName());
    
    private final String HOSTNAME;
    private final String DBNAME;
    private final String USERNAME;
    private final String PASSWORD;
    
    public SQLServerConnectionExample() {
        DatabaseCredentials credentials = new DatabaseCredentials();
        HOSTNAME = credentials.getHostname();
        DBNAME = credentials.getDbname();
        USERNAME = credentials.getUsername();
        PASSWORD = credentials.getPassword();
    }
    
    private void establishConnection() {
        Connection conn = null;
        try {
            String dbURL = "jdbc:sqlserver://" + HOSTNAME + ":1433;databaseName="
                + DBNAME + ";encrypt=true;trustServerCertificate=true;";
            conn = DriverManager.getConnection(dbURL, USERNAME, PASSWORD);
            if (conn != null) {
                DatabaseMetaData metaData = conn.getMetaData();
                System.out.println("Driver name: " + metaData.getDriverName());
                System.out.println("Driver version: " + metaData.getDriverVersion());
                System.out.println("Database name: " + metaData.getDatabaseProductName());
                System.out.println("Database version: " + metaData.getDatabaseProductVersion());
            }
        }
        catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
        }
        finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, e.toString(), e);
            }
        }
    }
    
    public static void main(String[] args) {
        new SQLServerConnectionExample().establishConnection();
    }
 
}