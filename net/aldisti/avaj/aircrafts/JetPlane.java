package net.aldisti.avaj.aircrafts;

import net.aldisti.avaj.Coordinates;
import net.aldisti.avaj.towers.InvalidWeatherException;

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
                System.out.println("Woohoo! I'm going to shine!");
                break;
            case "RAIN":
                coordinates.addLatitude(5);
                System.out.println("We didn't need this.");
                break;
            case "FOG":
                coordinates.addLatitude(1);
                System.out.println("I'm not a Tesla, I can see through this!");
                break;
            case "SNOW":
                coordinates.addHeight(-7);
                System.out.println("Nothing can stop me!");
                break;
            default:
                // Unreachable statement
                throw new InvalidWeatherException(weather);
        }
        super.updateConditions();
    }
}
