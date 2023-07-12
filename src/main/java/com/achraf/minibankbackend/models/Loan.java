package com.achraf.minibankbackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

import static jakarta.persistence.GenerationType.AUTO;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Loans")
public class Loan {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long idLoans;

    private Float amountRequest;
    private String reasonRequest;
    private String statusRequest;
    private Float interest;
    private Float paymentPerMonth;
    @JsonIgnore
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date dateRequest;

    @ManyToOne
    @JoinColumn(name = "id_Account")
    private BankAccount bankAccountLoan;
}
