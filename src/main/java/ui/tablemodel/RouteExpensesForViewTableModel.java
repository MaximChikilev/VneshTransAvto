package ui.tablemodel;

import dto.RouteExpensesForView;

import javax.swing.table.AbstractTableModel;
import java.util.List;


public class RouteExpensesForViewTableModel extends AbstractTableModel {
    private static final int COLUMN_COUNT = 2;
    private List<RouteExpensesForView> routeExpensesForViews;

    public RouteExpensesForViewTableModel(List<RouteExpensesForView> routeExpensesForViews) {
        this.routeExpensesForViews = routeExpensesForViews;
    }
    public void addRow(int routeID, float coast, int expensesID, String expensesTitle) {
        RouteExpensesForView routeExpensesForView;
        routeExpensesForView = new RouteExpensesForView();
        routeExpensesForView.setRouteID(routeID);
        routeExpensesForView.setCoast(coast);
        routeExpensesForView.setExpensesID(expensesID);
        routeExpensesForView.setExpensesTitle(expensesTitle);
        this.routeExpensesForViews.add(routeExpensesForView);
        this.fireTableRowsInserted(this.routeExpensesForViews.size() - 1, this.routeExpensesForViews.size() - 1);
    }
    @Override
    public int getRowCount() {
        return routeExpensesForViews.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_COUNT;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "Expenses";
            case 1:
                return "Coast";
        }
        return "";
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {

        switch (columnIndex) {
            case 0:
                return String.class;
            case 1:
                return Float.class;
        }
        throw new IllegalArgumentException("Invalid parameter from RouteExpensesForViewTableModel ");
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return false;
            case 1:
                return true;
        }
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        RouteExpensesForView routeExpensesForView = routeExpensesForViews.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return routeExpensesForView.getExpensesTitle();
            case 1:
                return routeExpensesForView.getCoast();
        }
        throw new IllegalArgumentException("Invalid parameter from RouteExpensesForViewTableModel ");
    }

    public void removeRow(int rowIndex) {
        this.routeExpensesForViews.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }
}
