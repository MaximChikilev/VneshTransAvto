package dao;

import dto.RouteForView;
import entity.Route;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MySqlRouteDao extends AbstractJDBCDao<Route> {
    private static MySqlRouteDao instance;

    private MySqlRouteDao(Connection connection) {
        super(connection);
    }

    public static MySqlRouteDao getRouteDao(Connection connection) {
        if (instance == null) {
            instance = new MySqlRouteDao(connection);
        }
        return instance;
    }

    @Override
    protected String getSelectQuery() {
        return "SELECT * FROM route";
    }

    @Override
    protected String getCreateQuery() {
        return "INSERT INTO route (routeTitle, departureCity, arrivalCity, distance) VALUES (?,?,?,?)";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE route SET routeTitle=?, departureCity=?, arrivalCity=?, distance=? WHERE routeID=?";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM route WHERE routeID=?";
    }

    @Override
    protected List<Route> parseResultSet(ResultSet resultSet) {
        ArrayList<Route> result = new ArrayList<>();
        try {
            while (resultSet.next()) {
                result.add(new Route(resultSet.getInt("routeID"), resultSet.getString("routeTitle"), resultSet.getInt("departureCity"), resultSet.getInt("arrivalCity"), resultSet.getInt("distance")));
            }

        } catch (Exception e) {
            throw new DatabaseException(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForCreate(PreparedStatement statement, Route entity) {
        try {
            statement.setString(1, entity.getRouteTitle());
            statement.setInt(2, entity.getDepartureCity());
            statement.setInt(3, entity.getArrivalCity());
            statement.setInt(4, entity.getDistance());
        } catch (Exception e) {
            throw new DatabaseException("Failed to prepare the statement for creation of entity: " + entity, e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Route entity) {
        try {
            statement.setString(1, entity.getRouteTitle());
            statement.setInt(2, entity.getDepartureCity());
            statement.setInt(3, entity.getArrivalCity());
            statement.setInt(4, entity.getDistance());
            statement.setInt(5, entity.getRouteID());
        } catch (Exception e) {
            throw new DatabaseException("Failed to prepare the statement for update of entity: " + entity, e);
        }
    }

    public List<RouteForView> getAllRouteForView() {
        List<RouteForView> list;
        String sql = "SELECT routeID, routeTitle, departureCity, arrivalCity, distance, first.cityName, second.cityName FROM route, city first, city second WHERE (route.departureCity = first.cityID)and(route.arrivalCity=second.cityID) order by routeID ";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            list = parseResultSetForView(rs);
        } catch (Exception e) {
            throw new DatabaseException("Failed to retrieve all entities", e);
        }
        return list;
    }

    protected List<RouteForView> parseResultSetForView(ResultSet resultSet) {
        ArrayList<RouteForView> result = new ArrayList<>();
        try {
            while (resultSet.next()) {
                result.add(new RouteForView(resultSet.getInt("routeID"), resultSet.getString("routeTitle"), resultSet.getInt("departureCity"), resultSet.getInt("arrivalCity"), resultSet.getInt("distance"), resultSet.getString("first.cityName"), resultSet.getString("second.cityName")));
            }

        } catch (Exception e) {
            throw new DatabaseException(e);
        }
        return result;
    }
}
