package dao;

import entity.Clients;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MySqlClientDao extends AbstractJDBCDao<Clients> {
    private static MySqlClientDao instance;

    private MySqlClientDao(Connection connection) {
        super(connection);
    }

    public static MySqlClientDao getClientDao(Connection connection) {
        if (instance == null) {
            instance = new MySqlClientDao(connection);
        }
        return instance;
    }

    @Override
    protected String getSelectQuery() {
        return "SELECT * FROM clients";
    }

    @Override
    protected String getCreateQuery() {
        return "INSERT INTO clients (Name, OKPO, Adress, phone) VALUES (?,?,?,?)";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE clients SET Name=?, OKPO=?, Adress=?, Phone=? WHERE id=?";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM clients WHERE id=?";
    }

    @Override
    protected List<Clients> parseResultSet(ResultSet resultSet){
        ArrayList<Clients> result = new ArrayList<>();
        try {
            while (resultSet.next()) {
                result.add(new Clients(resultSet.getInt("id"), resultSet.getString("Name"), resultSet.getInt("OKPO"), resultSet.getString("Adress"), resultSet.getString("Phone")));
            }

        } catch (Exception e) {
            throw new DatabaseException("Filed to parse result set", e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForCreate(PreparedStatement statement, Clients entity) {
        try {
            statement.setString(1, entity.getClientsName());
            statement.setInt(2, entity.getClientOKPO());
            statement.setString(3, entity.getClientAdress());
            statement.setString(4, entity.getClientPhone());
        } catch (Exception e) {
            throw new DatabaseException("Failed to prepare the statement for creation of entity: " + entity, e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Clients entity) {
        try {
            statement.setString(1, entity.getClientsName());
            statement.setInt(2, entity.getClientOKPO());
            statement.setString(3, entity.getClientAdress());
            statement.setString(4, entity.getClientPhone());
            statement.setInt(5, entity.getClientsID());
        } catch (Exception e) {
            throw new DatabaseException("Failed to prepare the statement for update of entity: " + entity, e);
        }
    }
}
