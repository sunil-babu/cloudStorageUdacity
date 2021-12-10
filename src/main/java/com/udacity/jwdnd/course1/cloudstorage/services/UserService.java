package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.model.User;

public interface UserService {

    int registerUser(User user) throws Exception;
    User findUserByUserId(Integer userId);
    User findUserByUserName(String username);
}
