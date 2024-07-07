package oop.presenter;

public interface FamilyTreePresenter {
    void addPerson(String name, String birthDate);
    void listPersons();
    void sortPersonsByName();
    void sortPersonsByBirthDate();
    void saveFamilyTree(String filePath);
    void loadFamilyTree(String filePath);
}
