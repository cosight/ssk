package com.cosight.ssk.web.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @PostMapping("/auth/login")
    public String doLogin() {
        return "redirect:/login";
    }

    @PostMapping("/logout")
    public String doLogout() {

        return null;
    }

}
