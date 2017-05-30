package entity;

public class Car {
    private int carID;
    private int carBrand;
    private String carNumber;
    private String carShassis;
    private int carYear;

    public Car(int carID, int carBrand, String carNumber, String carShassis, int carYear) {
        this.carID = carID;
        this.carBrand = carBrand;
        this.carNumber = carNumber;
        this.carShassis = carShassis;
        this.carYear = carYear;
    }

    public Car() {

    }

    public void setCarID(int carID) {
        this.carID = carID;
    }

    public void setCarBrand(int brandCar) {
        this.carBrand = brandCar;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public void setCarShassis(String carShassis) {
        this.carShassis = carShassis;
    }

    public void setCarYear(int carYear) {
        this.carYear = carYear;
    }

    public int getCarID() {
        return this.carID;
    }

    public int getCarBrand() {
        return this.carBrand;
    }

    public String getCarNumber() {
        return this.carNumber;
    }

    public String getCarShassis() {
        return this.carShassis;
    }

    public int getCarYear() {
        return this.carYear;
    }
}
