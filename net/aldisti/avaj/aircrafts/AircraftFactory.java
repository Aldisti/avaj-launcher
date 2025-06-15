
package net.aldisti.avaj.aircrafts;

import net.aldisti.avaj.Coordinates;
import net.aldisti.avaj.exceptions.InvalidAircraftException;

public class AircraftFactory {

    private static AircraftFactory factory = null;
    private static long idCounter = 0;

    private AircraftFactory() { }

    public static AircraftFactory create() {
        if (factory == null)
            factory = new AircraftFactory();
        return factory;
    }

    public Flyable newAircraft(String type, String name, Coordinates coordinates) throws InvalidAircraftException {
        idCounter++;
        switch (type.toLowerCase()) {
            case "jetplane":
                return new JetPlane(idCounter, name, coordinates);
            case "helicopter":
                return new Helicopter(idCounter, name, coordinates);
            case "baloon":
                return new Baloon(idCounter, name, coordinates);
            default:
                idCounter--;
                throw new InvalidAircraftException(type);
        }
    }
}
