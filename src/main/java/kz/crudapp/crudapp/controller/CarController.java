package kz.crudapp.crudapp.controller;

import kz.crudapp.crudapp.entity.Car;
import kz.crudapp.crudapp.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "cars")
public class CarController {
    @Autowired
    private CarService carService;

    @GetMapping
    public String showAllCars(Model model) {
        List<Car> cars = carService.getAllCars();
        model.addAttribute("cars", cars);
        return "cars-page";
    }

    @PostMapping("/addCar")
    public String addNewCar(Model model, @ModelAttribute("newCar") @Valid Car newCar, Errors errors) {
        if (errors.hasErrors()) {
            System.err.println("Error appeared");
            model.addAttribute("cars",carService.getAllCars());
            return "cars-page";
        }

        return "redirect:/cars";
    }
}
