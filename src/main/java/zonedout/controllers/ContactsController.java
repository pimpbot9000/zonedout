/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zonedout.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import zonedout.services.ContactsService;

/**
 *
 * @author tvali
 */
@Controller
public class ContactsController {

    @Autowired
    private ContactsService contactsService;

    @PostMapping("/contacts/{contactId}")
    public String removeContact(
            Authentication auth,
            @PathVariable Long contactId,
            @RequestParam String redirect
    ) {        
        contactsService.removeContact(auth.getName(), contactId);
        System.out.println("Removing contact " + contactId);
        return "redirect:/" + redirect;
    }
}
