package kz.crudapp.crudapp.controller;

import kz.crudapp.crudapp.entity.MyUser;
import kz.crudapp.crudapp.service.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String addUser(@ModelAttribute("myUser") @Valid MyUser myUser, BindingResult result) {
        if (result.hasErrors()) {
            System.err.println("error appeared");
            return "/registration";
        }
        myUserDetailService.regUser(myUser);
        return "redirect:/login";
    }
}
