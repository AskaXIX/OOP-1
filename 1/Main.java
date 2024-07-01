import java.util.List;

public class Main {
    public static void main(String[] args) {
        FamilyTree familyTree = new FamilyTree();

        // Создание людей
        Person john = new Person("John", 50);
        Person mary = new Person("Mary", 48);
        Person mike = new Person("Mike", 25);
        Person anna = new Person("Anna", 23);

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

        // Проведение исследования: получение всех детей Джона
        List<Person> childrenOfJohn = familyTree.getChildrenOf("John");
        System.out.println("Children of John:");
        for (Person child : childrenOfJohn) {
            System.out.println(child.getName());
        }
    }
}
