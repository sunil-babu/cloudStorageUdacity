package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserMapper usersMapper;
    private  HashService hashService;

    @Override
    public int registerUser(User user) throws Exception {

        User existingUser = usersMapper.findByUserId(user.getUserId());

        if (existingUser != null)
        {
            throw new Exception("Username already exists.");
        }

        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        String hashedPassword = hashService.getHashedValue(user.getPassword(), encodedSalt);

        return usersMapper.create(new User(null, user.getUsername(), encodedSalt, hashedPassword, user.getFirstName(), user.getLastName()));
    }

    @Override
    public User findUserByUserId(Integer userId) {
        return usersMapper.findByUserId(userId);
    }

    @Override
    public User findUserByUserName(String username) {
        return usersMapper.findByUserName(username);
    }
}
