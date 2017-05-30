package ui.tablemodel;

import entity.Expenses;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ExpensesTableModel extends AbstractTableModel {
    private List<Expenses> expenses;
    private static final int COLUMN_COUNT = 2;

    public ExpensesTableModel(List<Expenses> expenses) {
        this.expenses = expenses;
    }

    public void removeRow(int rowIndex) {
        this.expenses.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    public void addRow(String brandName) {
        Expenses newExpenses;
        newExpenses = new Expenses();
        newExpenses.setExpensesName(brandName);
        this.expenses.add(newExpenses);
        fireTableRowsInserted(this.expenses.size() - 1, this.expenses.size() - 1);
    }
    @Override
    public int getRowCount() {
        return expenses.size();
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
                return "Expenses";
        }
        throw new IllegalArgumentException("Invalid parameter from ExpensesTableModel ");
    }
    @Override
    public Class<?> getColumnClass(int columnIndex) {

        switch (columnIndex) {
            case 0:
                return Integer.class;
            case 1:
                return String.class;
        }
        throw new IllegalArgumentException("Invalid parameter from ExpensesTableModel ");
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
        Expenses expensess = expenses.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return expensess.getExpensesID();
            case 1:
                return expensess.getExpensesName();
        }
        throw new IllegalArgumentException("Invalid parameter from ExpensesTableModel ");
    }
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Expenses currentExpenses = expenses.get(rowIndex);
        switch (columnIndex) {
            case 0: {
                currentExpenses.setExpensesID((Integer) aValue);
                break;
            }
            case 1:
                currentExpenses.setExpensesName((String) aValue);
                break;

        }
        this.fireTableDataChanged();

    }
}
