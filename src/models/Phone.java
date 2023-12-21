package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Phone {
    private Long id;
    private String name;
    private String brand;
    private final List<Contact> contacts = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    @Override
    public String toString() {
        return "Phone: " +
                "id = " + id +
                ", name = " + name +
                ", brand = " + brand;
    }

    public static Phone createPhone() {
        Scanner scanner = new Scanner(System.in);
        Phone phone = new Phone();
        System.out.print("Введите название телефона: ");
        phone.setName(scanner.nextLine());
        System.out.print("Введите бренд телефона: ");
        phone.setBrand(scanner.nextLine());
        if (phone.getBrand().isEmpty() || phone.getName().isEmpty()) {
            System.out.println("Значение не должно быть пустым!");
            return null;
        }
        return phone;
    }
}
