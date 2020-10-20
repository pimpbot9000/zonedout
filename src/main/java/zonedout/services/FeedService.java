/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zonedout.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zonedout.models.Post;
import zonedout.models.UserAccount;
import zonedout.repositories.PostRepository;

/**
 *
 * @author tvali
 */

@Service
public class FeedService {
    
    @Autowired
    private PostRepository postRepository;
    
    public List<Post> getPosts(UserAccount account){
                
        return postRepository.findAll();
    }
}
