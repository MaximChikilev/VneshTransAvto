package ui.newrecord;

import ui.tablemodel.BrandTableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewDBBrandRecord extends NewDBRecordFrame {

    public NewDBBrandRecord(int amount, final BrandTableModel tm) {
        super(amount);
        setLabelTextFieldName(0, "Новый бренд");
        yesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkNewDBRecord()) {
                    tm.addRow(getTextFieldValue(0));
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
