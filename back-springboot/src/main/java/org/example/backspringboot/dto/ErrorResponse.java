package org.example.backspringboot.dto;

public class ErrorResponse {

    // ========== Propriétés ==========

    private String message;


    // ========== Constructeur ==========

    public ErrorResponse(String message) {
        this.message = message;
    }


    // ========== Méthodes ==========

    public String getMessage() {
        return message;
    }

}
