package ui.tablemodel;

import entity.Clients;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ClientTableModel extends AbstractTableModel {
    private static final int COLUMN_COUNT = 5;
    private List<Clients> clients;

    public ClientTableModel(List<Clients> clients) {
        this.clients = clients;
    }

    public void removeRow(int rowIndex) {
        this.clients.remove(rowIndex);
        this.fireTableRowsDeleted(rowIndex, rowIndex);
    }

    public void addRow(String clientName, int clientOKPO, String clientAdress, String clientPhone) {
        Clients newClient;
        newClient = new Clients();
        newClient.setClientsName(clientName);
        newClient.setClientOKPO(clientOKPO);
        newClient.setClientAdress(clientAdress);
        newClient.setClientPhone(clientPhone);
        this.clients.add(newClient);
        this.fireTableRowsInserted(this.clients.size() - 1, this.clients.size() - 1);
    }

    @Override
    public int getRowCount() {
        return clients.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_COUNT;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "id";
            case 1:
                return "Name";
            case 2:
                return "OKPO";
            case 3:
                return "Address";
            case 4:
                return "Phone";
        }
        throw new IllegalArgumentException("Invalid parameter from ClientTableModel ");
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {

        switch (columnIndex) {
            case 0:
                return Integer.class;
            case 1:
                return String.class;
            case 2:
                return Integer.class;
            case 3:
                return String.class;
            case 4:
                return String.class;
        }
        throw new IllegalArgumentException("Invalid parameter from ClientTableModel ");
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return false;
            case 1:
                return true;
            case 2:
                return true;
            case 3:
                return true;
            case 4:
                return true;
        }
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Clients client = clients.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return client.getClientsID();
            case 1:
                return client.getClientsName();
            case 2:
                return client.getClientOKPO();
            case 3:
                return client.getClientAdress();
            case 4:
                return client.getClientPhone();
        }
        throw new IllegalArgumentException("Invalid parameter from ClientTableModel ");
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Clients client = clients.get(rowIndex);
        switch (columnIndex) {
            case 1: {
                client.setClientsName((String) aValue);
                break;
            }
            case 2:
                client.setClientOKPO((int) aValue);
                break;
            case 3:
                client.setClientAdress((String) aValue);
                break;
            case 4:
                client.setClientPhone((String) aValue);
                break;
        }
        this.fireTableDataChanged();

    }
}
