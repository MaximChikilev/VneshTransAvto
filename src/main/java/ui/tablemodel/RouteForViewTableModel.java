package ui.tablemodel;

import dto.RouteForView;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class RouteForViewTableModel extends AbstractTableModel {
    private static final int COLUMN_COUNT = 5;
    private List<RouteForView> routes;

    public RouteForViewTableModel(List<RouteForView> routes){
        this.routes = routes;
    }

    public void removeRow(int rowIndex) {
        this.routes.remove(rowIndex);
        this.fireTableRowsDeleted(rowIndex, rowIndex);
    }

    public void addRow(String routeTitle, int departureCity, int arrivalCity, int distance, String departureCityName, String arrivalCityName) {
        RouteForView newRoute;
        newRoute = new RouteForView();
        newRoute.setRouteTitle(routeTitle);
        newRoute.setDepartureCity(departureCity);
        newRoute.setArrivalCity(arrivalCity);
        newRoute.setDistance(distance);
        newRoute.setDepartureCityName(departureCityName);
        newRoute.setArrivalCityName(arrivalCityName);
        this.routes.add(newRoute);
        this.fireTableRowsInserted(this.routes.size() - 1, this.routes.size() - 1);
    }

    @Override
    public int getRowCount() {
        return routes.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_COUNT;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "id";
            case 1:
                return "Route";
            case 2:
                return "Departure city";
            case 3:
                return "Arrival city";
            case 4:
                return "Distance";
        }
        throw new IllegalArgumentException("Invalid parameter from RouteTableModel ");
    }
    @Override
    public Class<?> getColumnClass(int columnIndex) {

        switch (columnIndex) {
            case 0:
                return Integer.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            case 3:
                return String.class;
            case 4:
                return Integer.class;
        }
        throw new IllegalArgumentException("Invalid parameter from RouteTableModel ");
    }
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return false;
            case 1:
                return true;
            case 2:
                return true;
            case 3:
                return true;
            case 4:
                return true;
        }
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        RouteForView route = routes.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return route.getRouteID();
            case 1:
                return route.getRouteTitle();
            case 2:
                return route.getDepartureCityName();
            case 3:
                return route.getArrivalCityName();
            case 4:
                return route.getDistance();
        }
        throw new IllegalArgumentException("Invalid parameter from RouteTableModel ");
    }
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        RouteForView route = routes.get(rowIndex);
        switch (columnIndex) {
            case 1: {
                route.setRouteTitle((String) aValue);
                break;
            }
            case 2:
                route.setDepartureCityName((String) aValue);
                break;
            case 3:
                route.setArrivalCityName((String) aValue);
                break;
            case 4:
                route.setDistance((int) aValue);
                break;
        }
        this.fireTableDataChanged();
    }
}
