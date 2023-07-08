package com.achraf.minibankbackend.services;

import com.achraf.minibankbackend.models.User;

public interface UserService {
    public User getUser(Long ID);
    public Boolean loginUser(String username, String password);
    public User registerUser(User newUser);
    public void deleteUser(Long ID);
    public User updateUser(Long ID, User updatedUser);



}
