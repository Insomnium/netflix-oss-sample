package net.ins.hw.netflix.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserController {

    @GetMapping("/me")
    public Principal me(Principal principal) {
        return principal;
    }
}
