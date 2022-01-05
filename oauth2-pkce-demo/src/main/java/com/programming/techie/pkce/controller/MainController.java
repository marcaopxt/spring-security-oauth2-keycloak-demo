package com.programming.techie.pkce.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;  

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class MainController {

	@ResponseStatus(HttpStatus.OK)
    @GetMapping("/home")
	public String home() {
		return "home";
	}

    @GetMapping("/oauthinfo")  
    @ResponseBody  
    public String oauthUserInfo(@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient authorizedClient,  
                              @AuthenticationPrincipal OAuth2User oauth2User) {  
         return  
            "User Name: " + oauth2User.getName() + "<br/>" +  
            "User Authorities: " + oauth2User.getAuthorities() + "<br/>" +  
            "Client Name: " + authorizedClient.getClientRegistration().getClientName() + "<br/>" +  
            prettyPrintAttributes(oauth2User.getAttributes());  
    }  
  
    private String prettyPrintAttributes(Map<String, Object> attributes) {  
        String acc = "User Attributes: <br/><div style='padding-left:20px'>";  
        for (String key : attributes.keySet()){  
            Object value = attributes.get(key);  
            acc += "<div>"+key + ":&nbsp" + value.toString() + "</div>";  
        }  
        return acc + "</div>";  
    } 	
}
