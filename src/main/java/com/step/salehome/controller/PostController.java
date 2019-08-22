package com.step.salehome.controller;

import com.step.salehome.config.CaptchaSettings;
import com.step.salehome.constants.MessageConstants;
import com.step.salehome.constants.URLConstants;
import com.step.salehome.model.Post;
import com.step.salehome.service.PostService;
import com.step.salehome.util.EmailUtil;
import com.step.salehome.util.ReCaptchaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestOperations;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.net.URI;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Controller
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private RestOperations restTemplate;
    @Autowired
    private CaptchaSettings captchaSettings;
    @Autowired
    private EmailUtil emailUtil;

    @RequestMapping("/send-email/{id}")
    public String sendEmail(@PathVariable("id") int id,
                            @RequestParam("email") String email,
                            @RequestParam("phone") String phone,
                            @RequestParam("message") String message,
                            @RequestParam(name = "g-recaptcha-response") String reCaptchaResponse,
                            RedirectAttributes redirectAttributes,
                            HttpServletRequest httpServletRequest) {
        Post post = postService.getPostById(id);
        URI verifyUri = URI.create(captchaSettings.getUrl() +
                "?secret=" + captchaSettings.getSecret() +
                "&response=" + reCaptchaResponse +
                "&remoteip=" + httpServletRequest.getRemoteAddr());
        ReCaptchaResponse response = restTemplate.getForObject(verifyUri, ReCaptchaResponse.class);
        try {
            if (!response.isSuccess()) {
                redirectAttributes.addFlashAttribute("message", MessageConstants.ERROR_RECAPTCHA);
                return "redirect:" + httpServletRequest.getHeader("Referer");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (post.isEmailAllowed() && (email != null && !email.trim().isEmpty()) && (phone != null && !phone.trim().isEmpty()) && message != null) {
            String subject = "Someone is interested in about your post";
            String emailMessage = message + "\n \n \n" + phone + "\n\n\n" + email;
            Executors.newSingleThreadExecutor().submit(() -> emailUtil.sendMessageToPostOwner(post.getUser().getEmail(), subject, emailMessage));
            redirectAttributes.addFlashAttribute("message", MessageConstants.SUCCES_SENDING_EMAIL);
            return URLConstants.REDIRECT_HOME;
        } else {
            redirectAttributes.addFlashAttribute("message", MessageConstants.ERROR_SENDING_EMAIL);
            return URLConstants.REDIRECT_POST + "/" + id;
        }
    }
}
