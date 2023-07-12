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
@Table(name="Savings")
public class Saving {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long idSavings;

    private Float amountSavings;

    @JsonIgnore
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date dateCreated;
    private Date dateToClose;

    @ManyToOne
    @JoinColumn(name = "id_account")
    private BankAccount bankAccountSavings;
}
