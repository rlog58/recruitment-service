package org.recruitmentservice.service;


import lombok.RequiredArgsConstructor;
import org.recruitmentservice.entity.Resume;
import org.recruitmentservice.entity.Skill;
import org.recruitmentservice.entity.Vacancy;
import org.recruitmentservice.repository.ResumeRepository;
import org.recruitmentservice.repository.VacancyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ResumeService {

    private final ResumeRepository resumeRepository;
    private final VacancyRepository vacancyRepository;

    public Resume addResume(Resume resume) {
        System.out.println(resume);
        resumeRepository.save(resume);
        return resume;
    }

    public Resume getResumeById(Long resumeId) {
        return resumeRepository.findById(resumeId).get();
    }

    public List<Resume> getAllResumes() {
        return resumeRepository.findAll();
    }

    public Resume updateResume(Resume resume) {
        resumeRepository.save(resume);
        return resume;
    }

    public Resume deleteResume(Long resumeId) {
        Resume resume = resumeRepository.findById(resumeId).get();
        resumeRepository.deleteById(resumeId);
        return resume;
    }

    public List<Vacancy> searchVacancies(Long resumeId) {
        List<Skill> resumeSkills = getResumeById(resumeId).getSkills();
        List<Vacancy> vacancies = vacancyRepository.findAll()
        .stream()
        .filter(vacancy -> vacancy.getSkills().containsAll(resumeSkills))
        .collect(Collectors.toList());
        return vacancies;
    }

}
