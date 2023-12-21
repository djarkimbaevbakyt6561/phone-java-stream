package models;

import dao.impls.ContactDaoImpl;
import service.impls.ContactServiceImpl;
import service.impls.PhoneServiceImpl;

import java.util.Scanner;

public class Contact {
    private String name;
    private String phoneNumber;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Contact: " +
                " name = " + name +
                ", phoneNumber = " + phoneNumber;
    }

    public static Contact createContact() {
        Scanner scanner = new Scanner(System.in);
        Contact contact = new Contact();
        System.out.print("Введите фио контакта: ");
        contact.setName(scanner.nextLine());
        System.out.print("Введите номер телефона: ");
        while (true) {
            String num = scanner.nextLine();
            if (num.length() == 10 && num.matches("\\d+")) {
                contact.setPhoneNumber(num);
                break;
            } else {
                System.out.println("Введите правильный номер телефона!");
            }

        }
        if (contact.getName().isEmpty()) {
            System.out.println("Значение не должно быть пустым");
            return null;
        }

        return contact;
    }
}
