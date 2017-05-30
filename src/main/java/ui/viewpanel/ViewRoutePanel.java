package ui.viewpanel;

import dao.DatabaseException;
import dto.RouteForView;
import listeners.ViewPanelTableModelListener;
import ui.Main;
import ui.newrecord.NewDBRouteRecord;
import ui.tablemodel.RouteForViewTableModel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

public class ViewRoutePanel extends NewViewPanel<RouteForView> {
    private RouteForViewTableModel routeForViewTableModel;

    public ViewRoutePanel() {
        super();
        resultAR = Main.ROUTE_DAO.getAllRouteForView();
        routeForViewTableModel = new RouteForViewTableModel(resultAR);
        viewJTable.setModel(routeForViewTableModel);
        add(innerPanel, BorderLayout.CENTER);
        viewJTable.setRowSelectionInterval(0, 0);
        JComboBox<String> departureCityComboBox = new JComboBox<>(Main.CITY_DAO.getCityList());
        JComboBox<String> arrivalCityComboBox = new JComboBox<>(Main.CITY_DAO.getCityList());
        setDefaultCBEditor(2, departureCityComboBox);
        setDefaultCBEditor(3, arrivalCityComboBox);
        ListSelectionModel selectionModel = viewJTable.getSelectionModel();
        selectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                try {
                    int row = viewJTable.getSelectedRow();
                    if (row == -1) {
                        row = viewJTable.getEditingRow();
                    }
                    Main.viewRouteExpensesForViewPanel.changeForViewTableModel((Integer) routeForViewTableModel.getValueAt(row, 0));
                } catch (DatabaseException x) {
                    x.printStackTrace();
                }
            }
        });
        routeForViewTableModel.addTableModelListener(new ViewPanelTableModelListener<>(Main.ROUTE_DAO, resultAR, viewJTable, this));
    }

    public int getSelectedRoutID() {
        final int selectedRow = viewJTable.getSelectedRow();
        final RouteForView routeForView = resultAR.get(selectedRow);
        return routeForView.getRouteID();
    }

    @Override
    protected void removeMethod() {
        final int selectedRow = viewJTable.getSelectedRow();
        idDeletedRecord = resultAR.get(selectedRow).getRouteID();
        routeForViewTableModel.removeRow(selectedRow);
    }

    @Override
    protected void addMethod() {
        try {
            NewDBRouteRecord newDBRouteRecord = new NewDBRouteRecord(4, routeForViewTableModel);
        } catch (DatabaseException e1) {
            e1.printStackTrace();
        }
    }

}
