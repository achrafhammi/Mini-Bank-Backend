package com.achraf.minibankbackend.services;

import com.achraf.minibankbackend.models.User;

public interface UserService {
    User getUser(Long ID);
    Boolean loginUser(String username, String password);
    User registerUser(User newUser);
    void deleteUser(Long ID);
    User updateUser(Long ID, User updatedUser);



}
