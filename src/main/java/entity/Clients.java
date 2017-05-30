package entity;

public class Clients {
    private int clientsID;
    private String clientsName;
    private int clientOKPO;
    private String clientAdress;
    private String clientPhone;

    public Clients(int clientsID, String clientsName, int clientOKPO, String clientAdress, String clientPhone) {
        this.clientsID = clientsID;
        this.clientsName = clientsName;
        this.clientOKPO = clientOKPO;
        this.clientAdress = clientAdress;
        this.clientPhone = clientPhone;
    }

    public Clients() {
    }

    public int getClientsID() {
        return clientsID;
    }

    public String getClientsName() {
        return clientsName;
    }

    public int getClientOKPO() {
        return clientOKPO;
    }

    public String getClientAdress() {
        return clientAdress;
    }

    public String getClientPhone() {
        return clientPhone;
    }

    public void setClientsID(int clientsID) {
        this.clientsID = clientsID;
    }

    public void setClientsName(String clientsName) {
        this.clientsName = clientsName;
    }

    public void setClientOKPO(int clientOKPO) {
        this.clientOKPO = clientOKPO;
    }

    public void setClientAdress(String clientAdress) {
        this.clientAdress = clientAdress;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }
}
