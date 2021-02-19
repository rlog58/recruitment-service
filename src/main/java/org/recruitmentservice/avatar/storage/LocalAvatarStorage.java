package org.recruitmentservice.avatar.storage;

import org.recruitmentservice.exception.CannotFindAvatarException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Component
public class LocalAvatarStorage implements AvatarStorage {

    private final String basePath;

    public LocalAvatarStorage(AvatarProperties avatarProperties) {
        Objects.requireNonNull(avatarProperties);
        this.basePath = avatarProperties.getBasePath();
    }

    @Override
    public void save(InputStream avatarBytes, long employeeId, long avatarId) throws IOException {
        Path newFile = createDirectories(employeeId, avatarId);
        Files.copy(avatarBytes, newFile, StandardCopyOption.REPLACE_EXISTING);
    }

    @Override
    public byte[] read(Long avatarId, Long employeeId) {
        try {
            return Files.readAllBytes(Paths.get(String.format("%s/%d/%d.png", basePath, employeeId, avatarId)));
        } catch (IOException e) {
            throw new CannotFindAvatarException("Can't find avatar");
        }
    }

    private Path createDirectories(long employeeId, long avatarId) throws IOException {
        Files.deleteIfExists(Paths.get(String.format("%s/%d/%d.png", basePath, employeeId, avatarId)));
        Files.createDirectories(Paths.get(String.format("%s/%d", basePath, employeeId)));
        return Files.createFile(Paths.get(String.format("%s/%d/%d.png", basePath, employeeId, avatarId)));
    }
}

