package org.example;

import java.util.Locale;

public class Temperature {

    private static final float absoluteZeroInKelvin = 0.0f;
    private static final float absoluteZeroInCelsius = -273.15f;
    private static final float absoluteZeroInFahrenheit = -459.67f;

    private static final float zeroCelsiusInCelsius = 0.0f;
    private static final float zeroCelsiusInKelvin = 273.15f;
    private static final float zeroCelsiusInFahrenheit = 32.0f;

    private static final float freezingPointOfWaterInCelsius = 0.0f;
    private static final float boilingPointOfWaterInCelsius = 99.9839f;

    private final float kelvin;

    private Temperature(float kelvin) {
        this.kelvin = kelvin;
    }

    public static Temperature fromKelvin(float kelvin) {
        if (kelvin < absoluteZeroInKelvin) {
            throw new IllegalArgumentException(
                    String.format("Provided temperature (%f Kelvin) is below absolute zero.", kelvin));
        }
        return new Temperature(kelvin);
    }

    public static Temperature fromCelsius(float celsius) {
        final float kelvin = celsiusToKelvin(celsius);
        if (kelvin < absoluteZeroInKelvin) {
            throw new IllegalArgumentException(
                    String.format("Provided temperature (%f Celsius) is below absolute zero.", celsius));
        }
        return new Temperature(kelvin);
    }

    public static Temperature fromFahrenheit(float fahrenheit) {
        final float kelvin = celsiusToKelvin(fahrenheitToCelsius(fahrenheit));
        if (kelvin < absoluteZeroInKelvin) {
            throw new IllegalArgumentException(
                    String.format("Provided temperature (%f Fahrenheit) is below absolute zero.", fahrenheit));
        }
        return new Temperature(kelvin);
    }

    public static Temperature absoluteZero() {
        return new Temperature(absoluteZeroInKelvin);
    }

    public static Temperature freezingPointOfWater() {
        return new Temperature(celsiusToKelvin(freezingPointOfWaterInCelsius));
    }

    public static Temperature boilingPointOfWater() {
        return new Temperature(celsiusToKelvin(boilingPointOfWaterInCelsius));
    }

    public float asKelvin() {
        return kelvin;
    }

    public float asCelsius() {
        return kelvinToCelsius(kelvin);
    }

    public float asFahrenheit() {
        return celsiusToFahrenheit(kelvinToCelsius(kelvin));
    }

    public String formatAsKelvin() {
        return String.format(Locale.ENGLISH, "%.2f\u00A0K", kelvin);
    }

    public String formatAsCelsius() {
        final var celsius = kelvinToCelsius(kelvin);
        return String.format(Locale.ENGLISH, "%.2f\u00A0°C", celsius);
    }

    public String formatAsFahrenheit() {
        final var celsius = kelvinToCelsius(kelvin);
        final var fahrenheit = celsiusToFahrenheit(celsius);
        return String.format(Locale.ENGLISH, "%.2f\u00A0°F", fahrenheit);
    }

    private static float kelvinToCelsius(float kelvin) {
        return kelvin - zeroCelsiusInKelvin;
    }

    private static float celsiusToKelvin(float celsius) {
        return celsius + zeroCelsiusInKelvin;
    }

    private static float fahrenheitToCelsius(float fahrenheit) {
        return remap(fahrenheit,
                absoluteZeroInFahrenheit, zeroCelsiusInFahrenheit,
                absoluteZeroInCelsius, zeroCelsiusInCelsius);
    }

    private static float celsiusToFahrenheit(float celsius) {
        return remap(celsius,
                absoluteZeroInCelsius, zeroCelsiusInCelsius,
                absoluteZeroInFahrenheit, zeroCelsiusInFahrenheit);
    }

    private static float remap(float value, float source1, float source2, float target1, float target2) {
        // The original value normalized within its original range, resulting in
        // a value between 0.0 (as it approaches "source1") and 1.0 (as it approaches "source2").
        final var valueNormalized = (value - source1) / (source2 - source1);
        return target1 + valueNormalized * (target2 - target1);
    }
}
