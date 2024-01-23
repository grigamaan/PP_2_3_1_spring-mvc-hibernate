package web.dao;

import org.springframework.stereotype.Component;
import web.model.Car;

import java.util.ArrayList;
import java.util.List;

@Component
public class CarsDAO {
    private static int CARS_COUNT;
    private List<Car> cars;

    {
        cars = new ArrayList<>();

        cars.add(new Car(++CARS_COUNT, "BYD", "China"));
        cars.add(new Car(++CARS_COUNT, "BMW", "Germany"));
        cars.add(new Car(++CARS_COUNT, "KAMAZ", "Russia"));
        cars.add(new Car(++CARS_COUNT, "VAZ", "SSSR"));
        cars.add(new Car(++CARS_COUNT, "Tavriya", "SSSR"));
    }

    public List<Car> index() {
        return cars;
    }

    public Car show(int id) {
        return cars.stream().filter(car -> car.getId() == id).findAny().orElse(null);
    }

}
