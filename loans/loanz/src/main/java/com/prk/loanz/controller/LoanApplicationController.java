package com.prk.loanz.controller;

import com.prk.loanz.model.LoanApplication;
import com.prk.loanz.service.LoanApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/loans")
public class LoanApplicationController {

    private final LoanApplicationService service;

    public LoanApplicationController(LoanApplicationService service) {
        this.service = service;
    }

    @GetMapping
    public List<LoanApplication> getAllApplications() {
        return service.getAllApplications();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoanApplication> getApplicationById(@PathVariable Long id) {
        return service.getApplicationById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/apply")
    public LoanApplication applyForLoan(@RequestBody LoanApplication loanApplication) {
        return service.submitApplication(loanApplication);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> updateLoanStatus(@PathVariable Long id, @RequestParam String status) {
        service.updateLoanStatus(id, status);
        return ResponseEntity.ok().build();
    }
}
