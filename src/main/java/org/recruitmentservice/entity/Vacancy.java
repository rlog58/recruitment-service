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

@Entity
@Data
@Table(name = "vacancies", schema = "public")
public class Vacancy {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "vacancy_id")
    private Long id;

    @Column(name = "position")
    private String position;

    @Column(name = "min_salary")
    private Integer minSalary;

    @Column(name = "max_salary")
    private Integer maxSalary;

    @ManyToOne
    @JoinColumn(name = "employer_id", referencedColumnName = "employer_id", insertable = false, updatable = false)
    private Employer employer;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "skills_vacancies",
            joinColumns = @JoinColumn(name = "vacancy_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private List<Skill> skills;
}
