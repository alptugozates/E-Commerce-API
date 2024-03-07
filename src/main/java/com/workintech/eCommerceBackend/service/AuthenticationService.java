package com.workintech.eCommerceBackend.service;

import com.workintech.eCommerceBackend.dto.UserDto;
import com.workintech.eCommerceBackend.entity.Role;
import com.workintech.eCommerceBackend.entity.User;
import com.workintech.eCommerceBackend.exception.RoleException;
import com.workintech.eCommerceBackend.repository.RoleRepository;
import com.workintech.eCommerceBackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthenticationService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private UserService userService;

    @Autowired
    public AuthenticationService(UserRepository userRepository, RoleRepository roleRepository,PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder=passwordEncoder;
    }
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public User register(String fullName,String email,String password){
        String encodedPassword = passwordEncoder.encode(password);
        Role customerRole = roleRepository.findByAuthority("Customer").get();

        Set<Role> roles = new HashSet<>();
        roles.add(customerRole);

        User user = new User();
        user.setEmail(email);
        user.setFullName(fullName);
        user.setPassword(encodedPassword);
        user.setRole((Role) roles);

        return userRepository.save(user);
    }
}
