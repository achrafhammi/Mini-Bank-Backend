package com.achraf.minibankbackend.controllers;

import com.achraf.minibankbackend.models.BankAccount;
import com.achraf.minibankbackend.services.BankAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/accountsapi/")
@CrossOrigin
@RequiredArgsConstructor
public class BankAccountController {
    private final BankAccountService bankAccountService;

    @GetMapping("/account/{id}")
    public ResponseEntity<?> getAccount(@PathVariable Long id){
        try {
            BankAccount account = bankAccountService.getAccount(id);
            return ResponseEntity.status(OK).body(account);
        }catch(Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(e);
        }
    }

    @PostMapping("/createaccount?userid={iduser}")
    public ResponseEntity<?> createAccount(@RequestBody BankAccount createAccount, @PathVariable Long iduser){
        try {
            BankAccount createdAccount = bankAccountService.createAccount(iduser, createAccount);
            return ResponseEntity.status(CREATED).body(createdAccount);
        }catch(Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(e);
        }
    }

    @PatchMapping("/updateaccount/{id}")
    public ResponseEntity<?> updateAccount(@PathVariable Long id, @RequestBody BankAccount updateAccount){
        try{
            BankAccount account = bankAccountService.updateAccount(id,updateAccount);
            return ResponseEntity.status(OK).body(account);
        }catch (Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(e);
        }
    }

    @DeleteMapping("/deleteaccount/{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable Long id){
        try {
            bankAccountService.deleteAccount(id);
            return ResponseEntity.status(OK).body("Account Deleted");
        }catch (Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(e);
        }
    }
}
