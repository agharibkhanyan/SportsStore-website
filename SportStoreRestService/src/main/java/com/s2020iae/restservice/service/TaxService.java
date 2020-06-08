package com.s2020iae.restservice.service;
import com.s2020iae.restservice.db.DatabaseConnector;
import com.s2020iae.restservice.db.DatabaseUtils;
import com.s2020iae.restservice.model.Tax;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class TaxService {
    private final static String ALL_PRODUCTS_QUERY = "SELECT * FROM `tax`";
    public static Tax getTaxByZip(int zip) {
        Connection connection = DatabaseConnector.getConnection();
        ResultSet resultSet = DatabaseUtils.retrieveQueryResults(connection, ALL_PRODUCTS_QUERY + " WHERE `zipcode` = " + zip);
        if (resultSet != null) {
            try {
                while (resultSet.next()) {
                    Tax tax = new Tax();
                    tax.setState(resultSet.getString("state"));
                    tax.setZipcode(resultSet.getInt("zipcode"));
                    tax.setRegion(resultSet.getString("region"));
                    tax.setRate(resultSet.getDouble("rate"));
                    return tax;
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
}
