package org.example;

import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class TemperatureTest {

    private static final float EPSILON = 0.001f;

    private static final String KNOWN_VALUES = """
            # Celsius, Fahrenheit
            0.0, 32.0
            -273.15, -459.67
            99.9839, 211.97102
            """;

    @Test
    void shouldPreserveValue_fromCelsiusToCelsius() {
        final var expectedCelsius = 42.1337f;
        final var temperature = Temperature.fromCelsius(expectedCelsius);

        final var actualCelsius = temperature.asCelsius();

        assertThat(actualCelsius).isCloseTo(expectedCelsius, Offset.offset(EPSILON));
    }

    @ParameterizedTest
    @CsvSource(textBlock = KNOWN_VALUES)
    void shouldProvideCorrectValue_inCelsius(float knownCelsius, float knownFahrenheit) {
        final var temperature = Temperature.fromFahrenheit(knownFahrenheit);

        final var actualCelsius = temperature.asCelsius();

        assertThat(actualCelsius).isCloseTo(knownCelsius, Offset.offset(EPSILON));
    }

    @ParameterizedTest
    @CsvSource(textBlock = KNOWN_VALUES)
    void shouldProvideCorrectValue_inFahrenheit(float knownCelsius, float knownFahrenheit) {
        final var temperature = Temperature.fromCelsius(knownCelsius);

        final var actualFahrenheit = temperature.asFahrenheit();

        assertThat(actualFahrenheit).isCloseTo(knownFahrenheit, Offset.offset(EPSILON));
    }
}
