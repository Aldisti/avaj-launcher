package net.aldisti.avaj.aircrafts;

import net.aldisti.avaj.Coordinates;
import net.aldisti.avaj.towers.InvalidWeatherException;

public class Baloon extends Aircraft {

    public Baloon(long id, String name, Coordinates coordinates) {
        super(id, name, coordinates);
    }

    public void updateConditions() {
        String weather = weatherTower.getWeather(coordinates);
        switch (weather) {
            case "SUN":
                updated = new Coordinates(2, 0, 4);
                break;
            case "RAIN":
                updated = new Coordinates(0, 0, -5);
                break;
            case "FOG":
                updated = new Coordinates(0, 0, -3);
                break;
            case "SNOW":
                updated = new Coordinates(0, 0, -15);
                break;
            default:
                // Unreachable statement
                throw new InvalidWeatherException(weather);
        }
        super.updateConditions();
    }
}
