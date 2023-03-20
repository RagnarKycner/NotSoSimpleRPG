package Grafika;
import Kodzik.Postacie.Player;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class PlayerDao implements AutoCloseable {
    Connection connection;

    public PlayerDao() {
        try {
            String url = "jdbc:sqlite:C:/BazaDanych/pacz";
            connection = connect(url);
            connection.setAutoCommit(false);
            create();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private Connection connect(String url) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }

    private void create(){
        String sqlc = "CREATE TABLE IF NOT EXISTS Player(\n"
                + "PlayerID INTEGER primary KEY AUTOINCREMENT,\n"
                + "Klasa varchar(40),\n"
                + "Imie varchar(40),\n"
                + "Poziom integer"
                + ");";

        try (Statement statement = connection.createStatement()) {
            statement.execute(sqlc);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void close() throws Exception {
        connection.close();
    }

    public String[] read(int id) throws Exception {
        String[] exit;
        String sql = "select Klasa, Imie, Poziom \n"
                + "From Player where PlayerID = ?\n";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            exit = new String[]{
                    resultSet.getString(1),
                    resultSet.getString(2),
                    String.valueOf(resultSet.getInt(3)),
            };
        } catch (SQLException e) {
            throw new SQLDataException(e);
        }
        return  exit;
    }

    public void write(Player obj) throws Exception {
        try (PreparedStatement preparedStatement = connection
                .prepareStatement("insert into Player(Klasa, Imie, Poziom) values (?,?,?)")) {
            preparedStatement.setString(1,obj.getClass().getName());
            preparedStatement.setString(2, obj.getName());
            preparedStatement.setInt(3, obj.getLevel());
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            throw new SQLDataException(e);
        }
    }

    public List<String[]> readAll() throws SQLDataException {
        List<String[]> exit = new LinkedList<>();
        String sql = "select PlayerID, Klasa, Imie, Poziom \n"
                + "From Player\n";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String[] temp = new String[]{
                        String.valueOf(resultSet.getInt(1)),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        String.valueOf(resultSet.getInt(4)),
                };
                exit.add(temp);
            }
        } catch (SQLException e) {
            throw new SQLDataException(e);
        }
        return  exit;
    }
}
