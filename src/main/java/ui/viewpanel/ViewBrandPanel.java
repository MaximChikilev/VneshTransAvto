package ui.viewpanel;

import entity.Brand;
import listeners.ViewPanelTableModelListener;
import ui.Main;
import ui.newrecord.NewDBBrandRecord;
import ui.tablemodel.BrandTableModel;

import java.awt.*;

public class ViewBrandPanel extends NewViewPanel<Brand> {
    private BrandTableModel brandTableModel;

    public ViewBrandPanel() {
        super();
        resultAR = Main.BRAND_DAO.getAll();
        brandTableModel = new BrandTableModel(resultAR);
        this.viewJTable.setModel(brandTableModel);
        this.add(innerPanel, BorderLayout.CENTER);
        viewJTable.setRowSelectionInterval(0, 0);
        viewJTable.getColumnModel().getColumn(0).setMaxWidth(50);
        viewJTable.getTableHeader().setReorderingAllowed(false);

        brandTableModel.addTableModelListener(new ViewPanelTableModelListener<>(Main.BRAND_DAO, resultAR, viewJTable, this));
    }

    @Override
    protected void removeMethod() {
        idDeletedRecord = resultAR.get(viewJTable.getSelectedRow()).getBrandID();
        brandTableModel.removeRow(viewJTable.getSelectedRow());
    }

    @Override
    protected void addMethod() {
        NewDBBrandRecord newDBBrandRecord = new NewDBBrandRecord(1, brandTableModel);
    }
}
