package ui.viewpanel;

import dto.CarForView;
import entity.Car;
import listeners.ViewPanelTableModelListener;
import ui.Main;
import ui.newrecord.NewDBCarRecord;
import ui.tablemodel.CarForViewTableModel;

import javax.swing.*;
import java.awt.*;

import static ui.Main.CAR_DAO;

public class ViewCarPanel extends NewViewPanel<CarForView> {
    private CarForViewTableModel carForViewTableModel;

    public ViewCarPanel() {
        super();
        resultAR = CAR_DAO.getAllCarForView();
        carForViewTableModel = new CarForViewTableModel(resultAR);
        viewJTable.setModel(carForViewTableModel);
        add(innerPanel, BorderLayout.CENTER);
        viewJTable.setRowSelectionInterval(0, 0);
        JComboBox<String> brandNameComboBox = new JComboBox<>(Main.BRAND_DAO.getBrandList());
        System.out.println(brandNameComboBox.getItemCount());
        setDefaultCBEditor(1, brandNameComboBox);
        carForViewTableModel.addTableModelListener(new ViewPanelTableModelListener<Car, CarForView>(CAR_DAO, resultAR, viewJTable, this));
    }

    @Override
    protected void removeMethod() {
        idDeletedRecord = resultAR.get(viewJTable.getSelectedRow()).getCarID();
        carForViewTableModel.removeRow(viewJTable.getSelectedRow());
    }

    @Override
    protected void addMethod() {
        NewDBCarRecord newDBCarRecord = new NewDBCarRecord(4, carForViewTableModel);
    }
}
