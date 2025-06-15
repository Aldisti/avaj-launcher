
package net.aldisti.avaj.exceptions;

public class ObserverNotRegisteredException extends RuntimeException {
    private final long observerId;

    public ObserverNotRegisteredException(long observerId) {
        super();
        this.observerId = observerId;
    }

    public long getObserverId() {
        return observerId;
    }
}