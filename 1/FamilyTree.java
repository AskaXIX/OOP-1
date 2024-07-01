import java.io.Serializable;
import java.security.Permission;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FamilyTree implements Serializable {
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

    public Map<String, Permission> getPersons() {
        return persons;
    }

    public void setPersons(Map<String, Permission> persons) {
        this.persons = persons;
    }
}
