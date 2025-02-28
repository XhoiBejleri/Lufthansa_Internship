package org.example.model.resource;

public class UserResource {

    private Long id;
    private String password;
    private String role;
    private UserDetailsResource userDetails;

    public UserResource() {
    }

    public UserResource(Long id, String password, String role, UserDetailsResource userDetails) {
        this.id = id;
        this.password = password;
        this.role = role;
        this.userDetails = userDetails;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public UserDetailsResource getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetailsResource userDetails) {
        this.userDetails = userDetails;
    }

    @Override
    public String toString() {
        return "UserResource{" +
                "id=" + id +
                ", role='" + role + '\'' +
                ", userDetails=" + userDetails +
                '}';
    }
}
