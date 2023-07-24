package com.achraf.minibankbackend.controllers;

import com.achraf.minibankbackend.models.Loan;
import com.achraf.minibankbackend.services.implementations.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/loans")
@RequiredArgsConstructor
@CrossOrigin
public class LoanController {
    private final LoanService loanService;

    @GetMapping("/loan/{id}")
    public ResponseEntity<?> getLoan(@PathVariable Long id){
        try{
            Loan loan = loanService.getLoan(id);
            return ResponseEntity.status(OK).body(loan);
        }catch (Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(e);
        }
    }

    @GetMapping("/loans")
    public ResponseEntity<?> getAllLoans(){
        try{
            return ResponseEntity.status(OK).body(loanService.getAllLoans());
        }catch (Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(e);
        }
    }

    @PostMapping("/createloan?accountId={idAccount}")
    public ResponseEntity<?> createLoan(@PathVariable Long idAccount, @RequestBody Loan newLoan){
        try{
            Loan createdLoan = loanService.createLoan(idAccount, newLoan);
            return ResponseEntity.status(CREATED).body(createdLoan);
        }catch (Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(e);
        }
    }

    @PatchMapping("/updateloan/{id}")
    public ResponseEntity<?> updateLoan(@PathVariable Long id, @RequestBody Loan updateLoan){
        try{
            Loan updatedLoan = loanService.updateLoan(id, updateLoan);
            return ResponseEntity.status(OK).body(updatedLoan);
        }catch (Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(e);
        }
    }

    @GetMapping("/approve?accountId={idAccount}&loanId={idLoan}")
    public ResponseEntity<?> approveLoan(@PathVariable Long idAccount, @PathVariable Long idLoan){
        try{
            loanService.approveLoan(idAccount, idLoan);
            return ResponseEntity.status(OK).body("Loan Approved");
        }catch (Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(e);
        }
    }

    @DeleteMapping("/deleteloan/{id}")
    public ResponseEntity<?> deleteLoan(@PathVariable Long id){
        try{
            loanService.deleteLoan(id);
            return ResponseEntity.status(OK).body("Loan Deleted");
        }catch (Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(e);
        }
    }
}
