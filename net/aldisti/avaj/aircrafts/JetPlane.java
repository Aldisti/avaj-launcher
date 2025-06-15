package net.aldisti.avaj.aircrafts;

import net.aldisti.avaj.Coordinates;
import net.aldisti.avaj.exceptions.InvalidWeatherException;

public class JetPlane extends Aircraft {
    public JetPlane(long id, String name, Coordinates coordinates) {
        super(id, name, coordinates);
        this.touchDownMessage = "Following in AI171's footsteps...";
    }

    public void updateConditions() {
        String weather = weatherTower.getWeather(coordinates);
        switch (weather) {
            case "SUN":
                coordinates.addLatitude(10);
                coordinates.addHeight(2);
                say("Woohoo! I'm going to shine!");
                break;
            case "RAIN":
                coordinates.addLatitude(5);
                say("We didn't need this.");
                break;
            case "FOG":
                coordinates.addLatitude(1);
                say("I'm not a Tesla, I can see through this!");
                break;
            case "SNOW":
                coordinates.addHeight(-7);
                say("Nothing can stop me!");
                break;
            default:
                // Unreachable statement
                throw new InvalidWeatherException(weather);
        }
        super.updateConditions();
    }
}
