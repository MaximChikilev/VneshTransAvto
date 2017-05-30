package ui;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MainMenu extends JMenuBar {
    public JMenuItem exitMenuItem;
    public JMenuItem carMenuItem;
    public JMenuItem clientsMenuItem;
    public JMenuItem cityMenuItem;
    public JMenuItem brandMenuItem;
    public JMenuItem expensesMenuItem;
    public JMenuItem routeMenuItem;


    public MainMenu() {
        JMenu programmMenu = new JMenu("Program");
        JMenu directoryMenu = new JMenu("Dictionary");
        brandMenuItem = new JMenuItem("Brand");
        exitMenuItem = new JMenuItem("Exit");
        carMenuItem = new JMenuItem("Car");
        clientsMenuItem = new JMenuItem("Client");
        cityMenuItem = new JMenuItem("City");
        expensesMenuItem = new JMenuItem("Expenses");
        routeMenuItem = new JMenuItem("Route");

        directoryMenu.add(brandMenuItem);
        directoryMenu.add(carMenuItem);
        directoryMenu.add(clientsMenuItem);
        directoryMenu.add(cityMenuItem);
        directoryMenu.add(expensesMenuItem);
        directoryMenu.add(routeMenuItem);
        programmMenu.add(exitMenuItem);
        add(programmMenu);
        add(directoryMenu);
    }
}
