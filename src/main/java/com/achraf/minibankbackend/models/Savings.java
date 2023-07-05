package com.achraf.minibankbackend.models;

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
@NoArgsConstructor
@Table(name="Savings")
public class Savings {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long idSavings;

    private Float amountSavings;
    private Date dateCreated;
    private Date dateToClose;

    @ManyToOne
    @JoinColumn(name = "savings")
    private BankAccount bankAccountSavings;
}
