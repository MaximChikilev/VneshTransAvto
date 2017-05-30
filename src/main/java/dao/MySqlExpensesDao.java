package dao;

import entity.Expenses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MySqlExpensesDao extends AbstractJDBCDao<Expenses> {
    private static MySqlExpensesDao instance;

    protected MySqlExpensesDao(Connection connection) {
        super(connection);
    }

    public static MySqlExpensesDao getExpensesDao(Connection connection) {
        if (instance == null) {
            instance = new MySqlExpensesDao(connection);
        }
        return instance;
    }

    @Override
    protected String getSelectQuery() {
        return "SELECT * FROM expenses";
    }

    @Override
    protected String getCreateQuery() {
        return "INSERT INTO expenses (expensesName) VALUES (?)";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE expenses SET expensesName=? WHERE expensesID=?";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM expenses WHERE expensesID=?";
    }

    @Override
    protected List<Expenses> parseResultSet(ResultSet resultSet)  {
        ArrayList<Expenses> result = new ArrayList<>();
        try {
            while (resultSet.next()) {
                result.add(new Expenses(resultSet.getInt("expensesID"), resultSet.getString("expensesName")));
            }

        } catch (Exception e) {
            throw new DatabaseException(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForCreate(PreparedStatement statement, Expenses entity)  {
        try {
            statement.setString(1, entity.getExpensesName());
        } catch (Exception e) {
            throw new DatabaseException("Failed to prepare the statement for creation of entity: " + entity, e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Expenses entity)  {
        try {
            statement.setString(1, entity.getExpensesName());
            statement.setInt(2, entity.getExpensesID());
        } catch (Exception e) {
            throw new DatabaseException("Failed to prepare the statement for update of entity: " + entity, e);
        }
    }

    public String[] getExpensesList() {
        return getArrayForComboBox("SELECT expensesName FROM expenses", "expensesName");
    }
}
