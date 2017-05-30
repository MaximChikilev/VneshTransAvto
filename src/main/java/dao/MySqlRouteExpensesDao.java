package dao;

import entity.RouteExpenses;
import dto.RouteExpensesForView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MySqlRouteExpensesDao extends AbstractJDBCDao<RouteExpenses> {
    private static MySqlRouteExpensesDao instance;

    private MySqlRouteExpensesDao(Connection connection) {
        super(connection);
    }

    public static MySqlRouteExpensesDao getRouteExpensesDao(Connection connection) {
        if (instance == null) {
            instance = new MySqlRouteExpensesDao(connection);
        }
        return instance;
    }

    @Override
    protected String getSelectQuery() {
        return "SELECT * FROM routeExpenses";
    }

    @Override
    protected String getCreateQuery() {
        return "INSERT INTO routeExpenses (routeID, expensesID, expensesCoast) VALUES (?,?,?)";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE routeExpenses SET routeID=?, expensesID=?, expensesCoast=? WHERE routeExpencesID=?";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM routeExpenses WHERE routeExpencesID=?";
    }

    @Override
    protected List<RouteExpenses> parseResultSet(ResultSet resultSet) {
        ArrayList<RouteExpenses> result = new ArrayList<>();
        try {
            while (resultSet.next()) {
                result.add(new RouteExpenses(resultSet.getInt("routeExpencesID"), resultSet.getInt("routeID"), resultSet.getFloat("expensesCoast"), resultSet.getInt("expensesID")));
            }

        } catch (Exception e) {
            throw new DatabaseException(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForCreate(PreparedStatement statement, RouteExpenses entity) {
        try {
            statement.setInt(1, entity.getRouteID());
            statement.setInt(2, entity.getExpensesID());
            statement.setFloat(3, entity.getCoast());
        } catch (Exception e) {
            throw new DatabaseException("Failed to prepare the statement for creation of entity: " + entity, e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, RouteExpenses entity)  {
        try {
            statement.setInt(1, entity.getRouteID());
            statement.setInt(2, entity.getExpensesID());
            statement.setFloat(3, entity.getCoast());
            statement.setInt(4, entity.getRouteExpensesID());
        } catch (Exception e) {
            throw new DatabaseException("Failed to prepare the statement for update of entity: " + entity, e);
        }
    }
    protected void prepareStatementForSelect(PreparedStatement statement, int routeID)  {
        try {
            statement.setInt(1, routeID);
        } catch (Exception e) {
            throw new DatabaseException("Failed to prepare the statement for select: ", e);
        }
    }
    public List<RouteExpensesForView> getAllForView(int routeID)  {
        List<RouteExpensesForView> list;
        String sql = "SELECT routeexpenses.routeExpencesId, route.routeTitle, routeexpenses.routeID, expenses.expensesName, routeexpenses.expensesID, routeexpenses.expensesCoast FROM route, expenses, routeexpenses WHERE route.routeID=? and routeexpenses.routeID=route.routeID and routeexpenses.expensesID = expenses.expensesID";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            prepareStatementForSelect(statement, routeID);
            ResultSet rs = statement.executeQuery();
            list = parseResultSetForView(rs);
        } catch (Exception e) {
            throw new DatabaseException("Failed to retrieve all entities", e);
        }
        return list;
    }
    protected List<RouteExpensesForView> parseResultSetForView(ResultSet resultSet) {
        ArrayList<RouteExpensesForView> result = new ArrayList<>();
        try {
            while (resultSet.next()) {
                result.add(new RouteExpensesForView(resultSet.getInt("routeexpenses.routeExpencesId"), resultSet.getInt("routeID"), resultSet.getFloat("expensesCoast"), resultSet.getInt("expensesID"), resultSet.getString("expensesName")));
            }

        } catch (Exception e) {
            throw new DatabaseException(e);
        }
        return result;
    }

}
