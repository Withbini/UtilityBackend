package gm.utility.chart.service;

import gm.utility.global.exception.Base64DecodingException;
import gm.utility.global.exception.FileCreationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
@Slf4j
public class FileManager {
    private final Decoder decoder;

    public String createFile(String rootPath, String username, String data) throws Base64DecodingException {
        Path userFolder = Paths.get(rootPath + '/' + username);
        Path dataFolder = Paths.get(userFolder.toAbsolutePath().toString() + '/' +
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm:ss")));
        Path dataFilePath = Paths.get(dataFolder.toAbsolutePath().toString() + "/chart.out");

        log.info("현재 작업 경로: {}", System.getProperty("user.dir"));
        log.info("현재 dataFilePath 경로: {}",dataFilePath.toAbsolutePath().toString());
        try {
            if (!userFolderExists(userFolder))
                Files.createDirectory(userFolder);

            if (!folderExists(dataFolder))
                Files.createDirectory(dataFolder);

            Files.createFile(dataFilePath);

            byte[] decodedData = decoder.decode(data);

            Files.write(dataFilePath, decodedData);
        } catch (IOException e) {
            throw new FileCreationException("신규 파일 생성에 실패했습니다.");
        }
        return username + "/" + dataFolder.getFileName().toString() + "/" + dataFilePath.getFileName().toString();
    }

    private boolean userFolderExists(Path userFolder) {
        return folderExists(userFolder);
    }

    public boolean folderExists(Path folder) {
        return Files.exists(folder) && Files.isDirectory(folder);
    }
}
