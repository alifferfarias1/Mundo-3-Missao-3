package cadastrobd.model.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;

public class ConectorBD {

    private static final String HOSTNAME;
    private static final String DBNAME;
    private static final String LOGIN;
    private static final String PASSWORD;

    static {
        CredentialsLoader loader = new CredentialsLoader();
        HOSTNAME = loader.getHostname();
        DBNAME = loader.getDbname();
        LOGIN = loader.getLogin();
        PASSWORD = loader.getPassword();
    }

    private static Connection con;

    public static synchronized Connection getConnection() throws SQLException {
        if (con == null || con.isClosed()) {
            String URL = String.format("jdbc:sqlserver://%s:1433;databaseName=%s;",
                    HOSTNAME, DBNAME).concat("encrypt=true;trustServerCertificate=true");
            con = DriverManager.getConnection(URL, LOGIN, PASSWORD);
        }
        return con;
    }

    public static PreparedStatement getPrepared(String sql) throws SQLException {
        return getConnection().prepareStatement(sql);
    }

    public static ResultSet getSelect(String sql) throws SQLException {
        PreparedStatement stmt = getPrepared(sql);
        return stmt.executeQuery();
    }

    public static int insert(String sql) throws SQLException {
        PreparedStatement stmt = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        stmt.executeUpdate();
        ResultSet rs = stmt.getGeneratedKeys();
        if (rs.next()) {
            return rs.getInt(1);
        } else {
            throw new SQLException("Data insert failed.");
        }
    }

    public static boolean update(String sql) throws SQLException {
        return getPrepared(sql).executeUpdate() > 0;
    }

    public static void close(Connection con, PreparedStatement stmt, ResultSet rs) throws SQLException {
        if (stmt != null && !stmt.isClosed()) {
            stmt.close();
        }
        if (rs != null && !rs.isClosed()) {
            rs.close();
        }
        if (con != null && !con.isClosed()) {
            con.close();
        }
    }
}