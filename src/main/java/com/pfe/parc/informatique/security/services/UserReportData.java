package com.pfe.parc.informatique.security.services;

public class UserReportData {
    private Long id;
    private String username;
    private String email;
    private String departmentName;

    public UserReportData(Long id, String username, String email, String departmentName) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.departmentName = departmentName;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getDepartmentName() { return departmentName; }
    public void setDepartmentName(String departmentName) { this.departmentName = departmentName; }
}
