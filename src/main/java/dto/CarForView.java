package dto;

import entity.Car;

public class CarForView extends Car {
    private String carBrandName;

    public CarForView(int carID, int carBrand, String carNumber, String carShassis, int carYear, String carBrandName) {
        super(carID, carBrand, carNumber, carShassis, carYear);
        this.carBrandName = carBrandName;
    }

    public CarForView() {
    }

    public String getCarBrandName() {
        return carBrandName;
    }

    public void setCarBrandName(String carBrandName) {
        this.carBrandName = carBrandName;
    }
}
