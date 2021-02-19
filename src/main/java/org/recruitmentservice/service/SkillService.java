package org.recruitmentservice.service;


import lombok.RequiredArgsConstructor;
import org.recruitmentservice.entity.Skill;
import org.recruitmentservice.exception.NoSuchSkillException;
import org.recruitmentservice.repository.SkillRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SkillService {
    private final SkillRepository skillRepository;

    public Skill findSkillByDescription(String description) {
        List<Skill> skill = skillRepository.findSkillByDescription(description);
        if (skill.size() < 1) {
            throw new NoSuchSkillException(String.format("Skill with description=\"%s\" not exist", description));
        }
        return skill.get(0);
    }

}
