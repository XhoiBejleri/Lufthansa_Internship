package org.example.main;

import org.example.model.dto.UserDTO;
import org.example.model.dto.UserDetailsDTO;
import org.example.service.UserService;
import org.example.service.impl.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        UserDTO user = new UserDTO();
        user.setPassword("secretPassword");
        user.setRole("ADMIN");

        UserDetailsDTO userDetailsDTO = new UserDetailsDTO();
        userDetailsDTO.setEmail("xhoi@gmail.com");
        userDetailsDTO.setFirstName("John");
        userDetailsDTO.setLastName("Doe");
        userDetailsDTO.setPhoneNumber("1234567890");

        user.setUserDetailsDTO(userDetailsDTO);

        userService.saveUser(user);

        List<UserDetailsDTO> list = userService.findAllNamedQuery("xhoi@gmail.com");


        System.out.println(list);
    }
}