package dao;

import models.Contact;

import java.util.List;

public interface ContactDao {
    String addContactToPhone(Long phoneId, Contact contact);
    Contact findContactByName(Long phoneId, String contactName);
    Contact findContactByPhoneNumber(Long phoneId, String phoneNumber);
    List<Contact> sortContactsByName(Long phoneId);
    void deleteContactByNameFromPhone(Long phoneId, String contactName);
}
