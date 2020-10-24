/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zonedout.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 *
 * @author tvali
 */
@Entity
@Getter
@Setter
public class Post extends AbstractPersistable<Long> {

    private LocalDateTime dateTime;
    
    private String content;

    @ManyToOne
    private UserAccount author;
    
    @OneToMany(mappedBy="post")
    private List<Reply> replies = new ArrayList<>();
    
}
