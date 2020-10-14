/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zonedout.models;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 *
 * @author tvali
 */
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class ProfileImage extends AbstractPersistable<Long>{
    
    @Lob
    @Basic(fetch = FetchType.LAZY)
    //@Column(name = "content", columnDefinition="clob")
    @Type(type = "org.hibernate.type.BinaryType")
    
    private byte[] content;
    
    @OneToOne(mappedBy = "profileImage")
    private UserAccount userAccount;
    
}
