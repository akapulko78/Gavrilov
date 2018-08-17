package sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Main program = new Main();
        if (program.open()) {
            program.insert();
            program.select();
            program.close();
        }
    }

    private void select() {
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT id, name, phone " +
                    "FROM users " +
                    "ORDER BY name";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String phone = resultSet.getString("phone");
                System.out.println(id + "\t| " + name + "  \t| " + phone);
            }
            resultSet.close();
            statement.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    Connection connection;

    boolean open() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(
                    "jdbc:sqlite:C:\\sqlite\\users.db");
            System.out.println("connected...");
            return true;
        } catch (Exception e) {
            e.getMessage();
            return false;
        }
    }

    void insert() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter username");
            String name = scanner.nextLine();
            System.out.println("Enter phone");
            String phone = scanner.nextLine();

            String query = "INSERT INTO users (name, phone) " +
                    "VALUES ('" + name + "', '" + phone + "')";
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Rows added");
            statement.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    void close() {
        try {
            connection.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
