package com.s2020iae.restservice.service;
import com.s2020iae.restservice.db.DatabaseConnector;
import com.s2020iae.restservice.db.DatabaseUtils;
import com.s2020iae.restservice.model.State;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class StateService {
    private final static String ALL_STATE_QUERY = "SELECT * FROM `states`";
    public static List<State> getAllStates(String query) {
        List<State> states = new ArrayList<State>();
        Connection connection = DatabaseConnector.getConnection();
        ResultSet resultSet = DatabaseUtils.retrieveQueryResults(connection, ALL_STATE_QUERY + " WHERE `name` LIKE '%" + query + "%'");
        if (resultSet != null) {
            try {
                while (resultSet.next()) {
                    State state = new State();
                    state.setId(resultSet.getInt("id"));
                    state.setName(resultSet.getString("name"));
                    state.setAbbr(resultSet.getString("abbr"));
                    states.add(state);
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
        return states;
    }
}
