package kz.crudapp.crudapp.controller;

import kz.crudapp.crudapp.entity.Car;
import kz.crudapp.crudapp.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/cars")
public class CarRestController {
    @Autowired
    private CarService carService;

    @GetMapping
    public List<Car> findAllCars() {
        return carService.getAllCars();
    }

    @GetMapping(value = "/{id}")
    public Car findCarById(@PathVariable("id") Long id) {
        return carService.getCarById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long createCar(@RequestBody Car car) {
        return carService.createCar(car);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateCar(@PathVariable("id") Long id, @RequestBody Car car) {
        carService.updateCar(id, car);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCar(@PathVariable("id") Long id) {
        carService.deleteCar(id);
    }

    @GetMapping(value = "/search/byYear/{year}")
    public List<Car> findCarsByYear(@PathVariable("year") String year) {
        return carService.getCarsByYear(year);
    }

    @GetMapping(value = "/search/byModel/{model}")
    public List<Car> findCarsByModel(@PathVariable("model") String model) {
        return carService.getCarsByModel(model);
    }

}
