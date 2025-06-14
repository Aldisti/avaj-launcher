
package net.aldisti.avaj.aircrafts;

import net.aldisti.avaj.Coordinates;

public class Aircraft extends Flyable {
    protected long id;
    protected String name;
    protected Coordinates coordinates;

    protected Coordinates updated;

    protected Aircraft(long id, String name, Coordinates coordinates) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
    }

    public void updateConditions() {
        coordinates.add(updated);
        updated = new Coordinates();
        if (coordinates.getHeight() <= 0) {
            weatherTower.unregister(this);
            return;
        }
    }

    public long getId() {
        return id;
    }

    public String toString() {
        return this.getClass().getName() + "#" + name;
    }
}
