package db;

import models.Phone;

import java.util.ArrayList;
import java.util.List;

public class Database {
    private List<Phone> phones = new ArrayList<>();

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }
}

