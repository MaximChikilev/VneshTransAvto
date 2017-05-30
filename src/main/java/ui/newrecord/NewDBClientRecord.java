package ui.newrecord;

import ui.CheckUtils;
import ui.tablemodel.ClientTableModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewDBClientRecord extends NewDBRecordFrame {

    public NewDBClientRecord(int amount, final ClientTableModel tableModel) {
        super(amount);
        setLabelTextFieldName(0, "Название компании");
        setLabelTextFieldName(1, "ОКПО");
        setLabelTextFieldName(2, "Адрес");
        setLabelTextFieldName(3, "Телефон");
        yesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkNewDBRecord()) {
                    tableModel.addRow(getTextFieldValue(0), Integer.parseInt(getTextFieldValue(1)), getTextFieldValue(2), getTextFieldValue(3));
                    dispose();
                }
            }
        });
    }

    @Override
    public boolean checkNewDBRecord() {
        Boolean flag = true;
        if (!CheckUtils.checkLength(getTextFieldValue(1), 8)) {
            JOptionPane.showMessageDialog(this, "ОКПО должен быть равен 8 символам");
            flag = false;
        }
        if (!CheckUtils.isInteger(getTextFieldValue(1))) {
            JOptionPane.showMessageDialog(this, "ОКПО не является числом");
            flag = false;
        }
        return flag;
    }
}
