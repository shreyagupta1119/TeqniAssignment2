package com.shreya.web;

import com.shreya.auth.LoginCredentials;
import com.shreya.model.UserInfo;
import com.shreya.service.JwtService;
import com.shreya.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

/**
 * Created by shreya on 20/1/17.
 */
@Controller
@RequestMapping(path = "/logincred")
public class LoginController {

    private final LoginService loginService;
    private final JwtService jwtService;


    @SuppressWarnings("unused")
    public LoginController() {
        this(null, null);
    }

    @Autowired
    public LoginController(LoginService loginService, JwtService jwtService) {
        this.loginService = loginService;
        this.jwtService = jwtService;
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    public String login(@RequestBody LoginCredentials credentials,
                        HttpServletResponse response, Model model, Principal principal) {
        UserInfo userInfo = loginService.login(credentials);
        if (userInfo != null) {

            try {
                response.setHeader("Token", jwtService.tokenFor(userInfo));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            model.addAttribute("message",userInfo.getDob());
            return "loginSuccessfulPage";
        } else {
            return "loginErrorPage";
        }

    }
}