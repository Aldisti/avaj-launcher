package net.aldisti.avaj.aircrafts;

import net.aldisti.avaj.Coordinates;
import net.aldisti.avaj.towers.InvalidWeatherException;

public class Helicopter extends Aircraft {
    public Helicopter(long id, String name, Coordinates coordinates) {
        super(id, name, coordinates);
    }

    public void updateConditions() {
        String weather = weatherTower.getWeather(coordinates);
        switch (weather) {
            case "SUN":
                updated = new Coordinates(10, 0, 2);
                break;
            case "RAIN":
                updated = new Coordinates(5, 0, 0);
                break;
            case "FOG":
                updated = new Coordinates(1, 0, 0);
                break;
            case "SNOW":
                updated = new Coordinates(0, 0, -12);
                break;
            default:
                // Unreachable statement
                throw new InvalidWeatherException(weather);
        }
        super.updateConditions();
    }
}
