import java.io.IOException;

public interface FamilyTreeIO {
    void saveToFile(FamilyTree familyTree, String filePath) throws IOException;
    FamilyTree loadFromFile(String filePath) throws IOException, ClassNotFoundException;
}
