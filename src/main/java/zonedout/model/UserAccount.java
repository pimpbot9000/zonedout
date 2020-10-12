/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zonedout.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 *
 * @author tvali
 */

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class UserAccount extends AbstractPersistable<Long>{
    private String username;
    private String firstname;
    private String lastname;
    private String password;
    
    //@ManyToMany
    //private List<UserAccount> contacts = new ArrayList<>();
    
    
}
