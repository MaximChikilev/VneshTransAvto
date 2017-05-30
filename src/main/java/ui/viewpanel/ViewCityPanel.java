package ui.viewpanel;

import entity.City;
import listeners.ViewPanelTableModelListener;
import ui.Main;
import ui.newrecord.NewDBCityRecord;
import ui.tablemodel.CityTableModel;

import java.awt.*;

public class ViewCityPanel extends NewViewPanel<City> {
    private CityTableModel cityTableModel;

    public ViewCityPanel() {
        super();
        resultAR = Main.CITY_DAO.getAll();
        cityTableModel = new CityTableModel(resultAR);
        viewJTable.setModel(cityTableModel);
        add(innerPanel, BorderLayout.CENTER);
        viewJTable.setRowSelectionInterval(0, 0);
        viewJTable.getColumnModel().getColumn(0).setMaxWidth(50);
        viewJTable.getTableHeader().setReorderingAllowed(false);
        cityTableModel.addTableModelListener(new ViewPanelTableModelListener<>(Main.CITY_DAO,resultAR, viewJTable,this));
     }

    @Override
    protected void removeMethod() {
        idDeletedRecord = resultAR.get(viewJTable.getSelectedRow()).getCityID();
        cityTableModel.removeRow(viewJTable.getSelectedRow());
    }

    @Override
    protected void addMethod() {
        NewDBCityRecord newDBCityRecord = new NewDBCityRecord(1, cityTableModel);
    }
}
