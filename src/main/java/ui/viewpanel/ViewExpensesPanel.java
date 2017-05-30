package ui.viewpanel;

import entity.Expenses;
import listeners.ViewPanelTableModelListener;
import ui.Main;
import ui.newrecord.NewDBExpensesRecord;
import ui.tablemodel.ExpensesTableModel;

import java.awt.*;

public class ViewExpensesPanel extends NewViewPanel<Expenses> {
    private ExpensesTableModel expensesTableModel;

    public ViewExpensesPanel() {
        super();
        resultAR = Main.EXPENSES_DAO.getAll();
        expensesTableModel = new ExpensesTableModel(resultAR);
        this.viewJTable.setModel(expensesTableModel);
        this.add(innerPanel, BorderLayout.CENTER);
        viewJTable.setRowSelectionInterval(0, 0);
        viewJTable.getColumnModel().getColumn(0).setMaxWidth(50);
        viewJTable.getTableHeader().setReorderingAllowed(false);
        expensesTableModel.addTableModelListener(new ViewPanelTableModelListener<>(Main.EXPENSES_DAO, resultAR, viewJTable, this));
    }

    @Override
    protected void removeMethod() {
        idDeletedRecord=resultAR.get(viewJTable.getSelectedRow()).getExpensesID();
        expensesTableModel.removeRow(viewJTable.getSelectedRow());
    }

    @Override
    protected void addMethod() {
        NewDBExpensesRecord newDBExpensesRecord = new NewDBExpensesRecord(1, expensesTableModel);
    }
}
