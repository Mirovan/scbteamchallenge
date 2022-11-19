package ru.bigint.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.bigint.webapp.dto.user.UserDto;
import ru.bigint.webapp.entity.UserEntity;
import ru.bigint.webapp.service.iface.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/register")
    public ModelAndView register(@ModelAttribute("user") @Valid UserDto userDto,
                                     HttpServletRequest request, Errors errors) {
        try {
            UserEntity userEntity = userService.registerUser(userDto);
        } catch (Exception e) {
            //ToDo: выбросить ошибку и отобразить форму заново
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", userDto);
        modelAndView.setViewName("user/register-ok");
        return modelAndView;
    }

    @GetMapping(value = "/personal")
    public ModelAndView personal() {
        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("user", userDto);
        modelAndView.setViewName("user/personal");
        return modelAndView;
    }

}
