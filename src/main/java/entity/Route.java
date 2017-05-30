package entity;

public class Route {
    private int routeID;
    private String routeTitle;
    private int departureCity;
    private int arrivalCity;
    private int distance;

    public Route(int routeID, String routeTitle, int departureCity, int arrivalCity, int distance) {
        this.routeID = routeID;
        this.routeTitle = routeTitle;
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.distance = distance;
    }

    public Route() {
    }

    public int getRouteID() {
        return routeID;
    }

    public String getRouteTitle() {
        return routeTitle;
    }

    public int getDepartureCity() {
        return departureCity;
    }

    public int getArrivalCity() {
        return arrivalCity;
    }

    public int getDistance() {
        return distance;
    }

    public void setRouteID(int routeID) {
        this.routeID = routeID;
    }

    public void setRouteTitle(String routeTitle) {
        this.routeTitle = routeTitle;
    }

    public void setDepartureCity(int departureCity) {
        this.departureCity = departureCity;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public void setArrivalCity(int arrivalCity) {
        this.arrivalCity = arrivalCity;
    }
}
