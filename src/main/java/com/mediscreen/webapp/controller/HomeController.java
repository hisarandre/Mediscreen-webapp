package com.mediscreen.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller class for managing the home page.
 */
@Controller
public class HomeController {

    /**
     * Returns the view for the home page.
     *
     * @return the name of the view template to be rendered
     */
    @RequestMapping({ "/", "/home" })
    public String home(Model model) {
        return "home";
    }
}
