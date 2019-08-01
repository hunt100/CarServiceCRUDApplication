package kz.crudapp.crudapp.controller;

import kz.crudapp.crudapp.entity.MyUser;
import kz.crudapp.crudapp.service.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class MainController {
    @Autowired
    private MyUserDetailService myUserDetailService;
    @GetMapping({"/","/index","/home"})
    public String redirectToSwagger() {
        return "redirect:/owner";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
            MyUser myUser = new MyUser();
            model.addAttribute("myUser", myUser);
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@ModelAttribute("myUser") @Valid MyUser myUser, BindingResult result, Model model) {
        if (result.hasErrors()) {
            System.err.println("error appeared");
            return "/registration";
        }
        String serviceErrorMessage = myUserDetailService.regUser(myUser);
        if (serviceErrorMessage == null) {
            return "redirect:/login";
        }
        model.addAttribute("myError", serviceErrorMessage);
        return "/registration";
    }

    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code) {
        boolean isActivated = myUserDetailService.activateUser(code);

        if (isActivated) {
            model.addAttribute("myMessage", "User successfully activated");
        }
        else {
            model.addAttribute("myMessage", "Activation code is not found");
        }

        return "login";

    }
}
