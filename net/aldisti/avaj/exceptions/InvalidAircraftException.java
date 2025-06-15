
package net.aldisti.avaj.exceptions;

public class InvalidAircraftException extends Exception {
    private final String type;

    public InvalidAircraftException(String type) {
        super();
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
