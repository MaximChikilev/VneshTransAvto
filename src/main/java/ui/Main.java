package ui;

import dao.*;
import listeners.*;
import ui.viewpanel.*;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import static java.awt.Frame.MAXIMIZED_BOTH;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Main {
    public static final MySqlCarDao CAR_DAO;
    public static final MySqlBrandDao BRAND_DAO;
    public static final MySqlClientDao CLIENT_DAO;
    public static final MySqlCityDao CITY_DAO;
    public static final MySqlExpensesDao EXPENSES_DAO;
    public static final MySqlRouteDao ROUTE_DAO;
    public static final MySqlRouteExpensesDao ROUTE_EXPENSES_DAO;
    public static JFrame mainFrame;
    public static ViewCarPanel viewCarPanel;
    public static ViewBrandPanel viewBrandPanel;
    public static ViewClientPanel viewClientPanel;
    public static ViewCityPanel viewCityPanel;
    public static ViewExpensesPanel viewExpensesPanel;
    public static ViewRoutePanel viewRoutePanel;
    public static ViewRouteExpensesForViewPanel viewRouteExpensesForViewPanel;
    private static MainMenu mainMenu;

    static {
        Connection connection = MySqlConnectionFactory.getConnection();
        CAR_DAO = MySqlCarDao.getCarDao(connection);
        BRAND_DAO = MySqlBrandDao.getBrandDao(connection);
        CLIENT_DAO = MySqlClientDao.getClientDao(connection);
        CITY_DAO = MySqlCityDao.getCityDao(connection);
        EXPENSES_DAO = MySqlExpensesDao.getExpensesDao(connection);
        ROUTE_DAO = MySqlRouteDao.getRouteDao(connection);
        ROUTE_EXPENSES_DAO = MySqlRouteExpensesDao.getRouteExpensesDao(connection);
    }

    public Main() {
        mainFrame = new JFrame("Внештранс авто v1.1");
        mainFrame.setLayout(new BorderLayout());
        mainFrame.setExtendedState(MAXIMIZED_BOTH);
        mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        mainMenu = new MainMenu();
        mainFrame.add(mainMenu, BorderLayout.NORTH);
        mainFrame.setVisible(true);
        setListeners();
    }

    private void setListeners() {
        mainMenu.brandMenuItem.addActionListener(new BrandMenuListener(mainFrame, viewBrandPanel));
        mainMenu.carMenuItem.addActionListener(new CarMenuListener(mainFrame, viewCarPanel));
        mainMenu.clientsMenuItem.addActionListener(new ClientsMenuListeners(mainFrame, viewClientPanel));
        mainMenu.cityMenuItem.addActionListener(new CityMenuListener(mainFrame, viewCityPanel));
        mainMenu.expensesMenuItem.addActionListener(new ExpensesMenuListener(mainFrame, viewExpensesPanel));
        mainMenu.routeMenuItem.addActionListener(new RouteMenuListeners(mainFrame, viewRoutePanel, viewRouteExpensesForViewPanel));
        mainMenu.exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public static void checkVisible() {
        checkViewPanel(viewCarPanel, mainFrame);
        checkViewPanel(viewBrandPanel, mainFrame);
        checkViewPanel(viewClientPanel, mainFrame);
        checkViewPanel(viewCityPanel, mainFrame);
        checkViewPanel(viewExpensesPanel, mainFrame);
        checkViewPanel(viewRoutePanel, mainFrame);

    }

    public static void checkViewPanel(NewViewPanel viewPanel, JFrame mainFrame) {
        if ((viewPanel != null) && (viewPanel.isVisible())) {
            viewPanel.setVisible(false);
            mainFrame.remove(viewPanel);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main();
            }
        });
    }
}
