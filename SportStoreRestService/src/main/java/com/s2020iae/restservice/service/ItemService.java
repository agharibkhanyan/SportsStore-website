package com.s2020iae.restservice.service;
import com.s2020iae.restservice.db.DatabaseConnector;
import com.s2020iae.restservice.db.DatabaseUtils;
import com.s2020iae.restservice.model.Item;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class ItemService {
    private final static String ALL_ITEMS_QUERY = "SELECT * FROM `items`";
    public static Item getItemById(int id) {
        Connection connection = DatabaseConnector.getConnection();
        ResultSet resultSet = DatabaseUtils.retrieveQueryResults(connection, ALL_ITEMS_QUERY + " WHERE `id` = " + id);
        if (resultSet != null) {
            try {
                while (resultSet.next()) {
                    Item item = new Item();
                    item.setId(resultSet.getInt("id"));
                    item.setOrderId(resultSet.getInt("orderid"));
                    item.setProductId(resultSet.getInt("productid"));
                    return item;
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
    public static List<Item> getAllItems(int id) {
        List<Item> items = new ArrayList<Item>();
        Connection connection = DatabaseConnector.getConnection();
        ResultSet resultSet = DatabaseUtils.retrieveQueryResults(connection, ALL_ITEMS_QUERY + " WHERE `orderid` = " + id);
        if (resultSet != null) {
            try {
                while (resultSet.next()) {
                    Item item = new Item();
                    item.setId(resultSet.getInt("id"));
                    item.setOrderId(resultSet.getInt("orderid"));
                    item.setProductId(resultSet.getInt("productid"));
                    items.add(item);
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
        return items;
    }
    public static boolean AddItem(Item item) {
        String sql = "INSERT INTO `items`  (`orderid`,`productid`) VALUES (?, ?)";
        Connection connection = DatabaseConnector.getConnection();
        return DatabaseUtils.performDBUpdate(connection, sql, String.valueOf(item.getOrderId()), String.valueOf(item.getProductId()));
    }

    public static boolean updateProduct(Item item) {
        String sql = "UPDATE `items` SET `orderid`=?,`productid`=? WHERE `id`=?;";
        Connection connection = DatabaseConnector.getConnection();
        boolean updateStatus = DatabaseUtils.performDBUpdate(connection, sql, String.valueOf(item.getOrderId()), String.valueOf(item.getProductId()));
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return updateStatus;
    }
    public static boolean deleteItem(int retrievedItem) {
        String sql = "DELETE FROM `items` WHERE `orderid`=?;";
        Connection connection = DatabaseConnector.getConnection();
        boolean updateStatus = DatabaseUtils.performDBUpdate(connection, sql, String.valueOf(retrievedItem));
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return updateStatus;
    }
}
