package zonedout.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import zonedout.models.UserAccount;
import zonedout.services.UserAccountService;

/**
 *
 * @author tvali
 */
@Controller
public class SignInController {

    @Autowired
    UserAccountService userAccountService;

    @GetMapping("/signin")
    public String signIn(
            @RequestParam(required = false) String error,
            @RequestParam(required = false) String username,
            Model model) {

        if (error != null && error.equals("userexists")) {
            
            model.addAttribute("errorMessage", "Username \""+ username +"\" already exists");            
            
        } else if (error != null && error.equals("passwordmismatch"))  {
            
            model.addAttribute("errorMessage", "Passwords did not match");
            model.addAttribute("username", username);
            
        }

        return "signin";
    }

    @PostMapping("/signin")
    public String addAccount(
            @RequestParam String username,
            @RequestParam String firstname,
            @RequestParam String lastname,
            @RequestParam String password1,
            @RequestParam String password2) {

        if (!password1.equals(password2)) {
            return "redirect:/signin?error=passwordmismatch&username=" + username;
        }

        if (userAccountService.userExists(username)) {
            return "redirect:/signin?error=userexists&username=" + username;
        }

        UserAccount u = userAccountService.createUser(username, password1, firstname, lastname);

        return "redirect:/";
    }

}
