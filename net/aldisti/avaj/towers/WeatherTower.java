
package net.aldisti.avaj.towers;

import net.aldisti.avaj.Coordinates;

public class WeatherTower extends Tower {
    private static final WeatherProvider provider = WeatherProvider.create();

    public WeatherTower() {
        super("weather tower");
    }

	public String getWeather(Coordinates coordinates) {
        return provider.getCurrentWeather(coordinates);
    }

    public void changeWeather() {
        conditionChanged();
    }
}
