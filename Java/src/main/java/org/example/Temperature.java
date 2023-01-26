package org.example;

public class Temperature {

    private static final float absoluteZeroInCelsius = -273.15f;
    private static final float absoluteZeroInFahrenheit = -459.67f;
    private static final float zeroCelsiusInFahrenheit = 32.0f;

    private final float celsius;

    private Temperature(float celsius) {
        this.celsius = celsius;
    }

    public static Temperature fromCelsius(float celsius) {
        return new Temperature(celsius);
    }

    public static Temperature fromFahrenheit(float fahrenheit) {
        return new Temperature(fahrenheitToCelsius(fahrenheit));
    }

    public float asCelsius() {
        return celsius;
    }

    public float asFahrenheit() {
        return celsiusToFahrenheit(this.celsius);
    }

    private static float fahrenheitToCelsius(float fahrenheit) {
        return remap(fahrenheit,
                absoluteZeroInFahrenheit, zeroCelsiusInFahrenheit,
                absoluteZeroInCelsius, 0);
    }

    private static float celsiusToFahrenheit(float celsius) {
        return remap(celsius,
                absoluteZeroInCelsius, 0,
                absoluteZeroInFahrenheit, zeroCelsiusInFahrenheit);
    }

    private static float remap(float value, float source1, float source2, float target1, float target2) {
        // The original value normalized within its original range, resulting in
        // a value between 0.0 (as it approaches "source1") and 1.0 (as it approaches "source2").
        final var valueNormalized = (value - source1) / (source2 - source1);
        return target1 + valueNormalized * (target2 - target1);
    }
}
