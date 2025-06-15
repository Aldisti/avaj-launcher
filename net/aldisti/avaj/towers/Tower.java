package net.aldisti.avaj.towers;

import net.aldisti.avaj.aircrafts.Flyable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Tower {
    private String name;
    private List<Flyable> observers;
    private Set<Long> observersIds;

    public Tower(String name) {
        this.name = name;
        this.observers = new ArrayList<>();
        this.observersIds = new HashSet<>();
    }

    public void register(Flyable flyable) {
        if (observersIds.contains(flyable.getId())) {
            // TODO: throw exception
            System.out.println("Flyable already present! Throw exception!!!");
            return;
        }
        observers.add(flyable);
        observersIds.add(flyable.getId());
        System.out.println("Tower says: " + flyable.toString() + " registered to " + name + ".");
    }

    public void unregister(Flyable flyable) {
        if (!observersIds.contains(flyable.getId())) {
            // TODO: throw exception
            System.out.println("Flyable not found! Throw exception!!!");
            return;
        }
        observers.remove(flyable);
        observersIds.remove(flyable.getId());
        System.out.println("Tower says: " + flyable.toString() + " unregistered from " + name + ".");
    }

    protected void conditionChanged() {
        observers.forEach(Flyable::updateConditions);
    }

    public boolean hasAnyObserver() {
        return !observers.isEmpty();
    }
}
