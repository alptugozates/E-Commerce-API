package com.workintech.eCommerceBackend.service;

import com.workintech.eCommerceBackend.dto.UserDto;
import com.workintech.eCommerceBackend.entity.User;
import com.workintech.eCommerceBackend.exception.UserException;
import com.workintech.eCommerceBackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    private UserRepository userRepository;
    private AuthenticationService authenticationService;


    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.authenticationService = authenticationService;
    }

    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> new UserDto(user.getEmail(), user.getPassword(), user.getFullName()))
                .collect(Collectors.toList());
    }

    public UserDto updateUser(Long id, User updatedUser) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setEmail(updatedUser.getEmail());
            user.setPassword(updatedUser.getPassword());
            user.setFullName(updatedUser.getFullName());
            User savedUser = userRepository.save(user);
            return new UserDto(savedUser.getEmail(), savedUser.getPassword(), savedUser.getFullName());
        } else {
            throw new UserException("User not found with ID: " + id, HttpStatus.NOT_FOUND);
        }
    }

    public UserDto getUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return new UserDto(user.getEmail(), user.getPassword(), user.getFullName());
        } else {
            throw new UserException("User is not found with given ID: " + id, HttpStatus.NOT_FOUND);
        }
    }

    public UserDto createUser(User user) {
        User savedUser = userRepository.save(user);
        return new UserDto(savedUser.getEmail(), savedUser.getPassword(), savedUser.getFullName());
    }


    public UserDto deleteUser(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            userRepository.deleteById(id);
            return new UserDto(user.getEmail(), user.getPassword(), user.getFullName());
        } else {
            throw new UserException("User not found with ID: " + id, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + email));

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }
}
