/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zonedout.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    
    public boolean idStringExists(String idString){
        return userAccountRepo.findByIdString(idString) != null;
    }
    
    public UserAccount getUserAccountByIdString(String idString) {
        return userAccountRepo.findByIdString(idString);
    }
    
    /*
    @Transactional
    public List<UserAccount> getContacts(String username){
        UserAccount account = userAccountRepo.findByUsername(username);
        return account.getContacts();
    }
    
    @Transactional
    public List<UserAccount> getSentInvites(String username){
        UserAccount account = userAccountRepo.findByUsername(username);
        return account.getSentInvites();
    }
    
    @Transactional
    public List<UserAccount> getReceivedInvites(String username){
        UserAccount account = userAccountRepo.findByUsername(username);
        return account.getReceivedInvites();
    }*/

    public boolean userExists(String username) {

        return userAccountRepo.findByUsername(username) != null;

    }
    /**
     * Create a regular user with granted authority USER 
     */
    public UserAccount createUser(
            String username, 
            String password, 
            String firstname, 
            String lastname,
            String idString) {

        UserAccount u = new UserAccount();
        u.setPassword(encoder.encode(password));
        u.setUsername(username);
        u.setFirstname(firstname);
        u.setLastname(lastname);
        u.setIdString(idString);
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
