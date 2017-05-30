package dao;

import dto.CarForView;
import entity.Car;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MySqlCarDao extends AbstractJDBCDao<Car> {
    private static MySqlCarDao instance;

    private MySqlCarDao(Connection connection) {
        super(connection);
    }

    public static MySqlCarDao getCarDao(Connection connection) {
        if (instance == null) {
            instance = new MySqlCarDao(connection);
        }
        return instance;
    }

    @Override
    protected String getSelectQuery() {
        return "SELECT * FROM car";
    }

    @Override
    protected String getCreateQuery() {
        return "INSERT INTO car (brandCar, numberCar, shassiCar, yearCar) VALUES (?,?,?,?)";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE car SET brandCar=?, numberCar=?, shassiCar=?, yearCar=? WHERE id_Car=?";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM car WHERE id_Car=?";
    }

    @Override
    protected List<Car> parseResultSet(ResultSet resultSet) {
        ArrayList<Car> result = new ArrayList<>();
        try {
            while (resultSet.next()) {
                result.add(new Car(resultSet.getInt("id_car"), resultSet.getInt("brandCar"), resultSet.getString("numberCar"), resultSet.getString("shassiCar"), resultSet.getInt("yearCar")));
            }

        } catch (Exception e) {
            throw new DatabaseException(e);
        }
        return result;

    }

    @Override
    protected void prepareStatementForCreate(PreparedStatement statement, Car entity) {
        try {
            statement.setInt(1, entity.getCarBrand());
            statement.setString(2, entity.getCarNumber());
            statement.setString(3, entity.getCarShassis());
            statement.setInt(4, entity.getCarYear());
        } catch (Exception e) {
            throw new DatabaseException("Failed to prepare the statement for creation of entity: " + entity, e);
        }

    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Car entity) {
        try {
            statement.setInt(1, entity.getCarBrand());
            statement.setString(2, entity.getCarNumber());
            statement.setString(3, entity.getCarShassis());
            statement.setInt(4, entity.getCarYear());
            statement.setInt(5, entity.getCarID());
        } catch (Exception e) {
            throw new DatabaseException("Failed to prepare the statement for update of entity: " + entity, e);
        }
    }

    public List<CarForView> getAllCarForView() {
        List<CarForView> list;
        String sql = "SELECT car.id_car, car.brandCar, car.numberCar, car.shassiCar, car.yearCar, brand.brandName FROM car, brand WHERE car.brandCar = brand.brandID ORDER BY car.id_car";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            list = parseResultSetForView(rs);
        } catch (Exception e) {
            throw new DatabaseException("Failed to retrieve all entities", e);
        }
        return list;
    }

    protected List<CarForView> parseResultSetForView(ResultSet resultSet) {
        ArrayList<CarForView> result = new ArrayList<>();
        try {
            while (resultSet.next()) {
                result.add(new CarForView(resultSet.getInt("car.id_car"), resultSet.getInt("car.brandCar"), resultSet.getString("car.numberCar"), resultSet.getString("car.shassiCar"), resultSet.getInt("car.yearCar"), resultSet.getString("brand.brandName")));
            }

        } catch (Exception e) {
            throw new DatabaseException(e);
        }
        return result;
    }

}
