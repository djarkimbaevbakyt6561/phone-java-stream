package dao.impls;

import dao.ContactDao;
import db.Database;
import models.Contact;
import models.Phone;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ContactDaoImpl implements ContactDao {
    private final Database database;

    public ContactDaoImpl(Database database) {
        this.database = database;
    }

    @Override
    public String addContactToPhone(Long phoneId, Contact contact) {
        Phone phone = database.getPhones().stream().filter(x -> Objects.equals(x.getId(), phoneId)).findFirst().orElse(null);
        if (phone != null) {
            phone.getContacts().add(contact);
            return "Контакт успешно добавлен";
        } else {
            return "Неправильный id!";
        }
    }

    @Override
    public Contact findContactByName(Long phoneId, String contactName) {
        Phone phone = database.getPhones().stream().filter(x -> Objects.equals(x.getId(), phoneId)).findFirst().orElse(null);
        if (phone != null) {
            return phone.getContacts().stream().filter(contact -> Objects.equals(contact.getName(), contactName)).findFirst().orElse(null);
        }
        return null;
    }

    @Override
    public Contact findContactByPhoneNumber(Long phoneId, String phoneNumber) {
        Phone phone = database.getPhones().stream().filter(x -> Objects.equals(x.getId(), phoneId)).findFirst().orElse(null);
        if (phone != null) {
            return phone.getContacts().stream().filter(contact -> Objects.equals(contact.getPhoneNumber(), phoneNumber)).findFirst().orElse(null);
        }
        return null;
    }

    @Override
    public List<Contact> sortContactsByName(Long phoneId) {
        Phone phone = database.getPhones().stream().filter(x -> Objects.equals(x.getId(), phoneId)).findFirst().orElse(null);
        if (phone != null) {
            return phone.getContacts().stream().sorted(Comparator.comparing(Contact::getName)).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @Override
        public void deleteContactByNameFromPhone(Long phoneId, String contactName) {
        database.getPhones().stream().filter(x -> Objects.equals(x.getId(), phoneId)).findFirst().ifPresent(phone -> phone.getContacts().stream().filter(contact -> Objects.equals(contact.getName(), contactName)).findFirst().ifPresent(contact1 -> phone.getContacts().remove(contact1)));
    }
}
