
package net.aldisti.avaj.exceptions;

public class ObserverAlreadyRegisteredException extends RuntimeException {
    private final long observerId;

    public ObserverAlreadyRegisteredException(long observerId) {
        super();
        this.observerId = observerId;
    }

    public long getObserverId() {
        return observerId;
    }
}
