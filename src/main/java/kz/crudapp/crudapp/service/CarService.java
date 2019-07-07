package kz.crudapp.crudapp.service;

import kz.crudapp.crudapp.entity.Car;
import kz.crudapp.crudapp.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CarService {
    @Autowired
    private CarRepository carRepository;

    public CarRepository getCarRepository() {
        return carRepository;
    }

    public Long createCar(Car car) {
        carRepository.save(car);
        return car.getId();
    }

    public List<Car> getAllCars() {
        return (List<Car>) carRepository.findAll();
    }

    public Car getCarById (Long id) {
        Optional<Car> car = carRepository.findById(id);
        if (!car.isPresent()) {
            throw new IllegalArgumentException("Invalid car id" + id);
        }
        return car.get();
    }

    public void updateCar (Long id, Car car) {
        Optional<Car> foundedOptCar = carRepository.findById(id);
        if (foundedOptCar.isPresent()) {
            car.setId(id);
            carRepository.save(car);
        }
        else {
            throw new IllegalArgumentException("Invalid car id" + car.getId());
        }
    }

    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }

    public List<Car> getCarsByYear(String year) {
        List<Car> cars =  carRepository.getCarsByYearOfIssue(year);
        if (cars.isEmpty()) {
            throw new IllegalArgumentException("car is empty by this year of issue - " + year);
        }
        return cars;
    }

    public List<Car> getCarsByModel(String model) {
        List<Car> cars =  carRepository.getCarsByModel(model);
        if (cars.isEmpty()) {
            throw new IllegalArgumentException("car is empty by this model - " + model);
        }
        return cars;
    }

}
