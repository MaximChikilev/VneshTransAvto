package ui.newrecord;

import entity.City;
import ui.Main;
import ui.tablemodel.RouteForViewTableModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class NewDBRouteRecord extends NewDBRecordFrame {
    private List<City> cities;
    private int departureCityID;
    private int arrivalCityID;

    public NewDBRouteRecord(int amount, final RouteForViewTableModel tableModel) {
        super(amount);
        setLabelTextFieldName(0, "Название маршрута");
        setLabelTextFieldName(1, "Километраж");
        setLabelTextFieldName(2, "Начальный пункт");
        setLabelTextFieldName(3, "Пункт назначения");
        cities = Main.CITY_DAO.getAll();
        JComboBox<String> departureCityComboBox = new JComboBox<>(Main.CITY_DAO.getCityList());
        JComboBox<String> arrivalCityComboBox = new JComboBox<>(Main.CITY_DAO.getCityList());
        comboBoxes.add(departureCityComboBox);
        comboBoxes.add(arrivalCityComboBox);
        super.changeTextField(2, comboBoxes);
        yesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkNewDBRecord()) {
                    departureCityID = cities.get(comboBoxes.get(0).getSelectedIndex()).getCityID();
                    arrivalCityID = cities.get(comboBoxes.get(1).getSelectedIndex()).getCityID();
                    tableModel.addRow(getTextFieldValue(0), departureCityID, arrivalCityID, Integer.parseInt(getTextFieldValue(1)), (String) comboBoxes.get(0).getSelectedItem(), (String) comboBoxes.get(1).getSelectedItem());
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
