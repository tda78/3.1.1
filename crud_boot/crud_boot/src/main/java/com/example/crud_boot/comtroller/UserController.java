package com.example.crud_boot.comtroller;

import com.example.crud_boot.model.User;
import com.example.crud_boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.Console;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/user/")
    public String userPage(Model model) {

        String string = (SecurityContextHolder.getContext().getAuthentication().
                getPrincipal().toString());
        System.out.println("user: "+string);

        User user = (User) userService.loadUserByUsername(string);
        System.out.println("user -"+user.getUsername());

        model.addAttribute("user", user);
        return "userInfo";
    }
}
