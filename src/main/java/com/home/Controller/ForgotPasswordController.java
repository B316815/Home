package com.home.Controller;

import com.home.Service.GmailService;
import com.home.entity.AppUser;
import com.home.entity.ForgotPassword;
import com.home.paylod.ChangePassword;
import com.home.paylod.MailBody;
import com.home.repository.AppUserRepository;
import com.home.repository.ForgotPasswordRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.time.Instant;
import java.util.Date;
import java.util.Objects;
import java.util.Random;

@RestController
@RequestMapping("/forgotPassword")
public class ForgotPasswordController {

    private AppUserRepository appUserRepository;
    private GmailService gmailService;
    private ForgotPasswordRepository forgotPasswordRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public ForgotPasswordController(AppUserRepository appUserRepository, GmailService gmailService, ForgotPasswordRepository forgotPasswordRepository, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.gmailService = gmailService;
        this.forgotPasswordRepository = forgotPasswordRepository;

    }

    //send mail for email verification
@PostMapping("/verifyMail/{email}")
    public ResponseEntity<String> verifyEmail(@PathVariable String email) {
        AppUser appUser = appUserRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Please provide valid Email"));
        int otp = otpGenerator();
        MailBody mailBody = MailBody.builder()
            .to(email)
            .text("this is the otp for your forgot password request :"+ otp)
            .subject("otp for forgot password request")
            .build();

        ForgotPassword fp = ForgotPassword.builder()
                .otp(otp)
                .expirationTime(new Date(System.currentTimeMillis()+120*1000))
                .appUser(appUser)
                .build();
        gmailService.sendSimpleMessage(mailBody);
        forgotPasswordRepository.save(fp);

        return ResponseEntity.ok("Email sent For Verification");

    }
    private Integer otpGenerator(){
        Random random= new Random();
        return random.nextInt(100000,999999);
    }
    @PostMapping("/verifyOtp/{otp}/{email}")
    public ResponseEntity<String>verifyOtp(@PathVariable Integer otp,@PathVariable String email){
        AppUser appUser = appUserRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Please provide valid Email"));

        ForgotPassword fp = forgotPasswordRepository.findByOtpAndAppUser(otp, appUser)
                .orElseThrow(() -> new RuntimeException("Invalid Otp for email" + email));
        if(fp.getExpirationTime().before(Date.from(Instant.now()))){
            forgotPasswordRepository.deleteById(fp.getFpId());
            return new ResponseEntity<>("OTP is expired", HttpStatus.EXPECTATION_FAILED);
        }
        return ResponseEntity.ok("OTP Verifed!");

    }


    @PostMapping("/changePassword/{email}")
    public ResponseEntity<String>changePasswordHandler(@RequestBody ChangePassword changePassword,@PathVariable String email){
        if(!Objects.equals(changePassword.password(),changePassword.repeatPassword())){
            return new ResponseEntity<>("Please enter the password again!",HttpStatus.EXPECTATION_FAILED);
        }
        String encodedPassword = passwordEncoder.encode(changePassword.password());
         appUserRepository.updatePassword(email,encodedPassword);

         return ResponseEntity.ok("Password has been changed");
    }
}
