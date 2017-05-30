package dto;

import entity.RouteExpenses;

public class RouteExpensesForView extends RouteExpenses {
     private String expensesTitle;

    public RouteExpensesForView(int routeExpensesID, int routeID, Float coast, int expensesID, String expensesTitle) {
        super(routeExpensesID, routeID, coast, expensesID);
        this.expensesTitle = expensesTitle;
    }
    public RouteExpensesForView(){

    }

    public String getExpensesTitle() {
        return expensesTitle;
    }

    public void setExpensesTitle(String expensesTitle) {
        this.expensesTitle = expensesTitle;
    }
}
