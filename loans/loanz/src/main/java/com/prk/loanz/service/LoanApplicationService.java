package com.prk.loanz.service;

import com.prk.loanz.model.LoanApplication;
import com.prk.loanz.repository.LoanApplicationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class LoanApplicationService {

    private final LoanApplicationRepository repository;

    public LoanApplicationService(LoanApplicationRepository repository) {
        this.repository = repository;
    }

    public List<LoanApplication> getAllApplications() {
        return repository.findAll();
    }

    public Optional<LoanApplication> getApplicationById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public LoanApplication submitApplication(LoanApplication loanApplication) {
        loanApplication.setStatus("PENDING");
        return repository.save(loanApplication);
    }

    @Transactional
    public void updateLoanStatus(Long id, String status) {
        repository.findById(id).ifPresent(application -> {
            application.setStatus(status);
            repository.save(application);
        });
    }
}
