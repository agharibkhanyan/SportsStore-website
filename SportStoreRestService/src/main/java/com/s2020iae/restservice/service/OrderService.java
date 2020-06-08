/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.s2020iae.restservice.service;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.s2020iae.restservice.db.DatabaseConnector;
import com.s2020iae.restservice.db.DatabaseUtils;
import com.s2020iae.restservice.model.Orders;

/**
 *
 * @author anon
 */
public class OrderService {

    private final static String ALL_PRODUCTS_QUERY = "SELECT * FROM `orders`";

    public static Orders getOrderById(int id) {
        //Get a new connection object before going forward with the JDBC invocation.
        Connection connection = DatabaseConnector.getConnection();
        ResultSet resultSet = DatabaseUtils.retrieveQueryResults(connection, ALL_PRODUCTS_QUERY + " WHERE `id`= " + id);

        if (resultSet != null) {
            try {
                while (resultSet.next()) {
                    Orders order = new Orders();
                    order.setId(resultSet.getInt("id"));
                    order.setFirstName(resultSet.getString("firstname"));
                    order.setLastName(resultSet.getString("lastname"));
                    order.setEmail(resultSet.getString("email"));
                    order.setPhone(resultSet.getString("phone"));
                    order.setAddress(resultSet.getString("address"));
                    order.setCity(resultSet.getString("city"));
                    order.setState(resultSet.getString("state"));
                    order.setZip(resultSet.getInt("zip"));
                    order.setBillAddr(resultSet.getString("billaddr"));
                    order.setBillCity(resultSet.getString("billcity"));
                    order.setBillState(resultSet.getString("billstate"));
                    order.setBillZip(resultSet.getInt("billzip"));
                    order.setMethod(resultSet.getString("method"));
                    order.setCardName(resultSet.getString("cardname"));
                    order.setCardNumber(resultSet.getString("cardnumber"));
                    order.setExpMonth(resultSet.getInt("expmonth"));
                    order.setExpYear(resultSet.getInt("expyear"));
                    order.setCvv(resultSet.getInt("cvv"));
                    order.setPrice(resultSet.getString("price"));


                    return order;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    // We will always close the connection once we are done interacting with the Database.
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    public static List<Orders> getAllOrders() {
        List<Orders> orders = new ArrayList<Orders>();

        Connection connection = DatabaseConnector.getConnection();
        ResultSet resultSet = DatabaseUtils.retrieveQueryResults(connection, ALL_PRODUCTS_QUERY);

        if (resultSet != null) {
            try {
                while (resultSet.next()) {
                    Orders order = new Orders();
                    order.setId(resultSet.getInt("id"));
                    order.setFirstName(resultSet.getString("firstname"));
                    order.setLastName(resultSet.getString("lastname"));
                    order.setEmail(resultSet.getString("email"));
                    order.setPhone(resultSet.getString("phone"));
                    order.setAddress(resultSet.getString("address"));
                    order.setCity(resultSet.getString("city"));
                    order.setState(resultSet.getString("state"));
                    order.setZip(resultSet.getInt("zip"));
                    order.setBillAddr(resultSet.getString("billaddr"));
                    order.setBillCity(resultSet.getString("billcity"));
                    order.setBillState(resultSet.getString("billstate"));
                    order.setBillZip(resultSet.getInt("billzip"));
                    order.setMethod(resultSet.getString("method"));
                    order.setCardName(resultSet.getString("cardname"));
                    order.setCardNumber(resultSet.getString("cardnumber"));
                    order.setExpMonth(resultSet.getInt("expmonth"));
                    order.setExpYear(resultSet.getInt("expyear"));
                    order.setCvv(resultSet.getInt("cvv"));
                    order.setPrice(resultSet.getString("price"));

                    orders.add(order);

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
        return orders;
    }

    public static int getOrderLastId() {
        Connection connection = DatabaseConnector.getConnection();
        ResultSet resultSet = DatabaseUtils.retrieveQueryResults(connection, ALL_PRODUCTS_QUERY + " ORDER BY `id` DESC LIMIT 1");

        if (resultSet != null) {
            try {
                while (resultSet.next()) {
                    return resultSet.getInt("id");
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
        return -1;
    }
    public static boolean AddOrder(Orders order) {

        String sql = "INSERT INTO `orders`  (`firstname`,`lastname`,`email`,`phone`,`address`,`city`,`state`,`zip`,`billaddr`,`billcity`,"
                + " `billstate`,`billzip`,`method`,`cardname`,`cardnumber`,`expmonth`,`expyear`,`cvv`,`price`)" +
                "VALUES ( ?, ?, ?, ?,?, ?, ?, ?, ?,?, ?, ?, ?, ?,?, ?, ?, ?, ?)";
        Connection connection = DatabaseConnector.getConnection();
        return DatabaseUtils.performDBUpdate(connection, sql,order.getFirstName(),order.getLastName(),order.getEmail(),
            order.getPhone(),order.getAddress(),order.getCity(),order.getState(),String.valueOf(order.getZip()),order.getBillAddr(),order.getBillCity(),
            order.getBillState(),String.valueOf(order.getBillZip()),order.getMethod(),order.getCardName(),order.getCardNumber(),String.valueOf(order.getExpMonth()),String.valueOf(order.getExpYear()),
            String.valueOf(order.getCvv()),order.getPrice());

    }

    public static boolean updateOrder(Orders order) {

        String sql = "UPDATE 'orders' SET 'firstname'=?, 'lastname'=?, 'email'=?, 'phone'=?, 'address'=?, 'city'=?,'state'=?, 'zip'=?, 'billaddr'=?, 'billcity'=?, "
                + "'billstate'=?, 'billzip'=?, 'method'=?,'cardname'=?, 'cardnumber'=?, 'expmonth'=?, 'expyear'=?, 'cvv'=?, 'price'=?;";

        Connection connection = DatabaseConnector.getConnection();

        boolean updateStatus = DatabaseUtils.performDBUpdate(connection, sql,order.getFirstName(),order.getLastName(),order.getEmail(),
            order.getPhone(),order.getAddress(),order.getCity(),order.getState(),String.valueOf(order.getZip()),order.getBillAddr(),order.getBillCity(),
            order.getBillState(),String.valueOf(order.getBillZip()),order.getMethod(),order.getCardName(),order.getCardNumber(),String.valueOf(order.getExpMonth()),String.valueOf(order.getExpYear()),
            String.valueOf(order.getCvv()),order.getPrice());

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return updateStatus;

    }

    public static boolean deleteOrder(Orders order) {

        String sql = "DELETE FROM `orders` WHERE `id`=?;";

        Connection connection = DatabaseConnector.getConnection();

        boolean updateStatus = DatabaseUtils.performDBUpdate(connection, sql, String.valueOf(order.getId()));

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return updateStatus;
    }

}
