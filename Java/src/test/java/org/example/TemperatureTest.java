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
        final var actualKelvinFromKelvin = Temperature.fromKelvin(knownKelvin).asKelvin();
        assertThat(actualKelvinFromKelvin).isCloseTo(knownKelvin, Offset.offset(EPSILON));

        final var actualKelvinFromCelsius = Temperature.fromCelsius(knownCelsius).asKelvin();
        assertThat(actualKelvinFromCelsius).isCloseTo(knownKelvin, Offset.offset(EPSILON));

        final var actualKelvinFromFahrenheit = Temperature.fromFahrenheit(knownFahrenheit).asKelvin();
        assertThat(actualKelvinFromFahrenheit).isCloseTo(knownKelvin, Offset.offset(EPSILON));
    }

    @ParameterizedTest
    @CsvSource(textBlock = KNOWN_VALUES)
    void shouldProvideCorrectValue_inCelsius(float knownKelvin, float knownCelsius, float knownFahrenheit) {
        final var actualCelsiusFromKelvin = Temperature.fromKelvin(knownKelvin).asCelsius();
        assertThat(actualCelsiusFromKelvin).isCloseTo(knownCelsius, Offset.offset(EPSILON));

        final var actualCelsiusFromCelsius = Temperature.fromCelsius(knownCelsius).asCelsius();
        assertThat(actualCelsiusFromCelsius).isCloseTo(knownCelsius, Offset.offset(EPSILON));

        final var actualCelsiusFromFahrenheit = Temperature.fromFahrenheit(knownFahrenheit).asCelsius();
        assertThat(actualCelsiusFromFahrenheit).isCloseTo(knownCelsius, Offset.offset(EPSILON));
    }

    @ParameterizedTest
    @CsvSource(textBlock = KNOWN_VALUES)
    void shouldProvideCorrectValue_inFahrenheit(float knownKelvin, float knownCelsius, float knownFahrenheit) {
        final var actualFahrenheitFromKelvin = Temperature.fromKelvin(knownKelvin).asFahrenheit();
        assertThat(actualFahrenheitFromKelvin).isCloseTo(knownFahrenheit, Offset.offset(EPSILON));

        final var actualFahrenheitFromCelsius = Temperature.fromCelsius(knownCelsius).asFahrenheit();
        assertThat(actualFahrenheitFromCelsius).isCloseTo(knownFahrenheit, Offset.offset(EPSILON));

        final var actualFahrenheitFromFahrenheit = Temperature.fromFahrenheit(knownFahrenheit).asFahrenheit();
        assertThat(actualFahrenheitFromFahrenheit).isCloseTo(knownFahrenheit, Offset.offset(EPSILON));
    }
}
