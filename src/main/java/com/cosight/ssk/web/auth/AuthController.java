package com.cosight.ssk.web.auth;

import com.cosight.ssk.service.auth.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final AccountService accountService;

    @PostMapping("/auth/login")
    public String doLogin() {
        accountService.login();

        return "redirect:/login";
    }

    @PostMapping("/logout")
    public String doLogout() {

        return null;
    }

}
