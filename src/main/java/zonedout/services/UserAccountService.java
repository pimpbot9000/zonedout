/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zonedout.services;

import java.util.ArrayList;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import zonedout.models.UserAccount;
import zonedout.repositories.UserAccountRepository;

/**
 *
 * @author tvali
 */
@Service
public class UserAccountService {

    //Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    //String username = auth.getName();
    
    @Autowired
    private UserAccountRepository userAccountRepo;

    @Autowired
    private PasswordEncoder encoder;

    public UserAccount getUserAccount(String username) {

        return userAccountRepo.findByUsername(username);

    }

    public boolean userExists(String username) {

        return userAccountRepo.findByUsername(username) != null;

    }
    /**
     * Creates a regular user with granted authority USER
     * @param username
     * @param password
     * @param firstname
     * @param lastname
     * @return 
     */
    public UserAccount createUser(String username, String password, String firstname, String lastname) {

        UserAccount u = new UserAccount();
        u.setPassword(encoder.encode(password));
        u.setUsername(username);
        u.setFirstname(firstname);
        u.setLastname(lastname);
        u.setAuthorities(Arrays.asList("USER"));
       
        
        userAccountRepo.save(u);
        return getUserAccount(username);

    }
    
    public void updateBio(String username, String bio){
        UserAccount u = getUserAccount(username);
        u.setBio(bio);
        userAccountRepo.save(u);        
    }    
   
}
