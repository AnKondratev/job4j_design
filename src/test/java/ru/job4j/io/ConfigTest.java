package ru.job4j.io;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ConfigTest {

    @Test
    void checkAllVariantsOfCorrectPairs() {
        String path = "./data/pairs.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.dialect"))
                .isEqualTo("org.hibernate.dialect.PostgreSQLDialect");
        assertThat(config.value("hibernate.connection.pass2"))
                .isEqualTo("password=123");
        assertThat(config.value("hibernate.connection.pass3"))
                .isEqualTo("password=");
    }

    @Test
    void whenFileIsOnlyDelimiterCharacter() throws IOException {
        Path path = Paths.get("./data/tests.properties");
        Config config = new Config(path.toString());
        Files.writeString(path, "=");
        assertThrows(IllegalArgumentException.class, config::load,
                "The pair is unrecognized!");
    }

    @Test
    void whenFileIsOnlyTwoDelimiterCharacters() throws IOException {
        Path path = Paths.get("./data/tests.properties");
        Config config = new Config(path.toString());
        Files.writeString(path, "==");
        assertThrows(IllegalArgumentException.class, config::load,
                "The pair is unrecognized!");
    }

    @Test
    void whenLineInFileDoesNotContainKey() throws IOException {
        Path path = Paths.get("./data/tests.properties");
        Config config = new Config(path.toString());
        Files.writeString(path, "=value");
        assertThrows(IllegalArgumentException.class, config::load,
                "The pair is unrecognized!");
    }

    @Test
    void whenLineInFileDoesNotContainValue() throws IOException {
        Path path = Paths.get("./data/tests.properties");
        Config config = new Config(path.toString());
        Files.writeString(path, "key=");
        assertThrows(IllegalArgumentException.class, config::load,
                "The pair is unrecognized!");
    }

    @AfterEach
    public void cleanup() throws IOException {
        Path path = Paths.get("./data/tests.properties");
        Files.writeString(path, "");
    }
}