package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {

    private String message;

    public Result(boolean success, Object object) {
        this.success = success;
        this.object = object;
    }

    public Result(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    private boolean success;

    private Object object;
}
