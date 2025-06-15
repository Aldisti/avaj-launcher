package net.aldisti.avaj.towers;

public class InvalidWeatherException extends RuntimeException {
    private final String weather;

    public InvalidWeatherException(String weather) {
        super();
        this.weather = weather;
    }

    public String getWeather() {
        return weather;
    }
}
