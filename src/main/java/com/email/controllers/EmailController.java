package com.email.controllers;

import com.email.model.EmailRequest;
import com.email.model.EmailResponse;
import com.email.services.EmailService;
import org.apache.catalina.filters.ExpiresFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class EmailController {

    @Autowired
    EmailService emailServices;

    @GetMapping("/welcome")
    public ResponseEntity<?> welcome(){
        return ResponseEntity.ok(new EmailResponse("welcome to send email application"));
    }

    @RequestMapping(value = "/sendemail",method = RequestMethod.POST)
    public ResponseEntity<?> sendEmail(@RequestBody EmailRequest request){

//        this.emailServices.sendMail();
        System.out.println(request);
        boolean result = this.emailServices.sendMail(request.getTo(),request.getSubject(),request.getMessage());
        if (result) {
            return ResponseEntity.ok().body(new EmailResponse("Email Send Successfully to given address"));
        }else {

            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new EmailResponse("Unable to Send Email to the given address"));

        }




    }

}
