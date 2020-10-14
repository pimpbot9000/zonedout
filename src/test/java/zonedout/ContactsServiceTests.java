/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zonedout;

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


@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class ContactsServiceTests {

    @Autowired
    private UserAccountService accountService;
    
    @Autowired
    private UserAccountRepository accountRepo; 

    @Autowired
    private ContactsService contactsService;

    private static String username = "username10";
    private static String password = "swordfish";
    private static String firstname = "alice";
    private static String lastname = "smith";

    private static String other = "other";

    @Before
    public void setUp() {

        /*String username = "username10";
        String password = "swordfish";
        String firstname = "alice";
        String lastname = "smith";

        String other = "other";*/
        accountService.createUser(username, password, firstname, lastname);
        accountService.createUser(username + other, password + other, firstname + other, lastname + other);
    }

    @After
    public void tearDown() {
        accountRepo.deleteAll();
    }

    @Test
    public void sendInvite() {
        
        // initial test that test database holds user
        assertEquals(username, accountService.getUserAccount(username).getUsername());
        
        // initial test that test database holds other user
        assertEquals(username + other, accountService.getUserAccount(username + other).getUsername());
        
        // send invite using service
        //contactsService.sendInvite(username, username + other);
        
        //System.out.println("          ****** RECEIVED INVITES" + accountService.getUserAccount(username).getInvites().size());
        //assertEquals(username + other, accountService.getUserAccount(username).getInvites().get(0).getUsername());
        
        
    }
}
