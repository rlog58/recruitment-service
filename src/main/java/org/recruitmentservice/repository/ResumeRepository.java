package org.recruitmentservice.repository;


import org.recruitmentservice.entity.Resume;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResumeRepository extends CrudRepository<Resume, Long> {
    List<Resume> findAll();
}

