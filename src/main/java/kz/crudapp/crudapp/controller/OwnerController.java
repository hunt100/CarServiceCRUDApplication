package kz.crudapp.crudapp.controller;

import kz.crudapp.crudapp.entity.Owner;
import kz.crudapp.crudapp.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping(value = "/owner")
public class OwnerController {
    @Autowired
    private OwnerService ownerService;

    @GetMapping
    public String getAllOwners(Model model, Authentication authentication) {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        boolean isAdmin = false;
        for(GrantedAuthority grantedAuthority : authorities) {
            if(grantedAuthority.getAuthority().equals("ROLE_ADMIN")) {
                isAdmin = true;
                break;
            }
        }
        model.addAttribute("isAdmin", isAdmin);

        List<Owner> owners = ownerService.findAllOwner();
        model.addAttribute("owners", owners);
        if(!model.asMap().containsKey("newOwner"))
            model.addAttribute("newOwner", new Owner());

        return "owner-page";
    }

    @PostMapping("/addOwner")
    public String addNewOwner(Model model, @ModelAttribute("newOwner") @Valid Owner newOwner, BindingResult result,
                              RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            System.err.println("Error appeared");
            model.addAttribute("owners", ownerService.findAllOwner());
            redirectAttributes.addFlashAttribute("newOwner", newOwner);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.newOwner", result);
            return "redirect:/owner";
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
