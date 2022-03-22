package com.example.demo.enums;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public enum RoleName {
    ADMIN(Set.of(Permission.PRODUCT_GET, Permission.PRODUCT_ADD, Permission.PRODUCT_EDIT, Permission.PRODUCT_DELETE)),
    USER(Set.of(Permission.PRODUCT_GET));

    private final Set<Permission> permissions;

    public Set<Permission> getPermissions() {
        return permissions;
    }

    RoleName(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public List<SimpleGrantedAuthority> getAuthorities() {
        return permissions.stream().map(permission ->
                new SimpleGrantedAuthority(permission.getPermission())).collect(Collectors.toList());
    }
}
