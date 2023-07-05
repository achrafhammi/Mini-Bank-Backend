package com.achraf.minibankbackend.models;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Set;

import static jakarta.persistence.GenerationType.AUTO;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "BankAccount")
public class BankAccount {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long idAccount;

    private Integer passCode;

    private Float amount;

    private Date dateCreated;

    private boolean accountStatus;

    @ManyToOne
    @JoinColumn(name="idUser", nullable = false)
    private User ownerUser;

    @OneToMany(mappedBy = "bankAccountLoan")
    @ToString.Exclude
    private Set<Loans> loans;

    @OneToMany(mappedBy = "bankAccountSavings")
    @ToString.Exclude
    private Set<Savings> savings;

}
