/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zonedout.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import zonedout.models.Post;

/**
 *
 * @author tvali
 */
public interface PostRepository extends JpaRepository <Post, Long>{
    
}

