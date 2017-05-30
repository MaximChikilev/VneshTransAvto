package ui.newrecord;

import entity.Expenses;
import ui.Main;
import ui.tablemodel.RouteExpensesForViewTableModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class NewDBRouteExpensesForView extends NewDBRecordFrame {
    private int expensesID;
    private List<Expenses> expenses;
    private String expensesTitle;

    public NewDBRouteExpensesForView(int amount, final int routeID, final RouteExpensesForViewTableModel tm) {
        super(amount);
        setLabelTextFieldName(1, "Новая затрата");
        setLabelTextFieldName(0, "Стоимость затраты");
        JComboBox<String> expensesNameComboBox = new JComboBox<>(Main.EXPENSES_DAO.getExpensesList());
        comboBoxes.add(expensesNameComboBox);
        expenses = Main.EXPENSES_DAO.getAll();
        super.changeTextField(1, comboBoxes);

        yesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkNewDBRecord()) {
                    expensesID = expenses.get(comboBoxes.get(0).getSelectedIndex()).getExpensesID();
                    expensesTitle = (String) comboBoxes.get(0).getSelectedItem();
                    tm.addRow(routeID,Float.parseFloat(getTextFieldValue(0)),expensesID,expensesTitle);
                    dispose();
                }
            }
        });
    }
    @Override
    public boolean checkNewDBRecord() {
        return true;
    }
}
