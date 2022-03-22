package com.example.demo.enums;

public enum Permission {
    PRODUCT_GET("product:get"),
    PRODUCT_ADD("product:add"),
    PRODUCT_EDIT("product:edit"),
    PRODUCT_DELETE("product:delete");

    private final String permission;

    public String getPermission() {
        return permission;
    }

    Permission(String permission) {
        this.permission = permission;
    }
}
