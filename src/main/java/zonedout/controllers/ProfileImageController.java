/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zonedout.controllers;

import java.io.IOException;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import zonedout.models.ProfileImage;
import zonedout.models.UserAccount;
import zonedout.repositories.ProfileImageRepository;
import zonedout.repositories.UserAccountRepository;
import zonedout.services.UserAccountService;

/**
 *
 * @author tvali
 */
@Controller
public class ProfileImageController {

    //@Autowired
    //FileRepository fileRepository;
    @Autowired
    private UserAccountRepository userAccountRepo;

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private ProfileImageRepository profileImageRepo;

    /*@GetMapping("/files")
    public String files(Model model) {

        List<FileObject> files = fileRepository.findAll();
        model.addAttribute("files", files);

        return "files";
    }*/

 /*@GetMapping("/files/{id}")
    public ResponseEntity<byte[]> viewFile(@PathVariable Long id) {
        FileObject fo = fileRepository.getOne(id);

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(fo.getContentType()));
        headers.setContentLength(fo.getContentLength());
        headers.add("Content-Disposition", "attachment; filename=" + fo.getName());

        return new ResponseEntity<>(fo.getContent(), headers, HttpStatus.CREATED);
    }*/
    
    
    
    /**
     * Any authenticated user has acces to images, but that's ok
     *
     * @param username
     * @return
     */
    @GetMapping(path = "/profileimages/{username}/content", produces = "image/png")
    @ResponseBody
    @Transactional
    public byte[] get(@PathVariable String username) {
        return userAccountService.getUserAccount(username).getProfileImage().getContent();
    }

    @PostMapping("/profileimages")
    public String save(@RequestParam("file") MultipartFile file, Authentication authentication) throws IOException {

        UserAccount account = userAccountService.getUserAccount(authentication.getName());

        ProfileImage profileImage = account.getProfileImage();

        if (profileImage == null) {

            profileImage = new ProfileImage();

        }

        profileImage.setContent(file.getBytes());
        profileImageRepo.save(profileImage);

        account.setProfileImage(profileImage);
        userAccountRepo.save(account);

        return "redirect:/myprofile";
    }

}
