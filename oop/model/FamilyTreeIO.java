package oop.model;
import java.io.*;

public interface FamilyTreeIO<T extends Serializable> {
    void saveToFile(FamilyTree<T> familyTree, String filePath) throws IOException;
    FamilyTree<T> loadFromFile(String filePath) throws IOException, ClassNotFoundException;
}
