package mk.ukim.finki.wp.lab_b.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = {"/", ""})
public class HomeController {

    @GetMapping
    public String getHomePage(Model model) {
        return "redirect:/songs";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/access_denied")
    public String getAccessDeniedPage() {
        return "accessDenied";
    }

}
