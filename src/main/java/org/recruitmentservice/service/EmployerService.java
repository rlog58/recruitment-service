package org.recruitmentservice.service;


import lombok.RequiredArgsConstructor;
import org.recruitmentservice.entity.Employer;
import org.recruitmentservice.exception.NoSuchEmployerException;
import org.recruitmentservice.repository.EmployerRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployerService {
    private final EmployerRepository employerRepository;

    public Employer findById(Long employerId) {
        Employer employer = employerRepository.findById(employerId).orElseThrow(() -> new NoSuchEmployerException(employerId));
        return employer;
    }

}
