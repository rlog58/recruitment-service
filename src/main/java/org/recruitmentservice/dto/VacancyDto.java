package org.recruitmentservice.dto;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class VacancyDto {
    private Long id;
    private String position;
    private Integer minSalary;
    private Integer maxSalary;
    private Long employerId;
    private List<String> skills;
}

