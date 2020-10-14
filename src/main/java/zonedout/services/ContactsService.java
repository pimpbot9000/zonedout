/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zonedout.services;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zonedout.models.UserAccount;
import zonedout.repositories.UserAccountRepository;

/**
 *
 * @author tvali
 */
@Service
public class ContactsService {
    
    @Autowired
    private UserAccountRepository accountRepo;    
    
    //@Transactional
    public void sendInvite(String username, String otherUsername){
        
        /*UserAccount account = accountRepo.findByUsername(username);
        UserAccount otherAccount = accountRepo.findByUsername(otherUsername);  
        
        List<UserAccount> invites = account.getInvites();
        invites.add(otherAccount);
        
        account.setInvites(invites);
        
        accountRepo.save(account);*/
                
    }
}
