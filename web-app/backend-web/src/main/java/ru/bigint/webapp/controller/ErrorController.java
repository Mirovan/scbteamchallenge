package ru.bigint.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


@Controller
public class ErrorController {

    @RequestMapping(value = "/error/403/", method = RequestMethod.GET)
    public ModelAndView handleError403(HttpServletRequest request, Exception e) {
//        LoggerUtil.writeError(LOGGER, "Request: " + request.getRequestURL() + " raised " + e);
        return new ModelAndView("/error/403");
    }

}