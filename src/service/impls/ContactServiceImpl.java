package service.impls;

import dao.impls.ContactDaoImpl;
import models.Contact;
import service.ContactService;

import java.util.List;

public class ContactServiceImpl implements ContactService {
    private final ContactDaoImpl contactDao;

    public ContactServiceImpl(ContactDaoImpl contactDao) {
        this.contactDao = contactDao;
    }

    @Override
    public String addContactToPhone(Long phoneId, Contact contact) {
        return contactDao.addContactToPhone(phoneId, contact);
    }

    @Override
    public Contact findContactByName(Long phoneId, String contactName) {
        return contactDao.findContactByName(phoneId, contactName);
    }

    @Override
    public Contact findContactByPhoneNumber(Long phoneId, String phoneNumber) {
        return contactDao.findContactByPhoneNumber(phoneId, phoneNumber);
    }

    @Override
    public List<Contact> sortContactsByName(Long phoneId) {
        return contactDao.sortContactsByName(phoneId);
    }

    @Override
    public void deleteContactByNameFromPhone(Long phoneId, String contactName) {
        contactDao.deleteContactByNameFromPhone(phoneId, contactName);
    }
}
