package com.example.familytree.model;

import java.io.Serializable;
import java.util.*;

public class FamilyTree implements Serializable, Iterable<Person> {
    private Map<String, Person> persons;

    public FamilyTree() {
        this.persons = new HashMap<>();
    }

    public void addPerson(Person person) {
        persons.put(person.getName(), person);
    }

    public Person getPerson(String name) {
        return persons.get(name);
    }

    public List<Person> getChildrenOf(String name) {
        Person person = getPerson(name);
        if (person != null) {
            return person.getChildren();
        }
        return new ArrayList<>();
    }

    public Map<String, Person> getPersons() {
        return persons;
    }

    public void setPersons(Map<String, Person> persons) {
        this.persons = persons;
    }

    @Override
    public Iterator<Person> iterator() {
        return persons.values().iterator();
    }

    public List<Person> getSortedPersonsByName() {
        List<Person> sortedList = new ArrayList<>(persons.values());
        sortedList.sort(Comparator.comparing(Person::getName));
        return sortedList;
    }

    public List<Person> getSortedPersonsByBirthDate() {
        List<Person> sortedList = new ArrayList<>(persons.values());
        sortedList.sort(Comparator.comparing(Person::getBirthDate));
        return sortedList;
    }
}
