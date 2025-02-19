package ca.uqam.mgl7230.tp1.config;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class FileWriterProviderTest {

    private static final String FILENAME = "test";
    private static final String WRONG_FILENAME = "target";

    private final FileWriterProvider fileWriterProvider = new FileWriterProvider();

    @Test
    void createFileReturnsFile() throws IOException {
        FileWriter writer = fileWriterProvider.createFile(FILENAME);

        assertThat(writer).isNotNull();

        writer.close();
        File file = new File(FILENAME);
        file.delete();
    }

    @Test
    void createFileReturnsNull() {
        FileWriter writer = fileWriterProvider.createFile(WRONG_FILENAME);

        assertThat(writer).isNull();
    }
}