package org.example.mapper;

import org.example.model.dto.UserDetailsDTO;
import org.example.model.entity.UserDetails;

public class UserDetailsMapper extends AbstractMapper<UserDetails, UserDetailsDTO> {

    @Override
    public UserDetails toEntity(UserDetailsDTO userDetailsDTO) {
        if (userDetailsDTO == null) {
            return null;
        }
        UserDetails userDetails = new UserDetails();
        userDetails.setId(userDetailsDTO.getId());
        userDetails.setFirstName(userDetailsDTO.getFirstName());
        userDetails.setLastName(userDetailsDTO.getLastName());
        userDetails.setEmail(userDetailsDTO.getEmail());
        userDetails.setPhoneNumber(userDetailsDTO.getPhoneNumber());
        return userDetails;
    }

    @Override
    public UserDetailsDTO toDto(UserDetails userDetails) {
        if (userDetails == null) {
            return null;
        }
        UserDetailsDTO dto = new UserDetailsDTO();
        dto.setId(userDetails.getId());
        dto.setFirstName(userDetails.getFirstName());
        dto.setLastName(userDetails.getLastName());
        dto.setEmail(userDetails.getEmail());
        dto.setPhoneNumber(userDetails.getPhoneNumber());
        return dto;
    }
}