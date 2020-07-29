package dev.aga.model;

import lombok.Data;

@Data
public class ErrorDetails {
    private String path;
    private String error;

    public ErrorDetails(String path, String error) {
        this.path = path;
        this.error = error;
    }
}
