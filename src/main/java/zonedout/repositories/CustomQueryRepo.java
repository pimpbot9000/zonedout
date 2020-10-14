/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zonedout.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import zonedout.models.IMyQuery;
import zonedout.models.ProfileImage;
import zonedout.models.UserAccount;

/**
 *
 * @author tvali
 */

public interface CustomQueryRepo extends JpaRepository<ProfileImage, Long> {
    
    @Query(value = "SELECT ID AS id, USERNAME as username, CONTACTS_ID as contactsId FROM USER_ACCOUNT LEFT OUTER JOIN (SELECT * FROM USER_ACCOUNT_CONTACTS  WHERE CONTACTS_ID = 1) AS FRIEND ON USER_ACCOUNT.ID = FRIEND.USER_ACCOUNT_ID",  nativeQuery = true)
    List<IMyQuery> getWithFriends();  
    
    @Query("SELECT id as id, username as username, id as contactsId FROM UserAccount")
    List<IMyQuery> getWithFriends2();  
    
    //, CONTACTS_ID as contactsId
    @Query("SELECT id AS id, username as username, contactsId as contactsId FROM UserAccount LEFT OUTER JOIN (SELECT * FROM UserAccount u.contacts WHERE contactsId = 1) AS Friend ON UserAccount.If = Friend.UserAccountId")
    List<IMyQuery> getWithFriends3();  
}

