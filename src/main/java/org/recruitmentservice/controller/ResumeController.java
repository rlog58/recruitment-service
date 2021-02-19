package org.recruitmentservice.controller;


import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.recruitmentservice.dto.ResumeDto;
import org.recruitmentservice.dto.VacancyDto;
import org.recruitmentservice.entity.Resume;
import org.recruitmentservice.entity.Skill;
import org.recruitmentservice.entity.Vacancy;
import org.recruitmentservice.service.EmployeeService;
import org.recruitmentservice.service.ResumeService;
import org.recruitmentservice.service.SkillService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@CrossOrigin
@RestController
public class ResumeController {

    private final ResumeService resumeService;
    private final EmployeeService employeeService;
    private final SkillService skillService;
    private final ModelMapper modelMapper;

    @PostMapping("/resume")
    @ResponseStatus(HttpStatus.CREATED)
    public ResumeDto addResume(@RequestBody ResumeDto resumeDto) {
        resumeService.addResume(convertToEntity(resumeDto));
        return resumeDto;
    }

    @GetMapping("/resume/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResumeDto getResumeById(@PathVariable("id") Long id) {
        Resume resume = resumeService.getResumeById(id);
        return convertToDto(resume);
    }

    @GetMapping("/resumes")
    @ResponseStatus(HttpStatus.OK)
    public List<ResumeDto> getAllResumes() {
        List<Resume> list = resumeService.getAllResumes();
        return list.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @PutMapping("/resume")
    @ResponseStatus(HttpStatus.OK)
    public ResumeDto updateResume(@RequestBody ResumeDto resumeDto) {
        resumeService.updateResume(convertToEntity(resumeDto));
        return resumeDto;
    }

    @DeleteMapping("/resume/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteResume(@PathVariable("id") Long id) {
        resumeService.deleteResume(id);
    }

    @GetMapping("/resume/search/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<VacancyDto> searchVacancies(@PathVariable("id") Long id) {
        List<Vacancy> vacancies = resumeService.searchVacancies(id);
        return vacancies.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private Resume convertToEntity(ResumeDto resumeDto) {
        Resume resume = modelMapper.map(resumeDto, Resume.class);
        resume.setEmployee(employeeService.getEmployeeById(resume.getEmployeeId()));
        resume.setSkills(resumeDto.getSkills().stream().map(skillService::findSkillByDescription).collect(Collectors.toList()));
        return resume;
    }

    private ResumeDto convertToDto(Resume resume) {
        ResumeDto resumeDto = modelMapper.map(resume, ResumeDto.class);
        resumeDto.setSkills(resume.getSkills().stream().map(Skill::getDescription).collect(Collectors.toList()));
        return resumeDto;
    }

    private VacancyDto convertToDto(Vacancy vacancy) {
        VacancyDto vacancyDto = modelMapper.map(vacancy, VacancyDto.class);
        vacancyDto.setSkills(vacancy.getSkills().stream().map(Skill::getDescription).collect(Collectors.toList()));
        return vacancyDto;
    }

}
