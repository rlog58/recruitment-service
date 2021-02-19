package org.recruitmentservice.service;

import lombok.RequiredArgsConstructor;
import org.recruitmentservice.avatar.storage.AvatarStorage;
import org.recruitmentservice.entity.AvatarInfo;
import org.recruitmentservice.repository.AvatarRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AvatarService {
    private final AvatarStorage avatarStorage;
    private final AvatarRepository avatarRepository;

    public AvatarInfo save(InputStream avatarBytes, AvatarInfo avatarInfo) throws IOException {
        avatarInfo = avatarRepository.save(avatarInfo);
        avatarStorage.save(avatarBytes, avatarInfo.getEmployee().getEmployeeId(), avatarInfo.getId());
        return avatarInfo;
    }

    public Optional<AvatarInfo> findById(Long id) {
        return avatarRepository.findById(id);
    }

    public byte[] read(Long avatarId, Long employeeId) {
        return avatarStorage.read(avatarId, employeeId);
    }
}
