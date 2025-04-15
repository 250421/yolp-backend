package com.revature.yolp.services;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.revature.yolp.dtos.requests.NewUserRequest;
import com.revature.yolp.models.User;
import com.revature.yolp.repositories.UserRepository;
import com.revature.yolp.utils.custom_exceptions.DuplicateUsernameException;
import com.revature.yolp.utils.custom_exceptions.InvalidPasswordException;
import com.revature.yolp.utils.custom_exceptions.InvalidUsernameException;

import lombok.Data;

@Service
@Data
public class AuthService {
    private final UserRepository userRepository;

    public boolean signUp(NewUserRequest newUserRequest) {
        if (!IsValidUsername(newUserRequest.getUsername())) {
            throw new InvalidUsernameException("Invalid username");
        }
        if (!IsValidPassword(newUserRequest.getPassword())) {
            throw new InvalidPasswordException("Invalid password");
        }
        if (!isUniqueUsername(newUserRequest.getUsername())) {
            throw new DuplicateUsernameException("Username already exists");
        }

        String hashedPassword = BCrypt.hashpw(newUserRequest.getPassword(), BCrypt.gensalt());

        User newUser = new User(newUserRequest.getUsername(), hashedPassword);

        userRepository.save(newUser);

        return true;
    }

    public User signIn(NewUserRequest newUserRequest) {
        User user = userRepository.findByUsername(newUserRequest.getUsername());

        if (user == null) {
            throw new InvalidUsernameException("Invalid username or password");
        }

        if (!BCrypt.checkpw(newUserRequest.getPassword(), user.getPassword())) {
            throw new InvalidPasswordException("Invalid username or password");
        }

        return user;
    }

    private boolean IsValidUsername(String username) {
        return username.matches("^[a-zA-Z0-9_-]{3,20}$");
    }

    private boolean IsValidPassword(String password) {
        return password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$");
    }

    private boolean isUniqueUsername(String username) {
        return !userRepository.existsByUsername(username);
    }
}
