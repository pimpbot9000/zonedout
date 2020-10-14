/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zonedout.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import zonedout.models.IMyQuery;
import zonedout.models.UserAccount;
import zonedout.repositories.CustomQueryRepo;
import zonedout.repositories.UserAccountRepository;

/**
 *
 * @author tvali
 */
@Controller
@Profile("dev")
public class DevelopmentController {

    @Autowired
    private UserAccountRepository userAccountRepo;
    
    @Autowired
    private CustomQueryRepo custom;

    @GetMapping("/dev/createcontact/{targetId}/{contactId}")
    public String createContact(@PathVariable Long targetId, @PathVariable Long contactId) {

        UserAccount account = userAccountRepo.getOne(targetId);

        UserAccount otherAccount = userAccountRepo.getOne(contactId);
        
        List<UserAccount> contacts = account.getContacts();

        List<UserAccount> otherContacts = otherAccount.getContacts();        

        if (!contacts.contains(otherAccount)) {
            contacts.add(otherAccount);
            account.setContacts(contacts);
            userAccountRepo.save(account);
        }

        if (!otherContacts.contains(account)) {
            otherContacts.add(account);            
            userAccountRepo.save(otherAccount);
        }                
        
        return "redirect:/home";
    }

    @GetMapping("/dev/test")
    public String test(){
        List<IMyQuery> res = custom.getWithFriends();
        
        //for( IMyQuery item : res){
        //    System.out.println("" + item.getUsername() + " " + item.getId() + " " + item.getContactsId());
        //}
        return "redirect:/";
    }
}
