package com.hospital.backend.dto;

public class AuthResponse {
    private boolean success;
    private String message;
    private Long id;
    private String fullName;
    private String email;
    private String role;
    private String phone;
    private String address;

    public AuthResponse() {}

    public AuthResponse(boolean success, String message, Long id, String fullName, String email, String role, String phone, String address) {
        this.success = success;
        this.message = message;
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.role = role;
        this.phone = phone;
        this.address = address;
    }

    public static AuthResponse failure(String message) {
        AuthResponse res = new AuthResponse();
        res.setSuccess(false);
        res.setMessage(message);
        return res;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
