import dao.impls.ContactDaoImpl;
import dao.impls.PhoneDaoImpl;
import db.Database;
import models.Contact;
import models.Phone;
import service.impls.ContactServiceImpl;
import service.impls.PhoneServiceImpl;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Database database = new Database();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        ContactDaoImpl contactDao = new ContactDaoImpl(database);
        PhoneDaoImpl phoneDao = new PhoneDaoImpl(database);

        PhoneServiceImpl phoneService = new PhoneServiceImpl(phoneDao);
        ContactServiceImpl contactService = new ContactServiceImpl(contactDao);
        while (!exit) {
            System.out.println("""
                    1.  Добавить телефон
                    2.  Получить телефон по id
                    3.  Обновить название телефона по id
                    4.  Получить все телефоны
                    5.  Получить все телефоны по бренду
                    6.  Удалить телефон по id
                    7.  Добавить контакт к телефону
                    8.  Найти контакт по названию в телефоне
                    9.  Найти контакт по номеру телефона в телефоне
                    10. Сортировать контакты по названию в телефоне
                    11. Удалить контакт по названию в телефоне
                    12. Выйти""");
            System.out.print("Введите действие: ");
            String num = scanner.nextLine();
            switch (num) {
                case "1" -> {
                    Phone newPhone = Phone.createPhone();
                    if (newPhone != null) {
                        System.out.println(phoneService.addPhone(newPhone));
                    }
                }
                case "2" -> {
                    if (phoneService.getAllPhones().isEmpty()) {
                        System.out.println("Телефонов нету!");
                    } else {
                        try {
                            System.out.print("Введите id: ");
                            Phone foundPhone = phoneService.getPhoneById(scanner.nextLong());
                            if (foundPhone != null) {
                                System.out.println(foundPhone);
                            } else {
                                System.out.println("Неправильный id");
                            }
                            scanner.nextLine();
                        } catch (InputMismatchException e) {
                            System.out.println("Введите число");
                            scanner.nextLine();
                        }
                    }
                }
                case "3" -> {
                    if (phoneService.getAllPhones().isEmpty()) {
                        System.out.println("Телефонов нету!");
                    } else {
                        try {
                            System.out.println("Все телефоны:");
                            phoneService.getAllPhones().forEach(System.out::println);
                            System.out.print("Введите id: ");
                            Long id = scanner.nextLong();
                            scanner.nextLine();
                            System.out.print("Введите новое название: ");
                            Phone foundPhone = phoneService.updatePhoneNameById(id, scanner.nextLine());
                            if (foundPhone != null) {
                                System.out.println(foundPhone);
                                System.out.println("Телефон успешно изменен!");
                            } else {
                                System.out.println("Неправильный id");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Введите число");
                            scanner.nextLine();
                        }
                    }
                }
                case "4" -> {
                    if (phoneService.getAllPhones().isEmpty()) {
                        System.out.println("Телефонов нету!");
                    } else {
                        System.out.println("Все телефоны:");
                        phoneService.getAllPhones().forEach(System.out::println);
                    }
                }
                case "5" -> {
                    if (phoneService.getAllPhones().isEmpty()) {
                        System.out.println("Телефонов нету!");
                    } else {
                        System.out.print("Введите бренд: ");
                        List<Phone> allPhonesByBrand = phoneService.getAllPhonesByBrand(scanner.nextLine());
                        if (allPhonesByBrand.isEmpty()) {
                            System.out.println("Телефонов не обнаружено!");
                        } else {
                            System.out.println("Все телефоны: ");
                            allPhonesByBrand.forEach(System.out::println);
                        }
                    }
                }
                case "6" -> {
                    if (phoneService.getAllPhones().isEmpty()) {
                        System.out.println("Телефонов нету!");
                    } else {
                        try {
                            System.out.println("Все телефоны:");
                            phoneService.getAllPhones().forEach(System.out::println);
                            System.out.print("Введите id: ");
                            Phone foundPhone = phoneService.getPhoneById(scanner.nextLong());
                            if (foundPhone != null) {
                                phoneService.deletePhoneById(foundPhone.getId());
                                System.out.println("Телефон успешно удален!");
                            } else {
                                System.out.println("Неправильный id");
                            }
                            scanner.nextLine();
                        } catch (InputMismatchException e) {
                            System.out.println("Введите число");
                            scanner.nextLine();
                        }
                    }
                }
                case "7" -> {
                    if (phoneService.getAllPhones().isEmpty()) {
                        System.out.println("Телефонов нету!");
                    } else {
                        try {
                            System.out.println("Все телефоны:");
                            phoneService.getAllPhones().forEach(System.out::println);
                            System.out.print("Введите id: ");
                            Phone foundPhone = phoneService.getPhoneById(scanner.nextLong());
                            scanner.nextLine();
                            if (foundPhone != null) {
                                Contact newContact = Contact.createContact();
                                if (newContact != null) {
                                    if (foundPhone.getContacts().stream().filter(x -> x.getName().equals(newContact.getName())).findFirst().orElse(null) == null) {
                                        contactService.addContactToPhone(foundPhone.getId(), newContact);
                                    } else {
                                        System.out.println("Контакт с таким именем уже существует!");
                                    }
                                }
                            } else {
                                System.out.println("Неправильный id");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Введите число");
                            scanner.nextLine();
                        }
                    }
                }
                case "8" -> {
                    if (phoneService.getAllPhones().isEmpty()) {
                        System.out.println("Телефонов нету!");
                    } else {
                        try {
                            System.out.println("Все телефоны:");
                            phoneService.getAllPhones().forEach(System.out::println);
                            System.out.print("Введите id: ");
                            Long id = scanner.nextLong();
                            scanner.nextLine();
                            System.out.print("Введите название контакта: ");
                            Contact foundContact = contactService.findContactByName(id, scanner.nextLine());
                            if (foundContact != null) {
                                System.out.println(foundContact);
                            } else {
                                System.out.println("Контакт не обнаружен!");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Введите число");
                            scanner.nextLine();
                        }
                    }
                }
                case "9" -> {
                    if (phoneService.getAllPhones().isEmpty()) {
                        System.out.println("Телефонов нету!");
                    } else {
                        try {
                            System.out.println("Все телефоны:");
                            phoneService.getAllPhones().forEach(System.out::println);
                            System.out.print("Введите id: ");
                            Long id = scanner.nextLong();
                            scanner.nextLine();
                            System.out.print("Введите номер телефона: ");
                            Contact foundContact = contactService.findContactByPhoneNumber(id, scanner.nextLine());
                            if (foundContact != null) {
                                System.out.println(foundContact);
                            } else {
                                System.out.println("Контакт не обнаружен!");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Введите число");
                            scanner.nextLine();
                        }
                    }
                }
                case "10" -> {
                    if (phoneService.getAllPhones().isEmpty()) {
                        System.out.println("Телефонов нету!");
                    } else {
                        try {
                            System.out.println("Все телефоны:");
                            phoneService.getAllPhones().forEach(System.out::println);
                            System.out.print("Введите id: ");
                            List<Contact> contacts = contactService.sortContactsByName(scanner.nextLong());
                            if (!contacts.isEmpty()) {
                                System.out.println("Все контакты: ");
                                contacts.forEach(System.out::println);
                            } else {
                                System.out.println("Контактов не обнаружено!");
                            }
                            scanner.nextLine();
                        } catch (InputMismatchException e) {
                            System.out.println("Введите число");
                            scanner.nextLine();
                        }
                    }
                }
                case "11" -> {
                    if (phoneService.getAllPhones().isEmpty()) {
                        System.out.println("Телефонов нету!");
                    } else {
                        try {
                            System.out.println("Все телефоны:");
                            phoneService.getAllPhones().forEach(System.out::println);
                            System.out.print("Введите id: ");
                            Long id = scanner.nextLong();
                            scanner.nextLine();
                            System.out.print("Введите название контакта: ");
                            Contact foundContact = contactService.findContactByName(id, scanner.nextLine());
                            if (foundContact != null) {
                                contactService.deleteContactByNameFromPhone(id, foundContact.getName());
                                System.out.println("Контакт успешно удален!");
                            } else {
                                System.out.println("Контактов не обнаружено!");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Введите число");
                            scanner.nextLine();
                        }
                    }
                }
                case "12" -> {
                    exit = true;
                    System.out.println("Вы успешно вышли!");
                }

            }
        }

    }
}