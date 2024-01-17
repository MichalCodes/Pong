package lab;

import javafx.scene.canvas.Canvas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBmanager {
    private final String[] usernames = new String[5];
    private final int[] scores = new int[5];
    private boolean block;

    public DBmanager() {
        this.block = false;
    }

    public void createTable(Connection connection) throws SQLException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS score (id INT AUTO_INCREMENT PRIMARY KEY, sc INT not null, name VARCHAR(30))";
        try (PreparedStatement preparedStatement = connection.prepareStatement(createTableSQL)) {
            preparedStatement.executeUpdate();
        }
    }
    public void insertData(Connection connection, String name, int sc) throws SQLException {
        String insertDataSQL = "INSERT INTO score (sc, name) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertDataSQL)) {
            preparedStatement.setString(2, name);
            preparedStatement.setInt(1, sc);
            preparedStatement.executeUpdate();
        }
        block = false;
    }
    public void selectData(Connection connection, Canvas canvas) throws SQLException {
        if(!block){
            String selectDataSQL = "SELECT COALESCE(name, '0') AS jm, COALESCE(sc, 0) AS score FROM score ORDER BY sc DESC";
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectDataSQL);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                int i = 0;
                while (resultSet.next() && i < 5) {
                    usernames[i] = resultSet.getString("jm");
                    scores[i] = resultSet.getInt("score");
                    i++;
                }

            }
            block = true;
        }

        canvas.getGraphicsContext2D().fillText(" Top scores: \n1." + usernames[0] + " \t" + scores[0] +
                " \n2." + usernames[1] + " \t" + scores[1] + " \n3." +usernames[2] + " \t" + scores[2]+" \n4." +
                usernames[3] + " \t" + scores[3]+" \n5." + usernames[4] + " \t" + scores[4], 255, 100);
    }
    public void deleteData(Connection connection, String name) throws SQLException {
        String deleteDataSQL = "DELETE FROM score WHERE name = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteDataSQL)) {
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
        }
    }
}
