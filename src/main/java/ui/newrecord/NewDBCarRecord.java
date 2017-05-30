package ui.newrecord;

import entity.Brand;
import ui.CheckUtils;
import ui.Main;
import ui.tablemodel.CarForViewTableModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class NewDBCarRecord extends NewDBRecordFrame {
    private List<Brand> brand;
    private int brandID;

    public NewDBCarRecord(int amount, final CarForViewTableModel tm) {
        super(amount);
        setLabelTextFieldName(0, "Номер машины");
        setLabelTextFieldName(1, "Номер шасси");
        setLabelTextFieldName(2, "Год выпуска");
        setLabelTextFieldName(3, "Марка автомобиля");
        brand = Main.BRAND_DAO.getAll();
        JComboBox<String> brandNameComboBox = new JComboBox<>(Main.BRAND_DAO.getBrandList());

        comboBoxes.add(brandNameComboBox);
        changeTextField(3, comboBoxes);
        yesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkNewDBRecord()) {
                    brandID = brand.get(comboBoxes.get(0).getSelectedIndex()).getBrandID();
                    tm.addRow(getTextFieldValue(0), brandID, getTextFieldValue(1), Integer.parseInt(getTextFieldValue(2)), (String) comboBoxes.get(0).getSelectedItem());
                    dispose();
                }
            }
        });
    }

    @Override
    public boolean checkNewDBRecord() {
        Boolean flag;
        flag = true;
        if (!CheckUtils.checkMaxLength(getTextFieldValue(0), 8)) {
            JOptionPane.showMessageDialog(this, "Номер машины не может превышать 8 символов");
            flag = false;
        }
        if (!CheckUtils.isInteger(getTextFieldValue(2))) {
            JOptionPane.showMessageDialog(this, "Введенный год не является числом");
            flag = false;
        }
        return flag;
    }

}
