/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zonedout.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import zonedout.models.UserAccount;
import zonedout.repositories.UserAccountRepository;
import zonedout.services.UserAccountService;

/**
 *
 * @author tvali
 */
@Controller
public class DefaultController {
    
    @Autowired
    private UserAccountService userAccountService;
    
    @Autowired
    private Environment env;
    
    @GetMapping("/")
    public String root(Model model, Authentication authentication){
        
        if(authentication != null && authentication.isAuthenticated()){
            return "redirect:/home";
        }
        
        //UserAccount userAcc = userAccountService.getUserAccount(authentication.getName());       
        
        //model.addAttribute("userAccount", userAcc);
        
        printActiveProfiles();
        return "frontpage";
        
    }
    
    private void printActiveProfiles(){
        for ( String profileName : env.getActiveProfiles()){
            System.out.println("Currently active profile - " + profileName);
        }
    }
    
}