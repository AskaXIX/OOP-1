package oop.presenter;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import oop.model.FamilyTree;
import oop.model.FamilyTreeFileHandler;
import oop.model.Person;
import oop.view.FamilyTreeView;

public class FamilyTreePresenterImpl implements FamilyTreePresenter {
    private final FamilyTreeView view;
    private final FamilyTree<Person> familyTree;
    private final FamilyTreeFileHandler<Person> fileHandler;
    private final SimpleDateFormat sdf;

    public FamilyTreePresenterImpl(FamilyTreeView view) {
        this.view = view;
        this.familyTree = new FamilyTree<>();
        this.fileHandler = new FamilyTreeFileHandler<>();
        this.sdf = new SimpleDateFormat("yyyy-MM-dd");
    }

    @Override
    public void addPerson(String name, String birthDate) {
        try {
            Date date = sdf.parse(birthDate);
            Person person = new Person(name, date);
            familyTree.addEntity(name, person);
            view.showMessage("Person added: " + name);
        } catch (ParseException e) {
            view.showError("Invalid date format. Use yyyy-MM-dd.");
        }
    }

    @Override
    public void listPersons() {
        StringBuilder sb = new StringBuilder("List of persons:\n");
        for (Person person : familyTree) {
            sb.append(person.getName()).append("\n");
        }
        view.showPersons(sb.toString());
    }

    @Override
    public void sortPersonsByName() {
        List<Person> sortedList = familyTree.getSortedEntitiesByComparator(Comparator.comparing(Person::getName));
        StringBuilder sb = new StringBuilder("Persons sorted by name:\n");
        for (Person person : sortedList) {
            sb.append(person.getName()).append("\n");
        }
        view.showPersons(sb.toString());
    }

    @Override
    public void sortPersonsByBirthDate() {
        List<Person> sortedList = familyTree.getSortedEntitiesByComparator(Comparator.comparing(Person::getBirthDate));
        SimpleDateFormat outputSdf = new SimpleDateFormat("yyyy-MM-dd");
        StringBuilder sb = new StringBuilder("Persons sorted by birth date:\n");
        for (Person person : sortedList) {
            sb.append(person.getName()).append(" - ").append(outputSdf.format(person.getBirthDate())).append("\n");
        }
        view.showPersons(sb.toString());
    }

    @Override
    public void saveFamilyTree(String filePath) {
        try {
            fileHandler.saveToFile(familyTree, filePath);
            view.showMessage("Family tree saved to " + filePath);
        } catch (IOException e) {
            view.showError("Error saving family tree: " + e.getMessage());
        }
    }

    @Override
    public void loadFamilyTree(String filePath) {
        try {
            FamilyTree<Person> loadedFamilyTree = fileHandler.loadFromFile(filePath);
            familyTree.setEntities(loadedFamilyTree.getEntities());
            view.showMessage("Family tree loaded from " + filePath);
        } catch (IOException | ClassNotFoundException e) {
            view.showError("Error loading family tree: " + e.getMessage());
        }
    }
}
