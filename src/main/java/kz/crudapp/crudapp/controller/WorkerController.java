package kz.crudapp.crudapp.controller;

import kz.crudapp.crudapp.entity.Worker;
import kz.crudapp.crudapp.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/worker")
public class WorkerController {
    @Autowired
    private WorkerService workerService;

    @GetMapping
    public String getAllWorkers(Model model) {
        List<Worker> workers = workerService.findAllWorker();
        model.addAttribute("workers", workers);
        if(!model.asMap().containsKey("newWorker"))
            model.addAttribute("newWorker", new Worker());

        return "master-page";
    }

    @PostMapping("/addWorker")
    public String addNewWorker(Model model, @ModelAttribute("newWorker") @Valid Worker newWorker, BindingResult result,
                              RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            System.err.println("Error appeared");
            model.addAttribute("workers", workerService.findAllWorker());
            redirectAttributes.addFlashAttribute("newWorker", newWorker);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.newWorker", result);
            return "redirect:/worker";
        }
        if(newWorker == null) {
            System.err.println("empty worker form detected");
        }
        workerService.createWorker(newWorker);
        return "redirect:/worker";
    }

    @GetMapping("/edit/{id}")
    public String getEditedOwner(@PathVariable("id")Long id, Model model) {
        Worker worker = workerService.findWorkerById(id);
        model.addAttribute("worker", worker);
        return "edit-worker";
    }

    @PostMapping("/update/{id}")
    public String updateOwner(@PathVariable("id")Long id, @Valid Worker worker, Errors errors) {
        if(errors.hasErrors()) {
            worker.setId(id);
            System.err.println("error appeared");
            return "edit-worker";
        }
        workerService.updateWorker(id, worker);
        return "redirect:/worker";
    }

    @GetMapping("/delete/{id}")
    public String deleteOwnerById(@PathVariable("id")Long id) {
        workerService.deleteWorkerById(id);
        return "redirect:/worker";
    }
}
