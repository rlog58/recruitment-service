package org.recruitmentservice.repository;

import org.recruitmentservice.entity.AvatarInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AvatarRepository extends CrudRepository<AvatarInfo, Long> {
    AvatarInfo save(AvatarInfo avatarInfo);

    Optional<AvatarInfo> findById(Long id);
}
