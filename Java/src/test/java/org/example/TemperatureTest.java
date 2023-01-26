package org.example;

import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class TemperatureTest {

    private static final float EPSILON = 0.001f;

    private static final String KNOWN_VALUES = """
            # Kelvin, Celsius, Fahrenheit
            0.0, -273.15, -459.67
            273.15, 0.0, 32.0
            373.1339, 99.9839, 211.97102
            """;

    @Test
    void shouldPreserveValue_fromCelsiusToCelsius() {
        final var expectedCelsius = 42.1337f;
        final var temperature = Temperature.fromCelsius(expectedCelsius);

        final var actualCelsius = temperature.asCelsius();

        assertThat(actualCelsius).isCloseTo(expectedCelsius, Offset.offset(EPSILON));
    }

    @Test
    void shouldPreserveValue_fromFahrenheitToFahrenheit() {
        final var expectedFahrenheit = 42.1337f;
        final var temperature = Temperature.fromFahrenheit(expectedFahrenheit);

        final var actualFahrenheit = temperature.asFahrenheit();

        assertThat(actualFahrenheit).isCloseTo(expectedFahrenheit, Offset.offset(EPSILON));
    }

    @ParameterizedTest
    @CsvSource(textBlock = KNOWN_VALUES)
    void shouldProvideCorrectValue_inKelvin(float knownKelvin, float knownCelsius, float knownFahrenheit) {
        final var temperature = Temperature.fromFahrenheit(knownFahrenheit);

        final var actualKelvin = temperature.asKelvin();

        assertThat(actualKelvin).isCloseTo(knownKelvin, Offset.offset(EPSILON));
    }

    @ParameterizedTest
    @CsvSource(textBlock = KNOWN_VALUES)
    void shouldProvideCorrectValue_inCelsius(float knownKelvin, float knownCelsius, float knownFahrenheit) {
        final var temperature = Temperature.fromFahrenheit(knownFahrenheit);

        final var actualCelsius = temperature.asCelsius();

        assertThat(actualCelsius).isCloseTo(knownCelsius, Offset.offset(EPSILON));
    }

    @ParameterizedTest
    @CsvSource(textBlock = KNOWN_VALUES)
    void shouldProvideCorrectValue_inFahrenheit(float knownKelvin, float knownCelsius, float knownFahrenheit) {
        final var temperature = Temperature.fromCelsius(knownCelsius);

        final var actualFahrenheit = temperature.asFahrenheit();

        assertThat(actualFahrenheit).isCloseTo(knownFahrenheit, Offset.offset(EPSILON));
    }
}
