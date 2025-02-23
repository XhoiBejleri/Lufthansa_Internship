package org.example.model.dto;

public class UserDTO {

    private Long id;
    private String password;
    private String role;
    private UserDetailsDTO userDetailsDTO;

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

    public void setUserDetailsDTO(UserDetailsDTO userDetailsDTO) {
        this.userDetailsDTO = userDetailsDTO;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", userDetailsDTO=" + userDetailsDTO +
                '}';
    }

    public UserDetailsDTO getUserDetailsDTO() {
        return getUserDetailsDTO();
    }
}
