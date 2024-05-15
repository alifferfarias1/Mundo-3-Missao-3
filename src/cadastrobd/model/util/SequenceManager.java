package cadastrobd.model.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SequenceManager {
    
    private final Connection connection;

    public SequenceManager(Connection connection) {
        this.connection = connection;
    }
    
    public int getNextValue(String sequence) throws SQLException {
        String sql = "SELECT NEXT VALUE FOR ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, sequence);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                } else {
                    throw new SQLException("Next value not achievable for sequence: " + sequence);
                }
            }
        }
    }
}