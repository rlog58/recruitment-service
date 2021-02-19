package org.recruitmentservice.service;


import lombok.RequiredArgsConstructor;
import org.recruitmentservice.entity.Resume;
import org.recruitmentservice.entity.Skill;
import org.recruitmentservice.entity.Vacancy;
import org.recruitmentservice.repository.ResumeRepository;
import org.recruitmentservice.repository.VacancyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class VacancyService {
    private final VacancyRepository vacancyRepository;
    private final ResumeRepository resumeRepository;

    public List<Vacancy> findAll() {
        return vacancyRepository.findAll();
    }

    public List<Vacancy> findByEmployerId(Long id) {
        return vacancyRepository.findByEmployerId(id);
    }

    public Optional<Vacancy> findById(Long id) {
        return vacancyRepository.findById(id);
    }

    public List<Resume> searchResumes(Long vacancyId) {
        List<Skill> skills = findById(vacancyId).get().getSkills();
        return resumeRepository.findAll()
        .stream()
        .filter(resume -> resume.getSkills().containsAll(skills))
        .collect(Collectors.toList());
    }

    public Vacancy updateVacancy(Vacancy vacancy) {
        vacancyRepository.save(vacancy);
        return vacancy;
    }

    public void save(Vacancy vacancy) {
        vacancyRepository.save(vacancy);
    }

    public void deleteById(Long id) {
        vacancyRepository.deleteById(id);
    }


}
