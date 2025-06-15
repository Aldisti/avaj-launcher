package net.aldisti.avaj.aircrafts;

import net.aldisti.avaj.Coordinates;
import net.aldisti.avaj.towers.InvalidWeatherException;

public class Baloon extends Aircraft {

    public Baloon(long id, String name, Coordinates coordinates) {
        super(id, name, coordinates);
        touchDownMessage = "I'm becoming one with the Earth!";
    }

    public void updateConditions() {
        String weather = weatherTower.getWeather(coordinates);
        switch (weather) {
            case "SUN":
                coordinates.addLongitude(2);
                coordinates.addHeight(4);
                System.out.println("Let's beat that weakling of Carl Fredricksen.");
                break;
            case "RAIN":
                coordinates.addHeight(-5);
                System.out.println("Whoa, whoa, whoa!");
                break;
            case "FOG":
                coordinates.addHeight(-3);
                System.out.println("I just need to stay afloat!");
                break;
            case "SNOW":
                coordinates.addHeight(-15);
                System.out.println("Perhaps he wasn't so weak...");
                break;
            default:
                // Unreachable statement
                throw new InvalidWeatherException(weather);
        }
        super.updateConditions();
    }
}
