/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zonedout.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 *
 * @author tvali
 */

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
public class UserAccount extends AbstractPersistable<Long>{
    private String username;
    private String firstname;
    private String lastname;
    private String password;
    private String idString;
    private String bio;
    
    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> authorities;    
    
    @OneToOne(fetch = FetchType.LAZY)    
    private ProfileImage profileImage;
    
    @ManyToMany(fetch = FetchType.LAZY)    
    private List<UserAccount> contacts = new ArrayList<>();
    
    @ManyToMany(fetch = FetchType.LAZY) 
    private List<UserAccount> sentInvites = new ArrayList<>();
    
    @ManyToMany(fetch = FetchType.LAZY)
    private List<UserAccount> receivedInvites = new ArrayList<>();    
   
    @OneToMany(mappedBy = "author")
    private List<Post> posts = new ArrayList<>();
    
}
