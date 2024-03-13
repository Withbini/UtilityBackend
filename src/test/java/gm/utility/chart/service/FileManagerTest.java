package gm.utility.chart.service;

import gm.utility.chart.service.Decoder;
import gm.utility.chart.service.FileManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class FileManagerTest {
    @InjectMocks
    private FileManager fileManager;
    @Mock
    private Decoder decoder;

    @AfterAll
    static void teardown() {
        Path rootFolder = Paths.get(System.getProperty("user.dir") + "/test");
        if (Files.exists(rootFolder)) {
            try {
                deleteDirectory(rootFolder);
            } catch (IOException e) {
                System.out.println(e.getMessage());
                throw new RuntimeException(e);
            }
        }
    }

    private static void deleteDirectory(Path directory) throws IOException {
        Files.walk(directory)
                .sorted((p1, p2) -> -p1.compareTo(p2))
                .forEach(path -> {
                    try {
                        Files.delete(path);
                        System.out.println("삭제: " + path);
                    } catch (IOException e) {
                        System.err.println("삭제 실패: " + path + ", " + e.getMessage());
                    }
                });
    }

    @Test
    @DisplayName("파일 생성 성공")
    void createFile() throws IOException {
        //given
        String data = "abcdef";
        Path rootFolder = Paths.get(System.getProperty("user.dir") + "/test");
        if (!Files.exists(rootFolder))
            Files.createDirectory(rootFolder);

        //when
        byte[] expected = data.getBytes(StandardCharsets.UTF_8);

        when(decoder.decode(any())).thenReturn(expected);

        String filePath = fileManager.createFile("test", "jb",
                new String(Base64.getEncoder().encode(data.getBytes(StandardCharsets.UTF_8))));

        //then
        assertThat(filePath).matches("jb/\\d{4}-\\d{2}-\\d{2}-\\d{2}:\\d{2}:\\d{2}/chart.out");
        assertThat(Files.readString(Paths.get(rootFolder.toAbsolutePath().toString() + "/" + filePath))).isEqualTo(data);
    }
}