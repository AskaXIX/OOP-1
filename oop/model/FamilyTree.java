package oop.model;

import java.io.Serializable;
import java.util.*;

public class FamilyTree<T extends Serializable> implements Serializable, Iterable<T> {
    private Map<String, T> entities;

    public FamilyTree() {
        this.entities = new HashMap<>();
    }

    public void addEntity(String name, T entity) {
        entities.put(name, entity);
    }

    public T getEntity(String name) {
        return entities.get(name);
    }

    public Map<String, T> getEntities() {
        return entities;
    }

    public void setEntities(Map<String, T> entities) {
        this.entities = entities;
    }

    @Override
    public Iterator<T> iterator() {
        return entities.values().iterator();
    }

    public List<T> getSortedEntitiesByName(Comparator<T> comparator) {
        List<T> sortedList = new ArrayList<>(entities.values());
        sortedList.sort(comparator);
        return sortedList;
    }

    public List<T> getSortedEntitiesByComparator(Comparator<T> comparator) {
        List<T> sortedList = new ArrayList<>(entities.values());
        sortedList.sort(comparator);
        return sortedList;
    }
}
