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
@Table(name = "Operation")
public class Operation {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long idOperation;
    private String typeOperation;

    @JsonIgnore
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date dateOperation;
    private String motive;

    @ManyToOne
    @JoinColumn(name = "id_Account_Made")
    private BankAccount bankAccountMade;

    @ManyToOne
    @JoinColumn(name = "id_Account_Concerned")
    private BankAccount bankAccountConcerned;


}
