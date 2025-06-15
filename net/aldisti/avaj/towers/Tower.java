package net.aldisti.avaj.towers;

import net.aldisti.avaj.aircrafts.Flyable;
import net.aldisti.avaj.exceptions.ObserverAlreadyRegisteredException;
import net.aldisti.avaj.exceptions.ObserverNotRegisteredException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Tower {
    private final String name;
    private final List<Flyable> observers;
    private final Set<Long> observersIds;

    public Tower(String name) {
        this.name = name;
        this.observers = new ArrayList<>();
        this.observersIds = new HashSet<>();
    }

    public void register(Flyable flyable) {
        if (observersIds.contains(flyable.getId()))
            throw new ObserverAlreadyRegisteredException(flyable.getId());

        observers.add(flyable);
        observersIds.add(flyable.getId());
        System.out.println("Tower says: " + flyable.toString() + " registered to " + name + ".");
    }

    public void unregister(Flyable flyable) {
        if (!observersIds.contains(flyable.getId()))
            throw new ObserverNotRegisteredException(flyable.getId());

        observers.remove(flyable);
        observersIds.remove(flyable.getId());
        System.out.println("Tower says: " + flyable.toString() + " unregistered from " + name + ".");
    }

    protected void conditionChanged() {
        List.copyOf(observers).forEach(Flyable::updateConditions);
    }

    public boolean hasAnyObserver() {
        return !observers.isEmpty();
    }
}
