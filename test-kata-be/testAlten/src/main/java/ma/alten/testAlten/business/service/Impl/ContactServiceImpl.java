package ma.alten.testAlten.business.service.Impl;

import ma.alten.testAlten.business.domain.Contact;
import ma.alten.testAlten.business.repository.ContactRepository;
import ma.alten.testAlten.business.service.ContactService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
/**
 * Service implementation for managing contacts.
 * Provides methods to retrieve, save, and delete contacts.
 */
@Service
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;

    /**
     * Constructs a new ContactServiceImpl with the given repository.
     *
     * @param contactRepository the repository used for contact data access
     */
    public ContactServiceImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    /**
     * Retrieves all contacts from the repository.
     *
     * @return a list of all contacts
     */
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    /**
     * Retrieves a contact by its ID.
     *
     * @param id the ID of the contact to retrieve
     * @return an Optional containing the found contact, or empty if not found
     */
    public Optional<Contact> getContactById(Long id) {
        return contactRepository.findById(id);
    }

    /**
     * Saves a contact in the repository.
     * If the contact already exists, it will be updated.
     *
     * @param contact the contact to save
     * @return the saved contact
     */
    public Contact saveContact(Contact contact) {
        return contactRepository.save(contact);
    }

    /**
     * Deletes a contact by its ID.
     *
     * @param id the ID of the contact to delete
     */
    public void deleteContact(Long id) {
        contactRepository.deleteById(id);
    }
}