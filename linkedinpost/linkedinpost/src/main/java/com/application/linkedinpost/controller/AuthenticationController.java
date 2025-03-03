package com.application.linkedinpost.controller;

import com.application.linkedinpost.dto.AuthenticationRequestBody;
import com.application.linkedinpost.dto.AuthenticationResponseBody;
import com.application.linkedinpost.model.AuthenticationUser;
import com.application.linkedinpost.service.AuthenticationService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/api/v1/authentication")

@CrossOrigin("*")
public class AuthenticationController {


    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService){
        this.authenticationService = authenticationService;
    }

//    @GetMapping("/user")
//    public AuthenticationUser getUser(){
//        return authenticationService.getUser("yaho@gmail.com");
//    }

    @GetMapping("/user")
    public AuthenticationUser getUser(@RequestAttribute("authenticatedUser") AuthenticationUser authenticationUser){
        return authenticationService.getUser(authenticationUser.getEmail());
    }

    @PostMapping(value = "/login", consumes = "application/json")
    public AuthenticationResponseBody loginPage(@Valid @RequestBody AuthenticationRequestBody loginRequestBody) {
        return authenticationService.login(loginRequestBody);
    }

    @PostMapping(value = "/register", consumes = "application/json")
    public AuthenticationResponseBody registerPage(@Valid @RequestBody AuthenticationRequestBody registerRequestBody)
            throws MessagingException, UnsupportedEncodingException {
        return authenticationService.register(registerRequestBody);
    }


    @PutMapping("/validate-email-verification-token")
    public String verifyEmail(@RequestParam String token,@RequestAttribute("authenticatedUser")AuthenticationUser user){
        authenticationService.validateEmailVerificationToken(token, user.getEmail());
        return "Email verified successfully.";
    }
    @GetMapping("/send-email-verification-token")
    public String sendEmailVerificationToken(@RequestAttribute("authenticatedUser") AuthenticationUser user){
        authenticationService.sendEmailVerificationToken(user.getEmail());
        return "Email verification token sent successfully";
    }

    @PutMapping("/send-password-reset-token")
    public String sendPasswordResetToken(@RequestParam String email){
        authenticationService.sendPasswordResetToken(email);
        return "Password reset token sent successfully";
    }

    @DeleteMapping("/delete")
    public String deleteUser(@RequestAttribute("authenticatedUser") AuthenticationUser user){
        authenticationService.deleteUser(user.getId());
        return "User deleted successfully";
    }

    @PutMapping("/reset-password")
    public String resetPassword(@RequestParam String newPassword, @RequestParam String token,@RequestParam String email){
        authenticationService.resetPassword(email,newPassword , token);
        return "Password reset successfully";
    }

@PutMapping("/profile/{id}")
    public  AuthenticationUser updateUserProfile(
            @RequestAttribute("authenticatedUser") AuthenticationUser user,@PathVariable Long id,
            @RequestParam(required =false) String firstName,@RequestParam(required = false)String lastName,
            @RequestParam(required = false) String company,@RequestParam(required = false)String position,
            @RequestParam(required = false) String location
){
        if(!user.getId().equals(id)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,"User does not have permission to update this profile.");
        }
        return authenticationService.updateUserProfile(id,firstName,lastName,company,position,location);
}





}
