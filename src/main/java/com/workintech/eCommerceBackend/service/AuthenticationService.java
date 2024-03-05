//package com.workintech.eCommerceBackend.service;
//
//import com.workintech.eCommerceBackend.entity.Role;
//import com.workintech.eCommerceBackend.entity.User;
//import com.workintech.eCommerceBackend.exception.RoleException;
//import com.workintech.eCommerceBackend.repository.RoleRepository;
//import com.workintech.eCommerceBackend.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.HashSet;
//import java.util.Set;
//
//@Service
//public class AuthenticationService {
//    private UserRepository userRepository;
//    private RoleRepository roleRepository;
//    private PasswordEncoder passwordEncoder;
//
//    @Autowired
//    public AuthenticationService(UserRepository userRepository, RoleRepository roleRepository,PasswordEncoder passwordEncoder) {
//        this.userRepository = userRepository;
//        this.roleRepository = roleRepository;
//        this.passwordEncoder=passwordEncoder;
//    }
//@Bean
//    public User register(User user) {
//        String encodedPassword = passwordEncoder.encode(user.getPassword());
//        Role customerRole = roleRepository.findByAuthority("Customer").
//                orElseThrow(() -> new RoleException("Customer role not found", HttpStatus.NOT_FOUND));
//
//        Set<Role> roles = new HashSet<>();
//        roles.add(customerRole);
//
//        user.setPassword(encodedPassword);
//        user.setRole(customerRole);
//
//        return userRepository.save(user);
//    }
//}
