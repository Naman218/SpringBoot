package com.example.springbootdemo.controller;

import com.example.springbootdemo.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class HomeController {

    @RequestMapping("/")
    public String home(){
        return "Hello World";
    }

    @GetMapping("/user")
    public User user(){
        User user= new User();
        user.setId("1");
        user.setEmailId("rossgeller@gmail.com");
        user.setName("Ross Geller");

        return user;
    }

    @GetMapping("/{arg1}/{arg2}")
    public String pathVariable(@PathVariable("arg1") String id,@PathVariable("arg2") String name){
        return "The path variable is: "+id+"\n Name: "+name;
    }

    @GetMapping("/requestParam")
    public String requestParams(@RequestParam String name,
                                @RequestParam(name="email",required=false,defaultValue = "") String emailId){
        return "Your Name is "+name+" email id is "+emailId;
    }
}
