package org.recruitmentservice.repository;


import org.recruitmentservice.entity.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

}
