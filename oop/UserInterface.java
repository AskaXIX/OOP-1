package oop;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class UserInterface {
    private final Scanner scanner;
    private final FamilyTree<Person> familyTree;
    private final FamilyTreeFileHandler<Person> fileHandler;
    private final SimpleDateFormat sdf;

    public UserInterface() {
        this.scanner = new Scanner(System.in);
        this.familyTree = new FamilyTree<>();
        this.fileHandler = new FamilyTreeFileHandler<>();
        this.sdf = new SimpleDateFormat("yyyy-MM-dd");
    }

    public void start() {
        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();
            String[] tokens = input.split("\\s+");
            if (tokens.length == 0) continue;

            String command = tokens[0];
            switch (command) {
                case "add":
                    handleAddCommand(tokens);
                    break;
                case "list":
                    handleListCommand();
                    break;
                case "sort":
                    handleSortCommand(tokens);
                    break;
                case "save":
                    handleSaveCommand(tokens);
                    break;
                case "load":
                    handleLoadCommand(tokens);
                    break;
                case "exit":
                    return;
                default:
                    System.out.println("Unknown command");
            }
        }
    }

    private void handleAddCommand(String[] tokens) {
        if (tokens.length < 3) {
            System.out.println("Usage: add person <name> <birth_date>");
            return;
        }

        try {
            String name = tokens[1];
            Date birthDate = sdf.parse(tokens[2]);
            Person person = new Person(name, birthDate);
            familyTree.addEntity(name, person);
            System.out.println("Person added: " + name);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Use yyyy-MM-dd.");
        }
    }

    private void handleListCommand() {
        System.out.println("List of persons:");
        for (Person person : familyTree) {
            System.out.println(person.getName());
        }
    }

    private void handleSortCommand(String[] tokens) {
        if (tokens.length < 2) {
            System.out.println("Usage: sort <name|birthdate>");
            return;
        }

        String criterion = tokens[1];
        List<Person> sortedList;

        switch (criterion) {
            case "name":
                sortedList = familyTree.getSortedEntitiesByComparator(Comparator.comparing(Person::getName));
                System.out.println("Persons sorted by name:");
                for (Person person : sortedList) {
                    System.out.println(person.getName());
                }
                break;
            case "birthdate":
                sortedList = familyTree.getSortedEntitiesByComparator(Comparator.comparing(Person::getBirthDate));
                System.out.println("Persons sorted by birth date:");
                for (Person person : sortedList) {
                    SimpleDateFormat outputSdf = new SimpleDateFormat("yyyy-MM-dd");
                    System.out.println(person.getName() + " - " + outputSdf.format(person.getBirthDate()));
                }
                break;
            default:
                System.out.println("Unknown sort criterion. Use 'name' or 'birthdate'.");
        }
    }

    private void handleSaveCommand(String[] tokens) {
        if (tokens.length < 2) {
            System.out.println("Usage: save <file_path>");
            return;
        }

        String filePath = tokens[1];
        try {
            fileHandler.saveToFile(familyTree, filePath);
            System.out.println("Family tree saved to " + filePath);
        } catch (IOException e) {
            System.out.println("Error saving family tree: " + e.getMessage());
        }
    }

    private void handleLoadCommand(String[] tokens) {
        if (tokens.length < 2) {
            System.out.println("Usage: load <file_path>");
            return;
        }

        String filePath = tokens[1];
        try {
            FamilyTree<Person> loadedFamilyTree = fileHandler.loadFromFile(filePath);
            familyTree.setEntities(loadedFamilyTree.getEntities());
            System.out.println("Family tree loaded from " + filePath);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading family tree: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
        ui.start();
    }
}
