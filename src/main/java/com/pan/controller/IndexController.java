package com.pan.controller;

import com.pan.entity.User;
import com.pan.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by FantasticPan on 2018/6/4.
 */
@Controller
public class IndexController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @ResponseBody
    @RequestMapping("/personal_center")
    public void login(HttpServletRequest request) {
        System.out.println("登录成功");
    }

    @ResponseBody
    @PostMapping("/registry")
    public void registry(User user) {
        System.out.println("注册成功");
        userRepository.save(new User(user.getUsername(), passwordEncoder.encode(user.getPassword())));
    }
}
