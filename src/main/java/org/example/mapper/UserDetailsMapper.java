package org.example.mapper;


import org.example.model.entity.UserDetails;
import org.example.model.resource.UserDetailsResource;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsMapper {

    public UserDetails toEntity(UserDetailsResource resource) {
        if (resource == null) {
            return null;
        }
        UserDetails userDetails = new UserDetails();
        userDetails.setId(resource.getId());
        userDetails.setFirstName(resource.getFirstName());
        userDetails.setLastName(resource.getLastName());
        userDetails.setEmail(resource.getEmail());
        userDetails.setPhoneNumber(resource.getPhoneNumber());
        return userDetails;
    }

    public UserDetailsResource toDto(UserDetails entity) {
        if (entity == null) {
            return null;
        }
        UserDetailsResource resource = new UserDetailsResource();
        resource.setId(entity.getId());
        resource.setFirstName(entity.getFirstName());
        resource.setLastName(entity.getLastName());
        resource.setEmail(entity.getEmail());
        resource.setPhoneNumber(entity.getPhoneNumber());
        return resource;
    }
}
