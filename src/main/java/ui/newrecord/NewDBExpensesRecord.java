package ui.newrecord;

import ui.tablemodel.ExpensesTableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewDBExpensesRecord extends NewDBRecordFrame {
    public NewDBExpensesRecord(int amount, final ExpensesTableModel tableModel) {
        super(amount);
        setLabelTextFieldName(0, "Новая затрата");
        yesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkNewDBRecord()) {
                    tableModel.addRow(getTextFieldValue(0));
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
