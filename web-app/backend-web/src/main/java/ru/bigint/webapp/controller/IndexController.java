package ru.bigint.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.bigint.webapp.dto.user.UserDto;

import java.security.Principal;
import java.util.Objects;

@Controller
public class IndexController {

    @GetMapping(value = "/")
    public ModelAndView index(Principal principal) {
        if (Objects.isNull(principal)) {
            UserDto userDto = new UserDto();
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("user", userDto);
            modelAndView.setViewName("user/auth");
            return modelAndView;
        } else {
            return new ModelAndView("redirect:/user/personal");
        }
    }

}
