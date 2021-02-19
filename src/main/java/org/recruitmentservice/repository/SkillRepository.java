package org.recruitmentservice.repository;


import org.recruitmentservice.entity.Skill;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillRepository extends CrudRepository<Skill, Long> {
    @Query("SELECT skill FROM Skill skill WHERE skill.description = :description")
    List<Skill> findSkillByDescription(@Param("description") String description);
}
