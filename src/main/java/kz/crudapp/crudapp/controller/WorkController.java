package kz.crudapp.crudapp.controller;

import kz.crudapp.crudapp.entity.Car;
import kz.crudapp.crudapp.entity.Work;
import kz.crudapp.crudapp.entity.Worker;
import kz.crudapp.crudapp.service.CarService;
import kz.crudapp.crudapp.service.WorkService;
import kz.crudapp.crudapp.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/works")
public class WorkController {
    @Autowired
    private WorkService workService;

    @Autowired
    private CarService carService;

    @Autowired
    private WorkerService workerService;

    @GetMapping
    public String getAllWorks(Model model) {
        List<Work> works = workService.findAllWorks();
        List<Car> cars = carService.getAllCars();
        List<Worker> workers = workerService.findAllWorker();

        model.addAttribute("works", works);
        model.addAttribute("cars", cars);
        model.addAttribute("workers", workers);
        model.addAttribute("newWork", new Work());
        return "work-page";
    }

    @PostMapping
    public String addWork(Model model, @ModelAttribute("newWork") @Valid Work work, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.err.println("Error appeared");
            return "redirect:/works";

        }

        workService.createWork(work);
        return "redirect:/works";
    }

    @GetMapping("/edit/{id}")
    public String getEditedWork(@PathVariable("id")Long id, Model model) {
        Work work = workService.findWorkById(id);
        List<Car> cars = carService.getAllCars();
        List<Worker> workers = workerService.findAllWorker();

        model.addAttribute("cars", cars);
        model.addAttribute("workers", workers);
        model.addAttribute("work", work);
        return "edit-work";
    }

    @PostMapping("/update/{id}")
    public String updateWork(@PathVariable("id")Long id, @Valid Work work, Errors errors) {
        if(errors.hasErrors()) {
            work.setId(id);
            System.err.println("error appeared");
            return "edit-work";
        }
        workService.updateWork(id, work);
        return "redirect:/works";
    }

    @GetMapping("/delete/{id}")
    public String deleteOwnerById(@PathVariable("id")Long id) {
        workService.deleteWork(id);
        return "redirect:/works";
    }
}
