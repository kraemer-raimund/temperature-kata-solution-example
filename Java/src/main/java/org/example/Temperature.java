package org.example;

public class Temperature {

    private final float celsius;

    private final float absoluteZeroInCelsius = -273.15f;
    private final float absoluteZeroInFahrenheit = -459.67f;

    private final float zeroCelsiusInFahrenheit = 32.0f;

    private Temperature(float celsius) {
        this.celsius = celsius;
    }

    public static Temperature fromCelsius(float celsius) {
        return new Temperature(celsius);
    }

    public float asCelsius() {
        return celsius;
    }

    public float asFahrenheit() {
        final var differenceCelsius = 0 - absoluteZeroInCelsius;
        final var differenceFahrenheit = zeroCelsiusInFahrenheit - absoluteZeroInFahrenheit;
        final var scaleStretchFactor = differenceFahrenheit / differenceCelsius;
        final var scaleOffset = zeroCelsiusInFahrenheit;

        return remap(celsius, scaleStretchFactor, scaleOffset);
    }

    private static float remap(float value, float stretch, float offset) {
        return value * stretch + offset;
    }
}
