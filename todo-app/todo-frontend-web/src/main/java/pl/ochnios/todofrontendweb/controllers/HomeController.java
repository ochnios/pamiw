package pl.ochnios.todofrontendweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
@Controller
public class HomeController {
    @GetMapping
    public String getHomePage(Model model) {
        return "redirect:/tasks";
    }
}
