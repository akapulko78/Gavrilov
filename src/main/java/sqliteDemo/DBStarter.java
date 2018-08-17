package sqliteDemo;

import org.sqlite.JDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DBStarter {
    private static final String CON_ADDR = "C:/sqlite/products1.db";
    /* public static final String PREFIX = "jdbc:sqlite:";*/
    private String query;
    private List<ProducT> products = new ArrayList<>();
    private boolean hasProduct;

    public static void main(String[] args) {
        DBStarter dbStarter = new DBStarter();
        if (dbStarter.open()) {
            dbStarter.deleteAllProduct();
            dbStarter.insert();
            dbStarter.select();
            dbStarter.productFinder();
//            dbStarter.close();
//            dbStarter.deleteProduct(1);

        }
    }

    private void productFinder() {
        try (Statement statement = connection.createStatement()) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter product title");
            String searchingTitle = scanner.nextLine();
            String query1 = "SELECT title, cost " +
                    "FROM products1";

            ResultSet resultSet = statement.executeQuery(query1);
            while (resultSet.next()) {
                if (resultSet.getString("title").equals(searchingTitle)) {
                    Long cost = resultSet.getLong("cost");
                    System.out.println(cost.toString());
                    hasProduct = true;
                    break;
                }
            }
            if (!hasProduct)
                System.out.println("Такого товара нет");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteAllProduct() {
        try (PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM products1 ")) {
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Connection connection;

    private boolean open() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection
                    (JDBC.PREFIX + CON_ADDR);
            System.out.println("connected...");
            return true;
        } catch (Exception e) {
            e.getMessage();
            return false;
        }
    }

    private void select() {
        try (Statement statement = connection.createStatement()) {

            String query = "SELECT id, prodid, title, cost " +
                    "FROM products1 " +
                    "ORDER BY id";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                products.add(new ProducT(
                        resultSet.getInt("id"),
                        resultSet.getString("prodid"),
                        resultSet.getString("title"),
                        resultSet.getLong("cost")));
            }
               /* int id = resultSet.getInt("id");
                String prodid = resultSet.getString("prodid");
                String title = resultSet.getString("title");
                Long cost = resultSet.getLong("cost");
                System.out.println
                        (id + "\t| " + prodid + "  \t| " +
                                title + "\t| " + cost + "\t|");*/
            resultSet.close();

            for (ProducT p : products) {
                System.out.println(p);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void insert() {
        try (Statement statement = this.connection.createStatement()) {

            for (int i = 1; i <= 10; i++) {
                query = "INSERT INTO products1 (prodid, title, cost) " +
                        "VALUES ('" + i + "', '" +
                        i + "', '" + i * 10 + "')";
                statement.executeUpdate(query);
            }

            /*String query = "INSERT INTO products1 (prodid, title, cost) " +
                    "VALUES ('" + prodid + "', '" + title + "', '" + cost + "')";*/


            System.out.println("Rows added");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void close() {
        try {
            connection.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void deleteProduct(int id) {
        try (PreparedStatement statement = this.connection.prepareStatement(
                "DELETE FROM products1 WHERE id = ?")) {
            statement.setObject(1, id);
            // Выполняем запрос
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
