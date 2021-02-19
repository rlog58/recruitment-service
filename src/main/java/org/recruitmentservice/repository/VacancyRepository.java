package org.recruitmentservice.repository;


import org.recruitmentservice.entity.Vacancy;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VacancyRepository extends CrudRepository<Vacancy, Long> {
    List<Vacancy> findAll();

    List<Vacancy> findByEmployerId(Long id);
}
