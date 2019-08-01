package kz.crudapp.crudapp.controller;

import kz.crudapp.crudapp.entity.Car;
import kz.crudapp.crudapp.entity.Owner;
import kz.crudapp.crudapp.service.CarService;
import kz.crudapp.crudapp.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "cars")
public class CarController {
    @Autowired
    private CarService carService;

    @Autowired
    private OwnerService ownerService;

    @GetMapping
    public String showAllCars(Model model) {
        List<Car> cars = carService.getAllCars();
        List<Owner> owners = ownerService.findAllOwner();
        model.addAttribute("cars", cars);
        model.addAttribute("owners", owners);
        model.addAttribute("newCar", new Car());
        return "cars-page";
    }

    @PostMapping("/addCar")
    public String addNewCar(Model model, @ModelAttribute("newCar") @Valid Car newCar, Errors errors) {
        if (errors.hasErrors()) {
            System.err.println("Error appeared");
            model.addAttribute("cars",carService.getAllCars());
            return "cars-page";
        }
        carService.createCar(newCar);
        return "redirect:/cars";
    }

    @GetMapping("/edit/{id}")
    public String editCar(@PathVariable("id")Long id, Model model){
        Car car = carService.getCarById(id);
        List<Owner> owners = ownerService.findAllOwner();
        model.addAttribute("car", car);
        model.addAttribute("owners", owners);
        return "edit-car";
    }

    @PostMapping("/update/{id}")
    public String updateCar(@PathVariable("id")Long id, @Valid Car car, BindingResult result) {
        if (result.hasErrors()) {
            car.setId(id);
            System.err.println("Error appeared");
            return "edit-car";
        }
        carService.updateCar(id, car);
        return "redirect:/cars";
    }

    @GetMapping("/delete/{id}")
    public String deleteCar(@PathVariable("id") Long id) {
        carService.deleteCar(id);
        return "redirect:/cars";
    }


}
