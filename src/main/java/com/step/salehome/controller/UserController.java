package com.step.salehome.controller;

import com.step.salehome.constants.MessageConstants;
import com.step.salehome.constants.URLConstants;
import com.step.salehome.constants.UserConstants;
import com.step.salehome.exceptions.DuplicateEmailException;
import com.step.salehome.exceptions.InvalidTokenException;
import com.step.salehome.model.Role;
import com.step.salehome.model.User;
import com.step.salehome.service.UserService;
import com.step.salehome.util.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.UUID;
import java.util.concurrent.Executors;

@Controller
public class UserController {
    @Autowired
    private BCryptPasswordEncoder encoder;
    @Autowired
    private UserService userService;
    @Autowired
    private EmailUtil emailUtil;

    @PostMapping("/register")
    public String addUser(@Valid @ModelAttribute("user") User user,
                          @RequestParam("password2") String password2,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("message", bindingResult.getFieldError().getDefaultMessage());
            return URLConstants.REDIRECT_REGISTER;
        }
        if (!user.getPassword().equals(password2)) {
            redirectAttributes.addFlashAttribute("message", MessageConstants.ERROR_PASSWORD_IS_NOT_MATCH);
            return URLConstants.REDIRECT_REGISTER;
        }
        user.setRole(new Role(UserConstants.ROLE_ID_USER));
        user.setStatus(UserConstants.USER_STATUS_INACTIVE);
        user.setToken(UUID.randomUUID().toString());
        user.setPassword(encoder.encode(user.getPassword()));
        try {
            userService.registerUser(user);
        } catch (DuplicateEmailException e) {
            redirectAttributes.addFlashAttribute(MessageConstants.ERROR_DUBLICATE_EMAIL);
            return URLConstants.REDIRECT_REGISTER;
        }
        Executors.newSingleThreadExecutor().submit(() -> emailUtil.sendSimpleMessage(user.getEmail(), user.getFirstName(), user.getToken()));
        return URLConstants.REDIRECT_HOME;
    }

    @RequestMapping("/activate")
    public String activateUserByToken(@RequestParam("token") String token) throws InvalidTokenException {
        String newToken = UUID.randomUUID().toString();
        userService.updateUserStatusByToken(token, newToken);
        return URLConstants.REDIRECT_LOGIN;
    }
}
