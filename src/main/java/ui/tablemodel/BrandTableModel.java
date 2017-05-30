package ui.tablemodel;

import entity.Brand;

import javax.swing.table.AbstractTableModel;
import java.util.List;

 public class BrandTableModel extends AbstractTableModel {
    private List<Brand> brands;
    private static final int COLUMN_COUNT = 2;

    public BrandTableModel(List<Brand> brands) {
        this.brands = brands;
    }

    public void removeRow(int rowIndex) {
        this.brands.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    public void addRow(String brandName) {
        Brand newBrand;
        newBrand = new Brand();
        newBrand.setBrandName(brandName);
        this.brands.add(newBrand);
        fireTableRowsInserted(this.brands.size() - 1, this.brands.size() - 1);
    }

    @Override
    public int getRowCount() {
        return brands.size();
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
        }
        throw new IllegalArgumentException("Invalid parameter from BrantTableModel ");
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {

        switch (columnIndex) {
            case 0:
                return Integer.class;
            case 1:
                return String.class;
        }
        throw new IllegalArgumentException("Invalid parameter from BrantTableModel ");
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
        Brand brand = brands.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return brand.getBrandID();
            case 1:
                return brand.getBrandName();
        }
        throw new IllegalArgumentException("Invalid parameter from BrantTableModel ");
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Brand brand = brands.get(rowIndex);
        switch (columnIndex) {
            case 0: {
                brand.setBrandID((Integer) aValue);
                break;
            }
            case 1:
                brand.setBrandName((String) aValue);
                break;

        }
        this.fireTableDataChanged();

    }
}
