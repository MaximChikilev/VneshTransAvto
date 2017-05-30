package dto;

import entity.Route;

public class RouteForView extends Route {
    private String departureCityName;
    private String arrivalCityName;

    public RouteForView(int routeID, String routeTitle, int departureCity, int arrivalCity, int distance, String departureCityName, String arrivalCityName) {
        super(routeID, routeTitle, departureCity, arrivalCity, distance);
        this.departureCityName = departureCityName;
        this.arrivalCityName = arrivalCityName;
    }

    public RouteForView() {

    }

    public String getDepartureCityName() {
        return departureCityName;
    }

    public void setDepartureCityName(String departureCityName) {
        this.departureCityName = departureCityName;
    }

    public String getArrivalCityName() {
        return arrivalCityName;
    }

    public void setArrivalCityName(String arrivalCityName) {
        this.arrivalCityName = arrivalCityName;
    }
}
