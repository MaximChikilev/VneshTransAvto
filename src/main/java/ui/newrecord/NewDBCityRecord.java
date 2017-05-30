package ui.newrecord;

import ui.tablemodel.CityTableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class NewDBCityRecord extends NewDBRecordFrame {
    public NewDBCityRecord(int amount, final CityTableModel tableModel) {
        super(amount);
        setLabelTextFieldName(0, "Новый город");
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
