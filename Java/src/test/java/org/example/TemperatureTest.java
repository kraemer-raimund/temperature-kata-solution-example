package org.example;

import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TemperatureTest {

    private static final float EPSILON = 0.001f;

    @Test
    void shouldPreserveValue_fromCelsiusToCelsius() {
        final var expectedCelsius = 42.1337f;
        final var temperature = Temperature.fromCelsius(expectedCelsius);

        final var actualCelsius = temperature.asCelsius();

        assertThat(actualCelsius).isCloseTo(expectedCelsius, Offset.offset(EPSILON));
    }

    @Test
    void shouldProvideCorrectValue_inFahrenheit() {
        final var providedCelsius = 0.0f;
        final var temperature = Temperature.fromCelsius(providedCelsius);

        final var actualFahrenheit = temperature.asFahrenheit();
        final var expectedFahrenheit = 32.0f;

        assertThat(actualFahrenheit).isCloseTo(expectedFahrenheit, Offset.offset(EPSILON));
    }
}
