package ui.tablemodel;

import dto.CarForView;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class CarForViewTableModel extends AbstractTableModel {
    private static final int COLUMN_COUNT = 5;
    private List<CarForView> cars;

    public CarForViewTableModel(List<CarForView> cars) {
        this.cars = cars;
    }

    public void removeRow(int rowIndex) {
        this.cars.remove(rowIndex);
        this.fireTableRowsDeleted(rowIndex, rowIndex);
    }

    public void addRow(String carNumber, int carBrand, String carShassis, int carYear, String carBrandName) {
        CarForView newCar;
        newCar = new CarForView();
        newCar.setCarNumber(carNumber);
        newCar.setCarBrand(carBrand);
        newCar.setCarShassis(carShassis);
        newCar.setCarYear(carYear);
        newCar.setCarBrandName(carBrandName);
        this.cars.add(newCar);
        this.fireTableRowsInserted(this.cars.size() - 1, this.cars.size() - 1);
    }

    @Override
    public int getRowCount() {
        return cars.size();
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
                return "Brand";
            case 2:
                return "Car number";
            case 3:
                return "Shassis";
            case 4:
                return "Year of manufacturing";
        }
        throw new IllegalArgumentException("Invalid parameter from CarTableModel ");
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
        throw new IllegalArgumentException("Invalid parameter from CarTableModel ");
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
        CarForView car = cars.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return car.getCarID();
            case 1:
                return car.getCarBrandName();
            case 2:
                return car.getCarNumber();
            case 3:
                return car.getCarShassis();
            case 4:
                return car.getCarYear();
        }
        throw new IllegalArgumentException("Invalid parameter from CarTableModel ");
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        CarForView car = cars.get(rowIndex);
        switch (columnIndex) {
            case 1: {
                car.setCarBrandName((String) aValue);
                break;
            }
            case 2:
                car.setCarNumber((String) aValue);
                break;
            case 3:
                car.setCarShassis((String) aValue);
                break;
            case 4:
                car.setCarYear((int) aValue);
                break;
        }
        this.fireTableDataChanged();

    }
}
