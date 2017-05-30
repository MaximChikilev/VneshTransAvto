package dao;

import entity.City;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class MySqlCityDao extends AbstractJDBCDao<City> {
    private static MySqlCityDao instance;

    private MySqlCityDao(Connection connection) {
        super(connection);
    }

    public static MySqlCityDao getCityDao(Connection connection) {
        if (instance == null) {
            instance = new MySqlCityDao(connection);
        }
        return instance;
    }

    @Override
    protected String getSelectQuery() {
        return "SELECT * FROM city";
    }

    @Override
    protected String getCreateQuery() {
        return "INSERT INTO city (cityName) VALUES (?)";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE city SET cityName=? WHERE cityID=?";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM city WHERE cityID=?";
    }

    @Override
    protected List<City> parseResultSet(ResultSet resultSet) {
        ArrayList<City> result = new ArrayList<>();
        try {
            while (resultSet.next()) {
                result.add(new City(resultSet.getInt("cityID"), resultSet.getString("cityName")));
            }

        } catch (Exception e) {
            throw new DatabaseException(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForCreate(PreparedStatement statement, City entity) {
        try {
            statement.setString(1, entity.getCityName());
        } catch (Exception e) {
            throw new DatabaseException("Failed to prepare the statement for creation of entity: " + entity, e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, City entity) {
        try {
            statement.setString(1, entity.getCityName());
            statement.setInt(2, entity.getCityID());
        } catch (Exception e) {
            throw new DatabaseException("Failed to prepare the statement for update of entity: " + entity, e);
        }
    }

    public String[] getCityList() {
            return getArrayForComboBox("SELECT cityName FROM city", "cityName");
    }
}
