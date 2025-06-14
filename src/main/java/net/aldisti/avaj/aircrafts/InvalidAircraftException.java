package net.aldisti.avaj.aircrafts;

public class InvalidAircraftException extends Exception {
    private final String type;

    InvalidAircraftException(String type) {
        super();
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
