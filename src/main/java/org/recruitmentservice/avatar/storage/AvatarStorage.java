package org.recruitmentservice.avatar.storage;

import java.io.IOException;
import java.io.InputStream;

public interface AvatarStorage {
    void save(InputStream avatarBytes, long employeeId, long avatarId) throws IOException;

    byte[] read(Long avatarId, Long employeeId);
}

