package com.greenfox.controller;

import com.greenfox.model.Greeting;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {


  @RequestMapping(value = "/hello", method = RequestMethod.GET)
  public String main(@RequestParam("name") String name, Model model){
    Greeting greeting = new Greeting(name);
    model.addAttribute("name", greeting.getName());
    model.addAttribute("id", greeting.getId());
    return "index";
  }
}
