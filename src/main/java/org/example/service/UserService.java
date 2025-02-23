package org.example.service;

import org.example.model.dto.UserDTO;
import org.example.model.dto.UserDetailsDTO;

import java.util.List;

public interface UserService {

        List<UserDTO> loadAllUsers();

        void saveUser(UserDTO user);

        List<UserDetailsDTO> findAllNamedQuery(String email);

}
