package dao;

import entity.*;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.h2.jdbcx.JdbcDataSource;
import org.h2.tools.RunScript;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

import static junit.framework.Assert.assertEquals;

public class DaoTest {
    private static final String JDBC_DRIVER = org.h2.Driver.class.getName();
    private static final String JDBC_URL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    @BeforeClass
    public static void createSchema() throws Exception {
        RunScript.execute(JDBC_URL, USER, PASSWORD, "src/test/resources/init.sql", null, false);
    }

    @Before
    public void importDataSet() throws Exception {
        IDataSet dataSet = readDataSet();
        cleanlyInsert(dataSet);
    }

    private IDataSet readDataSet() throws Exception {
        return new FlatXmlDataSetBuilder().build(new File("src/test/resources/testbase.xml"));
    }

    private void cleanlyInsert(IDataSet dataSet) throws Exception {
        IDatabaseTester databaseTester = new JdbcDatabaseTester(JDBC_DRIVER, JDBC_URL, USER, PASSWORD);
        databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();
    }

    private Connection getTestConnection() throws SQLException {
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setURL(JDBC_URL);
        dataSource.setUser(USER);
        dataSource.setPassword(PASSWORD);
        return dataSource.getConnection();
    }

    @Test
    public void testCarDao() throws SQLException {
        Car car = new Car(7, 1, "AI1111AI", "1233eedrtt", 2015);
        MySqlCarDao mySqlCarDao = MySqlCarDao.getCarDao(getTestConnection());
        mySqlCarDao.create(car);
        assertEquals("Test create, expected - 2", mySqlCarDao.getAll().size(), 2);
        car.setCarNumber("AI2222AI");
        mySqlCarDao.update(car);
        assertEquals("Test update, expected - AI2222AI", mySqlCarDao.getAll().get(1).getCarNumber(), "AI2222AI");
        mySqlCarDao.delete(7);
        assertEquals("Test delete, expected - 1", mySqlCarDao.getAll().size(), 1);
    }

    @Test
    public void testBrandDao() throws SQLException {
        Brand brand = new Brand(2, "TestBrand2");
        MySqlBrandDao mySqlBrandDao = MySqlBrandDao.getBrandDao(getTestConnection());
        mySqlBrandDao.create(brand);
        assertEquals("Test create, expected - 2", mySqlBrandDao.getAll().size(), 2);
        brand.setBrandName("TestBrand3");
        mySqlBrandDao.update(brand);
        assertEquals("Test update, expected - TestBrand3", mySqlBrandDao.getAll().get(1).getBrandName(),"TestBrand3");
        mySqlBrandDao.delete(2);
        assertEquals("Test delete, expected - 1", mySqlBrandDao.getAll().size(), 1);
    }
    @Test
    public void testCityDao() throws SQLException {
        City city = new City(2, "TestCity2");
        MySqlCityDao mySqlCityDao = MySqlCityDao.getCityDao(getTestConnection());
        mySqlCityDao.create(city);
        assertEquals("Test create, expected - 2", mySqlCityDao.getAll().size(), 2);
        city.setCityName("TestCity3");
        mySqlCityDao.update(city);
        assertEquals("Test update, expected - TestCity3", mySqlCityDao.getAll().get(1).getCityName(),"TestCity3");
        mySqlCityDao.delete(2);
        assertEquals("Test delete, expected - 1", mySqlCityDao.getAll().size(), 1);
    }
    @Test
    public void testExpensesDao() throws SQLException {
        Expenses expenses = new Expenses(2, "TestExpenses2");
        MySqlExpensesDao mySqlExpensesDao = MySqlExpensesDao.getExpensesDao(getTestConnection());
        mySqlExpensesDao.create(expenses);
        assertEquals("Test create, expected - 2", mySqlExpensesDao.getAll().size(), 2);
        expenses.setExpensesName("TestExpenses3");
        mySqlExpensesDao.update(expenses);
        assertEquals("Test update, expected - TestExpenses3", mySqlExpensesDao.getAll().get(1).getExpensesName(),"TestExpenses3");
        mySqlExpensesDao.delete(2);
        assertEquals("Test delete, expected - 1",mySqlExpensesDao.getAll().size(), 1);
    }
    @Test
    public void testClientsDao() throws SQLException {
        Clients clients = new Clients(2, "TestClients2",12345679,"stroiteley","3212121");
        MySqlClientDao mySqlClientsDao = MySqlClientDao.getClientDao(getTestConnection());
        mySqlClientsDao.create(clients);
        assertEquals("Test create, expected - 2", mySqlClientsDao.getAll().size(), 2);
        clients.setClientsName("TestClients3");
        mySqlClientsDao.update(clients);
        assertEquals("Test update, expected - TestExpenses3", mySqlClientsDao.getAll().get(1).getClientsName(),"TestClients3");
        mySqlClientsDao.delete(2);
        assertEquals("Test delete, expected - 1",mySqlClientsDao.getAll().size(), 1);
    }
    @Test
    public void testRouteDao() throws SQLException {
        Route route = new Route(2, "TestRoute2",1,1,123);
        MySqlRouteDao mySqlRouteDao = MySqlRouteDao.getRouteDao(getTestConnection());
        mySqlRouteDao.create(route);
        assertEquals("Test create, expected - 2", mySqlRouteDao.getAll().size(), 2);
        route.setRouteTitle("TestRoute3");
        mySqlRouteDao.update(route);
        assertEquals("Test update, expected - TestExpenses3", mySqlRouteDao.getAll().get(1).getRouteTitle(),"TestRoute3");
        mySqlRouteDao.delete(2);
        assertEquals("Test delete, expected - 1",mySqlRouteDao.getAll().size(), 1);
    }
    @Test
    public void testRouteExpensesDao() throws SQLException {
        RouteExpenses routeExpenses = new RouteExpenses(2,1,123.0F,123);
        MySqlRouteExpensesDao mySqlRouteExpensesDao = MySqlRouteExpensesDao.getRouteExpensesDao(getTestConnection());
        mySqlRouteExpensesDao.create(routeExpenses);
        assertEquals("Test create, expected - 2", mySqlRouteExpensesDao.getAll().size(), 2);
        routeExpenses.setCoast(200.0F);
        mySqlRouteExpensesDao.update(routeExpenses);
        assertEquals("Test update, expected - 200.0", mySqlRouteExpensesDao.getAll().get(1).getCoast(),200.0F);
        mySqlRouteExpensesDao.delete(2);
        assertEquals("Test delete, expected - 1",mySqlRouteExpensesDao.getAll().size(), 1);
    }
}
