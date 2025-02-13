package ma.alten.testAlten.business.service;

import ma.alten.testAlten.business.domain.Contact;

import java.util.List;
import java.util.Optional;

public interface ContactService {
    public List<Contact> getAllContacts();

    public Optional<Contact> getContactById(Long id);

    public Contact saveContact(Contact contact);

    public void deleteContact(Long id);

}
