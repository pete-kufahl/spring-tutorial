package com.prk.loanz.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "loan_applications")
@Getter
@Setter
@ToString
public class LoanApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String applicantName;
    private BigDecimal income;
    private BigDecimal loanAmount;
    private int creditScore;
    private BigDecimal debtToIncomeRatio;
    private String status;  // PENDING, APPROVED, REJECTED

    // ✅ Default no-argument constructor
    public LoanApplication() {
        this.status = "PENDING"; // Default status
    }

    // ✅ Parameterized constructor
    public LoanApplication(String applicantName, BigDecimal income, BigDecimal loanAmount,
                           int creditScore, BigDecimal debtToIncomeRatio) {
        this.applicantName = applicantName;
        this.income = income;
        this.loanAmount = loanAmount;
        this.creditScore = creditScore;
        this.debtToIncomeRatio = debtToIncomeRatio;
        this.status = "PENDING";
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        LoanApplication that = (LoanApplication) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
