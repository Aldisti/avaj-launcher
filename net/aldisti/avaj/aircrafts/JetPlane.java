package net.aldisti.avaj.aircrafts;

import net.aldisti.avaj.Coordinates;
import net.aldisti.avaj.towers.InvalidWeatherException;

public class JetPlane extends Aircraft {
    public JetPlane(long id, String name, Coordinates coordinates) {
        super(id, name, coordinates);
    }

    public void updateConditions() {
        String weather = weatherTower.getWeather(coordinates);
        switch (weather) {
            case "SUN":
                updated = new Coordinates(0, 10, 2);
                break;
            case "RAIN":
                updated = new Coordinates(0, 5, 0);
                break;
            case "FOG":
                updated = new Coordinates(0, 1, 0);
                break;
            case "SNOW":
                updated = new Coordinates(0, 0, -7);
                break;
            default:
                // Unreachable statement
                throw new InvalidWeatherException(weather);
        }
        super.updateConditions();
    }
}
