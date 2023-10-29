package cadastro.model.util;

import java.sql.*;
public class ConectorBD {
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=Loja;encrypt=true;trustServerCertificate=true;;";
    private static final String USUARIO = "Loja";
    private static final String SENHA = "Loja";


    public static Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(URL, USUARIO, SENHA);
        return connection;
    }

    public static PreparedStatement getPrepared(String sql) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        return preparedStatement;
    }

    public static ResultSet getSelect(String sql) throws SQLException {
        PreparedStatement preparedStatement = getPrepared(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet;
    }

    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(PreparedStatement preparedStatement) {
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
