package org.example;

public class Temperature {

    private final float celsius;

    private Temperature(float celsius) {
        this.celsius = celsius;
    }

    public static Temperature fromCelsius(float celsius) {
        return new Temperature(celsius);
    }

    public float asCelsius() {
        return celsius;
    }
}
