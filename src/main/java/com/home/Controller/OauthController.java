package com.home.Controller;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class OauthController {
    @GetMapping
    public Map<String, Object> getUser(@AuthenticationPrincipal OAuth2User oAuth2User){
        return oAuth2User.getAttributes();
    }










//    @GetMapping("/")
//    public String home(Model model, @AuthenticationPrincipal OidcUser principal) {
//        if (principal != null) {
//            model.addAttribute("name", principal.getName());
//            model.addAttribute("email", principal.getEmail());
//        }
//        return "home";
//    }
}
