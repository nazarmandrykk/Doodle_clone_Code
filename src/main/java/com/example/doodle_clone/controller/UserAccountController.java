package com.example.doodle_clone.controller;

import jakarta.mail.SendFailedException;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import com.example.doodle_clone.models.ConfirmationToken;
import com.example.doodle_clone.models.User;
import com.example.doodle_clone.repo.ConfirmationTokenRepository;
import com.example.doodle_clone.repo.UserRepository;
import com.example.doodle_clone.services.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.management.remote.JMXAuthenticator;

@Controller
@Getter@Setter
public class UserAccountController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    private EmailSenderService emailSenderService;

    @RequestMapping(value="/register", method = RequestMethod.GET)
    public ModelAndView displayRegistration(ModelAndView modelAndView, User user)
    {
        modelAndView.addObject("user", user);
        modelAndView.setViewName("register");
        return modelAndView;
    }

    @RequestMapping(value="/register", method = RequestMethod.POST)
    public ModelAndView registerUser(ModelAndView modelAndView, User user) throws SendFailedException
    {

        User existingUser = userRepository.findByEmailId(user.getEmailId());
        if(existingUser != null)
        {
            modelAndView.addObject("message","Пошта зайнята!");
            modelAndView.setViewName("error");
        }
        else
        {

            userRepository.save(user);
            ConfirmationToken confirmationToken = new ConfirmationToken(user);
            confirmationTokenRepository.save(confirmationToken);

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(user.getEmailId());
            mailMessage.setSubject("Підтвердіть реєстрацію!");
            mailMessage.setFrom("bbmemes.1@gmail.com");
            mailMessage.setText("Щоб підтвердити,перейдіть по посиланню : "
                    +"http://localhost:8080/confirm-account?token="+confirmationToken.getConfirmationToken());

            emailSenderService.sendEmail(mailMessage);
            modelAndView.addObject("emailId", user.getEmailId());
            modelAndView.setViewName("successfulRegisteration");
        }

        return modelAndView;
    }

    @RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView confirmUserAccount(ModelAndView modelAndView, @RequestParam("token")String confirmationToken)
    {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
        if(token != null)
        {
            User user = userRepository.findByEmailId(token.getUser().getEmailId());
            user.setEnabled(true);
            userRepository.save(user);
            modelAndView.setViewName("accountVerified");
        }
        else
        {
            modelAndView.addObject("message","Посилання не робоче");
            modelAndView.setViewName("error");
        }

        return modelAndView;
    }
    /*
    @Autowired
    private AuthenticationManager authenticationManager;
    @RequestMapping(value="/login", method = RequestMethod.POST)
    public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password, RedirectAttributes redirectAttributes) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        redirectAttributes.addFlashAttribute("message", "Вхід виконано успішно!");
        return "redirect:/main-title";
    }
    */
}


