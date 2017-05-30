package entity;

public class RouteExpenses {
    private int routeExpensesID;
    private int routeID;
    private int expensesID;
    private Float coast;

    public RouteExpenses(int routeExpensesID, int routeID, Float coast, int expensesID) {
        this.routeExpensesID = routeExpensesID;
        this.routeID = routeID;
        this.coast = coast;
        this.expensesID = expensesID;
    }

    public RouteExpenses() {
    }

    public int getRouteExpensesID() {
        return routeExpensesID;
    }

    public int getRouteID() {
        return routeID;
    }

    public int getExpensesID() {
        return expensesID;
    }

    public Float getCoast() {
        return coast;
    }

    public void setRouteExpensesID(int routeExpensesID) {
        this.routeExpensesID = routeExpensesID;
    }

    public void setRouteID(int routeID) {
        this.routeID = routeID;
    }

    public void setExpensesID(int expensesID) {
        this.expensesID = expensesID;
    }

    public void setCoast(Float coast) {
        this.coast = coast;
    }
}
