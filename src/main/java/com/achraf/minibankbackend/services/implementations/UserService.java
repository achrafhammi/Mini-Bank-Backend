package com.achraf.minibankbackend.services.implementations;

import com.achraf.minibankbackend.exceptions.InternalErrorException;
import com.achraf.minibankbackend.models.User;
import com.achraf.minibankbackend.repos.UserRepo;
import com.achraf.minibankbackend.utils.ModelUpdater;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;

@Service
@Transactional
public class UserService implements com.achraf.minibankbackend.services.UserService {
    private final UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User getUser(Long ID) {
        return userRepo.getReferenceById(ID);
    }

    @Override
    public Boolean loginUser(String username, String password) {
        return userRepo.existsByUsernameAndPassword(username, password);
    }

    @Override
    public User registerUser(User newUser) {
        try {
            return userRepo.save(newUser);
        }catch(Exception e){
            throw new InternalErrorException("Problem at registration: " + e);
        }
    }

    @Override
    public void deleteUser(Long ID) {
        try{
            userRepo.deleteById(ID);
        }catch(Exception e){
            throw new InternalErrorException("Problem at deleting user" + e);
        }
    }

    @Override
    public User updateUser(Long ID, User updatedUser) {
        try {
            User existingUser = userRepo.getReferenceById(ID);
            User toSaveUpdatedUser = ModelUpdater.updateModel(existingUser, updatedUser);
            return userRepo.save(toSaveUpdatedUser);
        }catch (Exception e){
            throw new InternalErrorException("Error at updating"+ e);
        }
    }
}
