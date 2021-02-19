package org.recruitmentservice.dto;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class ResumeDto {
    private Long resumeId;
    private Long employeeId;
    private Long avatarId;
    private String employeeEducation;
    private String desiredPosition;
    private Long desiredSalary;
    private List<String> skills;
}
