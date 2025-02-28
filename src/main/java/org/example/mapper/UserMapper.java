package org.example.mapper;


import org.example.model.entity.User;
import org.example.model.resource.UserDetailsResource;
import org.example.model.resource.UserResource;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    private final UserDetailsMapper userDetailsMapper;

    public UserMapper() {
        this.userDetailsMapper = new UserDetailsMapper();
    }

    public User toEntity(UserResource resource) {
        if (resource == null) return null;
        User user = new User();
        user.setId(resource.getId());
        user.setPassword(resource.getPassword());
        user.setRole(resource.getRole());
        user.setUserDetails(userDetailsMapper.toEntity(resource.getUserDetails()));
        return user;
    }

    public UserResource toDto(User entity) {
        if (entity == null) return null;
        UserResource resource = new UserResource();
        resource.setId(entity.getId());
        resource.setPassword(entity.getPassword());
        resource.setRole(entity.getRole());
        resource.setUserDetails(userDetailsMapper.toDto(entity.getUserDetails()));
        return resource;
    }

    public UserDetailsResource toUserDetailsResource(User entity) {
        if (entity == null || entity.getUserDetails() == null) return null;
        return userDetailsMapper.toDto(entity.getUserDetails());
    }
}
