/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zonedout.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zonedout.models.UserAccount;
import zonedout.repositories.UserAccountRepository;

/**
 * Class violates DRY principle, but..
 *
 * @author tvali
 */
@Service
public class ContactsService {

    @Autowired
    private UserAccountRepository userAccountRepo;

    @Transactional
    //@SuppressWarnings({"unchecked", "unsafe"})
    public void createContact(Long id, Long otherId) {

        Optional<UserAccount> account = (Optional<UserAccount>) userAccountRepo.findById(id);

        Optional<UserAccount> otherAccount = (Optional<UserAccount>) userAccountRepo.findById(otherId);

        if (!account.isPresent() || !otherAccount.isPresent()) {
            return;
        }

        List<UserAccount> contacts = account.get().getContacts();

        List<UserAccount> otherContacts = otherAccount.get().getContacts();

        if (!contacts.contains(otherAccount.get())) {
            contacts.add(otherAccount.get());
        }

        if (!otherContacts.contains(account.get())) {
            otherContacts.add(account.get());
        }

    }

    @Transactional
    public void removeContact(Long userId, Long contactId) {

        Optional<UserAccount> account = userAccountRepo.findById(userId);
        Optional<UserAccount> contactAccount = userAccountRepo.findById(contactId);

        if (!account.isPresent() || !contactAccount.isPresent()) {
            return;
        }

        account.get().getContacts().remove(contactAccount.get());
        contactAccount.get().getContacts().remove(account.get());

    }

    /**
     * Remove a contact relation between user and contact.
     *
     * @param username
     * @param contactId
     */
    @Transactional
    public void removeContact(String username, Long contactId) {

        UserAccount account = userAccountRepo.findByUsername(username);
        Optional<UserAccount> contactAccount = userAccountRepo.findById(contactId);

        if (account == null || !contactAccount.isPresent()) {
            return;
        }
        System.out.println("ContactsService remove");
        account.getContacts().remove(contactAccount.get());
        contactAccount.get().getContacts().remove(account);

    }

    /**
     * Remove an invite the user has sent.
     *
     * @param username
     * @param contactId
     */
    @Transactional
    public void cancelPendingInvite(String username, Long contactId) {

        UserAccount account = userAccountRepo.findByUsername(username);
        Optional<UserAccount> contactAccount = userAccountRepo.findById(contactId);

        if (account == null || !contactAccount.isPresent()) {
            return;
        }

        account.getSentInvites().remove(contactAccount.get());
        contactAccount.get().getReceivedInvites().remove(account);

    }

    @Transactional
    public int acceptPendingApproval(String username, Long contactId) {

        UserAccount account = userAccountRepo.findByUsername(username);
        Optional<UserAccount> contactAccount = userAccountRepo.findById(contactId);

        if (account == null || !contactAccount.isPresent()) {
            return DOES_NOT_EXIST;
        }

        // if invite still exists        
        if (contactAccount.get().getSentInvites().contains(account)) {

            account.getReceivedInvites().remove(contactAccount.get());
            contactAccount.get().getSentInvites().remove(account);

            this.createContact(account.getId(), contactId);

            return SUCCESS;

        }

        return DOES_NOT_EXIST;

    }

    @Transactional
    public void rejectPendingApproval(String username, Long contactId) {

        UserAccount account = userAccountRepo.findByUsername(username);
        Optional<UserAccount> contactAccount = userAccountRepo.findById(contactId);

        if (account == null || !contactAccount.isPresent()) {
            return;
        }

        account.getReceivedInvites().remove(contactAccount.get());
        contactAccount.get().getSentInvites().remove(account);
    }

    @Transactional
    public void sendInvite(Long inviterId, Long inviteeId) {
        UserAccount inviterAccount = userAccountRepo.getOne(inviterId);
        UserAccount inviteeAccount = userAccountRepo.getOne(inviteeId);

        inviterAccount.getSentInvites().add(inviteeAccount);
        inviteeAccount.getReceivedInvites().add(inviterAccount);

    }

    public static int DOES_NOT_EXIST = -1;
    public static int SUCCESS = 1;

}
