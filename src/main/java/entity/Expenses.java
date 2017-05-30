package entity;

public class Expenses {
    private int expensesID;
    private String expensesName;

    public Expenses(int expensesID, String expensesName) {
        this.expensesID = expensesID;
        this.expensesName = expensesName;
    }

    public Expenses() {
    }

    public int getExpensesID() {
        return expensesID;
    }

    public String getExpensesName() {
        return expensesName;
    }

    public void setExpensesID(int expensesID) {
        this.expensesID = expensesID;
    }

    public void setExpensesName(String expensesName) {
        this.expensesName = expensesName;
    }
}
