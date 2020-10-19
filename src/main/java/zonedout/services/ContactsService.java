/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zonedout.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zonedout.models.UserAccount;
import zonedout.repositories.UserAccountRepository;

/**
 *
 * @author tvali
 */
@Service
public class ContactsService {
    
    @Autowired
    private UserAccountRepository userAccountRepo;    
    
    @Transactional
    public void createContact(Long id, Long otherId) {
        
        Optional<UserAccount> account = userAccountRepo.findById(id);

        Optional<UserAccount> otherAccount = userAccountRepo.findById(otherId);
        
        if(account.isEmpty() || otherAccount.isEmpty()){
            return;
        }
        
        List<UserAccount> contacts = account.get().getContacts();

        List<UserAccount> otherContacts = otherAccount.get().getContacts();        

        if (!contacts.contains(otherAccount.get())) {
            contacts.add(otherAccount.get());
        }

        if (!otherContacts.contains(account.get())) {
            otherContacts.add(account.get());          
        } 
                
    }
    
    @Transactional
    public void removeContact(Long userId, Long contactId){
        
        Optional<UserAccount> account = userAccountRepo.findById(userId);
        Optional<UserAccount> contactAccount = userAccountRepo.findById(contactId);
        
        if(account.isEmpty() || contactAccount.isEmpty()){
            return;
        }
                
        account.get().getContacts().remove(contactAccount.get());
        contactAccount.get().getContacts().remove(account.get());
        
    }
    
    @Transactional
    public void removeContact(String username, Long contactId){
        
        UserAccount account = userAccountRepo.findByUsername(username);
        Optional<UserAccount> contactAccount = userAccountRepo.findById(contactId);
        
        if(account == null || contactAccount.isEmpty()){
            return;
        }
        System.out.println("ContactsService remove");
        account.getContacts().remove(contactAccount.get());
        contactAccount.get().getContacts().remove(account);
        
    }
    
     
    
    @Transactional
    public void sendInvite(Long inviterId, Long inviteeId){
        UserAccount inviterAccount = userAccountRepo.getOne(inviterId);
        UserAccount inviteeAccount = userAccountRepo.getOne(inviteeId);
        
        inviterAccount.getSentInvites().add(inviteeAccount);
        inviteeAccount.getReceivedInvites().add(inviterAccount);        
        
    }
}
