package org.recruitmentservice.controller;


import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.recruitmentservice.dto.ResumeDto;
import org.recruitmentservice.dto.VacancyDto;
import org.recruitmentservice.entity.Resume;
import org.recruitmentservice.entity.Skill;
import org.recruitmentservice.entity.Vacancy;
import org.recruitmentservice.service.EmployerService;
import org.recruitmentservice.service.SkillService;
import org.recruitmentservice.service.VacancyService;
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


@RestController
@CrossOrigin
@AllArgsConstructor
public class VacancyController {

    private final VacancyService vacancyService;
    private final EmployerService employerService;
    private final ModelMapper modelMapper;
    private final SkillService skillService;

    @GetMapping(value = "/vacancies")
    @ResponseStatus(HttpStatus.OK)
    public List<VacancyDto> getAll() {
        return vacancyService.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @DeleteMapping(value = "/vacancy/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable(value = "id") Long id) {
        vacancyService.deleteById(id);
    }

    @GetMapping(value = "/vacancy/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VacancyDto findById(@PathVariable(value = "id") Long id) {
        return convertToDto(vacancyService.findById(id)
                .orElseThrow(() -> new NoSuchVacancyException(String.format("Vacancy not found (id = %d)", id))));
    }

    @GetMapping(value = "/vacancy/search/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<ResumeDto> searchResumes(@PathVariable(value = "id") Long employerId) {
        return vacancyService.searchResumes(employerId).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @PostMapping(value = "/vacancy")
    @ResponseStatus(HttpStatus.CREATED)
    public VacancyDto addVacancy(@RequestBody VacancyDto vacancyDto) {
        Vacancy vacancy = convertFromDto(vacancyDto);
        vacancyService.save(vacancy);
        return vacancyDto;
    }

    @PutMapping("/vacancy/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VacancyDto updateVacancy(@PathVariable("id") Long vacancyId, @RequestBody VacancyDto vacancyDto) {
        if (!vacancyId.equals(vacancyDto.getId())) {
            vacancyDto.setId(vacancyId);
        }
        vacancyService.updateVacancy(convertFromDto(vacancyDto));
        return vacancyDto;
    }


    private VacancyDto convertToDto(Vacancy vacancy) {
        VacancyDto vacancyDto = modelMapper.map(vacancy, VacancyDto.class);
        vacancyDto.setSkills(vacancy.getSkills().stream().map(Skill::getDescription).collect(Collectors.toList()));
        return vacancyDto;
    }

    private Vacancy convertFromDto(VacancyDto vacancyDto) {
        Vacancy vacancy = modelMapper.map(vacancyDto, Vacancy.class);
        vacancy.setEmployer(employerService.findById(vacancyDto.getEmployerId()));
        vacancy.setSkills(vacancyDto.getSkills().stream().map(skillService::findSkillByDescription).collect(Collectors.toList()));
        return vacancy;

    }

    private ResumeDto convertToDto(Resume resume) {
        ResumeDto resumeDto = modelMapper.map(resume, ResumeDto.class);
        resumeDto.setSkills(resume.getSkills().stream().map(Skill::getDescription).collect(Collectors.toList()));
        return resumeDto;
    }

}
