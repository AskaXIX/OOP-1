package oop.model;

import java.io.*;
import java.util.Map;

public class FamilyTreeFileHandler<T extends Serializable> implements FamilyTreeIO<T> {
    @Override
    public void saveToFile(FamilyTree<T> familyTree, String filePath) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(familyTree.getEntities());
        }
    }

    @Override
    public FamilyTree<T> loadFromFile(String filePath) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            Map<String, T> entities = (Map<String, T>) ois.readObject();
            FamilyTree<T> familyTree = new FamilyTree<>();
            familyTree.setEntities(entities);
            return familyTree;
        }
    }
}
