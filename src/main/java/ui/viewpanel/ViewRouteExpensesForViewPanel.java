package ui.viewpanel;

import dto.RouteExpensesForView;
import listeners.ViewPanelTableModelListener;
import ui.Main;
import ui.newrecord.NewDBRouteExpensesForView;
import ui.tablemodel.RouteExpensesForViewTableModel;

import java.awt.*;

public class ViewRouteExpensesForViewPanel extends NewViewPanel<RouteExpensesForView> {
    private RouteExpensesForViewTableModel routeExpensesForViewTableModel;

    public ViewRouteExpensesForViewPanel() {
        super();
        resultAR = Main.ROUTE_EXPENSES_DAO.getAllForView(1);
        routeExpensesForViewTableModel = new RouteExpensesForViewTableModel(resultAR);
        viewJTable.setModel(routeExpensesForViewTableModel);
        add(innerPanel, BorderLayout.CENTER);
        viewJTable.setRowSelectionInterval(0, 0);
        routeExpensesForViewTableModel.addTableModelListener(new ViewPanelTableModelListener<>(Main.ROUTE_EXPENSES_DAO, resultAR, viewJTable, this));
    }

    public void changeForViewTableModel(int routeID) {
        resultAR = Main.ROUTE_EXPENSES_DAO.getAllForView(routeID);
        if (resultAR.size() != 0) {
            routeExpensesForViewTableModel = new RouteExpensesForViewTableModel(resultAR);
            viewJTable.setModel(routeExpensesForViewTableModel);
            routeExpensesForViewTableModel.addTableModelListener(new ViewPanelTableModelListener<>(Main.ROUTE_EXPENSES_DAO, resultAR, viewJTable, this));
            viewJTable.setVisible(true);
        } else {
            viewJTable.setVisible(false);
        }
    }

    @Override
    protected void removeMethod() {
        idDeletedRecord = resultAR.get(viewJTable.getSelectedRow()).getRouteExpensesID();
        routeExpensesForViewTableModel.removeRow(viewJTable.getSelectedRow());
    }

    @Override
    protected void addMethod() {
        NewDBRouteExpensesForView newDBRouteExpensesForView = new NewDBRouteExpensesForView(2, Main.viewRoutePanel.getSelectedRoutID(), routeExpensesForViewTableModel);
    }
}
