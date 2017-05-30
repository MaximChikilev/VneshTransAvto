package listeners;

import ui.viewpanel.ViewRouteExpensesForViewPanel;
import ui.viewpanel.ViewRoutePanel;
import ui.Main;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RouteMenuListeners implements ActionListener {
    private JFrame mainFrame;
    private ViewRoutePanel viewRoutePanel;
    private ViewRouteExpensesForViewPanel viewRouteExpensesForViewPanel;

    public RouteMenuListeners(JFrame mainFrame, ViewRoutePanel viewRoutePanel, ViewRouteExpensesForViewPanel viewRouteExpensesForViewPanel) {
        this.mainFrame = mainFrame;
        this.viewRoutePanel = viewRoutePanel;
        this.viewRouteExpensesForViewPanel = viewRouteExpensesForViewPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Main.checkVisible();
        if (viewRoutePanel == null) {
            viewRoutePanel = new ViewRoutePanel();
            Main.viewRoutePanel = this.viewRoutePanel;
        }
        if (viewRouteExpensesForViewPanel == null) {
            viewRouteExpensesForViewPanel = new ViewRouteExpensesForViewPanel();
            Main.viewRouteExpensesForViewPanel = this.viewRouteExpensesForViewPanel;
        }

        viewRoutePanel.setVisible(true);
        viewRouteExpensesForViewPanel.setVisible(true);
        mainFrame.add(viewRouteExpensesForViewPanel, BorderLayout.EAST);
        mainFrame.add(viewRoutePanel, BorderLayout.CENTER);

    }
}
