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
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "Loans")
public class Loans {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long idLoans;

    private Float amountRequest;
    private String reasonRequest;
    private String statusRequest;
    private Float interest;
    private Float paymentPerMonth;
    @JsonIgnore
    private Date dateRequest;

    @ManyToOne
    @JoinColumn(name = "id_Account")
    private BankAccount bankAccountLoan;


}
