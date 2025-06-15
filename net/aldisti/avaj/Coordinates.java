
package net.aldisti.avaj;

public class Coordinates {
    private int longitude;
    private int latitude;
    private int height;

    public Coordinates() {
        longitude = 0;
        latitude = 0;
        height = 0;
    }

    public Coordinates(int longitude, int latitude, int height) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.height = height;
    }

    public int getLongitude() {
        return longitude;
    }

    public int getLatitude() {
        return latitude;
    }

    public int getHeight() {
        return height;
    }

    public void addLongitude(int longitude) {
        this.longitude += longitude;
    }

    public void addLatitude(int latitude) {
        this.latitude += latitude;
    }

    public void addHeight(int height) {
        this.height = Integer.min(100, this.height + height);
    }
}
