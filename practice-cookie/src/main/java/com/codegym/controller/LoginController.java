package com.codegym.controller;

import com.codegym.model.User;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes("user")
public class LoginController {

    @ModelAttribute("user")
    public User setUpUser() {
        return new User();
    }

    @RequestMapping("/login")
    public String Index(@CookieValue(value = "setUser", defaultValue = "") String setUser,
                        Model model) {
        Cookie cookie = new Cookie("setUser",
                setUser);
        model.addAttribute("cookieValue",
                cookie.getValue());
        return "login";
    }

    @PostMapping("/dologin")
    public String Index(@ModelAttribute User user,
                        Model model,
                        @CookieValue(value = "setUser", defaultValue = "") String setUser,
                        HttpServletResponse response,
                        HttpServletRequest request) {
        if (user.getEmail().equals("admin@gmail.com") && user.getPassword().equals("123456")) {
            setUser = user.getEmail();

            Cookie cookie = new Cookie("setUser",
                    setUser);
            cookie.setMaxAge(24 * 60 * 60);
            response.addCookie(cookie);

            Cookie[] cookies = request.getCookies();
            for (Cookie ck : cookies) {
                if (!ck.getName().equals("setUser")) {
                    ck.setValue("");
                }
                model.addAttribute("cookieValue",
                        ck.getValue());
            }
            model.addAttribute("message",
                    "Login success. Welcome!");
        } else {
            user.setEmail("");
            Cookie cookie = new Cookie("setUser",
                    setUser);
            model.addAttribute("cookieValue",
                    cookie.getValue());
            model.addAttribute("message",
                    "Login failed. Try again.");
        }
        return "login";
    }
}
