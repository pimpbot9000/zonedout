/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zonedout;

import java.util.List;
import org.hibernate.Hibernate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import zonedout.repositories.UserAccountRepository;
import zonedout.services.ContactsService;
import zonedout.services.UserAccountService;
import static org.junit.Assert.*;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.transaction.annotation.Transactional;
import zonedout.models.UserAccount;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ContactsServiceTests {

    @Autowired
    private UserAccountService accountService;

    @Autowired
    private UserAccountRepository accountRepo;

    @Autowired
    private ContactsService contactsService;

    private static final String username1 = "username10";
    private static final String password1 = "swordfish";
    private static final String firstname1 = "Alice";
    private static final String lastname1 = "Smith";
    private static final String idString1 = "username1";
    
    private static final String username2 = "username20";
    private static final String password2 = "swordfish";
    private static final String firstname2 = "Bob";
    private static final String lastname2 = "Smith";
    private static final String idString2 = "username2";
    
    private UserAccount account;
    private UserAccount otherAccount;
   
    @Before
    public void setUp() {
        accountService.createUser(username1, password1, firstname1, lastname1, idString1);
        accountService.createUser(username2, password2, firstname2, lastname2, idString2);
        account = accountService.getUserAccount(username1);
        otherAccount = accountService.getUserAccount(username2);
        contactsService.createContact(account.getId(), otherAccount.getId());
        contactsService.sendInvite(account.getId(), otherAccount.getId());
    }

    @After
    public void tearDown() {
        accountRepo.deleteAll();
    }

    @Test
    @Transactional
    public void createContact() {

        // initial test that test database holds user
        //UserAccount 
        assertEquals(username1, account.getUsername());
        // initial test that test database holds other user
        //UserAccount otherAccount = accountService.getUserAccount(username2);
        assertEquals(username2, otherAccount.getUsername());
        
        // create contact
        
        
        // test contacts
        assertEquals(1, accountService.getUserAccount(username1).getContacts().size());
        assertEquals(1, accountService.getUserAccount(username2).getContacts().size());
    }
    
    @Test
    @Transactional
    public void sendInvite(){
        
        UserAccount inviterAccount = accountService.getUserAccount(username1);
        UserAccount inviteeAccount = accountService.getUserAccount(username2);
        
        
        
        List<UserAccount> sentInvites = inviterAccount.getSentInvites();
        assertEquals(1, sentInvites.size());
        
    }
}
