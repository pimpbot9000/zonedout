/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zonedout.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import zonedout.models.UserAccount;
import zonedout.services.UserAccountService;

/**
 *
 * @author tvali
 */
@Controller 
public class ProfileController {
    
    @Autowired
    UserAccountService userAccountService;
    
    @GetMapping("/profiles/{idString}")
    public String getProfile(
            Authentication auth, 
            Model model, 
            @PathVariable String idString){
        
        // other user's account
        UserAccount account = userAccountService.getUserAccountByIdString(idString);
                
        //if profile is user's own profile
        if(account.getUsername().equals(auth.getName())){
            return "redirect:/home";
        }
        
        UserAccount userAccount = userAccountService.getUserAccount(auth.getName());
        model.addAttribute("userAccount", userAccount);
        model.addAttribute("account", account);
        return "profile";
    }
}
