package org.recruitmentservice.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "resumes", schema = "public")
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resume_id", nullable = false)
    private Long resumeId;

    @Column(name = "employee_id", nullable = false)
    private Long employeeId;

    @Column(name = "avatar_id")
    private Long avatarId;

    @Column(name = "employee_education", nullable = false)
    private String employeeEducation;

    @Column(name = "desired_position", nullable = false)
    private String desiredPosition;

    @Column(name = "desired_salary", nullable = false)
    private Long desiredSalary;

    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "employee_id", insertable = false, updatable = false)
    private Employee employee;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "skills_resumes",
            joinColumns = @JoinColumn(name = "resume_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private List<Skill> skills;


}
