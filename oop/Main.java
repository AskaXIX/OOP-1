package oop;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        FamilyTree familyTree = new FamilyTree();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            // Создание людей
            Person john = new Person("John", sdf.parse("1970-01-01"));
            Person mary = new Person("Mary", sdf.parse("1972-05-12"));
            Person mike = new Person("Mike", sdf.parse("1995-03-18"));
            Person anna = new Person("Anna", sdf.parse("1997-07-22"));

            // Установка родителей
            mike.setFather(john);
            mike.setMother(mary);
            anna.setFather(john);
            anna.setMother(mary);

            // Добавление детей к родителям
            john.addChild(mike);
            john.addChild(anna);
            mary.addChild(mike);
            mary.addChild(anna);

            // Добавление людей в генеалогическое древо
            familyTree.addPerson(john);
            familyTree.addPerson(mary);
            familyTree.addPerson(mike);
            familyTree.addPerson(anna);
        } catch (ParseException e) {
            System.err.println("Error parsing date: " + e.getMessage());
        }

        FamilyTreeFileHandler fileHandler = new FamilyTreeFileHandler();

        // Сохранение генеалогического древа в файл
        try {
            fileHandler.saveToFile(familyTree, "familyTree.dat");
        } catch (IOException e) {
            System.err.println("Error saving family tree: " + e.getMessage());
        }

        // Загрузка генеалогического древа из файла
        try {
            FamilyTree loadedFamilyTree = fileHandler.loadFromFile("familyTree.dat");

            // Сортировка и вывод по имени
            System.out.println("Persons sorted by name:");
            List<Person> sortedByName = loadedFamilyTree.getSortedPersonsByName();
            for (Person person : sortedByName) {
                System.out.println(person.getName());
            }

            // Сортировка и вывод по дате рождения
            System.out.println("\nPersons sorted by birth date:");
            List<Person> sortedByBirthDate = loadedFamilyTree.getSortedPersonsByBirthDate();
            SimpleDateFormat outputSdf = new SimpleDateFormat("yyyy-MM-dd");
            for (Person person : sortedByBirthDate) {
                System.out.println(person.getName() + " - " + outputSdf.format(person.getBirthDate()));
            }

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading family tree: " + e.getMessage());
        }
    }
}
