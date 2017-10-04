package io.ian.moviereviewer.controllers;

import io.ian.moviereviewer.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginForm(Model model,
                            HttpServletRequest request) {
        model.addAttribute("user", new User());
        Object message = request.getSession().getAttribute("error");
        model.addAttribute("errors", message);
        request.getSession().removeAttribute("error");
        return "login";
    }

    @RequestMapping(value = "/login/createSuccess", method = RequestMethod.GET)
    public String loginFormCreate(Model model,
                            HttpServletRequest request) {
        model.addAttribute("user", new User());
        model.addAttribute("createSuccess", "Account Created Successfully");
        Object message = request.getSession().getAttribute("error");
        model.addAttribute("errors", message);
        request.getSession().removeAttribute("error");
        return "login";
    }
}