package org.example;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Instead of passing around strings or floats and having to track via
        // variable names which scales are used, we can now treat all temperatures
        // the same, and as what they are: A temperature is just a temperature,
        // not a number. We can add and multiply floats, but cannot add or multiply
        // temperatures.
        final var temperatures = List.of(
                Temperature.fromKelvin(42.0f),
                Temperature.fromCelsius(-123.456f),
                Temperature.fromFahrenheit(456.789f));

        // We can simply get the representation we want. All temperatures are
        // treated equally.
        temperatures.forEach(temperature -> {
            System.out.println(temperature.formatAsKelvin());
            System.out.println(temperature.formatAsCelsius());
            System.out.println(temperature.formatAsFahrenheit());
            System.out.println();
        });

        // We can parse temperatures at the application boundary, pass the
        // temperature object around, and not worry about the implementation
        // details. Then we can again get the representation we need at the
        // boundary.
        // Provided by some API:
        final var averageTemperatureLastYear = Temperature.fromFahrenheit(68.427f);
        // Somewhere in the persistence layer:
        averageTemperatureLastYear.asKelvin(); // E.g. persist temperatures in Kelvin or something
        // Far down the call stack:
        System.out.printf(
                "Last year's average temperature was %s\n",
                averageTemperatureLastYear.formatAsCelsius());
    }
}
