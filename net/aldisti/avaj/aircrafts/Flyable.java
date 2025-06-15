
package net.aldisti.avaj.aircrafts;

import net.aldisti.avaj.towers.WeatherTower;

public abstract class Flyable {
    protected WeatherTower weatherTower;

    public abstract void updateConditions();
    // I need this so I can do better checks inside the Tower
    public abstract long getId();

    public void registerTower(WeatherTower tower) {
        weatherTower = tower;
        weatherTower.register(this);
    }
}
