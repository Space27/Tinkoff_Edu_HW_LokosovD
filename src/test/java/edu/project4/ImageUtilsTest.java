package edu.project4;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.assertj.core.api.Assertions.assertThat;

class ImageUtilsTest {

    @ParameterizedTest
    @EnumSource(ImageFormat.class)
    void save_shouldSaveImageWithCorrectExpansion(ImageFormat format) throws IOException {
        Image image = new Image(4, 4);
        Path path = Path.of("test." + format.toString().toLowerCase());

        ImageUtils.save(image, path, format);

        assertThat(Files.exists(path))
            .isTrue();

        Files.delete(path);
    }
}
