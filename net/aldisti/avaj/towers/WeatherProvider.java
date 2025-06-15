package net.aldisti.avaj.towers;

import net.aldisti.avaj.Coordinates;

public class WeatherProvider {

    private static WeatherProvider provider = null;

    private String[] weather = {"RAIN", "FOG", "SUN", "SNOW"};

    private WeatherProvider() {
        // TODO: remove this log
        System.out.println(">>> Instantiated weather provider!");
    }

    public static WeatherProvider create() {
        if (provider == null)
            provider = new WeatherProvider();
        return provider;
    }

    public String getCurrentWeather(Coordinates coordinates) {
        int a = coordinates.getLongitude();
        int b = coordinates.getLatitude();

        int index = (a * b ^ (a + b)) % weather.length;
        return weather[index];
    }
}
