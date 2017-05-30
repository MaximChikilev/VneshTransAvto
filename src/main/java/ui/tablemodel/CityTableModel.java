package ui.tablemodel;

import entity.City;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class CityTableModel extends AbstractTableModel {
    private List<City> city;
    private static final int COLUMN_COUNT = 2;

    public CityTableModel(List<City> city) {
        this.city = city;
    }

    public void removeRow(int rowIndex) {
        this.city.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    public void addRow(String cityName) {
        City newCity;
        newCity = new City();
        newCity.setCityName(cityName);
        this.city.add(newCity);
        fireTableRowsInserted(this.city.size() - 1, this.city.size() - 1);
    }

    @Override
    public int getRowCount() {
        return city.size();
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
                return "City";
        }
        throw new IllegalArgumentException("Invalid parameter from CityTableModel ");
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {

        switch (columnIndex) {
            case 0:
                return Integer.class;
            case 1:
                return String.class;
        }
        throw new IllegalArgumentException("Invalid parameter from CityTableModel ");
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
        City citys = city.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return citys.getCityID();
            case 1:
                return citys.getCityName();
        }
        throw new IllegalArgumentException("Invalid parameter from CityTableModel ");
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        City citys = city.get(rowIndex);
        switch (columnIndex) {
            case 0: {
                citys.setCityID((Integer) aValue);
                break;
            }
            case 1:
                citys.setCityName((String) aValue);
                break;

        }
        this.fireTableDataChanged();

    }
}
