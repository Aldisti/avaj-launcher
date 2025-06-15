package net.aldisti.avaj.aircrafts;

import net.aldisti.avaj.Coordinates;
import net.aldisti.avaj.towers.InvalidWeatherException;

public class Helicopter extends Aircraft {
    public Helicopter(long id, String name, Coordinates coordinates) {
        super(id, name, coordinates);
        touchDownMessage = "Icarus, I'll join you <3";
    }

    public void updateConditions() {
        String weather = weatherTower.getWeather(coordinates);
        switch (weather) {
            case "SUN":
                coordinates.addLongitude(10);
                coordinates.addHeight(2);
                System.out.println("Icarus, I'll surpass you!");
                break;
            case "RAIN":
                coordinates.addLongitude(5);
                System.out.println("You won't stop me!");
                break;
            case "FOG":
                coordinates.addLongitude(1);
                System.out.println("I don't need to see... maybe.");
                break;
            case "SNOW":
                coordinates.addHeight(-12);
                System.out.println("Give me time, and I'll beat you!");
                break;
            default:
                // Unreachable statement
                throw new InvalidWeatherException(weather);
        }
        super.updateConditions();
    }
}
