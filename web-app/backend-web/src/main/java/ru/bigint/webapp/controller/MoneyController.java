package ru.bigint.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/money")
public class MoneyController {

    @GetMapping
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("money/index");
        return modelAndView;
    }

    @GetMapping(value = "/payment")
    public ModelAndView paymentGet() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("money/payment");
        return modelAndView;
    }

    @PostMapping(value = "/payment")
    public ModelAndView paymentPost() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("money/payment-ok");
        return modelAndView;
    }
}
