package org.example.mapper;

import org.example.model.dto.UserDTO;
import org.example.model.dto.UserDetailsDTO;
import org.example.model.entity.User;

public class UserMapper extends AbstractMapper<User, UserDTO> {

    @Override
    public User toEntity(UserDTO dto) {
        if (dto == null) return null;
        User user = new User();
        user.setId(dto.getId());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());
        user.setUserDetails(new UserDetailsMapper().toEntity(dto.getUserDetailsDTO()));
        return user;
    }

    @Override
    public UserDTO toDto(User entity) {
        if (entity == null) return null;
        UserDTO dto = new UserDTO();
        dto.setId(entity.getId());
        dto.setPassword(entity.getPassword());
        dto.setRole(entity.getRole());
        dto.setUserDetailsDTO(new UserDetailsMapper().toDto(entity.getUserDetails()));
        return dto;
    }

    public UserDetailsDTO toUserDetailsDto(User entity) {
        if (entity == null || entity.getUserDetails() == null) return null;
        return new UserDetailsMapper().toDto(entity.getUserDetails());
    }
}