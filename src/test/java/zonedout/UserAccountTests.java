/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zonedout;

/**
 *
 * @author tvali
 */

import java.util.List;
import java.util.Optional;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import zonedout.models.UserAccount;
import zonedout.repositories.UserAccountRepository;
import zonedout.services.UserAccountService;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
//@AutoConfigureMockMvc
public class UserAccountTests {
    
    @Autowired
    private UserAccountRepository userAccountRepository;
    
    @Autowired
    private UserAccountService userAccountService;
    
    @Before
    public void setUp() {
        userAccountRepository.deleteAll();
    }

    @After
    public void tearDown() {
        userAccountRepository.deleteAll();
    }
    
    @Test
    public void createUserAccountCorrectly(){
        
        String username = "username10";
        String password = "swordfish";
        String firstname = "alice";
        String lastname = "smith";
                
        
        UserAccount account = userAccountService.createUser(username, password, firstname, lastname);
        
        // test the useraccount returned by service
        assertEquals(username, account.getUsername());
        
        // user should have only one authority, USER
        assertEquals(1, account.getAuthorities().size());
        assertEquals("USER", account.getAuthorities().get(0));
        
        // service should return user already exists
        assertEquals(true, userAccountService.userExists(username));
        
        // test the repository directly        
        List<UserAccount> userAccounts = userAccountRepository.findAll();
        
        // there should be onlu 1 user in repository
        assertEquals(1, userAccounts.size());
        assertEquals(username, userAccounts.get(0).getUsername());        
        
    }
    
}
