package com.bankManagement.bankManagement.service;

import com.bankManagement.bankManagement.model.User;
import com.bankManagement.bankManagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> login(String email, String password) {
        return userRepository.findByEmail(email)
                .filter(user -> user.getPassword().equals(password));
    }

    
    public User findByAccountNo(String accountNo) {
        return userRepository.findByAccountNo(accountNo)
                .orElseThrow(() -> new IllegalArgumentException("User with account number " + accountNo + " not found"));
    }
}
