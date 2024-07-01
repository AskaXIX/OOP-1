import java.security.Permission;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FamilyTree {
    private Map<String, Permission> persons;

    public FamilyTree() {
        this.persons = new HashMap<>();
    }

    public void addPerson(Permission person) {
        persons.put(person.getName(), person);
    }

    public Permission getPerson(String name) {
        return persons.get(name);
    }

    public List<Permission> getChildrenOf(String name) {
        Permission person = getPerson(name);
        if (person != null) {
            return person.getChildren();
        }
        return new ArrayList<>();
    }
}
