package cadastro.model.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SequenceManager {
    private Connection connection;

    public SequenceManager(Connection connection) {
        this.connection = connection;
    }

    public int getValue(String sequenceName) throws SQLException {
        int nextValue = -1;
        String sql = "SELECT nextval(?) as next_value";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, sequenceName);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    nextValue = resultSet.getInt("next_value");
                }
            }
        }
        return nextValue;
    }
}
