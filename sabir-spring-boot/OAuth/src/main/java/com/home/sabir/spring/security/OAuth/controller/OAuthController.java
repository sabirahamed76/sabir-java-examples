package com.home.sabir.spring.security.OAuth.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OAuthController implements ErrorController{
    
	@RequestMapping("/")
    public ResponseEntity<?> HelloWorld() {
        return new ResponseEntity<>("Hello World !! click to <a href='/oauth2/authorization/google' > Login</a>" , HttpStatus.OK);
    }
	
	@Override
    @RequestMapping("/error")
    @ResponseBody
    public String getErrorPath() {
        // TODO Auto-generated method stub
        return "No Mapping Found";
    }
   
	
	@RequestMapping("/user")
    public String user(@AuthenticationPrincipal OAuth2User user) {
        return "Hello, " + user.getName() +" Welcome !!! Click to <a href='/logout' > Logout</a>";
    }
    
	@RequestMapping("/restricted")
    public ResponseEntity<?> HelloWorldRestricted() {
        return new ResponseEntity<>("Hello World from restricted end point" , HttpStatus.OK);
    }
    
    
	@RequestMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return "redirect:/";  // Redirects to home after logout
    }

    
}