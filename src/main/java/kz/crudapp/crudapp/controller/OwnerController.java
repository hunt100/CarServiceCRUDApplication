package kz.crudapp.crudapp.controller;

import kz.crudapp.crudapp.entity.Owner;
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
@RequestMapping(value = "/owner")
public class OwnerController {
    @Autowired
    private OwnerService ownerService;

    @GetMapping
    public String getAllOwners(Model model) {
        List<Owner> owners = ownerService.findAllOwner();
        model.addAttribute("owners", owners);
        model.addAttribute("newOwner", new Owner());
        return "owner-page";
    }

    @PostMapping("/addOwner")
    public String addNewOwner(Model model, @ModelAttribute("newOwner") @Valid Owner newOwner, Errors errors) {
        if (errors.hasErrors()) {
            System.err.println("Error appeared");
            model.addAttribute("owners", ownerService.findAllOwner());
            return "owner-page";
        }
        if(newOwner == null) {
            System.err.println("empty owner form detected");
        }
        ownerService.createOwner(newOwner);
        return "redirect:/owner";
    }

    @GetMapping("/edit/{id}")
    public String getEditedOwner(@PathVariable("id")Long id, Model model) {
        Owner owner = ownerService.findOwnerById(id);
        model.addAttribute("owner", owner);
        return "edit-owner";
    }

    @PostMapping("/update/{id}")
    public String updateOwner(@PathVariable("id")Long id, @Valid Owner owner, Errors errors) {
        if(errors.hasErrors()) {
            owner.setId(id);
            System.err.println("error appeared");
            return "edit-owner";
        }
        ownerService.updateOwner(id, owner);
        return "redirect:/owner";
    }

    @GetMapping("/delete/{id}")
    public String deleteOwnerById(@PathVariable("id")Long id) {
        ownerService.deleteOwnerById(id);
        return "redirect:/owner";
    }


}
