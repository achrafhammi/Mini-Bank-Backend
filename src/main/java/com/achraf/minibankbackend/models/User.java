package com.achraf.minibankbackend.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.sql.Date;
import java.util.Set;

import static jakarta.persistence.GenerationType.AUTO;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Users")
public class User {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long idUser;

    private String firstName;
    private String lastName;

    private Date dateOfBirth;

    @Column(unique = true)
    @NotEmpty(message = "Please include a unique username")
    private String username;

    @Column(unique = true)
    private String email;

    private String password;

    @Column(columnDefinition="boolean default false")
    private Boolean admin;

    @OneToMany(mappedBy = "ownerUser")
    @ToString.Exclude
    private Set<BankAccount> bankAccounts;
}
