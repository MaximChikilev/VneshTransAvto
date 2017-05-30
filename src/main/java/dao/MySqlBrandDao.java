package dao;

import entity.Brand;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class MySqlBrandDao extends AbstractJDBCDao<Brand> {

    private static MySqlBrandDao instance;

    private MySqlBrandDao(Connection connection) {
        super(connection);
    }

    public static MySqlBrandDao getBrandDao(Connection connection) {
        if (instance == null) {
            instance = new MySqlBrandDao(connection);
        }
        return instance;
    }

    @Override
    protected String getSelectQuery() {
        return "SELECT * FROM brand";
    }

    @Override
    protected String getCreateQuery() {
        return "INSERT INTO brand (brandName) VALUES (?)";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE brand SET brandName=? WHERE brandID=?";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM brand WHERE brandId=?";
    }

    @Override
    protected List<Brand> parseResultSet(ResultSet resultSet) {
        ArrayList<Brand> result = new ArrayList<>();
        try {
            while (resultSet.next()) {
                result.add(new Brand(resultSet.getInt("brandID"), resultSet.getString("brandName")));
            }

        } catch (Exception e) {
            throw new DatabaseException(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForCreate(PreparedStatement statement, Brand entity) {
        try {
            statement.setString(1, entity.getBrandName());
        } catch (Exception e) {
            throw new DatabaseException("Failed to prepare the statement for creation of entity: " + entity, e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Brand entity) {
        try {
            statement.setString(1, entity.getBrandName());
            statement.setInt(2, entity.getBrandID());
        } catch (Exception e) {
            throw new DatabaseException("Failed to prepare the statement for update of entity: " + entity, e);
        }
    }

    public String[] getBrandList() {
        return getArrayForComboBox("SELECT brandName FROM brand", "brandName");
    }

}
