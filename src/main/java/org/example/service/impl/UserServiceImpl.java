package org.example.service.impl;

import org.example.mapper.UserMapper;
import org.example.model.dto.UserDTO;
import org.example.model.dto.UserDetailsDTO;
import org.example.model.entity.User;
import org.example.repository.UserRepository;
import org.example.repository.impl.UserRepositoryImpl;
import org.example.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl() {
        this.userRepository = new UserRepositoryImpl();
        this.userMapper = new UserMapper();
    }

    @Override
    public List<UserDTO> loadAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void saveUser(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        userRepository.save(user);
    }

    @Override
    public List<UserDetailsDTO> findAllNamedQuery(String email) {
        return userRepository.findAllNamedQuery(email).stream()
                .map(userMapper::toUserDetailsDto)
                .collect(Collectors.toList());
    }

}
