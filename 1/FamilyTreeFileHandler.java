package com.example.familytree.io;

import com.example.familytree.model.FamilyTree;
import com.example.familytree.model.Person;

import java.io.*;
import java.util.Map;

public class FamilyTreeFileHandler implements FamilyTreeIO {
    @Override
    public void saveToFile(FamilyTree familyTree, String filePath) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(familyTree.getPersons());
        }
    }

    @Override
    public FamilyTree loadFromFile(String filePath) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            Map<String, Person> persons = (Map<String, Person>) ois.readObject();
            FamilyTree familyTree = new FamilyTree();
            familyTree.setPersons(persons);
            return familyTree;
        }
    }
}
