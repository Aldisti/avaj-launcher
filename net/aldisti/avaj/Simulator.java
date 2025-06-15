package net.aldisti.avaj;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import net.aldisti.avaj.exceptions.InvalidAircraftException;
import net.aldisti.avaj.exceptions.ObserverAlreadyRegisteredException;
import net.aldisti.avaj.exceptions.ObserverNotRegisteredException;
import net.aldisti.avaj.aircrafts.AircraftFactory;
import net.aldisti.avaj.aircrafts.Flyable;
import net.aldisti.avaj.towers.WeatherTower;

public class Simulator {

    private static final AircraftFactory factory = AircraftFactory.create();
    private static final WeatherTower tower = new WeatherTower();
    private static int cycles = -1;

    public static void main(String[] av) {
        if (av.length != 1) {
            System.out.println("Error: ehi, give me a scenario to simulate!");
            System.exit(1);
        }

        initializeScenario(av[0]);

        if (!tower.hasAnyObserver()) {
            System.out.println("Error: cannot simulate without at least an aircraft!");
            System.exit(8);
        }

        try {
            mainLoop();
        } catch (ObserverNotRegisteredException onre) {
            System.out.println("Error: couldn't unregister observer with id " + onre.getObserverId());
            System.exit(9);
        }

        System.exit(0);
    }

    /**
     * Parses the scenario input file and initializes the cycles counter
     * and all the aircrafts.
     *
     * @param scenario The path to the scenario input file.
     */
    private static void initializeScenario(String scenario) {
        try (Scanner reader = new Scanner(new File(scenario))) {
            String line = reader.nextLine().trim();
            if (!line.matches("^\\d*?$")) {
                System.out.println("Error: first line of scenario must be an integer!");
                System.exit(2);
            }
            cycles = Integer.parseInt(line);
            while (reader.hasNextLine()) {
                line = reader.nextLine().trim();
                if (!line.matches("^([a-zA-Z]+)\\s+([a-zA-Z]\\d+)\\s+(\\d+\\s*){3}$")) {
                    System.out.println("Error: found invalid line format!");
                    System.exit(3);
                }
                parseFlyable(line).registerTower(tower);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: file " + scenario + " not found!");
            System.exit(4);
        } catch (NoSuchElementException | IllegalStateException ex) {
            System.out.println("Error: fatal error while reading scenario, message: " + ex.getMessage());
            System.exit(5);
        } catch (InvalidAircraftException iae) {
            System.out.println("Error: cannot create aircraft of type '" + iae.getType() + "'");
            System.exit(6);
        } catch (ObserverAlreadyRegisteredException oare) {
            System.out.println("Error: observer with id " + oare.getObserverId() + " already registered!");
            System.exit(7);
        }
    }

    /**
     * Creates a flyable implementation given a string from the scenario file.
     *
     * @param line A representation of a Flyable, pattern is <Type> <id> <longitude> <latitude> <height>
     * @return A {@link Flyable} implementation object;
     * @throws InvalidAircraftException When the type is unknown.
     */
    private static Flyable parseFlyable(String line) throws InvalidAircraftException {
        String[] fields = line.split(" ");

        Coordinates coordinates = new Coordinates(
            Integer.parseInt(fields[2]),
            Integer.parseInt(fields[3]),
            Integer.parseInt(fields[4])
        );

        return factory.newAircraft(fields[0], fields[1], coordinates);
    }

    private static void mainLoop() {
        while (cycles > 0) {
            tower.changeWeather();
            cycles--;
        }
    }
}
