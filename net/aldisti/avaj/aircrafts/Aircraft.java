
package net.aldisti.avaj.aircrafts;

import net.aldisti.avaj.Coordinates;

public class Aircraft extends Flyable {
    protected final long id;
    protected final String name;
    protected final Coordinates coordinates;

    protected String touchDownMessage;

    protected Aircraft(long id, String name, Coordinates coordinates) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
    }

    public void updateConditions() {
        if (coordinates.getHeight() <= 0) {
            say(touchDownMessage);
            weatherTower.unregister(this);
            return;
        }
    }

    protected void say(String message) {
        System.out.println(
            String.format("%s: %s", toString(), message)
        );
    }

    public long getId() {
        return id;
    }

    public String toString() {
        return String.format("%s#%s(%d)",
            this.getClass().getSimpleName(), name, id
        );
    }
}
