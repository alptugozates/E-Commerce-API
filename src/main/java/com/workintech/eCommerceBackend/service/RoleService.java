package com.workintech.eCommerceBackend.service;

import com.workintech.eCommerceBackend.dto.RoleDto;
import com.workintech.eCommerceBackend.entity.Role;
import com.workintech.eCommerceBackend.exception.RoleException;
import com.workintech.eCommerceBackend.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<RoleDto> getAllRoles() {
        List<Role> roles = roleRepository.findAll();

        List<RoleDto> roleDtos = roles.stream()
                .filter(role -> role.getName() != null && !role.getName().isEmpty())
                .map(role -> new RoleDto(role.getName()))
                .collect(Collectors.toList());

        if (roleDtos.size() < roles.size()) {
            throw new RoleException("Some roles have empty or null names", HttpStatus.BAD_REQUEST);
        }

        return roleDtos;
    }
    public Optional<Role> findByAuthority(String authority) {
        return roleRepository.findByAuthority(authority);
    }

    public RoleDto getRoleById(Long id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RoleException("Role not found with given id: " + id, HttpStatus.NOT_FOUND));

        return new RoleDto(role.getName());
    }

    public RoleDto createRole(Role role) {
        if (role.getName() == null || role.getName().isEmpty()) {
            throw new RoleException("Role name cannot be empty", HttpStatus.BAD_REQUEST);
        }

        Role savedRole = roleRepository.save(role);
        return new RoleDto(savedRole.getName());
    }

    public RoleDto deleteRole(Long id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RoleException("Role not found with id: " + id, HttpStatus.BAD_REQUEST));

        roleRepository.deleteById(id);

        return new RoleDto(role.getName());
    }
}
