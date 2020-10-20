/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zonedout.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import zonedout.models.Post;
import zonedout.models.UserAccount;
import zonedout.services.FeedService;
import zonedout.services.UserAccountService;

/**
 *
 * @author tvali
 */
@Controller
public class FeedController {

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private FeedService feedService;

    @GetMapping("/feed")
    @ResponseBody
    public String getFeed(Authentication auth, Model model) {

        UserAccount account = userAccountService.getUserAccount(auth.getName());
        
        List<Post> posts = feedService.getPosts(account);
        
        String result = "";
        
        for (Post p : posts){
            result += "username: " + p.getAuthor().getUsername() + " " + p.getContent() + "\n";
        }
        
        return result;
    }

}
