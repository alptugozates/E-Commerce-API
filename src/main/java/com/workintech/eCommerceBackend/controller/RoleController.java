package com.workintech.eCommerceBackend.controller;

import com.workintech.eCommerceBackend.dto.RoleDto;
import com.workintech.eCommerceBackend.entity.Role;
import com.workintech.eCommerceBackend.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/roles")
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public List<RoleDto> getAllRoles() {
        return roleService.getAllRoles();
    }

    @GetMapping("/{id}")
    public RoleDto getRoleById(@PathVariable Long id) {
        return roleService.getRoleById(id);
    }
    @GetMapping("/{authority}")
    public Optional<Role> findByAuthority(@PathVariable String authority) {
        return roleService.findByAuthority(authority);
    }

    @PostMapping
    public RoleDto createRole(@RequestBody Role role) {
        return roleService.createRole(role);
    }

    @DeleteMapping("/{id}")
    public RoleDto deleteRole(@PathVariable Long id) {
        return roleService.deleteRole(id);
    }
}
