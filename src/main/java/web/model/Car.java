package web.model;

public class Car {
    private int id;
    private String carModel;

    private String carCountry;

    public Car(int id, String carModel, String carCountry) {
        this.id = id;
        this.carModel = carModel;
        this.carCountry = carCountry;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getCarCountry() {
        return carCountry;
    }

    public void setCarCountry(String carOld) {
        this.carCountry = carOld;
    }
}
