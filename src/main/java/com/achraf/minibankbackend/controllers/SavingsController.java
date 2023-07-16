package com.achraf.minibankbackend.controllers;

import com.achraf.minibankbackend.models.Saving;
import com.achraf.minibankbackend.services.implementations.SavingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/savings")
@RequiredArgsConstructor
@CrossOrigin
public class SavingsController {
    private final SavingService savingService;

    @GetMapping("/savings")
    public ResponseEntity<?> getAllSavings(){
        try{
            List<Saving> savings = savingService.getAllSavings();
            return ResponseEntity.status(OK).body(savings);
        }catch(Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(e);
        }
    }

    @GetMapping("/savings/{id}")
    public ResponseEntity<?> getSavings(@PathVariable Long id){
        try{
            Saving saving = savingService.getSaving(id);
            return ResponseEntity.status(CREATED).body(saving);
        }catch (Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(e);

        }
    }

    @PostMapping("/savings?accountId={accountId}")
    public ResponseEntity<?> createSaving(@PathVariable Long accountId, @RequestBody Saving newSaving){
        try{
            Saving addedSaving = savingService.createSaving(accountId, newSaving);
            return ResponseEntity.status(CREATED).body(addedSaving);
        }catch (Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(e);

        }
    }

    @PatchMapping("/savings/{id}")
    public ResponseEntity<?> updateSaving(@PathVariable Long id, @RequestBody Saving updateSaving){
        try{
            Saving updatedSaving = savingService.updateSaving(id, updateSaving);
            return ResponseEntity.status(OK).body(updatedSaving);
        }catch(Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(e);
        }
    }

    @DeleteMapping("/savings/{id}")
    public ResponseEntity<?> deleteSaving(@PathVariable Long id){
        try{
            savingService.deleteSaving(id);
            return ResponseEntity.status(OK).body("Account Deleted");
        }catch (Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(e);
        }
    }

}
