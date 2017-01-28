package com.shreya.controller;

import com.shreya.dao.UserInfoDAO;
import com.shreya.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;


/**
 * Created by shreya on 8/1/17.
 */
@Controller
public class MainController {
    @Autowired
    UserInfoDAO userInfoDAO;

    public static final String APPLICATION_JSON = "application/json";
    public static final String APPLICATION_XML = "application/xml";
    public static final String APPLICATION_HTML = "text/html";

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcomePage(Model model) {
        return "welcomePage";
    }

    @RequestMapping(value = "/userinfo", method = RequestMethod.POST, consumes = {APPLICATION_JSON})
    public String addUser(@RequestBody UserInfo user) {
        userInfoDAO.addUser(user);
        return "registrationSuccessfulPage";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPage(Model model) {
        return "adminPage";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model) {

        return "loginPage";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registrationPage(Model model) {
        return "registrationPage";
    }

    @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
    public String logoutSuccessfulPage(Model model) {
        model.addAttribute("title", "Logout");
        return "logoutSuccessfulPage";
    }


    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    public String userInfo(Model model, Principal principal) {

        // After user login successfully.
        String userName = principal.getName();
        model.addAttribute("message",userInfoDAO.findUserInfo(userName));
        return "userInfoPage";
    }


    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied(Model model, Principal principal) {

        if (principal != null) {
            model.addAttribute("message", "Hi " + principal.getName()
                    + "<br> You do not have permission to access this page!");
        } else {
            model.addAttribute("msg",
                    "You do not have permission to access this page!");
        }
        return "403Page";
    }
}
