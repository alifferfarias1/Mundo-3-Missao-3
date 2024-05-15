package cadastrobd.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import cadastrobd.model.util.DatabaseConnector;

/**
 *
 * Author: Aliffer
 */
public class CompanyDAO {

    private DatabaseConnector dbConnector;

    public CompanyDAO() {
        dbConnector = new DatabaseConnector();
    }

    public Company getCompany(Integer id) throws SQLException {
        String sql = "SELECT c.FK_Entity_id, c.cnpj, e.name, e.address, e.city, e.state, e.phone, e.email "
                + "FROM Company c "
                + "INNER JOIN Entity e ON c.FK_Entity_id = e.id "
                + "WHERE c.FK_Entity_id = ?";
        try (Connection con = dbConnector.getConnection();
                PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Company(
                            rs.getInt("FK_Entity_id"),
                            rs.getString("name"),
                            rs.getString("address"),
                            rs.getString("city"),
                            rs.getString("state"),
                            rs.getString("phone"),
                            rs.getString("email"),
                            rs.getString("cnpj")
                    );
                }
            }
        }
        return null;
    }