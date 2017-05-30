package ui.viewpanel;

import entity.Clients;
import listeners.ViewPanelTableModelListener;
import ui.Main;
import ui.newrecord.NewDBClientRecord;
import ui.tablemodel.ClientTableModel;

import java.awt.*;

public class ViewClientPanel extends NewViewPanel<Clients> {
    private ClientTableModel clientTableModel;

    public ViewClientPanel() {
        super();
        resultAR = Main.CLIENT_DAO.getAll();
        clientTableModel = new ClientTableModel(resultAR);
        viewJTable.setModel(clientTableModel);
        add(innerPanel, BorderLayout.CENTER);
        clientTableModel.addTableModelListener(new ViewPanelTableModelListener<>(Main.CLIENT_DAO, resultAR, viewJTable, this));
    }

    @Override
    protected void removeMethod() {
        idDeletedRecord = resultAR.get(viewJTable.getSelectedRow()).getClientsID();
        clientTableModel.removeRow(viewJTable.getSelectedRow());
    }

    @Override
    protected void addMethod() {
        NewDBClientRecord newDBClientRecord = new NewDBClientRecord(4, clientTableModel);
    }

}
