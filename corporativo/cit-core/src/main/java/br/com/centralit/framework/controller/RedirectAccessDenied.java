package br.com.centralit.framework.controller;

import java.io.IOException;

import javax.servlet.ServletException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RedirectAccessDenied {

    @RequestMapping(value = "/accessDeniedPage.htm", method = RequestMethod.GET)
    public String redirectAccessDenied(Model model) throws IOException, ServletException {
        System.out.println("############### Redirect Access Denied Handler! ###############");
        return "403";
    }
}