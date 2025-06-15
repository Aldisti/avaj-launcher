
package net.aldisti.avaj.aircrafts;

import net.aldisti.avaj.Coordinates;

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
            case "helicopter":
                return new Helicopter(idCounter, name, coordinates);
            default:
                idCounter--;
                throw new InvalidAircraftException(type);
        }
    }
}
