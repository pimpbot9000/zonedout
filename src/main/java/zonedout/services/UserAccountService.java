/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zonedout.services;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import zonedout.model.UserAccount;
import zonedout.repositories.UserAccountRepository;

/**
 *
 * @author tvali
 */

@Service
public class UserAccountService {
    
    @Autowired
    private UserAccountRepository userAccountRepo;
    
    @Autowired
    private PasswordEncoder encoder;
    
    public UserAccount getUserAccount(String username){
        
        return userAccountRepo.findByUsername(username);      
        
    }
    
    public boolean userExists(String username){
        
        return userAccountRepo.findByUsername(username) != null;
        
    }
    
    public UserAccount createUser(String username, String password, String firstname, String lastname){
        
        UserAccount u = new UserAccount();
        u.setPassword(encoder.encode(password));
        u.setUsername(username);
        u.setFirstname(firstname);
        u.setLastname(lastname);
        userAccountRepo.save(u);
        return getUserAccount(username);
        
    }
}
