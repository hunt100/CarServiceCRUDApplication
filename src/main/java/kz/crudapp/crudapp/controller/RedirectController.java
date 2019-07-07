package kz.crudapp.crudapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RedirectController {
    @GetMapping({"/","/index","/home"})
    public String redirectToSwagger() {
        return "redirect:/swagger-ui.html";
    }
}
