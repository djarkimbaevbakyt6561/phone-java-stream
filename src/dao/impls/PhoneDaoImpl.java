package dao.impls;

import dao.PhoneDao;
import db.Database;
import models.Phone;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PhoneDaoImpl implements PhoneDao {
    private final Database database;
    private static Long idPhone = 1L;

    public PhoneDaoImpl(Database database) {
        this.database = database;
    }

    @Override
    public String addPhone(Phone phone) {
        phone.setId(idPhone++);
        database.getPhones().add(phone);
        return "Телефон успешно добавлен!";
    }

    @Override
    public Phone getPhoneById(Long phoneId) {
        return database.getPhones().stream()
                .filter(phone -> Objects.equals(phone.getId(), phoneId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Phone updatePhoneNameById(Long phoneId, String newName) {
        database.getPhones().stream()
                .filter(phone -> Objects.equals(phone.getId(), phoneId))
                .findFirst()
                .ifPresent(phone -> phone.setName(newName));
        return getPhoneById(phoneId);
    }

    @Override
    public List<Phone> getAllPhones() {
        return database.getPhones();
    }

    @Override
    public List<Phone> getAllPhonesByBrand(String brand) {
        return database.getPhones().stream()
                .filter(phone -> phone.getBrand().equals(brand))
                .collect(Collectors.toList());
    }

    @Override
    public void deletePhoneById(Long phoneId) {
        List<Phone> phones = database.getPhones();
        phones = phones.stream()
                .filter(phone -> !Objects.equals(phone.getId(), phoneId))
                .collect(Collectors.toList());
        database.setPhones(phones);
    }
}
