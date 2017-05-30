package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractJDBCDao<T> implements GenericDao<T> {
    protected Connection connection;

    protected AbstractJDBCDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void update(T entity) {
        String sql = getUpdateQuery();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            prepareStatementForUpdate(statement, entity);
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new DatabaseException("On update modify more then 1 record: " + count);
            }
        } catch (Exception e) {
            throw new DatabaseException("Failed to update the entity: " + entity, e);
        }
    }

    @Override
    public void create(T entity) {
        String sql = getCreateQuery();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            prepareStatementForCreate(statement, entity);
            statement.executeUpdate();
        } catch (Exception e) {
            throw new DatabaseException("Failed to store the entity: " + entity, e);
        }
    }

    @Override
    public void delete(int key) {
        String sql = getDeleteQuery();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            prepareStatementForDelete(statement, key);
            statement.executeUpdate();
        } catch (Exception e) {
            throw new DatabaseException("Failed to delete the entity by key: " + key, e);
        }

    }

    @Override
    public List<T> getAll() {
        List<T> list;
        String sql = getSelectQuery();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            throw new DatabaseException("Failed to retrieve all entities", e);
        }
        return list;
    }

    protected abstract String getSelectQuery();

    protected abstract String getCreateQuery();

    protected abstract String getUpdateQuery();

    protected abstract String getDeleteQuery();

    protected abstract List<T> parseResultSet(ResultSet resultSet);

    protected abstract void prepareStatementForCreate(PreparedStatement statement, T entity);

    protected abstract void prepareStatementForUpdate(PreparedStatement statement, T entity);

    protected void prepareStatementForDelete(PreparedStatement statement, int key) {
        try {
            statement.setInt(1, key);
        } catch (Exception e) {
            throw new DatabaseException("Failed to prepare the statement for delete with key: " + key, e);
        }
    }

    protected String[] getArrayForComboBox(String sql, String nameColumn){
        List<String> list;
        list = parseResultSetForComboBox(sql, nameColumn);
        String[] arrayForComboBox = new String[list.size()];
        return list.toArray(arrayForComboBox);
    }
    private List<String> parseResultSetForComboBox(String sql, String nameColumn) {
        List<String> listForComboBox = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                listForComboBox.add(resultSet.getString(nameColumn));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listForComboBox;
    }


}
