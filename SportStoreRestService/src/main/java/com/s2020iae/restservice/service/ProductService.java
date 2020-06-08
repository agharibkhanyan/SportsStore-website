package com.s2020iae.restservice.service;
import com.s2020iae.restservice.db.DatabaseConnector;
import com.s2020iae.restservice.db.DatabaseUtils;
import com.s2020iae.restservice.model.Product;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class ProductService {
    private final static String ALL_PRODUCTS_QUERY = "SELECT * FROM `products`";
    public static Product getProductById(int id) {
        Connection connection = DatabaseConnector.getConnection();
        ResultSet resultSet = DatabaseUtils.retrieveQueryResults(connection, ALL_PRODUCTS_QUERY + " WHERE `id` = " + id);
        if (resultSet != null) {
            try {
                while (resultSet.next()) {
                    Product product = new Product();
                    product.setId(resultSet.getInt("id"));
                    product.setName(resultSet.getString("name"));
                    product.setSummary(resultSet.getString("summary"));
                    product.setThumbnail(resultSet.getString("thumbnail"));
                    product.setCategory(resultSet.getString("category"));
                    product.setDetail(resultSet.getString("detail"));
                    product.setPrice(resultSet.getFloat("price"));
                    return product;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
    public static List<Product> getAllProducts() {
        List<Product> products = new ArrayList<Product>();
        Connection connection = DatabaseConnector.getConnection();
        ResultSet resultSet = DatabaseUtils.retrieveQueryResults(connection, ALL_PRODUCTS_QUERY);
        if (resultSet != null) {
            try {
                while (resultSet.next()) {
                    Product product = new Product();
                    product.setId(resultSet.getInt("id"));
                    product.setName(resultSet.getString("name"));
                    product.setSummary(resultSet.getString("summary"));
                    product.setThumbnail(resultSet.getString("thumbnail"));
                    product.setCategory(resultSet.getString("category"));
                    product.setDetail(resultSet.getString("detail"));
                    product.setPrice(resultSet.getFloat("price"));
                    products.add(product);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return products;
    }
    public static boolean AddProduct(Product product) {
        String sql = "INSERT INTO `products`  (`name`,`summary`,`thumbnail`,`category`,`detail`,`price`) VALUES (?, ?, ?, ?, ?, ?)";
        Connection connection = DatabaseConnector.getConnection();
        return DatabaseUtils.performDBUpdate(connection, sql, product.getName(), product.getSummary(), product.getThumbnail(), product.getCategory(), product.getDetail(), String.valueOf(product.getPrice()));
    }
    public static boolean updateProduct(Product product) {
        String sql = "UPDATE `products` SET `name`=?,`summary`=?,`thumbnail`=?,`category`=?,`detail`=?,`price`=? WHERE `id`=?;";
        Connection connection = DatabaseConnector.getConnection();
        boolean updateStatus = DatabaseUtils.performDBUpdate(connection, sql, product.getName(), product.getSummary(), product.getThumbnail(), product.getCategory(), product.getDetail(), String.valueOf(product.getPrice()),
                String.valueOf(product.getId()));
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return updateStatus;
    }
    public static boolean deleteProduct(Product retrievedProduct) {
        String sql = "DELETE FROM `products` WHERE `id`=?;";
        Connection connection = DatabaseConnector.getConnection();
        boolean updateStatus = DatabaseUtils.performDBUpdate(connection, sql, String.valueOf(retrievedProduct.getId()));
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return updateStatus;
    }
}
